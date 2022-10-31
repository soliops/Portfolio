<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Storage 삭제</title>
</head>
<script>
//1개씩 삭제
var data =localStorage.removeItem("mpw");
var data2 = localStorage.getItem("mid");
console.log(data);
console.log(data2);

//모든 Storage 전부 삭제하고 싶을때 사용
//localStorage.clear();
</script>
<body>

</body>
</html>