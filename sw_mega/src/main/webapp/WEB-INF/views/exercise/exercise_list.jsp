<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/ehr/resources/assets/css/food_list.css">
<link rel="stylesheet" href="/ehr/resources/assets/css/mypage_search.css">
<title>헬메이트</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

<div class="navbar">
  <div class="navbar-left">
    <div class="logo">🏋️‍♂️ 헬메이트</div>
    <ul class="main-menu">
      <li><a href="#">홈</a></li>
      <li class="has-submenu">
        <a href="#">운동</a>
        <ul class="submenu">
          <li><a href="/ehr/exerciseDiary/doRetrieve.do">운동 일지</a></li>
          <li><a href="/ehr/exercise/doRetrieve.do">운동 조회</a></li>
          <li><a href="/ehr/exercise/doForm.do">운동 추가</a></li>
        </ul>
      </li>
      <li class="has-submenu">
        <a href="#">음식</a>
        <ul class="submenu">
          <li><a href="/ehr/foodDiary/doRetrieve.do">음식 일지</a></li>
          <li><a href="/ehr/food/doRetrieve.do">음식 조회</a></li>
          <li><a href="/ehr/food/doForm.do">음식 추가</a></li>
        </ul>
      </li>
      <li><a href="#">커뮤니티</a></li>
    </ul>
  </div>

  <div class="navbar-right">
    <span>🔔</span>
    <div class="circle"></div>
    <span>로그인</span>
  </div>
</div>

<!-- 🔍 검색 영역 -->
<div class="search-bar">
    <form action="/ehr/exercise/doRetrieve.do"  method="get" class="search-form">
        
         <!-- 첫 셀렉트 + divider -->
         <div class="select-wrapper with-divider">
        <select name="searchDiv" id="searchDiv" class="search-select">
            <option value="10">운동명</option>
            <option value="20">운동 부위</option>
        </select>
         <span class="divider">|</span>
         </div>
        
        <!-- 검색어 입력 -->
        <input type="text" name="searchWord" id="searchWord"  placeholder="검색어를 입력하세요" class="search-input">
        
        <!-- 개수 셀렉트 -->
        <div class="select-wrapper">
        <select name="pageSize" id="pageSize" class="search-select">
            <option value="10">10개씩</option>
            <option value="20">20개씩</option>
        </select>
          <span class="select-arrow">▼</span>
        </div>
            <button type="submit" class="search-button">➔</button>
    </form>
</div>

<div class="scroll-box">
<c:forEach var="item" items="${list}">
  <div class="food-item">
  
    <!-- 👇 운동 코드 숨김 (hidden input) -->
    <input type="hidden" class="eCode" value="${item.eCode}" />
    
    <!-- 보이는 부분 -->
    <div class=food-name onclick="toggleDetail(this)"> 
      ${item.exerciseName} ${item.gender} ${item.weight}(kg) ${item.region} 
      </div>

        <!-- 상세 정보: 기본 숨김 -->
    <div class="food-detail" style="display: none;">
      <ul>
        <li>운동타입: ${item.exerciseType} </li>
        <li>기준체중: ${item.weight} kg</li>
        <li>소모칼로리: ${item.calBurn} kal</li>
      </ul>
            <!-- 👇 운동 내역 입력 후 소모 칼로리 조회 -->
	<form action="/ehr/exercise/doSelectOne.do" method="get">
	    <input type="hidden" name="eCode" value="${item.eCode}" />
        <input type="hidden" name="regDt" value="${param.regDt}" />	
	
	    <c:choose>
	        <c:when test="${item.exerciseType eq '유산소'}">
	            <label>운동 시간 (분):</label>
	            <input type="number" name="duration" required />
	        </c:when>
	        <c:otherwise>
	            <label>세트 수:</label>
	            <input type="number" name="setCount" required />
	            <label>세트당 횟수:</label>
	            <input type="number" name="repsPerSet" required />
	            <label>무게 (kg):</label>
	            <input type="number" name="strenthWeight" required />
	        </c:otherwise>
	    </c:choose>
	
	    <button type="submit">상세 조회</button>
	</form>
      
    </div>
  </div>
</c:forEach>
</div>
<!-- 📄 페이징 영역 -->
<div style="text-align:center; margin-top: 20px;">
  <c:if test="${totalCnt > 0}">
    <c:set var="totalPages" value="${(totalCnt / pageSize) + (totalCnt % pageSize > 0 ? 1 : 0)}" />
    <c:forEach begin="1" end="${totalPages}" var="i">
      <c:choose>
        <c:when test="${i == pageNo}">
          <strong>[${i}]</strong>
        </c:when>
        <c:otherwise>
          <a href="/ehr/exercise/doRetrieve.do?pageNo=${i}&pageSize=${pageSize}&searchDiv=${param.searchDiv}&searchWord=${param.searchWord}">[${i}]</a>
        </c:otherwise>
      </c:choose>
    </c:forEach>
  </c:if>
</div>

<script>
function toggleDetail(element) {
  const detail = element.nextElementSibling;
  if (detail.style.display === 'none') {
    detail.style.display = 'block';
  } else {
    detail.style.display = 'none';
  }
}
</script>
</body>
</html>