<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원가입</title>
    <style>
        label { display: block; margin-top: 10px; }
        input { width: 300px; padding: 5px; }
        .error { color: red; font-size: 12px; }
    </style>
</head>
<body>
    <h2>회원가입</h2>

    <form id="signupForm" action="<c:url value='/membership/doSave.do'/>" method="post">
        <label>아이디 *</label>
        <input type="text" name="userId" id="userId" required />

        <label>비밀번호 *</label>
        <input type="password" name="password" id="password" required />

        <label>비밀번호 확인 *</label>
        <input type="password" id="confirmPassword" required />

        <label>생년월일 *</label>
        <input type="date" name="birth" required />

        <label>이메일 *</label>
        <input type="email" name="email" id="email" required />
        <button type="button" onclick="sendAuthCode()">인증코드 전송</button>

        <label>인증코드 *</label>
        <input type="text" name="emailAuthToken" id="emailAuthToken" required />

        <label>
            <input type="checkbox" id="termsAgree" />
            이용약관에 모두 동의합니다.
        </label>

        <br/>
        <button type="submit">회원가입 완료</button>
    </form>

    <script>
        function sendAuthCode() {
            const email = document.getElementById("email").value;
            if (!email) {
                alert("이메일을 입력해주세요.");
                return;
            }
            fetch("<c:url value='/membership/sendEmailAuthCode.do'/>", {
                method: "POST",
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: "email=" + encodeURIComponent(email)
            })
            .then(function(response){ return response.text(); })
            .then(function(result){ alert(result); });

        }

        document.getElementById("signupForm").addEventListener("submit", function(e) {
            if (!document.getElementById("termsAgree").checked) {
                alert("이용약관에 동의해주세요.");
                e.preventDefault();
            }

            const pw = document.getElementById("password").value;
            const confirm = document.getElementById("confirmPassword").value;
            if (pw !== confirm) {
                alert("비밀번호가 일치하지 않습니다.");
                e.preventDefault();
            }
        });
    </script>
</body>
</html>
