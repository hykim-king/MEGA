<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${outVO.title}</title>
    <link rel="stylesheet" href="${CP}/resources/assets/css/comment.css" />
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

<div>${outVO.content}</div>
<hr/>

<!-- 수정 + 삭제 + 좋아요/싫어요 -->
<div class="freeboard-actions">
    <a href="/ehr/freeboard/doUpdateView.do?fbCode=${outVO.fbCode}">
        <button>수정하기</button>
    </a>
    <button onclick="deleteFreeboard(${outVO.fbCode})">삭제</button>

    <div style="display: flex; align-items: center; gap: 5px; margin-left: 13px;">
        <button id="likeBtn-FREEBOARD-${outVO.fbCode}" onclick="toggleReaction('FREEBOARD', 'L', ${outVO.fbCode})">
            👍 좋아요 <span id="likeCount-FREEBOARD-${outVO.fbCode}">${nLikeCount}</span>
        </button>
        <button id="dislikeBtn-FREEBOARD-${outVO.fbCode}" onclick="toggleReaction('FREEBOARD', 'D', ${outVO.fbCode})">
            👎 싫어요 <span id="dislikeCount-FREEBOARD-${outVO.fbCode}">${nDislikeCount}</span>
        </button>
        <button onclick="reportTarget('FREEBOARD', ${outVO.fbCode})">🚩 신고</button>
    </div>
</div>

<h3>댓글 목록</h3>
<div id="commentList">
<c:if test="${not empty commentList}">
    <c:forEach var="comment" items="${commentList}">
        <div class="comment-box">
            <p>${comment.content}</p>
            <p>${comment.userId} / ${comment.cDt}</p>
            <div class="comment-actions">
                <form action="/ehr/fbComment/doSelectOne.do" method="get" style="display:inline;">
                    <input type="hidden" name="fbCommentCode" value="${comment.commentedCode}" />
                    <button type="submit">수정</button>
                </form>
                <button onclick="deleteComment('${comment.commentedCode}')">삭제</button>
                <div class="reaction-buttons">
                    <button id="likeBtn-COMMENT-${comment.commentedCode}" onclick="toggleReaction('COMMENT', 'L', '${comment.commentedCode}')">
                        👍 <span id="likeCount-COMMENT-${comment.commentedCode}">${comment.likeCount}</span>
                    </button>
                    <button id="dislikeBtn-COMMENT-${comment.commentedCode}" onclick="toggleReaction('COMMENT', 'D', '${comment.commentedCode}')">
                        👎 <span id="dislikeCount-COMMENT-${comment.commentedCode}">${comment.dislikeCount}</span>
                    </button>
                </div>
                <button onclick="reportTarget('COMMENT', ${comment.commentedCode})">🚩 신고</button>
            </div>
        </div>
    </c:forEach>
</c:if>
</div>

<h3>댓글 남겨주세요</h3>
<textarea id="content" rows="3" cols="50" placeholder="내용을 입력하세요"></textarea><br/>
<input type="hidden" id="fbCode" value="${param.fbCode}" />
<input type="hidden" id="userId" value="user01" />
<button id="btnCommentSave">댓글 달기</button>

<button onclick="window.location.href='${CP}/freeboard/doRetrieve.do'">목록으로</button>

<script>
const userReactions = {};

function deleteFreeboard(fbCode) {
    if (!confirm("정말 삭제하시겠습니까?")) return;
    fetch("/ehr/freeboard/doDelete.do", {
        method: "POST",
        headers: {"Content-Type":"application/x-www-form-urlencoded"},
        body: new URLSearchParams({ fbCode })
    })
    .then(res => res.json())
    .then(data => {
        alert(data.message);
        if (data.messageId === 1) location.href = "/ehr/freeboard/doRetrieve.do";
    });
}

function deleteComment(commentedCode) {
    if (!confirm("정말 삭제하시겠습니까?")) return;
    fetch("/ehr/fbComment/doDelete.do", {
        method: "POST",
        headers: {"Content-Type":"application/x-www-form-urlencoded"},
        body: new URLSearchParams({ commentedCode })
    })
    .then(res => res.json())
    .then(data => {
        alert(data.message);
        if (data.messageId === 1) location.reload();
    });
}

function reportTarget(type, id) {
    if (!confirm("정말 신고하시겠습니까?")) return;
    fetch("/ehr/report/doReport.do", {
        method: "POST",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify({
            userId: "user01",
            reason: "부적절한 내용입니다.",
            targetType: type,
            targetCode: id
        })
    })
    .then(res => res.json())
    .then(data => alert(data.message));
}

function toggleReaction(targetType, reactionType, targetCode) {
    const key = `${targetType}-${targetCode}`;
    const current = userReactions[key];
    const sendType = (current === reactionType) ? null : reactionType;

    fetch("/ehr/reaction/doToggleReaction.do", {
        method: "POST",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify({ targetType, targetCode, reactionType: sendType })
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

document.addEventListener("DOMContentLoaded", function() {
    const fbCode = document.getElementById("fbCode").value;

    // 댓글 등록
    document.getElementById("btnCommentSave").addEventListener("click", function() {
        const content = document.getElementById("content").value.trim();
        const userId = document.getElementById("userId").value;
        if (!content) return alert("내용을 입력하세요.");
        if (!userId) return alert("로그인이 필요합니다.");

        fetch("/ehr/fbComment/doSave.do", {
            method: "POST",
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify({ fbCode, content, userId })
        })
        .then(res => res.json())
        .then(data => {
            if (data.flag === 1) {
                alert("댓글 등록 성공");
                location.reload();
            } else {
                alert("등록 실패");
            }
        });
    });

    // 초기화: 게시글 좋아요/싫어요
    fetch(`/ehr/reaction/getUserReaction.do?targetType=FREEBOARD&targetCode=${fbCode}`)
    .then(res => res.json())
    .then(data => {
        if (data.reactionType) {
            const key = `FREEBOARD-${fbCode}`;
            userReactions[key] = data.reactionType;
            updateButtonStyles("FREEBOARD", fbCode);
        }
    });

    // 초기화: 댓글 좋아요/싫어요
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
});
</script>

</body>
</html>
