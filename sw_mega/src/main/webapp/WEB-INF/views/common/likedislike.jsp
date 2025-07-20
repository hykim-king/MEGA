<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="comment-actions">
    <button onclick="likeTarget('${type}', ${targetCode})">
        <span id="likeCount-${type}-${targetCode}">${likeCount}</span>
    </button>
    <button onclick="dislikeTarget('${type}', ${targetCode})">
        <span id="dislikeCount-${type}-${targetCode}">${dislikeCount}</span>
    </button>
</div>