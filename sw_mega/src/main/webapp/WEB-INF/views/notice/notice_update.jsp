<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h3>댓글 수정</h3>
<form id="updateForm">
    <input type="hidden" id="commentedCode" value="${vo.commentedCode}" />
    <input type="hidden" id="noCode" value="${vo.noCode}" />
    <textarea id="content" rows="3" cols="50">${vo.content}</textarea><br/>
    <button type="button" id="btnCommentUpdate">수정 완료</button>
</form>

<script>
    $("#btnCommentUpdate").click(function() {
        const commentedCode = $("#commentedCode").val();
        const content = $("#content").val();
        const noCode = $("#noCode").val();

        if (content.trim() === "") {
            alert("내용을 입력하세요.");
            return;
        }

        $.ajax({
            type: "POST",
            url: "/ehr/noComment/doUpdate.do",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify({
                commentedCode: commentedCode,
                content: content
            }),
            success: function(result) {
                const res = JSON.parse(result);
                alert(res.message);
                if (res.messageId === 1) {
                    // 댓글이 달린 공지사항 상세보기 페이지로 리디렉션
                    location.href = "/ehr/notice/doDetail.do?noCode=" + noCode;
                }
            },
            error: function(xhr, status, error) {
                alert("수정 중 오류 발생: " + error);
            }
        });
    });
</script>

</body>
</html>