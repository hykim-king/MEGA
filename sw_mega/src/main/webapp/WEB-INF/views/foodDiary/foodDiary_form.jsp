<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${mode == 'edit' ? '🍽️ 음식 일지 수정' : '🍽️ 음식 일지 등록'}</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<h2>${mode == 'edit' ? '🍽️ 음식 일지 수정' : '🍽️ 음식 일지 등록'}</h2>

<!-- 등록/수정 폼 -->
<form id="foodForm">
    <c:if test="${mode == 'edit'}">
        <input type="hidden" id="fdCode" name="fdCode" value="${outVO.fdCode}" />
    </c:if>

    <input type="hidden" id="userId" name="userId" value="${outVO.userId}" />

    <label for="foodName">음식명: </label>
    <input type="text" id="foodName" name="foodName" value="${outVO.foodName}" required /><br/>

    <label for="grams">섭취 그람(g): </label>
    <input type="number" id="grams" name="grams" value="${outVO.grams}" required /><br/>

    <label for="mealType">식사시간: </label>
    <select id="mealType" name="mealType" required>
        <option value="아침" ${outVO.mealType == '아침' ? 'selected' : ''}>아침</option>
        <option value="점심" ${outVO.mealType == '점심' ? 'selected' : ''}>점심</option>
        <option value="저녁" ${outVO.mealType == '저녁' ? 'selected' : ''}>저녁</option>
    </select><br/>

    <label for="regDt">날짜: </label>
    <input type="date" id="regDt" name="regDt" value="${outVO.regDt}" required /><br/><br/>

    <!-- 저장 버튼 -->
    <button type="button" id="saveBtn">${mode == 'edit' ? '수정' : '등록'}</button>
</form>

<!-- AJAX 스크립트 -->
<script>
  $('#saveBtn').click(function() {
    const formData = {
      fdCode: $('#fdCode').val(),
      userId: $('#userId').val(),
      foodName: $('#foodName').val(),
      grams: $('#grams').val(),
      mealType: $('#mealType').val(),
      regDt: $('#regDt').val()
    };

    // 등록/수정 URL 결정
    let url = '/ehr/foodDiary/doSave.do';
    <c:if test="${mode == 'edit'}">
      url = '/ehr/foodDiary/doUpdate.do';
    </c:if>

    $.ajax({
      url: url,
      type: 'POST',
      data: formData,
      success: function(response) {
        const res = JSON.parse(response);
        alert(res.message);
        if(res.messageId === 1){
          window.location.href = '/ehr/foodDiary/doRetrieve.do?userId=' + formData.userId + '&regDt=' + formData.regDt;
        }
      },
      error: function(xhr, status, error) {
        alert('저장 중 오류 발생: ' + error);
      }
    });
  });
</script>

</body>
</html>