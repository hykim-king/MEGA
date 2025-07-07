<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>🍽️ 음식 일지</title>
</head>
<body>
<h2>🍽️ 음식 일지</h2>

<c:forEach var="meal" items="${mealList}">
    <h3>
        <c:choose>
            <c:when test="${meal == '아침'}">${meal}</c:when>
            <c:when test="${meal == '점심'}">${meal}</c:when>
            <c:otherwise>${meal}</c:otherwise>
        </c:choose>
    </h3>

    <c:set var="hasData" value="false" />

    <c:forEach var="item" items="${list}">
        <c:if test="${item.mealType == meal}">
            <c:set var="hasData" value="true" />
            <p>${item.foodName} / ${item.grams}g</p>
            <p>
                ${item.totalCal} kcal /
                ${item.totalCarb} g /
                ${item.totalFat} g /
                ${item.totalProt} g /
                ${item.totalNa} mg
            </p>
        </c:if>
    </c:forEach>

    <c:if test="${!hasData}">
        <p>등록된 음식 일지가 없습니다.</p>
    </c:if>
</c:forEach>
</body>
</html>