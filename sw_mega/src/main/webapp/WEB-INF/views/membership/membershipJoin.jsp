<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>회원가입</title>

<!-- Noto Sans KR 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">

<style>
body, input, button, label {
	font-family: 'Noto Sans KR', sans-serif !important;
}

body {
	background: #f8f8f8;
	padding: 40px;
}

label {
	display: block;
	margin-top: 10px;
	font-weight: bold;
}

input, button {
	width: 300px;
	padding: 8px;
	font-size: 14px;
	border-radius: 5px;
	border: 1px solid #ccc;
	margin-bottom: 10px;
}

button {
	background: #ADFF2F;
	color: black;
	cursor: pointer;
	border: none;
}

button:hover {
	background: #90cc27;
}

.error {
	color: red;
	font-size: 12px;
}
</style>
</head>
<body>

	<h2>회원가입</h2>

	<form id="signupForm" action="${pageContext.request.contextPath}/membership/doSave.do" method="post">


		<!-- 아이디 -->
		<label>아이디 *</label> <input type="text" name="userId" id="userId"
			required>

		<!-- 비밀번호 -->
		<label>비밀번호 *</label> <input type="password" name="password"
			id="password" placeholder="영문, 숫자 조합 8~16자"
			pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$"
			title="영문과 숫자를 포함한 8~16자(공백 불가)" autocomplete="new-password" required>

		<!-- 비밀번호 확인 -->
		<label>비밀번호 확인 *</label> <input type="password" name="passwordCheck"
			id="confirmPassword" placeholder="비밀번호를 한 번 더 입력해주세요"
			pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$"
			title="위와 같은 규칙으로 입력하세요" required>


		<!-- 생년월일 -->
		<label>생년월일 *</label> <input type="date" id="birth" required>

		<!-- 이메일 -->
		<label>이메일 *</label> <input type="email" id="email" name="email"
			required>
		<button type="button" onclick="sendAuthCode()">인증코드 전송</button>

		<!-- 이메일 인증코드 -->
		<label>인증코드 *</label> <input type="text" name="emailAuthToken"
			id="emailAuthToken" required>

		<!-- 약관 동의 -->
		<label><input type="checkbox" id="termsAgree"> 이용약관에
			모두 동의합니다.</label><br>

		<!-- 제출 -->
		<button type="submit">회원가입 완료</button>
	</form>

	<script>
/* ---------- 1. 인증코드 전송 ---------- */
function sendAuthCode() {
  const email = document.getElementById("email").value.trim();
  if (!email) { alert("이메일을 입력해주세요."); return; }

  fetch("<c:url value='/membership/sendEmailAuthCode.do'/>", {
    method : "POST",
    headers: { "Content-Type":"application/x-www-form-urlencoded" },
    body   : "email=" + encodeURIComponent(email)
  })
  .then(r => r.text())
  .then(alert)
  .catch(err => alert("인증 메일 전송 실패: " + err));
}

/* ---------- 2. 폼 제출 검증 ---------- */
document.getElementById("signupForm").addEventListener("submit", function(e){

  /* 약관 체크 */
  if (!document.getElementById("termsAgree").checked) {
    alert("이용약관에 동의해주세요.");
    e.preventDefault(); return;
  }

  const pw = document.getElementById("password").value;
  const pwPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;

  if (!pwPattern.test(pw)) {
     alert("비밀번호는 영문과 숫자를 포함해 8~16자로 입력하세요.");
     e.preventDefault();
  }

  /* 생년월일 yyyyMMdd 변환 */
  const birthEl  = document.getElementById("birth");
  const yyyyMMdd = birthEl.value.replaceAll("-", "");
  const hidden   = document.createElement("input");
  hidden.type  = "hidden";
  hidden.name  = "birth";
  hidden.value = yyyyMMdd;
  this.appendChild(hidden);
  birthEl.disabled = true;        // 원본 필드 전송 방지
});
</script>
</body>
</html>
