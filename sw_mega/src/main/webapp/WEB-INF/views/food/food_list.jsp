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
<link rel="stylesheet" href="/ehr/resources/assets/css/mypage_search.css">
<link rel="stylesheet" href="/ehr/resources/assets/css/pcwk_main.css">
<link rel="stylesheet" href="/ehr/resources/assets/css/food_exercise_list.css">
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
      </div>
      </main>
      <!--//main end-------------------->

      
 <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
   </div> 
</body>
</html>