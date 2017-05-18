<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<script src='<spring:url value="resources/js/jquery-2.2.1.min.js" />'></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Encrypt</title>
<style>
input[type="text"],input[type="password"],textarea{
	width:300px;
}

textarea{
	height:100px;
	resize:none;
	background:#EBEBE4;
}
</style>
</head>
<body>
Encrypt:<br>
<input type="password"/><button>Encrypt</button><input type="checkbox" checked="checked"/>Show text?<br><br>

<textarea readonly="readonly"></textarea>
<script type="text/javascript">
	$('input[type="checkbox"]').change(function(e){
		var type = $('input[type=text],input[type=password]').prop('type');
		$('input[type=text],input[type=password]').prop('type',type == 'text' ? 'password' : 'text')
	});
	
	$('button').click(function(e){
		$.post("",{_csrf:"${_csrf.token}",toEncrypt:$('input[type=text],input[type=password]').val()},function(encrypted){
			$('textarea').val(encrypted);
		});
	});
</script>
</body>
</html>