<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LocalStorage 저장</title>
</head>
<script>
//브라우저 Storage 작동여부
//console.log(typeof(Storage));

//localStorage = 비휘발성
//sessionStorage = 휘발성

if(typeof(Storage)!="undefined"){
	//setItem ("닉네임","저장값"); 
	var datas = ["hong@nate.com","010-1234-5678"];
	localStorage.setItem("data",JSON.stringify(datas));
	localStorage.setItem("mid","hong_gildong");
	localStorage.setItem("mpw","qwer1234");
	//패스워드같이 중요한 정보는 sessionStorage로 브라우저 닫으면 날아가도록
	sessionStorage.setItem("mname","홍길동");
}
else{
	alert("해당 브라우저는 접근이 되지 않습니다.");
}
</script>
<body>
화면1
<input type="text" name="ids" id="ids" value="">
</body>
</html>
<script>
document.getElementById("ids").value = localStorage.getItem("mid"); 
</script>