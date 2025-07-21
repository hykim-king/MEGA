<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd_HH:mm:ss" /></c:set> 
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>${outVO.title} | 자유게시판 상세</title>
    <link rel="stylesheet" href="/ehr/resources/assets/css/header.css">
    <link rel="stylesheet" href="/ehr/resources/assets/css/pcwk_main.css">
    <link rel="stylesheet" href="/ehr/resources/assets/css/comment.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="/ehr/resources/assets/css/freeboard_detail.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<div id="container">

    <jsp:include page="/WEB-INF/views/include/header.jsp"/>
    <main class="main-container">
        <div class="detail-container">
        
            <div class="freeboard-title">${outVO.title}</div>
            <div class="freeboard-meta">
                작성자: <b>${outVO.userId}</b> &nbsp; | &nbsp;
                작성일: ${outVO.cDt} &nbsp; | &nbsp;
                조회수: ${outVO.viewCount}
            </div>
            <hr/>
            <div class="freeboard-content">
            	${outVO.content}
            </div>
            <hr/>
            <div class="freeboard-actions">
                <input type="hidden" id="reactiontype" name="reactiontype" value="freeboard">       
                <a href="${CP}/freeboard/doUpdateView.do?fbCode=${outVO.fbCode}">
                    <button>수정</button>
                </a>
                <button onclick="deleteFreeboard(${outVO.fbCode})">삭제</button>
                <div class="reaction-buttons" style="display:flex; align-items:center; gap:5px; margin-left:11px;">
                    <button id="likeBtn-FREEBOARD-${outVO.fbCode}" onclick="toggleReaction('FREEBOARD', 'L', ${outVO.fbCode})">
                        👍 좋아요 <span id="likeCount-FREEBOARD-${outVO.fbCode}">${nLikeCount}</span>
                    </button>
                    <button id="dislikeBtn-FREEBOARD-${outVO.fbCode}" onclick="toggleReaction('FREEBOARD', 'D', ${outVO.fbCode})">
                        👎 싫어요 <span id="dislikeCount-FREEBOARD-${outVO.fbCode}">${nDislikeCount}</span>
                    </button>
                    <button onclick="reportTarget('FREEBOARD', ${outVO.fbCode})">🚩 신고</button>
                </div>
            </div>
            <!-- 댓글 목록 -->
            <div class="comment-section">
                <h3>댓글</h3>
                <div id="commentList">
                    <c:if test="${not empty commentList}">
                        <c:forEach var="comment" items="${commentList}">
                            <div class="comment-box">
                                <p>${comment.content}</p>
                                <p>${comment.userId} / <fmt:formatDate value="${comment.cDt}" pattern="yyyy-MM-dd HH:mm" /></p>
                                <div class="comment-actions">
                                    <form action="${CP}/fbComment/doSelectOne.do" method="get" style="display:inline;">
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
                <div class="comment-write-box">
                    <h3>댓글 남기기</h3>
                    <textarea id="content" name="content" rows="3" cols="50" placeholder="내용을 입력하세요"></textarea><br/>
                    <input type="hidden" id="fbCode" value="${param.fbCode}" />
                    <input type="hidden" id="userId" value="user01" />
                    <button id="btnCommentSave">댓글 달기</button>
                </div>
            </div>
            <button class="to-list-btn" onclick="window.location.href='${CP}/freeboard/doRetrieve.do'">목록으로</button>
        </div>
    </main>
    <jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</div>
<script>
    const userReactions = {};
    // 게시글 삭제
    function deleteFreeboard(fbCode) {
        if (!confirm("정말 삭제하시겠습니까?")) return;
        $.ajax({
            type: "POST",
            url: "${CP}/freeboard/doDelete.do",
            data: { fbCode: fbCode },
            success: function (result) {
                const res = (typeof result === "object") ? result : JSON.parse(result);
                alert(res.message);
                if (res.messageId === 1) {
                    location.href = "${CP}/freeboard/doRetrieve.do";
                }
            },
            error: function (xhr, status, error) {
                alert("삭제 중 오류 발생: " + error);
            }
        });
    }
    // 댓글 삭제
    function deleteComment(commentedCode) {
        if (!confirm("정말 삭제하시겠습니까?")) return;
        $.ajax({
            url: '${CP}/fbComment/doDelete.do',
            type: 'POST',
            data: { commentedCode:commentedCode },
            success: function(response) {
                const res = (typeof response === "object") ? response : JSON.parse(response);
                alert(res.message);
                if (res.messageId === 1) {
                    location.reload();
                }
            },
            error: function(xhr, status, error) {
                alert('삭제 중 오류 발생: ' + error);
            }
        });
    }
    // 댓글 등록
    $("#btnCommentSave").click(function () {
        var content = $("#content").val();
        var fbCode = $("#fbCode").val();
        var userId = $("#userId").val();
        if (content.trim() === "") {
            alert("내용을 입력하세요."); return;
        }
        if (!userId) {
            alert("로그인이 필요합니다."); return;
        }
        $.ajax({
            type: "POST",
            url: "${CP}/fbComment/doSave.do",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify({
                fbCode: fbCode,
                content: content,
                userId: userId
            }),
            success: function(result) {
                var data = (typeof result === "object") ? result : JSON.parse(result);
                if (data.flag === 1) {
                    alert("댓글이 등록되었습니다.");
                    location.reload();
                } else {
                    alert("등록 실패!");
                }
            }
        });
    });
    // 신고
    function reportTarget(type, id) {
        if (!confirm("정말 신고하시겠습니까?")) return;
        $.ajax({
            url: "${CP}/report/doReport.do",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify({
                userId: "user01", // 실제 로그인 사용자로 대체 필요
                reason: "부적절한 내용입니다.",
                targetType: type.toUpperCase(),
                targetCode: id
            }),
            success: function (res) {
                alert(res.message);
            }
        });
    }
    // 좋아요/싫어요(게시글/댓글) 상태관리 및 토글
    $(document).ready(function() {
        const fbCode = $("#fbCode").val();
        $.getJSON("${CP}/reaction/getUserReaction.do?targetType=FREEBOARD&targetCode=" + fbCode, function(data) {
            if (data.reactionType) {
                const key = `FREEBOARD-${fbCode}`;
                userReactions[key] = data.reactionType;
                updateButtonStyles("FREEBOARD", fbCode);
            }
        });
        $("[id^=likeBtn-COMMENT-]").each(function() {
            const code = this.id.split("-")[2];
            $.getJSON("${CP}/reaction/getUserReaction.do?targetType=COMMENT&targetCode=" + code, function(data) {
                if (data.reactionType) {
                    const key = `COMMENT-${code}`;
                    userReactions[key] = data.reactionType;
                    updateButtonStyles("COMMENT", code);
                }
            });
        });
    });
    function toggleReaction(targetType, reactionType, targetCode) {
        const key = `${targetType}-${targetCode}`;
        const current = userReactions[key];
        let sendType = (current === reactionType) ? null : reactionType;
        $.ajax({
            type: "POST",
            url: "${CP}/reaction/doToggleReaction.do",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify({ targetType, targetCode, reactionType: sendType }),
            success: function(data) {
                var d = (typeof data === "object") ? data : JSON.parse(data);
                if (d.flag === 1) {
                    $("#likeCount-" + key).text(d.likeCount);
                    $("#dislikeCount-" + key).text(d.dislikeCount);
                    userReactions[key] = sendType;
                    updateButtonStyles(targetType, targetCode);
                } else {
                    alert("처리 실패: " + d.message);
                }
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
</script>
</body>
</html>
