<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헬메이트</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<h2>운동 일지 등록</h2>

<form id="exerciseDiaryForm">
    
    <label for="exerciseName">운동명: </label>
    <input type="text" id="exerciseName" name="exerciseName" value="${param.exerciseName }" required readonly />
    <input type="hidden" name="eCode" value="${param.eCode}"/>
    <button type="button" onclick="goSearchExercise()">찾기</button><br/>
    
    <label>운동시간: </label>
    <input type="number" id="duration" name="duration" value="${param.duration}" required /><br/>
     
    <label>성별(유산소): </label>
    <input type="text" id="gender" name="gender" value="${param.gender}" readonly/><br/> 
     
    <label>기준 체중(유산소): </label>
    <input type="number" id="weight" name="weight" value="${param.weight}" readonly/><br/>
    
    <label>덤벨무게(근력): </label>
    <input type="number" id="strenthWeight" name="strenthWeight" value="${param.strenthWeight}" /><br/>
     
    <label>세트 수(근력): </label>
    <input type="number" id="setCount" name="setCount" value="${param.setCount}" /><br/>
     
    <label>세트 당 횟수(근력): </label>
    <input type="number" id="repsPerSet" name="repsPerSet" value="${param.repsPerSet}"  /><br/>
    
      <label for="regDt">날짜: </label>
  <input type="date" id="regDt" name="regDt" value="${param.regDt}" required /><br/><br/>
    
    <button type="button" id="saveBtn">등록</button>
    
</form>

<script>
function goSearchExercise() {
	const regDt = $('#regDt').val();
    
    const url = "/ehr/exercise/doRetrieve.do?mode=select&returnUrl=exerciseDiary/doForm.do"
    	+ "&regDt=" + encodeURIComponent(regDt);

    window.location.href = url;
}

$('#saveBtn').click(function() {
  const formData = $('#exerciseDiaryForm').serialize();

  $.post('/ehr/exerciseDiary/doSave.do', formData, function(response) {
      const res = JSON.parse(response);
      alert(res.message);
      if (res.messageId === 1) {
        location.href = "/ehr/exerciseDiary/doRetrieve.do?regDt=" + $('#regDt').val();
      }
    });
});
</script>
</body>
</html>