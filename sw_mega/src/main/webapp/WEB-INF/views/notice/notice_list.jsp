<%@page import="com.pcwk.ehr.cmn.PcwkString"%>
<%@page import="com.pcwk.ehr.cmn.SearchDTO"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="CP" value="${pageContext.request.contextPath }" />
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd_HH:mm:ss" /></c:set> 
<%
   int bottomCount = 10;
   int pageSize    = 0;//페이지 사이즈
   int pageNo      = 0;//페이지 번호
   int maxNum      = 0;//총글수
   
   String url      = "";//호출URL
   String scriptName="";//자바스크립트 이름
   
   
   //request: 요청 처리를 할수 있는 jsp 내장 객체
   String totalCntString = request.getAttribute("totalCnt").toString();
   //out.print("totalCntString:"+totalCntString);
   maxNum = Integer.parseInt(totalCntString);
   
   SearchDTO  paramVO = (SearchDTO)request.getAttribute("search");   
   pageSize = paramVO.getPageSize();
   pageNo   = paramVO.getPageNo();
   
   //out.print("pageSize:"+pageSize);
   //out.print("pageNo:"+pageNo);

   String cp = request.getContextPath();
   //out.print("cp:"+cp);
   
   url = cp+"/notice/doRetrieve.do";
   //out.print("url:"+url);
   
   scriptName = "pagerDoRetrieve";
   
   String pageHtml=PcwkString.renderingPager(maxNum, pageNo, pageSize, bottomCount, url, scriptName);
   //System.out.println(pageHtml);
%>
<head>
    <meta charset="UTF-8">
    <title>공지사항 목록</title>
    <link rel="stylesheet" href="/ehr/resources/assets/css/header.css">
    <link rel="stylesheet" href="/ehr/resources/assets/css/pcwk_main.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
        body { font-family: 'Do Hyeon', sans-serif; margin:0; background:#fff;}
        #container { min-height:100vh; background:#fff;}
        .main-container { display:flex; justify-content:center;}
        .list-container {
            width:100%; max-width:900px; margin:80px auto 60px auto;
            background: #F5F7FF; border-radius:13px; box-shadow: 0 4px 18px #e4e7fa7e;
            padding: 48px 38px 38px 38px;
        }
        .board-title { font-size:2.1rem; font-weight:bold; text-shadow:2px 2px #FDFF48; margin-bottom:16px;}
        .title-underline { border:none; border-top:2px solid #e1e4eb; margin:20px 0 26px 0;}
        .search-form {margin-bottom:20px;}
        .top-bar {display:flex; justify-content:space-between; align-items:center;}
        .search-box {display:flex; gap:7px;}
        .search-select, .search-input {height:38px; font-size:1rem; border-radius:6px; border:1.5px solid #bbb;}
        .search-input {width:220px; padding:0 12px;}
        .btn-search, .btn-new-post {font-size:1rem; border-radius:6px; padding:8px 17px; background:#5C6EFF; color:#fff; border:none; cursor:pointer;}
        .btn-search:hover, .btn-new-post:hover {background:#4958b8;}
        .button-box {margin-left:12px;}
        table.table {width:100%; border-collapse:collapse; margin-top:16px;}
        th, td {padding:12px 8px; border-bottom:1px solid #e1e4eb; text-align:center;}
        th {background:#ecf0ff; color:#222;}
        .highlight a {color:#4958b8; text-decoration:none; font-weight:bold;}
        .text-center {text-align:center;}
        .text-right {text-align:right;}
        .text-left {text-align:left;}
        .table-cell {background:#fff;}
        @media (max-width:600px){
            .list-container{padding:14px 4vw;}
            .search-input{width:100px;}
            .board-title{font-size:1.25rem;}
            
        }
        
			        .pagination {
			  display: flex;
			  justify-content: center;
			  align-items: center;
			  margin: 20px 0;
			  font-size: 16px;
			  gap: 5px;
			}
			
			.pagination  a{
			  display: inline-block;
			  padding: 8px 12px;
			  text-decoration: none;
			  color: #007bff;
			  border: 1px solid #dddddd;
			  border-radius: 4px;
			  background-color: #ffffff;
			  transition: all 0.3s ease;
			}
			.pagination a:hover{
			  background-color: #007bff;
			  color: #ffffff;
			  border-color: #007bff;
			}
			
			.pagination a.active{ 
			  background-color: #007bff;
			  color: #ffffff;
			  border-color: #007bff;
			} 
			 
			.pagination a.disabled{
			  background-color: #f8f9fa;
			  color: #000;
			  pointer-events: none;
			  border-color: #dddddd; 
			  cursor: not-allowed;
			}
    </style>
    <script>
        // 등록 및 검색 버튼 이벤트
        $(function(){
            $("#moveToReg").click(function(){
                if(confirm('등록 화면으로 이동 하시겠습니까?') === false) return;
                window.location.href = "${CP}/notice/doSaveView.do";
            });
            $("#doRetrieve").click(function(){
                $("form[name='userForm']").submit();
            });
        });
        // 페이징 함수는 그대로 사용
        function pagerDoRetrieve(url, pageNo){   
            const userForm = document.userForm;
            userForm.pageNo.value = pageNo;
            userForm.action = url;
            userForm.submit();
        }  
    </script>
</head>
<body>
<div id="container">
    <jsp:include page="/WEB-INF/views/include/header.jsp"/>
    <main class="main-container" id="main">
        <div class="list-container">
            <div class="board-title">공지사항</div>
            <hr class="title-underline"/>
            <!--검색 영역-->
            <form action="${CP}/notice/doRetrieve.do" name="userForm" class="search-form" method="get">
                <input type="hidden" name="pageNo" id="pageNo" value="${search.pageNo}"/>
                <div class="top-bar">
                    <div class="search-box">
                        <select name="searchDiv" id="searchDiv" class="search-select">
                            <option value="">전체</option> 
                            <option value="10" <c:if test="${search.searchDiv == 10}">selected</c:if>>제목</option> 
                            <option value="20" <c:if test="${search.searchDiv == 20}">selected</c:if>>내용</option> 
                            <option value="30" <c:if test="${search.searchDiv == 30}">selected</c:if>>사용자ID</option>
                            <option value="40" <c:if test="${search.searchDiv == 40}">selected</c:if>>제목+내용</option>
                        </select>
                        <input type="search" name="searchWord" id="searchWord" class="search-input" value="${search.searchWord}"/>
                        <select name="pageSize" id="pageSize" class="search-select">
                            <option value="10" <c:if test="${search.pageSize == 10}">selected</c:if>>10</option>
                            <option value="20" <c:if test="${search.pageSize == 20}">selected</c:if>>20</option>
                            <option value="30" <c:if test="${search.pageSize == 30}">selected</c:if>>30</option>
                            <option value="50" <c:if test="${search.pageSize == 50}">selected</c:if>>50</option>
                            <option value="100" <c:if test="${search.pageSize == 100}">selected</c:if>>100</option>
                        </select>
                        <input type="button" value="조회🔍" id="doRetrieve" class="btn-search"/>
                    </div>
                    <div class="button-box">
                        <button type="button" id="moveToReg" class="btn-new-post">+ 새 글</button>
                    </div>
                </div>
            </form>
            <!--목록 테이블-->
            <table class="table">
                <colgroup>
                    <col width="10%"><col width="40%"><col width="12%"><col width="18%"><col width="10%"><col width="0%">
                </colgroup>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>작성일</th>
                        <th>조회수</th>
                        <th style="display:none;">noCode</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${list.size() > 0}">
                            <c:forEach var="vo" items="${list}" varStatus="status">
                                <tr>
                                    <td class="table-cell text-center">${(search.pageNo-1)*search.pageSize+status.index+1}</td>
                                    <td class="table-cell text-left highlight">
                                        <a href="${CP}/notice/doDetail.do?noCode=${vo.noCode}&pageSize=10&pageNo=1">
                                            <c:out value="${vo.title}"/>
                                        </a>
                                    </td>
                                    <td class="table-cell text-center"><c:out value="${vo.userId}"/></td>
                                    <td class="table-cell text-center"><c:out value="${vo.upDt}"/></td>
                                    <td class="table-cell text-right"><c:out value="${vo.viewCount}"/></td>
                                    <td style="display:none;"><c:out value="${vo.noCode}"/></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="99" class="table-cell text-center">조회된 데이터가 없습니다.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
            <!-- paging -->
                <% out.print(pageHtml); %>
        </div>
    </main>
    <jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</div>
</body>
</html>
