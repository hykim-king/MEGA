<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="CP" value="${pageContext.request.contextPath }" />
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd_HH:mm:ss" /></c:set> 

<!DOCTYPE html>
<html>  
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${CP}/resources/assets/css/freeboard_list.css">
<title>게시글 관리</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="/ehr/resources/assets/js/common.js"></script>
 <script>
    //DOMContentLoaded: HTML문서 loading이 완료되면 발생 되는 event
    document.addEventListener('DOMContentLoaded', function(){
        console.log('DOMContentLoaded');
        
        //seq
        const fbCodeInput = document.querySelector("#fbCode");
        console.log(fbCodeInput.value);
        
        //title
        const titleInput = document.querySelector("#title");
        console.log(titleInput);
        
        //contents
        const contentTextarea = document.querySelector("#content");
        console.log(contentTextarea);
        
        //목록
        const moveToListButton = document.querySelector("#moveToList");
        console.log(moveToListButton);
        
        //수정 버튼
        const doUpdateButton = document.querySelector("#doUpdate");
        console.log(doUpdateButton);
        
        //삭제 버튼
        const doDeleteButton = document.querySelector("#doDelete");
        console.log(doDeleteButton);
        
         
        //목록 버튼 click event감지
        moveToListButton.addEventListener('click',function(event){
            
            if(confirm('목록으로 이동 하시겠습니까?') === false)return;
            //목록화면으로 이동
            window.location.href = '/ehr/freeboard/doRetrieve.do?fbcode=+${fbcodeValue}';         
        });
        
        
        //수정 버튼 click event감지
        doUpdateButton.addEventListener('click',function(event){
            console.log('doUpdateButton click');
            
            //제목
            if(isEmpty(titleInput.value) === true){
                 alert('제목을 입력 하세요');
                 titleInput.focus();
                 return;                
            }
            
            //내용
            if(isEmpty(contentTextarea.value) === true){
                 alert('내용을 입력 하세요');
                 contentTextarea.focus();
                 return;                
            }
            
            if(confirm('게시글을 수정 하시겠습니까?') === false)return;
            
            
            $.ajax({
                type:"POST",    //GET/POST
                url:"/ehr/freeboard/doUpdate.do", //서버측 URL
                asyn:"true",    //비동기
                dataType:"html",//서버에서 받을 데이터 타입
                data:{          //파라메터
                    "fbCode": fbCodeInput.value,
                    "title": titleInput.value,
                    "content": contentTextarea.value,
                    "userId":'${vo.userId}'
                },
                success:function(response){//요청 성공
                    console.log("success:"+response)
                    //문자열 : Javascript객체로 변환
                    const message = JSON.parse(response);
                
                    if( 1 === message.messageId){//삭제 성공
                        alert(message.message);

                        //목록화면으로 이동
                        window.location.href = '/ehr/freeboard/doRetrieve.do?div=+${divValue}';
                    }else{
                        alert(message.message);
                    }                     
                    
                },
                error:function(response){//요청 실패
                    console.log("error:"+response)
                }
                
                
            });         
            
            
        });
        
        //삭제 버튼 event감지
        doDeleteButton.addEventListener('click',function(event){
            console.log('doDeleteButton click');
            
            //seq
            if(isEmpty(fbCodeInput.value) === true){
                alert("SEQ를 확인 하세요.");
                return;
            }
            
            if(confirm('게시글을 삭제 하시겠습니까?') === false)return;
            
            $.ajax({
                type:"POST",    //GET/POST
                url:"/ehr/freeboard/doDelete.do", //서버측 URL
                asyn:"true",    //비동기
                dataType:"html",//서버에서 받을 데이터 타입
                data:{          //파라메터
                    "fbCode": fbCodeInput.value,
                    "userId": userIdInput.value
                },
                success:function(response){//요청 성공
                    console.log("success:"+response)
                    //문자열 : Javascript객체로 변환
                    const message = JSON.parse(response);
                
                    if( 1 === message.messageId){//삭제 성공
                        alert(message.message);

                        //목록화면으로 이동
                        window.location.href = '/ehr/freeboard/doRetrieve.do?div=' + '${divValue}';
                     }else{
                        alert(message.message);
                     }                    
                    
                },
                error:function(response){//요청 실패
                    console.log("error:"+response)
                }
                
                
            });         
            
            
        });
        
    });
 
 </script>
</head>
<body>     
    <div class="form-container">

      <h2>자유게시판 - 관리</h2>
       
    <hr class="title-underline">
       
    <!-- Button area -->
    <div  class="button-area">
        
        <input type="button" id="moveToList"   value="목록">
        <input type="button" id="doUpdate"     value="수정">
        <input type="button" id="doDelete"     value="삭제">
      <%--   <input type="hidden" name="noCode" value="${notice.noCode}" /> --%>
        
    </div>
    <!--// Button area -->
    
    <!-- form area -->
    <form action="/ehr/freeboard/doSave.do" method="post" >
        <input type="hidden" name="fbCode" id="fbCode" value="<c:out value='${vo.fbCode }'/>">
        
        <label>글번호</label>
            <input type="text" name="fbCode" value="${freeboard.fbCode}" readonly />   
        
        <div class="form-group">
            <label for="userId">제목</label>
            <input type="text"  maxlength="50" name="title" id="title" value="${vo.title }" >
        </div>
        <div class="form-group">
            <label for="name">조회수</label>
            <input type="text"  maxlength="9" name="viewCount" id="viewCount" disabled value="${vo.viewCount }" >
        </div>         
        <div class="form-group">
            <label for="name">등록일</label>
            <input type="text"   name="cDt" id="cDt" disabled value="${vo.cDt }">
        </div>   
        <div class="form-group">
            <label for="name">등록자</label>
            <input type="text"  maxlength="30" name="userId" id="userId" disabled="disabled" value="${sessionScope.user.userId}" disabled="disabled" >
        </div>                
   
        <div class="form-group">
            <label  for="content">내용</label>
            <textarea class="content" id="content" name="content" placeholder="내용을 입력하세요">${vo.content }</textarea>
        </div>                                
    </form>
    <!--// form area -->
  </div>  
</body>
</html>