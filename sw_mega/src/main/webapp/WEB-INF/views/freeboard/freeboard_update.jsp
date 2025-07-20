<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h2>게시글 수정</h2>

<form id="updateForm">
    <input type="hidden" id="fbCode" name="fbCode" value="${vo.fbCode}" />

    <label for="title">제목</label><br>
    <input type="text" id="title" name="title" value="${vo.title}" style="width:400px;"><br><br>

    <label for="content">내용</label><br>
    <textarea id="content" name="content" rows="10" cols="80">${vo.content}</textarea><br><br>

    <button type="button" id="btnUpdate">수정 완료</button>
    <button type="button" id="btnCancel">취소</button>
</form>

<script>
    // 수정 요청
    $("#btnUpdate").click(function () {
        const fbCode = $("#fbCode").val();
        const title = $("#title").val();
        const content = $("#content").val();

        if (title.trim() === "" || content.trim() === "") {
            alert("제목과 내용을 입력해주세요.");
            return;
        }

        $.ajax({
            type: "POST",
            url: "${CP}/freeboard/doUpdate.do",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify({
                fbCode: fbCode,
                title: title,
                content: content
            }),
            success: function (res) {
                const result = JSON.parse(res);
                alert(result.message);
                if (result.messageId === 1) {
                    location.href = "${CP}/freeboard/doDetail.do?fbCode=" + fbCode + "&pageSize=10&pageNo=1";
                }
            },
            error: function (xhr, status, error) {
                alert("수정 중 오류 발생: " + error);
            }
        });
    });

    // 취소 시 상세 페이지로 이동
    $("#btnCancel").click(function () {
        const fbCode = $("#fbCode").val();
        location.href = "${CP}/freeboard/doDetail.do?fbCode=" + fbCode + "&pageSize=10&pageNo=1";
    });
</script>

</body>
</html>