<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>${outVO.title} | 게시글 상세</title>
    <link rel="stylesheet" href="${CP}/resources/assets/css/header.css">
    <link rel="stylesheet" href="${CP}/resources/assets/css/pcwk_main.css">
    <link rel="stylesheet" href="${CP}/resources/assets/css/comment.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: 'Do Hyeon', sans-serif;
            margin: 0;
            background: #fff;
        }
        #container {
            min-height: 100vh;
            background: #fff;
        }
        .main-container {
            display: flex;
            justify-content: center;
        }
        .detail-container {
            width: 100%;
            max-width: 700px;
            margin: 70px auto 60px auto;
            background: #F5F7FF;
            border-radius: 13px;
            box-shadow: 0 4px 18px #e4e7fa7e;
            padding: 45px 38px 38px 38px;
        }
        .notice-title {
            font-size: 2.1rem;
            font-weight: bold;
            text-shadow: 2px 2px #FDFF48;
            margin-bottom: 18px;
            text-align: left;
        }
        .notice-meta {
            color: #555;
            font-size: 1.05rem;
            margin-bottom: 7px;
            text-align: left;
        }
        .notice-content {
            font-size: 1.12rem;
            margin: 28px 0 35px 0;
            white-space: pre-line;
            text-align: left;
        }
        .notice-actions {
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            gap: 7px;
            margin-bottom: 20px;
        }
        .notice-actions button {
            font-family: 'Do Hyeon', sans-serif;
            font-size: 1rem;
            border-radius: 6px;
            padding: 9px 20px;
            background: #5C6EFF;
            color: #fff;
            border: none;
            margin-right: 4px;
            transition: background 0.15s;
            cursor: pointer;
        }
        .notice-actions button:hover {
            background: #4958b8;
        }
        .notice-actions .reaction-buttons button {
            background: #F5F7FF;
            color: #222;
            border: 1px solid #aaa;
            padding: 7px 15px;
        }
        .notice-actions .reaction-buttons button.active {
            background: #4958b8;
            color: #fff;
            border-color: #4958b8;
        }
        hr {
            border: none;
            border-top: 1.5px solid #e1e4eb;
            margin: 24px 0;
        }
        .comment-section {
            margin-top: 30px;
        }
        .comment-box {
            background: #fff;
            border-radius: 8px;
            margin-bottom: 16px;
            padding: 18px 16px 11px 16px;
            box-shadow: 0 1px 3px #ededed9e;
        }
        .comment-box p {
            margin: 3px 0;
            text-align: left;
        }
        .comment-actions {
            display: flex;
            gap: 5px;
            margin-top: 6px;
        }
        .comment-actions button {
            font-size: 0.95rem;
            background: #F5F7FF;
            color: #222;
            border: 1px solid #aaa;
            border-radius: 5px;
            padding: 6px 14px;
            transition: background 0.15s;
        }
        .comment-actions button.active {
            background: #4958b8;
            color: #fff;
            border-color: #4958b8;
        }
        .comment-actions .reaction-buttons button {
            padding: 4px 10px;
        }
        .comment-section h3 {
            text-align: left;
            font-size: 1.2rem;
            margin-bottom: 7px;
            margin-top: 36px;
        }
        .comment-write-box {
            text-align: left;
            margin-top: 22px;
        }
        .comment-write-box textarea {
            width: 97%;
            font-family: 'Do Hyeon', sans-serif;
            font-size: 1.04rem;
            padding: 10px;
            border: 1.5px solid #bbb;
            border-radius: 6px;
            margin-bottom: 7px;
            min-height: 54px;
            resize: vertical;
            background: #fff;
            color: #222;
        }
        .comment-write-box button {
            font-size: 1rem;
            border-radius: 6px;
            padding: 8px 21px;
            background: #5C6EFF;
            color: #fff;
            border: none;
            margin-top: 7px;
            cursor: pointer;
        }
        .to-list-btn {
            margin-top: 40px;
            margin-bottom: 8px;
            padding: 12px 40px;
            font-size: 18px;
            background-color: #F5F7FF;
            color: #222;
            border: 1px solid #aaa;
            border-radius: 7px;
            cursor: pointer;
        }
        .to-list-btn:hover {
            background: #ecefff;
            color: #4958b8;
            border-color: #4958b8;
        }
        @media (max-width: 600px) {
            .detail-container {
                padding: 18px 4vw;
                margin-top: 26px;
            }
            .notice-title { font-size: 1.25rem; }
            .to-list-btn { padding: 11px 12vw; font-size: 16px; }
        }
    </style>
</head>
<body>
<div id="container">
    <%-- 헤더 --%>
    <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
    <main class="main-container">
      <div class="detail-container">
        <div class="notice-title">${outVO.title}</div>
        <div class="notice-meta">
            작성자: <b>${outVO.userId}</b> &nbsp; | &nbsp;
            작성일: ${outVO.cDt} &nbsp; | &nbsp;
            조회수: ${outVO.viewCount}
        </div>
        <hr/>
        <div class="notice-content">
            ${outVO.content}
        </div>
        <hr/>
        <!-- 액션/리액션 버튼 -->
        <div class="notice-actions">
            <input type="hidden" id="reactiontype" name="reactiontype" value="notice">
            <a href="${CP}/notice/doUpdateView.do?noCode=${outVO.noCode}">
                <button>수정</button>
            </a>
            <button onclick="deleteNotice(${outVO.noCode})">삭제</button>
	<div class="reaction-buttons" style="display: flex; align-items: center; gap: 5px; margin-left: 11px;">
		<button id="likeBtn-NOTICE-${outVO.noCode}" onclick="toggleReaction('NOTICE', 'L', '${outVO.noCode}')">
		    👍 좋아요 <span id="likeCount-NOTICE-${outVO.noCode}">${nLikeCount}</span>
		</button>
		<button id="dislikeBtn-NOTICE-${outVO.noCode}" onclick="toggleReaction('NOTICE', 'D', '${outVO.noCode}')">
		    👎 싫어요 <span id="dislikeCount-NOTICE-${outVO.noCode}">${nDislikeCount}</span>
		</button>
                <button onclick="reportTarget('NOTICE', ${outVO.noCode})">🚩 신고</button>
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
                                <form action="${CP}/noComment/doSelectOne.do" method="get" style="display: inline;">
                                    <input type="hidden" name="noCommentCode" value="${comment.commentedCode}" />
                                    <button type="submit">수정</button>
                                </form>
                                <button onclick="deleteDiary('${comment.commentedCode}')">삭제</button>
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
            <!-- 댓글 작성 -->
            <div class="comment-write-box">
                <h3>댓글 남기기</h3>
                <textarea id="content" name="content" rows="3" cols="50" placeholder="내용을 입력하세요"></textarea><br/>
                <input type="hidden" id="noCode" value="${param.noCode}" />
                <input type="hidden" id="userId" value="user01" />
                <button id="btnCommentSave">댓글 달기</button>
            </div>
        </div>
        <!-- 목록으로 이동 버튼 -->
        <button class="to-list-btn" onclick="window.location.href='${CP}/notice/doRetrieve.do'">목록으로</button>
      </div>
    </main>
    <%-- 푸터 --%>
    <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
</div>

<!-- JS 영역 (모든 기능 통합) -->
<script>
    // 게시글 삭제
    function deleteNotice(noCode) {
        if (!confirm("정말 삭제하시겠습니까?")) return;
        $.ajax({
            type: "POST",
            url: "${CP}/notice/doDelete.do",
            data: { noCode: noCode },
            success: function (result) {
                const res = JSON.parse(result);
                alert(res.message);
                if (res.messageId === 1) {
                    location.href = "${CP}/notice/doRetrieve.do";
                }
            },
            error: function (xhr, status, error) {
                alert("삭제 중 오류 발생: " + error);
            }
        });
    }

    // 댓글 삭제
    function deleteDiary(commentedCode) {
        if (!confirm('정말 삭제하시겠습니까?')) return;
        $.ajax({
            url: '${CP}/noComment/doDelete.do',
            type: 'POST',
            data: { commentedCode:commentedCode },
            success: function(response) {
                const res = JSON.parse(response);
                alert(res.message);
                if (res.messageId === 1) {
                    window.location.href = '${CP}/notice/doDetail.do?noCode=${param.noCode}&pageSize=10&pageNo=1';
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
            url: "${CP}/noComment/doSave.do",
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

    // 신고
    function reportTarget(type, id) {
        if (!confirm("정말 신고하시겠습니까?")) return;
        $.ajax({
            url: "${CP}/report/doReport.do",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify({
                userId: "user01", // 실제 사용자로 대체 필요
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
    var userReactions = {};
    // 초기값 조회 (게시글)
    $(document).ready(function() {
        const postCode = "${outVO.noCode}";
        $.getJSON("${CP}/reaction/getUserReaction.do?targetType=NOTICE&targetCode=" + postCode, function(data) {
            if (data.reactionType) {
                const key = `NOTICE-${postCode}`;
                userReactions[key] = data.reactionType;
                updateButtonStyles("NOTICE", postCode);
            }
        });
        // 댓글 초기값 조회
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

    // 좋아요/싫어요 버튼 이벤트
    function toggleReaction(targetType, reactionType, targetCode) {
        console.log("🧪 targetType:", targetType);
        console.log("🧪 reactionType:", reactionType);
        console.log("🧪 targetCode:", targetCode);
        
        const key = targetType+"-"+targetCode;
        console.log("💡 key:", key);

        const current = userReactions[key];
        let sendType = (current === reactionType) ? null : reactionType;

        $.ajax({
            type: "POST",
            url: "${CP}/reaction/doToggleReaction.do",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify({ targetType, targetCode, reactionType: sendType }),
            success: function(data) {
                if (data.flag === 1) {
                    $("#likeCount-" + key).text(data.likeCount);
                    console.log($("#likeCount-" + key));
                    $("#dislikeCount-" + key).text(data.dislikeCount);
                    console.log("싫어요 수:{}",key);
                    userReactions[key] = sendType;
                    updateButtonStyles(targetType, targetCode);
                } else {
                    alert("처리 실패: " + data.message);
                }
            }
        });
    }
    

    // 버튼 스타일 업데이트
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
