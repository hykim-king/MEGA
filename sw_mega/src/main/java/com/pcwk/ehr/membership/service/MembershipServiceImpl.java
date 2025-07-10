package com.pcwk.ehr.membership.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;

@Service
@Transactional
public class MembershipServiceImpl implements MembershipService {

    /* ────── 의존성 주입 대상 ────── */
    private final MembershipMapper membershipMapper;
    private final PasswordEncoder  passwordEncoder;
    private final JavaMailSender   mailSender;

    /* 생성자 주입: final 필드 모두 초기화 */
    @Autowired
    public MembershipServiceImpl(MembershipMapper membershipMapper,
                                 PasswordEncoder  passwordEncoder,
                                 JavaMailSender   mailSender) {
        this.membershipMapper = membershipMapper;
        this.passwordEncoder  = passwordEncoder;
        this.mailSender       = mailSender;
    }

    /*──────────────────────────────────────────*/
    /* 1. 회원 등록                             */
    /*──────────────────────────────────────────*/
    @Override
    public int save(MembershipDTO dto) throws SQLException {

        // 1차 서버-사이드 유효성 검사
        if (!isValidPassword(dto.getPassword())) {
            throw new IllegalArgumentException(
                "비밀번호는 8~16자이며 영문·숫자를 모두 포함해야 합니다.");
        }

        // 비밀번호 암호화
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        // 매퍼에 저장 (Mapper 인터페이스·XML에 save(...) 정의 필요)
        return membershipMapper.doSave(dto);
    }

    /*──────────────────────────────────────────*/
    /* 2. 목록 조회                             */
    /*──────────────────────────────────────────*/
    @Override
    public List<MembershipDTO> retrieve(SearchDTO search) throws SQLException {
        return membershipMapper.doRetrieve(search);
    }

    /* 3. 단건 조회 */
    @Override
    public MembershipDTO selectOne(MembershipDTO dto) throws SQLException {
        return membershipMapper.doSelectOne(dto);
    }

    /* 4. 회원 수정 */
    @Override
    public int update(MembershipDTO dto) {
        return membershipMapper.doUpdate(dto);
    }

    /* 5. 회원 삭제 */
    @Override
    public int delete(MembershipDTO dto) {
        return membershipMapper.doDelete(dto);
    }

    /* 6. 등록 건수 */
    @Override
    public int getCount() throws SQLException {
        return membershipMapper.getCount();
    }

    /* 7. 아이디 중복 체크 */
    @Override
    public boolean isUserIdAvailable(String userId) throws SQLException {
        return membershipMapper.idCheck(userId) == 0;
    }

    /*──── 공용 유틸 – 비밀번호 정규식 체크 ────*/
    private boolean isValidPassword(String pw) {
        // 영문 + 숫자, 8~16자(공백 불가)
        return pw != null && pw.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$");
    }

    /*──── 이메일 관련 메서드 (미구현) ────*/
    @Override public boolean isEmailAvailable(String email)                { return false; }
    @Override public boolean sendEmailVerification(MembershipDTO dto)      { return false; }
    @Override public boolean verifyEmailToken(String token)                { return false; }

    @Override
    public void saveEmailToken(String email, String code) throws SQLException {
        membershipMapper.updateEmailAuthToken(email, code);
    }
}
