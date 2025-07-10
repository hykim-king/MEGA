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

.form-group {
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

.form-check {
	margin: 10px 0;
}

.input-with-button input[type="text"], .input-with-button input[type="email"]
	{
	border: none;
	flex: 1;
	padding: 0 10px;
	height: 100%;
	font-size: 14px;
	box-sizing: border-box;
}

.input-with-button .check-btn, .input-with-button .mail-btn {
	border: none;
	height: 100%;
	padding: 0 16px;
	background-color: #ccc;
	color: white;
	font-size: 14px;
	cursor: not-allowed;
	box-sizing: border-box;
}

.input-with-button input:focus {
	outline: none;
}

/* 버튼 활성화 스타일 */
.check-btn:disabled, .mail-btn:disabled {
	background: #ccc;
	cursor: not-allowed;
}

.check-btn:not(:disabled), .mail-btn:not(:disabled) {
	background: #ADFF2F;
	cursor: pointer;
	color: black;
}

.form-check input {
	margin-right: 5px;
}

.btn {
	width: 100%;
	padding: 10px;
	background: #ADFF2F;
	color: black;
	border: none;
	border-radius: 5px;
	font-size: 16px;
}

.btn:hover {
	background: #90cc27;
	cursor: pointer;
}

.input-with-button {
	display: flex;
	align-items: center;
	border: 1px solid #ccc;
	border-radius: 5px;
	overflow: hidden;
	height: 40px;
}

.input-with-button input[type="text"] {
	border: none;
	flex: 1;
	padding: 0 10px;
	height: 100%;
	font-size: 14px;
	box-sizing: border-box;
}

.input-with-button .check-btn {
	border: none;
	height: 100%;
	padding: 0 16px;
	background-color: #ccc;
	color: white;
	font-size: 14px;
	cursor: not-allowed;
	box-sizing: border-box;
}

.input-with-button input:focus {
	outline: none;
}

.check-btn:disabled {
	background: #ccc;
	cursor: not-allowed;
}

.check-btn:not(:disabled) {
	background: #ADFF2F;
	cursor: pointer;
	color: black;
}

.email-group {
	display: flex;
	gap: 10px;
}

.email-group input[type="email"] {
	flex: 1;
}
</style>

</head>
<body>





	<div class="form-container">
		<h2>회원가입</h2>

		<c:if test="${not empty param.msg}">
			<p style="color: red; text-align: center;">${param.msg}</p>
		</c:if>

		<form action="<c:url value='/membership/doSave.do'/>" method="post">


			<div class="form-group">
				<label>아이디 *</label>
				<div class="input-with-button">

					<input type="text" name="userId" placeholder="영문, 숫자 조합 6~12자"
						required>
					<button type="button" class="check-btn" disabled>중복확인</button>
				</div>
			</div>

			<div class="form-group">
				<label>비밀번호 *</label> <input type="password" name="password"
					placeholder="비밀번호를 입력해주세요"required>
			</div>

			<div class="form-group">
				<label>비밀번호 확인 *</label> <input type="password" name="passwordCheck"
					placeholder="비밀번호를 한 번 더 입력해주세요" required>
			</div>



			<div class="form-group">
				<label>생년월일 *</label> <input type="date" name="birth"
					placeholder="예) 19980101" required>
			</div>
			<div class="form-group">
				<label>이메일 본인 인증 *</label>
				<div class="input-with-button">
					<input type="email" name="email" placeholder="abc123@naver.com"
						required>
					<button type="button" class="mail-btn" disabled>인증하기</button>
				</div>
				<input type="text" name="emailToken" placeholder="인증번호 6자리를 입력해주세요">
			</div>

			<div class="form-check">
				<label><input type="checkbox" name="termsRequired" required>
					이용약관 필수 동의</label>
			</div>
			<div class="form-check">
				<label><input type="checkbox" name="termsOptional">
					마케팅 정보 수신 동의 (선택)</label>
			</div>
			<button type="submit" class="btn">회원가입 완료</button>
		</form>
	</div>



	<script>
document.addEventListener('DOMContentLoaded', function () {
  var ctx             = '<%=request.getContextPath()%>';
  var idCheckUrl      = ctx + '/membership/idCheck.do';
  var sendAuthCodeUrl = ctx + '/membership/sendAuthCode.do';

  /* ---------- 1. 아이디 ---------- */
  var userIdInput = document.querySelector('input[name="userId"]');
  var checkBtn    = document.querySelector('.check-btn');

  userIdInput.addEventListener('input', function () {
    var ok = /^[a-zA-Z0-9]{6,12}$/.test(userIdInput.value.trim());
    checkBtn.disabled = !ok;
  });

  checkBtn.addEventListener('click', function () {
    var userId = userIdInput.value.trim();
    fetch(idCheckUrl + '?userId=' + encodeURIComponent(userId))
      .then(function (r) { return r.text(); })
      .then(function (res) {
        alert(res === '0' ? '사용 가능' : '이미 사용 중');
      })
      .catch(function (err) {
        alert('ID 체크 실패: ' + err);
      });
  });

  /* ---------- 2. 이메일 ---------- */
  var emailInput = document.querySelector('input[name="email"]');
  var mailBtn    = document.querySelector('.mail-btn');

  emailInput.addEventListener('input', function () {
    mailBtn.disabled = !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(emailInput.value.trim());
  });

  mailBtn.addEventListener('click', function () {
    var email = emailInput.value.trim();
    fetch(sendAuthCodeUrl, {
      method : 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body   : 'email=' + encodeURIComponent(email)
    })
    .then(function (r)  { return r.text(); })
    .then(function ()    { alert('인증 메일 발송! 5분 내 코드 입력'); })
    .catch(function (err){ alert('메일 발송 실패: ' + err);    });
  });

  /* ---------- 3. 비밀번호 확인 ---------- */
const pw = document.getElementById("password").value;
const pwPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;

if (!pwPattern.test(pw)) {
  alert("비밀번호는 영문과 숫자를 포함한 8~16자로 입력하세요.");
  e.preventDefault(); return;
}
  });

  
  
  
</script>



</body>
</html>
