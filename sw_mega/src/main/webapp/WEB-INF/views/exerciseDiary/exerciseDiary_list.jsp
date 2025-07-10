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
<link rel="stylesheet" href="/ehr/resources/assets/css/food_exercise_list.css">
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

<h2>운동 일지</h2>


<!-- 날짜 선택 폼 시작 -->
<form method="get" action="/ehr/exerciseDiary/doRetrieve.do">
    <label for="regDt">날짜 선택: </label>
    <input type="date" id="regDt" name="regDt" value="${regDt}" required />
    <button type="submit">조회</button>
</form>
<!-- 날짜 선택 폼 끝 -->

<!-- 운동 일지 추가 버튼 -->
<div style="margin-top: 10px; text-align: right;">
    <a href="/ehr/exerciseDiary/doForm.do?regDt=${regDt}">➕ 운동 일지 추가</a>
</div>

<c:forEach var="meal" items="${exerciseType}">
    <h3>
        <c:choose>
            <c:when test="${meal == '유산소'}">${meal}</c:when>
            <c:otherwise>${meal}</c:otherwise>
        </c:choose>
    </h3>

    <c:set var="hasData" value="false" />

    <c:forEach var="item" items="${list}">
        <c:if test="${item.exerciseType == meal}">
            <c:set var="hasData" value="true" />
             <div class="exercise-card">
            <p>${item.exerciseName}</p>
            <p>운동 타입: ${item.exerciseType}</p>
            
	        <!-- 유산소 -->
	        <c:if test="${item.exerciseType eq '유산소'}">
	            <p>기준 체중: ${item.weight}kg</p>
	            <p>성별: ${item.gender}</p>
	            <p>운동 시간: ${item.duration} 분</p>
	            <p>총 소모 칼로리: ${item.totalCalories} kcal</p>
	        </c:if>
	
	        <!-- 근력 -->
	        <c:if test="${item.exerciseType eq '근력'}">
	            <p>운동 부위: ${item.region}</p>
	            <p>세트 수: ${item.setCount}</p>
	            <p>세트당 반복 수: ${item.repsPerSet}</p>
	            <p>덤벨 무게: ${item.strenthWeight} kg</p>
	            <p>운동 시간: ${item.duration} 분</p>
	            <p>총 소모 칼로리: ${item.totalCalories} kcal</p>
	        </c:if>
	        
            <!-- 수정 버튼: doSelectOne.do 호출 후 수정 페이지로 이동 -->
            <a href="/ehr/exerciseDiary/selectOneWithJoin.do?edCode=${item.edCode}&eCode=${item.eCode}&regDt=${item.regDt}">수정</a>
            <button onclick="deleteDiary('${item.edCode}', '${item.regDt}')">삭제</button>
           </div>
        </c:if>
    </c:forEach>

    <c:if test="${!hasData}">
        <p>등록된 운동 일지가 없습니다.</p>
    </c:if>
</c:forEach>

<c:if test="${not empty vo}">
 <div class="exercise-card">
    <h3>🍽️ 총 소모 칼로리 및 시간 요약</h3>
    <p>
        ${vo.totalDuration} (분) /
        ${vo.totalCalories} kcal 
    </p>
   </div>
</c:if>


<script>
  function deleteDiary(edCode, regDt) {
    if (!confirm('정말 삭제하시겠습니까?')) return;

    $.ajax({
      url: '/ehr/exerciseDiary/doDelete.do',
      type: 'POST',
      data: { edCode:edCode },
      success: function(response) {
        const res = JSON.parse(response);
        alert(res.message);
        if (res.messageId === 1) {
          window.location.href = '/ehr/exerciseDiary/doRetrieve.do?regDt=' + regDt;
        }
      },
      error: function(xhr, status, error) {
        alert('삭제 중 오류 발생: ' + error);
      }
    });
  }
</script>
      </div>
      </main>
      <!--//main end-------------------->

      
 <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
   </div> 
</body>
</html>