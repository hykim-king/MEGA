<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <title>게시글 상세</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/comment.css" />
    <meta charset="UTF-8">
    <title>${vo.title}</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h2>${vo.title}</h2>
<div>
    작성자: ${vo.userId} <br/>
    작성일: ${vo.cDt} <br/>
    조회수: ${vo.viewCount}
</div>
<hr/>
<div>
    ${vo.content}
</div>
<hr/>



<h3>댓글 목록</h3>
<div id="commentList">
<c:if test="${not empty commentList}">
    <c:forEach var="comment" items="${commentList}">
        <div class="comment-box">
            <p>${comment.content}</p> 
            <p>${comment.userId} / ${comment.cDt}</p>
            
            <!-- 수정 버튼!! -->
            <form action = "/ehr/noComment/doSelectOne.do" method="get" style="display: inline;">
            	<input type="hidden" name="noCommentCode" value="${comment.commentedCode}"/>
            	<button type="submit">수정</button>
            </form>
			           	
           	<!-- 삭제 버튼!! -->
            <button onclick="deleteDiary('${comment.commentedCode}')">삭제</button>
        </div>
    </c:forEach>
</c:if>
</div>

<h3>댓글 남겨주세요</h3>
<textarea id="content" name="content" rows="3" cols="50" placeholder="내용을 입력하세요"></textarea><br/>
	<input type="hidden" id="noCode" value="${param.noCode}" />
	<input type="hidden" id="userId" value="user01" />
<button id="btnCommentSave">댓글 달기</button>




<!-- 댓글 삭제 -->
<script>
  function deleteDiary(commentedCode) {
    if (!confirm('정말 삭제하시겠습니까?')) return;

	    $.ajax({
	      url: '/ehr/noComment/doDelete.do',
	      type: 'POST',
	      data: { commentedCode:commentedCode },
	      success: function(response) {
	        const res = JSON.parse(response);
	        alert(res.message);
	        if (res.messageId === 1) {
	        	window.location.href = '/ehr/notice/doDetail.do?noCode=${param.noCode}&pageSize=10&pageNo=1';
	        }
	      },
	      error: function(xhr, status, error) {
	        alert('삭제 중 오류 발생: ' + error);
      }
    });
  }
</script>


<!-- 댓글 등록 -->
<script>
    $("#btnCommentSave").click(function () {
        var content = $("#content").val();
        var noCode = $("#noCode").val();
        var userId = $("#userId").val();

        if (content.trim() === "") {
            alert("내용을 입력하세요.");
            return;
        }

        if (!userId) {
            alert("로그인이 필요합니다.");
            return;
        }

	        $.ajax({
	            type: "POST",
	            url: "/ehr/noComment/doSave.do",
	            contentType: "application/json; charset=UTF-8",
	            data: JSON.stringify({
	                noCode: noCode,
	                content: content,
	                userId: userId
	            }),
	            success: function(result) {
	                var data = JSON.parse(result);
	                if (data.flag === 1) {
	                    alert("댓글이 등록되었습니다.");
	                    location.reload();
	                } else {
	                    alert("등록 실패!");
                }
            }
        });
    });
</script>

<button onclick="window.location.href='${CP}/notice/doRetrieve.do'">목록으로</button>

</body>
</html>
