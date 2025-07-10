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
<link rel="stylesheet" href="/ehr/resources/assets/css/food_exercise_list.css">
<link rel="stylesheet" href="/ehr/resources/assets/css/header.css">
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
		 <br/>
		<label for="gender">성별 (유산소만):</label>
		<select id="gender" name="gender">
		  <option value="">지정 안 함</option>
		  <option value="남성">남성</option>
		  <option value="여성">여성</option>
		</select>
		 <br/>
		<label for="weight">기준 체중 (kg, 유산소만):</label>
		<input type="number" id="weight" name="weight" placeholder="유산소 운동만 입력" />
		 <br/>
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
				  $('#weight').val('');
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
      </div>
      </main>
      <!--//main end-------------------->

      
 <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
   </div> 
</body>
</html>