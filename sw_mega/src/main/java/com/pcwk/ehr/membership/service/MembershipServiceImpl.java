package com.pcwk.ehr.membership.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.MembershipMapper;
import com.pcwk.ehr.membership.domain.MembershipDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service // ① Service 빈으로 등록
@Transactional // (선택) 트랜잭션 전파
public class MembershipServiceImpl implements MembershipService {

	@Autowired // ② MyBatis 매퍼 주입
	MembershipMapper membershipMapper;

	@Autowired // 메일 발송기
	private JavaMailSender mailSender;
	private final MembershipMapper mapper;
	private final PasswordEncoder passwordEncoder;

	/* ─────────────────────────────────────────── */
	/* 1. 회원 등록 */
	/* ─────────────────────────────────────────── */
	 @Override
	    public int save(MembershipDTO dto) throws SQLException {

	        // 1차 서버 유효성 검사
	        if (!isValidPassword(dto.getPassword())) {
	            throw new IllegalArgumentException("비밀번호는 8~16자, 영문·숫자·특수문자를 모두 포함해야 합니다.");
	        }

	        // 비밀번호 암호화
	        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

	        return mapper.doSave(dto);
	    }

	/* ─────────────────────────────────────────── */
	/* 2. 목록 조회 */
	/* ─────────────────────────────────────────── */
	@Override
	public List<MembershipDTO> retrieve(SearchDTO search) throws SQLException {
		return membershipMapper.doRetrieve(search);
	}

	/* ─────────────────────────────────────────── */
	/* 3. 단건 조회 */
	/* ─────────────────────────────────────────── */
	@Override
	public MembershipDTO selectOne(MembershipDTO dto) throws SQLException {
		return membershipMapper.doSelectOne(dto);
	}

	/* ─────────────────────────────────────────── */
	/* 4. 회원 수정 */
	/* ─────────────────────────────────────────── */
	@Override
	public int update(MembershipDTO dto) {
		return membershipMapper.doUpdate(dto);
	}

	/* ─────────────────────────────────────────── */
	/* 5. 회원 삭제 */
	/* ─────────────────────────────────────────── */
	@Override
	public int delete(MembershipDTO dto) {
		return membershipMapper.doDelete(dto);
	}

	/* ─────────────────────────────────────────── */
	/* 6. 등록건수 */
	/* ─────────────────────────────────────────── */
	@Override
	public int getCount() throws SQLException {
		return membershipMapper.getCount();
	}

	/* ─────────────────────────────────────────── */
	/* 7. 아이디 체크 */
	/* ─────────────────────────────────────────── */

	@Override
	public boolean isUserIdAvailable(String userId) throws SQLException {
		return membershipMapper.idCheck(userId) == 0;
	}

	
	//패스워드
	@Override
	public int save(MembershipDTO dto) throws SQLException {
		// 서버단 1차 방어
		if (!isValidPassword(dto.getPassword())) {
			throw new IllegalArgumentException("비밀번호 형식 오류");
		}
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return mapper.save(dto);
	}

	/* 동일한 정규식 */
	private boolean isValidPassword(String pw) {
		String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+]).{8,16}$";
		return pw != null && pw.matches(regex);
	}

	// 이메일

	@Override
	public boolean isEmailAvailable(String email) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendEmailVerification(MembershipDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyEmailToken(String token) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveEmailToken(String email, String code) throws SQLException {
		membershipMapper.updateEmailAuthToken(email, code);
	}

//    /*───────────────────────────────────────────*/
//    /* 8. 등급 업그레이드                                                           */
//    /*───────────────────────────────────────────*/
//    @Override
//    public void upgradeLevels() throws SQLException {
//        List<MembershipDTO> list = membershipMapper.getAll();
//        for (MembershipDTO m : list) {
//            boolean changed = false;
//
//           // BASIC → SILVER
//           if (m.getGrade() == 1 && m.getLogin() >= MIN_LOGIN_COUNT_FOR_SILVER) {
//               m.setGrade(2);
//               changed = true;
//           }
//           // SILVER → GOLD
//           if (m.getGrade() == 2 && m.getRecommend() >= MIN_RECOMMEND_FOR_GOLD) {
//               m.setGrade(3);
//               changed = true;
//           }
//           if (changed) {
//               membershipMapper.doUpdate(m);
//           }
//      }
//    }
//

}
