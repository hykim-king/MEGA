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
      <li><a href="/ehr/main.do">홈</a></li>
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

  <c:choose>
  <c:when test="${not empty sessionScope.userId}">
    <!-- 로그인 상태 -->
    <span>${sessionScope.userId}님</span>
    <a href="/ehr/logout.do">로그아웃</a>
  </c:when>
  <c:otherwise>
    <!-- 비로그인 상태 -->
    <a href="/ehr/login/login.do">로그인</a>
    <a href="/ehr/membership/doSaveView.do">회원가입</a>
  </c:otherwise>
</c:choose>
  </div>
</div>


<!-- 🔍 검색 영역 -->
<div class="search-bar">
    <form action="/ehr/food/doRetrieve.do"  method="get" class="search-form">
        <div class="select-wrapper with-divider">
        <select name="pageSize" id="pageSize" class="search-select">
            <option value="10">10개씩</option>
            <option value="20">20개씩</option>
        </select>
           <span class="divider">|</span>
        </div>
            <button type="submit" class="search-button">➔</button>
        <input type="text" name="searchWord" id="searchWord"  placeholder="검색어를 입력하세요" class="search-input">
    </form>
</div>

<div class="scroll-box">
<c:forEach var="item" items="${list}">
  <div class="food-item">
    <!-- 음식 이름만 보이게 -->
    <div class="food-name" onclick="toggleDetail(this)"> ${item.foodName}</div>

        <!-- 상세 정보: 기본 숨김 -->
    <div class="food-detail" style="display: none;">
      <ul>
        <li>칼로리: ${item.cal} kcal</li>
        <li>탄수화물: ${item.carb} g</li>
        <li>단백질: ${item.prot} g</li>
        <li>지방: ${item.fat} g</li>
        <li>나트륨: ${item.na} mg</li>
        <li>기준 그람: ${item.stGrams}g</li>
      </ul>
            <!-- 👇 섭취 그람 수 입력 후 조회 -->
	<form action="/ehr/food/doSelectOne.do" method="get">
	    <input type="hidden" name="foodName" value="${item.foodName}" />
	    <input type="hidden" name="regDt" value="${param.regDt}" />
	
	    <label>섭취 그람수:</label>
	    <input type="number" name="grams" min="1" required />
	    <input type="submit" value="섭취 조회">
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
          <a href="/ehr/food/doRetrieve.do?pageNo=${i}&pageSize=${pageSize}&searchWord=${param.searchWord}">[${i}]</a>
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