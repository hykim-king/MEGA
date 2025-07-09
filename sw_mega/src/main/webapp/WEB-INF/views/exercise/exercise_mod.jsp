<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="/ehr/resources/assets/css/food_mod.css">
<title>Insert title here</title>
</head>
<body>

<div class="summary-box">
    <h2>${vo.exerciseName} 소모 칼로리 정보</h2>
    <h5>*사용자 운동 정보 반영된 결과</h5>

	<c:if test="${vo.exerciseType eq '유산소'}">
	    <p>운동 타입: ${vo.exerciseType}</p>
	    <p>성별: ${vo.gender}</p>
	    <p>기준 체중: ${vo.weight} kg</p>
	    <p>입력한 시간: ${param.duration} 분</p>
	</c:if>
	<c:if test="${vo.exerciseType eq '근력'}">
	    <p>운동 타입: ${vo.exerciseType}</p>
	    <p>운동 부위: ${vo.region}</p>
	    <p>세트 수: ${param.setCount}</p>
	    <p>세트당 반복 수: ${param.repsPerSet}</p>
	    <p>무게: ${param.strenthWeight} kg</p>
	</c:if>

	<h3>🔥 총 소모 칼로리: ${vo.totalCal} kcal</h3>
    
    <form action="/ehr/exerciseDiary/doForm.do" method="get">
        <input type="hidden" name="exerciseName" value="${vo.exerciseName}" />
        <input type="hidden" name="eCode" value="${vo.eCode}" />
        <input type="hidden" name="duration" value="${param.duration}" />
        <input type="hidden" name="weight" value="${param.weight}" />
        <input type="hidden" name="setCount" value="${param.setCount}" />
        <input type="hidden" name="repsPerSet" value="${param.repsPerSet}" />
         <input type="hidden" name="strenthWeight" value="${param.strenthWeight}" />
        <!-- userId는 세션에서 꺼내거나 이미 바인딩된 값 사용 -->
        <button type="submit">👉 운동 일지 추가</button>
    </form>

    <div class="btn-back">
        <a href="/ehr/exercise/doRetrieve.do">← 목록으로 돌아가기</a>
    </div>
</div>


</body>
</html>