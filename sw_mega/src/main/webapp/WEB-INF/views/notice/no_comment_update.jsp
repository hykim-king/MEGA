<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- 좋아요/싫어요 스타일 있으면 여기다 -->

</head>
<body>

<h3>댓글 수정</h3>
<form id="updateForm">
    <input type="hidden" id="commentedCode" value="${vo.commentedCode}" />
    <input type="hidden" id="noCode" value="${vo.noCode}" />
    <textarea id="content" rows="3" cols="50">${vo.content}</textarea><br/>
    
    
    <button type="button" id="btnCommentUpdate">수정 완료</button>
    <button type="button" id="btnCommentDelete">취소</button>
</form>


<script>
    $("#btnCommentUpdate").click(function() {
        const commentedCode = $("#commentedCode").val();
        const content = $("#content").val();

        if (content.trim() === "") {
            alert("내용을 입력하세요.");
            return;
        }
        if (!confirm("수정하시겠습니까?")) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "/ehr/noComment/doUpdate.do",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data: JSON.stringify({
                commentedCode: commentedCode,
                content: content
            }),
            success: function(result) {
                console.log(result);
                if (result.messageId === 1) {
                    const noCode = encodeURIComponent($("#noCode").val());
                    location.href = "/ehr/notice/doDetail.do?noCode=" + noCode + "&pageSize=10&pageNo=1";
                } else {
                    alert(result.message);
                }
            },
            error: function(xhr, status, error) {
                alert("수정 중 오류 발생: " + error);
            }
        });
    });

    //  수정: btnCommentDelete는 AJAX 바깥에 있어야 함
    $("#btnCommentDelete").click(function() {
        const noCode = encodeURIComponent($("#noCode").val());
        location.href = "/ehr/notice/doDetail.do?noCode=" + noCode + "&pageSize=10&pageNo=1";
    });
  
</script>

<script>
    function likeTarget(type, id) {
        $.ajax({
            url: "/ehr/reaction/doLike.do",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify({
                userId: "user01",             // 실제 로그인 사용자로 바꿔야 함
                reactionType: "L",            // 좋아요
                targetType: type.toUpperCase(), // "NOTICE" 또는 "COMMENT"
                targetCode: id
            }),
            success: function (res) {
                $(`#likeCount-${type}-${id}`).text(res.likeCount);
            }
        });
    }

    function dislikeTarget(type, id) {
        $.ajax({
            url: "/ehr/reaction/doDislike.do",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify({
                userId: "user01",
                reactionType: "D", // 싫어요
                targetType: type.toUpperCase(),
                targetCode: id
            }),
            success: function (res) {
                $(`#dislikeCount-${type}-${id}`).text(res.dislikeCount);
            }
        });
    }

    function reportTarget(type, id) {
        if (!confirm("신고하시겠습니까?")) return;

        $.ajax({
            url: "/ehr/report/doReport.do",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify({
                userId: "user01",
                reason: "부적절한 내용입니다.",
                targetType: type.toUpperCase(),
                targetCode: id
            }),
            success: function (res) {
                alert(res.message);
            }
        });
    }
</script>




</body>
</html>