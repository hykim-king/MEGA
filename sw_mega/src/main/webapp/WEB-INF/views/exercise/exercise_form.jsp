<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="/ehr/resources/assets/css/food_list.css">
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

<h2>운동 추가</h2>
<br>
<h4>세부 내용:</h4>
  <form id="exerciseForm">
    
    <label for="exerciseName">운동명:</label>
    <input type="text" id="exerciseName" name="exerciseName" required >
    <br/>
	<label for="exerciseType">운동 타입:</label>
	<select id="exerciseType" name="exerciseType" required>
	  <option value="">-- 운동 타입 선택 --</option>
	  <option value="유산소">유산소</option>
	  <option value="근력">근력</option>
	</select>
	
	<label for="gender">성별 (유산소만):</label>
	<select id="gender" name="gender">
	  <option value="">지정 안 함</option>
	  <option value="남성">남성</option>
	  <option value="여성">여성</option>
	</select>
	
	<label for="cardioWeight">기준 체중 (kg, 유산소만):</label>
	<input type="number" id="cardioWeight" name="cardioWeight" placeholder="유산소 운동만 입력" />
	
	<label for="region">운동 부위 (근력만):</label>
	<input type="text" id="region" name="region" placeholder="ex) 하체, 가슴, 등" />
    <br/>
    <label for="calBurn">소모칼로리:</label>
    <input type="number" id="calBurn" name="calBurn" required >
    <br/>
    <button type="button" id="saveBtn">등록</button>
  </form>
  
  <script>
	  $('#saveBtn').click(function() {
		  if ($('#exerciseType').val() === '유산소') {
			  $('#region').val('');
			} else if ($('#exerciseType').val() === '근력') {
			  $('#gender').val('');
			  $('#cardioWeight').val('');
			}
		  
	      const formData = $('#exerciseForm').serialize();

      $.post('/ehr/exercise/doSave.do', formData, function(response) {
          const res = JSON.parse(response);
          alert(res.message);
          if (res.messageId === 1) {
            location.href = "/ehr/exercise/doRetrieve.do";
          }
        });
      });
  
	  $('#exerciseType').on('change', function () {
		  const type = $(this).val();
	
		  if (type === '유산소') {
		    // 유산소일 때 활성화
		    $('#gender').prop('disabled', false).prop('required', true);
		    $('#cardioWeight').prop('disabled', false).prop('required', true);
		    $('#region').val('').prop('disabled', true).prop('required', false);
		  } else if (type === '근력') {
		    // 근력일 때 활성화
		    $('#gender').val('').prop('disabled', true).prop('required', false);
		    $('#cardioWeight').val('').prop('disabled', true).prop('required', false);
		    $('#region').prop('disabled', false).prop('required', true);
		  } else {
		    // 아무 것도 선택되지 않았을 때
		    $('#gender').val('').prop('disabled', true).prop('required', false);
		    $('#cardioWeight').val('').prop('disabled', true).prop('required', false);
		    $('#region').val('').prop('disabled', true).prop('required', false);
		  }
		});
    </script>
</body>
</html>