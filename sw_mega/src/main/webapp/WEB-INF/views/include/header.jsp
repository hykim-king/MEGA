<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<!--header-->
<header id="header">
<div class = "header-container">
<div class="navbar">
<div class="menu-bar">
  <div class="navbar-left">
    <div class="logo">🏋️‍♂️ 헬메이트</div>
    <ul class="main-menu">
      <li><a href="/ehr/common/main.do">홈</a></li>
      <li class="has-submenu">
        <a href="/ehr/exercise/doRetrieve.do">운동</a>
        <div class="submenu-box">
        <ul class="submenu">
          <li><a href="/ehr/exerciseDiary/doRetrieve.do">운동 일지</a></li>
          <li><a href="/ehr/exercise/doRetrieve.do">운동 조회</a></li>
          <li><a href="/ehr/exercise/doForm.do">운동 추가</a></li>
        </ul>
        </div>
      </li>
      <li class="has-submenu">
        <a href="/ehr/food/doRetrieve.do">음식</a>
        <div class="submenu-box">
        <ul class="submenu">
          <li><a href="/ehr/foodDiary/doRetrieve.do">음식 일지</a></li>
          <li><a href="/ehr/food/doRetrieve.do">음식 조회</a></li>
          <li><a href="/ehr/food/doForm.do">음식 추가</a></li>
        </ul>
        </div>
      </li>
      <li class="has-submenu">
        <a href="/ehr/notice/doRetrieve.do">커뮤니티</a>
        <div class="submenu-box">
        <ul class="submenu">
          <li><a href="/ehr/notice/doRetrieve.do">공지사항</a></li>
          <li><a href="/ehr/freeboard/doRetrieve.do">자유 게시판</a></li>
        </ul>
        </div>  
      </li>
    </ul>
  </div>

  <div class="navbar-right">
  
  <c:choose>
  <c:when test="${not empty sessionScope.userId}">
    <!-- 로그인 상태 -->
    <span>${sessionScope.userId}님</span>
    <a href="/ehr/login/logout.do">로그아웃</a>
  </c:when>
  <c:otherwise>
    <!-- 비로그인 상태 -->
    <a href="/ehr/login/login.do">로그인</a>
    <a href="/ehr/membership/doSaveView.do">회원가입</a>
  </c:otherwise>
</c:choose>
  </div>
</div>
</div>
</div>
</header>
<!--//header end------------------->    