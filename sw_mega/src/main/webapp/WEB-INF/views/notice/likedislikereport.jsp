<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 공통 좋아요/싫어요/신고 버튼 --%>
<div class="comment-actions">
    <button onclick="likeTarget('${type}', ${targetId})">
        👍 <span id="likeCount-${type}-${targetId}">${likeCount}</span>
    </button>
    <button onclick="dislikeTarget('${type}', ${targetId})">
        👎 <span id="dislikeCount-${type}-${targetId}">${dislikeCount}</span>
    </button>
    <button onclick="reportTarget('${type}', ${targetId})">🚩 신고</button>
</div>
