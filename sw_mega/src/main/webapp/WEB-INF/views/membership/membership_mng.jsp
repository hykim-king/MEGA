<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<style>
body {
    font-family: Noto Sans Korean;
    background-color: #f8f8f8;
    padding: 40px;
}
.form-container {
    width: 400px;
    margin: 0 auto;
    background: white;
    padding: 30px;
    border-radius: 10px;
}
h2 {
    text-align: center;
}
.form-group, .form-check {
    margin-bottom: 16px;
}
.form-group label {
    display: block;
    margin-bottom: 6px;
    font-weight: bold;
}
.form-group input {
    width: 100%;
    height: 35px;
    padding: 6px;
    border: 1px solid #ccc;
    border-radius: 5px;
}
.input-with-button {
    display: flex;
    align-items: center;
    border: 1px solid #ccc;
    border-radius: 5px;
    overflow: hidden;
    height: 40px;
}
.input-with-button input[type="text"],
.input-with-button input[type="email"] {
    border: none;
    flex: 1;
    padding: 0 10px;
    font-size: 14px;
}
.input-with-button input:focus {
    outline: none;
}
.check-btn, .mail-btn {
    border: none;
    height: 100%;
    padding: 0 16px;
    font-size: 14px;
    cursor: not-allowed;
    background-color: #ccc;
    color: white;
}
.check-btn:not(:disabled), .mail-btn:not(:disabled) {
    background: #ADFF2F;
    color: black;
    cursor: pointer;
}
.submit-btn {
    width: 100%;
    padding: 10px;
    background: #ADFF2F;
    color: black;
    border: none;
    border-radius: 5px;
    font-size: 16px;
}
.submit-btn:hover {
    background: #90cc27;
    cursor: pointer;
}
.terms-label {
    cursor: pointer;
    color: #000;
    text-decoration: underline;
}
.terms-content {
    display: none;
    margin-top: 10px;
    padding: 10px;
    border: 1px solid #ccc;
    background: #f9f9f9;
    font-size: 13px;
    white-space: pre-wrap;
}
</style>
</head>
<body>

<div class="form-container">
    <h2>회원가입</h2>

    <c:if test="${not empty param.msg}">
        <p style="color: red; text-align: center;">${param.msg}</p>
    </c:if>

    <form action="/ehr/membership/doSave.do" method="post">

        <div class="form-group">
            <label>아이디 *</label>
            <div class="input-with-button">
                <input type="text" name="userId" placeholder="영문, 숫자 조합 6~12자" required>
                <button type="button" class="check-btn" disabled>중복확인</button>
            </div>
        </div>

        <div class="form-group">
            <label>비밀번호 *</label>
            <input type="password" name="password" placeholder="비밀번호 입력" required>
        </div>

        <div class="form-group">
            <label>비밀번호 확인 *</label>
            <input type="password" name="passwordCheck" placeholder="다시 입력해주세요" required>
        </div>

        <div class="form-group">
            <label>생년월일 *</label>
            <input type="date" name="birth" required>
        </div>

        <div class="form-group">
            <label>이메일 본인 인증 *</label>
            <div class="input-with-button">
                <input type="email" name="email" placeholder="abc123@naver.com" required>
                <button type="button" class="mail-btn" disabled>인증하기</button>
            </div>
            <input type="text" name="emailAuthToken" id="emailAuthToken" placeholder="인증번호 6자리를 입력해주세요">
        </div>

        <div class="form-check">
            <input type="checkbox" name="termsRequired" required>
            <span class="terms-label" data-target="#terms-required">이용약관 필수 동의</span>
            <div id="terms-required" class="terms-content">
                [이용약관 예시]
                본 서비스는 귀하에게 다양한 서비스를 제공합니다. 귀하는 본 약관에 동의함으로써 관련된 모든 정책에 동의하게 됩니다...
            </div>
        </div>

        <div class="form-check">
            <input type="checkbox" name="termsOptional">
            <span class="terms-label" data-target="#terms-optional">마케팅 정보 수신 동의 (선택)</span>
            <div id="terms-optional" class="terms-content">
                [마케팅 수신 동의 예시]
                본인은 향후 이벤트, 쿠폰 등 마케팅 정보를 이메일/SMS로 수신하는 것에 동의합니다.
            </div>
        </div>

        <button type="submit" class="submit-btn">회원가입 완료</button>
    </form>
</div>

<script>
document.addEventListener('DOMContentLoaded', function () {
    const ctx = '<%=request.getContextPath()%>';
    const idCheckUrl = ctx + '/membership/idCheck.do';
    const sendAuthCodeUrl = ctx + '/membership/sendAuthCode.do';

    // 아이디 확인
    const userIdInput = document.querySelector('input[name="userId"]');
    const checkBtn = document.querySelector('.check-btn');

    userIdInput.addEventListener('input', function () {
        const valid = /^[a-zA-Z0-9]{6,12}$/.test(userIdInput.value.trim());
        checkBtn.disabled = !valid;
    });

    checkBtn.addEventListener('click', function () {
        const userId = userIdInput.value.trim();
        fetch(idCheckUrl + '?userId=' + encodeURIComponent(userId))
            .then(r => r.text())
            .then(res => alert(res === '0' ? '사용 가능' : '이미 사용 중입니다.'))
            .catch(err => alert('ID 확인 오류: ' + err));
    });

    // 이메일 인증
    const emailInput = document.querySelector('input[name="email"]');
    const mailBtn = document.querySelector('.mail-btn');

    emailInput.addEventListener('input', function () {
        const valid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(emailInput.value.trim());
        mailBtn.disabled = !valid;
    });

    mailBtn.addEventListener('click', function () {
        const email = emailInput.value.trim();
        fetch(sendAuthCodeUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: 'email=' + encodeURIComponent(email)
        })
        .then(r => r.text())
        .then(() => alert('인증 메일이 전송되었습니다. 5분 이내 코드를 입력해주세요.'))
        .catch(err => alert('메일 전송 실패: ' + err));
    });

    // 약관 내용 펼치기
    document.querySelectorAll('.terms-label').forEach(label => {
        label.addEventListener('click', function () {
            const target = document.querySelector(this.dataset.target);
            if (target) {
                target.style.display = (target.style.display === 'block') ? 'none' : 'block';
            }
        });
    });
});
</script>

</body>
</html>