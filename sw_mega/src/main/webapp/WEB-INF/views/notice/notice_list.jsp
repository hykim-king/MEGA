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

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/ehr/resources/assets/css/notice_list.css">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="${CP}/resources/assets/js/common.js"></script>
 <script>
 document.addEventListener('DOMContentLoaded', function(){
       console.log('DOMContentLoaded');
       //조회버튼
       const doRetieveButton=document.querySelector("#doRetrieve");
       console.log(doRetieveButton);
       //등록 버튼
       const moveToRegButton=document.querySelector("#moveToReg");
       console.log(moveToRegButton);
       
      /*  const divInput = document.querySelector("#div");
       console.log(divInput);
    */
       
       //등록 버튼 이벤트 감지
       moveToRegButton.addEventListener("click",function(event){
           console.log('moveToRegButton click');
           
           if(confirm('등록 화면으로 이동 하시겠습니까?') === false)return;
           
           window.location.href = "/ehr/notice/doSaveView.do";
           
       });
       
       
       //
       doRetieveButton.addEventListener("click",function(event){
           event.stopPropagation();// 이벤트 버블링 중지
           console.log('doRetieveButton click');
           doRetieve(1);
           
       });
            
       //
       function doRetieve(pageNo){
           console.log('doRetieve pageNo:'+pageNo);
        
           //form
           const userForm = document.userForm;
           userForm.pageNo.value =pageNo;
           
           userForm.action="/ehr/notice/doRetrieve.do";
           
           userForm.submit();
       }
       
   
       
 });      
 
 
 //페이징 
 function pagerDoRetrieve(url, pageNo){   
     console.log('pagerDoRetrieve pageNo:'+pageNo);
     console.log('pagerDoRetrieve url:'+url);
     
     //form
     const userForm = document.userForm;
     userForm.pageNo.value =pageNo;
     
     userForm.action=url;
     
     userForm.submit();     
     
 }    
 </script> 
</head>


<body>
<div class="main-container">   
   <h2>공지사항 - 목록</h2>


    <hr class="title-underline"/>
    <!--검색 영역-->
    <form action="#" name="userForm" class="search-form"method="get" enctype="application/x-www-form-urlencoded">
      <input type="hidden" name="pageNo" id="pageNo">
      
      <div class="top-bar">
        <div class="search-box">
	        <select name="searchDiv" id="searchDiv" class="search-select">	        
	           <option value="">전체</option> 
	           <option value="10" <c:if test="${search.searchDiv == 10 }">selected</c:if>>제목</option> 
	           <option value="20" <c:if test="${search.searchDiv == 20 }">selected</c:if>>내용</option> 
	           <option value="30" <c:if test="${search.searchDiv == 30 }">selected</c:if>>사용자ID</option>  
	           <option value="40" <c:if test="${search.searchDiv == 40 }">selected</c:if>>제목+내용</option>  	           
	        </select>
	        
            <input type="search" name="searchWord" id="searchWord" class="search-input" value="${search.searchWord }">
	        <select name="pageSize" id="pageSize" class="search-select">
	            <option value="10" <c:if test="${search.pageSize == 10 }">selected</c:if> >10</option>
	            <option value="20" <c:if test="${search.pageSize == 20 }">selected</c:if> >20</option>
	            <option value="30" <c:if test="${search.pageSize == 30 }">selected</c:if> >30</option>
	            <option value="50" <c:if test="${search.pageSize == 50 }">selected</c:if> >50</option>
	            <option value="100" <c:if test="${search.pageSize == 100 }">selected</c:if> >100</option>
		        </select>       
	            <input type="button" value="조회🔍" id="doRetrieve" class="btn-search">
	        </div>   
        
         <div class="button-box">
             <button type="button" id="moveToReg" class="btn-new-post">+ 새 글</button>
        </div>
       </div>
    </form>
    
    
    <!--//검색 영역 end-->
    <table border="1" id="listTable"  class="table">
      <colgroup>
        <col width="10%">
        <col width="40%">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        <col width="0%"> 
      </colgroup>
      <thead>
          <tr>
            <th class="table-head">번호</th>
            <th class="table-head">제목</th>
            <th class="table-head">글쓴이</th>
            <th class="table-head">작성일</th>
            <th class="table-head">조회수</th>
            <th style="display: none;">noCode</th>
          </tr>
      </thead>
      <tbody>
      
      
          <c:choose>
            <c:when test="${list.size() > 0 }"> <!-- if -->
                <c:forEach var="vo" items="${list }" varStatus="status"> <!-- 향상된 for -->
                  <tr>
                   <td class="table-cell text-center"><c:out value="${(search.pageNo - 1) * search.pageSize + status.index + 1}" /></td>
                   
                   <td class="table-cell text-left highlight">
                    <a href="${CP}/notice/doDetail.do?noCode=${vo.noCode}&pageSize=${10}&pageNo=${1}">
                        <c:out value="${vo.title}"/>
                    </a>
                   </td>                    
                    <td class="table-cell text-center"><c:out value="${vo.userId }"/></td>
                    <td class="table-cell text-center"><c:out value="${vo.upDt }"/></td>
                    <td class="table-cell text-right"><c:out value="${vo.viewCount }"/></td>
                    <td style="display: none;"><c:out value="${vo.noCode }"/></td>
                  </tr> 
                </c:forEach>
            </c:when>
            <c:otherwise>    <!-- else -->
                <tr>
                
                   <td colspan="99"  class="table-cell text-center">조회된 데이터가 없습니다.</td> 
                </tr>
            </c:otherwise>
          </c:choose>
                   
      </tbody>
    </table>
    <!-- paging -->
    <%
        out.print(pageHtml);
    %>
    <!--// paging end -->
</div>

</body>
</html>