<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
<style type="text/css">
#find-friends{
	border-color: green;
	border-style: solid;
	width: 500px;
	height: 650px;
}
</style>
<script type="text/javascript">
function findFriends(){
	var user_id = $('#f-name').val();
	var url = 'find_friends?user_id='+user_id;
	$.ajax({
		url : url,
		type : 'get',
		dataType : 'json',
		success : function(data){
			$.each(data,function(index,value){
				$('#find-friends').append($('<a>'+ value.user_id +'</a><button class="f-request-btn">'+"친구요청"+'</button><br>'));
			})
		}
	})
}
$(function(){
	$('#find-friends').on('click','.f-request-btn',function(){
		console.log('aaa');
	})
})
</script>
<title>Home</title>
</head>
<body>
	<a href="signUp">회원가입</a>
	<a href="chat">채팅방 입장</a>
	<button type="button">친구 찾기</button>
	<input type="text" name="user_id" id="f-name">
	<button type="button" onclick="findFriends()">찾기</button>
	<div id="find-friends"></div>
</body>
</html>