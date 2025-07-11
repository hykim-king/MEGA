<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <title>게시글 상세</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/comment.css" />
    <meta charset="UTF-8">
    <title>${outVO.title}</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h2>${outVO.title}</h2>
<div>
    작성자: ${outVO.userId} <br/>
    작성일: ${outVO.cDt} <br/>
    조회수: ${outVO.viewCount}
</div>
<hr/>

<div>
    ${outVO.content}
</div>

<hr/>


<!-- 수정 + 삭제 + 좋아요/싫어요 묶음 -->
<div class="notice-actions">
    <input type="hidden" id="reactiontype" name="reactiontype" value="notice">
    <a href="/ehr/notice/doUpdateView.do?noCode=${outVO.noCode}">
        <button>수정하기</button>
    </a>
    <button onclick="deleteNotice(${outVO.noCode})">삭제</button>

    <div style="display: flex; align-items: center; gap: 5px; margin-left: 13px;">
    
		       <!-- 게시글 좋아요/싫어요 -->
		<button id="likeBtn-NOTICE-${outVO.noCode}" 
		        onclick="toggleReaction('NOTICE', 'L', ${outVO.noCode})">
		    👍 좋아요 <span id="likeCount-NOTICE-${outVO.noCode}">${nLikeCount}</span>
		</button>
		
		<button id="dislikeBtn-NOTICE-${outVO.noCode}" 
		        onclick="toggleReaction('NOTICE', 'D', ${outVO.noCode})">
		    👎 싫어요 <span id="dislikeCount-NOTICE-${outVO.noCode}">${nDislikeCount}</span>
		</button>

        
        <button onclick="reportTarget('NOTICE', ${outVO.noCode})">🚩 신고</button>
    </div>
</div>



 <h3>댓글 목록</h3>
<div id="commentList">
<c:if test="${not empty commentList}">
    <c:forEach var="comment" items="${commentList}">

        <div class="comment-box">
            <p>${comment.content}</p> 
            <p>${comment.userId} / ${comment.cDt}</p>
		</div>
            <!-- 버튼 영역 -->
            <div class="comment-actions">
                <!-- 수정 버튼 -->
                <form action="/ehr/noComment/doSelectOne.do" method="get" style="display: inline;">
                    <input type="hidden" name="noCommentCode" value="${comment.commentedCode}" />
                    <button type="submit">수정</button>
                </form>

                <!-- 삭제 버튼 -->
                <button onclick="deleteDiary('${comment.commentedCode}')">삭제</button>

               <!-- 댓글 좋아요/싫어요 -->
				    <div class="reaction-buttons">
					<button id="likeBtn-COMMENT-${comment.commentedCode}"
					        onclick="toggleReaction('COMMENT', 'L', '${comment.commentedCode}')">
					    👍 <span id="likeCount-COMMENT-${comment.commentedCode}">${comment.likeCount}</span>
					</button>
					
					<button id="dislikeBtn-COMMENT-${comment.commentedCode}"
					        onclick="toggleReaction('COMMENT', 'D', '${comment.commentedCode}')">
					    👎 <span id="dislikeCount-COMMENT-${comment.commentedCode}">${comment.dislikeCount}</span>
					</button>
                
                
                <!-- 신고 -->
                <button onclick="reportTarget('COMMENT', ${comment.commentedCode})">🚩신고</button>
            </div>
        </div>

    </c:forEach>
</c:if>
</div> 



<h3>댓글 남겨주세요</h3>
<textarea id="content" name="content" rows="3" cols="50" placeholder="내용을 입력하세요"></textarea><br/>
	<input type="hidden" id="noCode" value="${param.noCode}" />
	<input type="hidden" id="userId" value="user01" />
<button id="btnCommentSave">댓글 달기</button>


<!-- 게시글 삭제 -->
<script>
function deleteNotice(noCode) {
    if (!confirm("정말 삭제하시겠습니까?")) return;

    $.ajax({
        type: "POST",
        url: "/ehr/notice/doDelete.do",
        data: { noCode: noCode },
        success: function (result) {
            const res = JSON.parse(result);
            alert(res.message);
            if (res.messageId === 1) {
                location.href = "/ehr/notice/doRetrieve.do";
            }
        },
        error: function (xhr, status, error) {
            alert("삭제 중 오류 발생: " + error);
        }
    });
}
</script>


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

<!-- 댓글 등록 처리 script 아래에 추가 -->
<script>
    $("#btnCommentSave").click(function () {
        // ... 댓글 등록 코드 ...
    });
</script>


<script>
function reportTarget(type, id) {
    if (!confirm("정말 신고하시겠습니까?")) return;

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


<script>
document.addEventListener("DOMContentLoaded", function () {
  const userReactions = {};

  // 댓글 등록
  document.getElementById("btnCommentSave").addEventListener("click", function () {
    const content = document.getElementById("content").value.trim();
    const noCode = document.getElementById("noCode").value;
    const userId = document.getElementById("userId").value;

    if (!content) return alert("내용을 입력하세요.");
    if (!userId) return alert("로그인이 필요합니다.");

    fetch("/ehr/noComment/doSave.do", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ noCode, content, userId }),
    })
    .then(res => res.json())
    .then(data => {
      if (data.flag === 1) {
        alert("댓글 등록 성공");
        location.reload();
      } else {
        alert("댓글 등록 실패");
      }
    });
  });

  // 게시글 삭제
  window.deleteNotice = function(noCode) {
    if (!confirm("정말 삭제하시겠습니까?")) return;
    fetch("/ehr/notice/doDelete.do", {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: new URLSearchParams({ noCode }),
    })
    .then(res => res.json())
    .then(data => {
      alert(data.message);
      if (data.messageId === 1) location.href = "/ehr/notice/doRetrieve.do";
    });
  };

  // 댓글 삭제
  document.querySelectorAll(".delete-comment-btn").forEach(btn => {
    btn.addEventListener("click", function () {
      if (!confirm("정말 삭제하시겠습니까?")) return;
      const id = this.dataset.id;
      fetch("/ehr/noComment/doDelete.do", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: new URLSearchParams({ commentedCode: id }),
      })
      .then(res => res.json())
      .then(data => {
        alert(data.message);
        if (data.messageId === 1) location.reload();
      });
    });
  });

  // 신고 기능
  document.querySelectorAll(".report-btn").forEach(btn => {
    btn.addEventListener("click", function () {
      if (!confirm("정말 신고하시겠습니까?")) return;
      const id = this.dataset.id;
      const type = this.dataset.type;

      fetch("/ehr/report/doReport.do", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          userId: "user01",
          reason: "부적절한 내용입니다.",
          targetType: type,
          targetCode: id
        }),
      })
      .then(res => res.json())
      .then(data => alert(data.message));
    });
  });

  // 좋아요/싫어요 초기화 (게시글)
  const postCode = "${outVO.noCode}";
  fetch(`/ehr/reaction/getUserReaction.do?targetType=NOTICE&targetCode=${postCode}`)
    .then(res => res.json())
    .then(data => {
      if (data.reactionType) {
        const key = `NOTICE-${postCode}`;
        userReactions[key] = data.reactionType;
        updateButtonStyles("NOTICE", postCode);
      }
    });

  // 좋아요/싫어요 초기화 (댓글)
  document.querySelectorAll("[id^=likeBtn-COMMENT-]").forEach(btn => {
    const code = btn.id.split("-")[2];
    fetch(`/ehr/reaction/getUserReaction.do?targetType=COMMENT&targetCode=${code}`)
      .then(res => res.json())
      .then(data => {
        if (data.reactionType) {
          const key = `COMMENT-${code}`;
          userReactions[key] = data.reactionType;
          updateButtonStyles("COMMENT", code);
        }
      });
  });

  // 버튼 클릭 이벤트 등록
  document.querySelectorAll("[id^=likeBtn-]").forEach(btn => {
    btn.addEventListener("click", function () {
      const [_, type, code] = this.id.split("-");
      toggleReaction(type, 'L', code);
    });
  });

  document.querySelectorAll("[id^=dislikeBtn-]").forEach(btn => {
    btn.addEventListener("click", function () {
      const [_, type, code] = this.id.split("-");
      toggleReaction(type, 'D', code);
    });
  });

  function toggleReaction(targetType, reactionType, targetCode) {
    const key = `${targetType}-${targetCode}`;
    const current = userReactions[key];
    let sendType = (current === reactionType) ? null : reactionType;

    fetch("/ehr/reaction/doToggleReaction.do", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ targetType, targetCode, reactionType: sendType }),
    })
    .then(res => res.json())
    .then(data => {
      if (data.flag === 1) {
        document.getElementById(`likeCount-${key}`).innerText = data.likeCount;
        document.getElementById(`dislikeCount-${key}`).innerText = data.dislikeCount;
        userReactions[key] = sendType;
        updateButtonStyles(targetType, targetCode);
      } else {
        alert("처리 실패: " + data.message);
      }
    });
  }

  function updateButtonStyles(targetType, targetCode) {
    const key = `${targetType}-${targetCode}`;
    const likeBtn = document.getElementById(`likeBtn-${key}`);
    const dislikeBtn = document.getElementById(`dislikeBtn-${key}`);

    if (!likeBtn || !dislikeBtn) return;

    likeBtn.classList.toggle("active", userReactions[key] === 'L');
    dislikeBtn.classList.toggle("active", userReactions[key] === 'D');
  }

});
</script>






<button onclick="window.location.href='${CP}/notice/doRetrieve.do'">목록으로</button>

</body>
</html>
