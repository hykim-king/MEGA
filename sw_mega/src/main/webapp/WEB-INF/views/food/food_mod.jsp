<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/ehr/resources/assets/css/food_mod.css">
    <title>${vo.foodName} 섭취 정보</title>
</head>
<body>

<div class="summary-box">
    <h2>${vo.foodName} 영양정보</h2>
    <h5>*섭취량 반영된 결과</h5>

    <ul>
        <li><span class="label">기준량:</span> ${vo.stGrams}g</li>
        <li><span class="label">섭취량:</span> ${vo.grams}g</li>
        <li><span class="label">칼로리:</span> ${vo.totalCal} kcal</li>
        <li><span class="label">탄수화물:</span> ${vo.totalCarb} g</li>
        <li><span class="label">단백질:</span> ${vo.totalProt} g</li>
        <li><span class="label">지방:</span> ${vo.totalFat} g</li>
        <li><span class="label">나트륨:</span> ${vo.totalNa} mg</li>
    </ul>
    
    <form action="/ehr/foodDiary/doForm.do" method="get" >
	    <input type="hidden" name="foodName" value="${vo.foodName}" />
	    <input type="hidden" name="grams" value="${param.grams}" />
	    <!-- userId는 세션에서 꺼내거나 이미 바인딩된 값 사용 -->
	    <button type="submit">👉 음식 일지 추가</button>
    </form>

    <div class="btn-back">
        <a href="/ehr/food/doRetrieve.do">← 목록으로 돌아가기</a>
    </div>
</div>


</body>
</html>
