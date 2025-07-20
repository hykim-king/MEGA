<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>🍽️ 음식 일지 수정</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <link rel="stylesheet" href="/ehr/resources/assets/css/foodDiary_mod.css">
</head>
<body>

<div class="main-container">
<h2 style="text-align: center;">🍽️ 음식 일지 수정</h2>
<form id="foodDiaryForm">
  <input type="hidden" name="fdCode" value="${outVO.fdCode}" />
  <input type="hidden" name="regDt" value="${outVO.regDt}" />

  <label>음식명: </label>
  <input type="text" name="foodName" value="${outVO.foodName}" readonly /><br/>

  <label>섭취 그람(g): </label>
  <input type="number" name="grams" value="${outVO.grams}" required /><br/>

  <label>식사시간: </label>
  <select name="mealType" required>
	<option value="아침" <c:if test="${outVO.mealType == '아침'}">selected</c:if>>아침</option>
	<option value="점심" <c:if test="${outVO.mealType == '점심'}">selected</c:if>>점심</option>
	<option value="저녁" <c:if test="${outVO.mealType == '저녁'}">selected</c:if>>저녁</option>
  </select><br/>

  <button type="button" id="updateBtn">수정</button>
</form>
</div>

<script>
	$('#updateBtn').click(function() {
	  const formData = $('#foodDiaryForm').serialize();

	$.post('/ehr/foodDiary/doUpdate.do', formData, function(response) {
	  const res = JSON.parse(response);
	  alert(res.message);
	  if (res.messageId === 1) {
		 window.location.href = '/ehr/foodDiary/doRetrieve.do?regDt=' + encodeURIComponent('${outVO.regDt}');
	    }
	  });
	});
</script>
</body>
</html>