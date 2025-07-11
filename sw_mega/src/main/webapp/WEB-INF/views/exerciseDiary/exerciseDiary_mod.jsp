<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/ehr/resources/assets/css/exerciseDiary_mod.css">
<title>헬메이트</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

<div class="exercise-edit-container">
  <div class="exercise-edit-card">
<h2>운동 일지 수정</h2>
<form id="exerciseDiaryForm">
  <input type="hidden" name="edCode" value="${outVO.edCode}" />
  <input type="hidden" name="userId" value="${outVO.userId}" />

  <label>운동명: </label>
  <input type="text" name="exerciseName" value="${outVO.exerciseName}" readonly /><br/>

  <label>운동 시간(분/ 유산소운동): </label>
  <input type="number" name="duration" value="${outVO.duration}" min="0"/><br/>
  
  <label>덤벨 무게(kg/근력운동): </label>
  <input type="number" name="strenthWeight" value="${outVO.strenthWeight}" min="0"/><br/>
    
  <label>세트 수(근력운동): </label>
  <input type="number" name="setCount" value="${outVO.setCount}" min="0"/><br/>
  
  <label>세트 당 횟수(근력운동): </label>
  <input type="number" name="repsPerSet" value="${outVO.repsPerSet}" min="0"/><br/>

  <button type="button" id="updateBtn">수정</button>
</form>
  </div>
</div>
<script>
	$('#updateBtn').click(function() {
	  const formData = $('#exerciseDiaryForm').serialize();
	
	  $.post('/ehr/exerciseDiary/doUpdate.do', formData, function(response) {
	    const res = JSON.parse(response);
	    alert(res.message);
	    if (res.messageId === 1) {
	        window.location.href = '/ehr/exerciseDiary/doRetrieve.do?regDt=' + encodeURIComponent('${outVO.regDt}');
	    }
	  });
	});
</script>

</body>
</html>