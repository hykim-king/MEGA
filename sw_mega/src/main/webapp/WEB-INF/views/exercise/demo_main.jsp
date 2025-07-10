<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<c:set var="CP" value="${pageContext.request.contextPath }" />    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/ehr/resources/assets/css/header.css">
<link rel="stylesheet" href="/ehr/resources/assets/css/mypage_search.css">
<link rel="stylesheet" href="/ehr/resources/assets/css/pcwk_main.css">
<title>헬메이트</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="/ehr/resources/assets/js/common.js"></script>
</head>
<body>
   <div id="container">
   
    <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
    
      <!--main-->
      <main id="main">
      <div class="main-container">

      <!-- 이 사이에 각자 항목 넣으시면 됩니다! -->

      </div>
      </main>
      <!--//main end-------------------->

      
 <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
   </div> 
</body>
</html>