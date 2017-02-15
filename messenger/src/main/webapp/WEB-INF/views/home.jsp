<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
<style type="text/css">
#find-friends {
	border-color: green;
	border-style: solid;
	width: 500px;
	height: 650px;
}

#login-form {
	border-color: black;
	border-style: solid;
	width: 500px;
	height: 50px;
}

#friends-list {
	border-color: red;
	border-style: solid;
	width: 300px;
	height: 350px;
}
</style>
<script type="text/javascript">
	function findFriends() {
		var user_id = $('#f-name').val();
		var url = 'find_friends?user_id=' + user_id;
		$.ajax({
			url : url,
			type : 'get',
			dataType : 'json',
			success : function(data) {
				$.each(data, function(index, value) {
					$('#find-friends').append(
							$('<a>' + value.user_id
									+ '</a><button class="f-request-btn">'
									+ "친구요청" + '</button><br>'));
				})
			}
		})
	}
	$(function() {
		$('#find-friends').on('click', '.f-request-btn', function() {
			var user_id = $(this).prev().html();
			$(location).attr('href', 'f-request?user_id=' + user_id);
		})
	})
</script>
<title>Home</title>
</head>
<body>
	<div id="login-form">
		<c:choose>
			<c:when test="${user.user_id == null }">
				<form action="login" method="post">
					<input type="text" name="user_id"> <input type="password"
						name="user_pw">
					<button type="submit">로그인</button>
				</form>
			</c:when>
			<c:otherwise>
				${user.user_id } hello
			</c:otherwise>
		</c:choose>
	</div>
	<a href="signUp">회원가입</a>
	<a href="chat">채팅방 입장</a>
	<button type="button">친구 찾기</button>
	<input type="text" name="user_id" id="f-name">
	<button type="button" onclick="findFriends()">찾기</button>
	<div id="find-friends"></div>
	<div id="friends-list">
		<ul>
			<c:forEach var="list" items="${list }">
				<li>${list.user_id}</li>
			</c:forEach>
		</ul>
	</div>
	<div id="add-request">
		<c:forEach var="list" items="${list }">
			<li>${list.user_id}</li>
		</c:forEach>
	</div>
</body>
</html>