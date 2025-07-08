<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<title>Insert title here</title>
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
          <li><a href="#">운동 조회</a></li>
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

<h2>음식 추가</h2>
<br>
<h4>세부 내용:</h4>
  <form id="foodForm">
    <input type="hidden" name="userId" value="${param.userId}" />
    
    <label for="foodName">음식명:</label>
    <input type="text" id="foodName" name="foodName" required >
    <br/>
    <label for="stGrams">기준 그람:</label>
    <input type="number" id="stGrams" name="stGrams" required >
    <br/>
    <label for="cal">칼로리:</label>
    <input type="number" id="cal" name="cal" required >
    <br/>
    <label for="carb">탄수화물:</label>
    <input type="number" id="carb" name="carb" required >
    <br/>
    <label for="fat">지방:</label>
    <input type="number" id="fat" name="fat" required >
    <br/>
    <label for="prot">단백질:</label>
    <input type="number" id="prot" name="prot" required >
    <br/>
    <label for="na">나트륨:</label>
    <input type="number" id="na" name="na" required >
    <br/>
    <button type="button" id="saveBtn">등록</button>
  </form>
  
  <script>
  $('#saveBtn').click(function() {
	  const formData = $('#foodForm').serialize();

	  $.post('/ehr/food/doSave.do', formData, function(response) {
	      const res = JSON.parse(response);
	      alert(res.message);
	      if (res.messageId === 1) {
	        location.href = "/ehr/food/doRetrieve.do?userId=" + $('[name=userId]').val();
	      }
	    });
  });
	</script>

</body>
</html>