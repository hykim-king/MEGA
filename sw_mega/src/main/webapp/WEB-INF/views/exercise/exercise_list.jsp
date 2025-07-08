<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헬메이트</title>
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
          <li><a href="#">운동 일지</a></li>
          <li><a href="/ehr/exercise/doRetrieve.do">운동 조회</a></li>
        </ul>
      </li>
      <li class="has-submenu">
        <a href="#">음식</a>
        <ul class="submenu">
          <li><a href="/ehr/foodDiary/doRetrieve.do?userId=user01">음식 일지</a></li>
          <li><a href="/ehr/food/doRetrieve.do?userId=user01">음식 조회</a></li>
          <li><a href="/ehr/food/doForm.do?userId=user01">음식 추가</a></li>
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
<div>
    <form action="/ehr/exercise/doRetrieve.do"  method="get">
        <input type="text" name="searchWord" id="searchWord"  size="15" placeholder="검색어를 입력하세요">
        <select name="searchDiv" id="searchDiv">
            <option value="10">운동명</option>
            <option value="20">운동 부위</option>
        </select>
        <select name="pageSize" id="pageSize">
            <option value="10">10개씩</option>
            <option value="20">20개씩</option>
        </select>
        <input type="submit" value="조회">
    </form>
</div>

<div class="scroll-box">
<c:forEach var="item" items="${list}">
  <div class="food-item">
    <!-- 운동 이름만 보이게 -->
    <div class="exercise-name" onclick="toggleDetail(this)"> 
      <p>${item.exerciseName}</p>
      <p>${item.gender}</p>
      </div>

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
        <input type="hidden" name="userId" value="${param.userId}" />
        <input type="hidden" name="regDt" value="${param.regDt}" />
    
        <label>섭취 그람수:</label>
        <input type="number" name="grams" min="1" required />
        <input type="submit" value="섭취 조회">
    </form>
      
    </div>
  </div>
</c:forEach>
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