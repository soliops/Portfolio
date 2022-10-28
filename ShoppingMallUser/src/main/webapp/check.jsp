<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
//getItem : Storage에 있는 키를 호출하면 값을 가져올 수 있습니다.
var mid = localStorage.getItem("mid");
var mpw = localStorage.getItem("mpw");
var mname = sessionStorage.getItem("mname");
// var data = localStorage.getItem("datas");
var datas = JSON.parse(localStorage.getItem("data"));
console.log(mid);
console.log(mpw);
console.log(mname);
console.log(datas);
console.log(datas[0]);
</script>
<body>
화면 2 
</body>
</html>