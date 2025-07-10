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
<link rel="stylesheet" href="/ehr/resources/assets/css/food_exercise_list.css">
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

<h2>음식 추가</h2>
<br>
<h4>세부 내용:</h4>
  <form id="foodForm">
    
    <label for="foodName">음식명:</label>
    <input type="text" id="foodName" name="foodName" required >
    <br/>
    <label for="stGrams">기준 그람:</label>
    <input type="number" id="stGrams" name="stGrams" required >
    <br/>
    <label for="cal">칼로리:</label>
    <input type="number" id="cal" name="cal" required >
    <br/>
    <label for="carb">탄수화물:</label>
    <input type="number" id="carb" name="carb" required >
    <br/>
    <label for="fat">지방:</label>
    <input type="number" id="fat" name="fat" required >
    <br/>
    <label for="prot">단백질:</label>
    <input type="number" id="prot" name="prot" required >
    <br/>
    <label for="na">나트륨:</label>
    <input type="number" id="na" name="na" required >
    <br/>
    <button type="button" id="saveBtn">등록</button>
  </form>
  
  <script>
	  $('#saveBtn').click(function() {
		  const formData = $('#foodForm').serialize();

	  $.post('/ehr/food/doSave.do', formData, function(response) {
	      const res = JSON.parse(response);
	      alert(res.message);
	      if (res.messageId === 1) {
	        location.href = "/ehr/food/doRetrieve.do";
	      }
	    });
	  });
	</script>

      </div>
      </main>
      <!--//main end-------------------->

      
 <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
   </div> 
</body>
</html>
