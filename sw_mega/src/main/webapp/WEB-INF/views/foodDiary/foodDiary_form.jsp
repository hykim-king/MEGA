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
<link rel="stylesheet" href="/ehr/resources/assets/css/foodDiary_form.css">
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

<div class="food-form-container">
  <div class="food-form-card">
<h2>🍽️ 음식 일지 등록</h2>

<form id="foodDiaryForm">

  <label for="foodName">음식명: </label>
  <input type="text" id="foodName" name="foodName" value="${param.foodName}" required readonly />
  <button type="button" onclick="goSearchFood()" class="search-btn">찾기</button><br/>

  <label>섭취 그람(g): </label>
  <input type="number" id="grams" name="grams" value="${param.grams}" required /><br/>

  <label for="mealType">식사시간: </label>
  <select name="mealType" id="mealType" required>
    <option value="아침">아침</option>
    <option value="점심">점심</option>
    <option value="저녁">저녁</option>
  </select><br/>

  <label for="regDt">날짜: </label>
  <input type="date" id="regDt" name="regDt" value="${param.regDt}" required /><br/><br/>

  <button type="button" id="saveBtn">등록</button>
</form>
 </div>
</div>

<script>
	function goSearchFood() {
		const regDt = $('#regDt').val();
		
		const url = "/ehr/food/doRetrieve.do?mode=select&returnUrl=foodDiary/doForm.do"
	          + "&regDt=" + encodeURIComponent(regDt);
	
	    window.location.href = url;
	}

	$('#saveBtn').click(function() {
	  const formData = $('#foodDiaryForm').serialize();
	
	  $.post('/ehr/foodDiary/doSave.do', formData, function(response) {
		  const res = JSON.parse(response);
		  alert(res.message);
		  if (res.messageId === 1) {
			 location.href = "/ehr/foodDiary/doRetrieve.do?regDt=" + $('#regDt').val();
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
