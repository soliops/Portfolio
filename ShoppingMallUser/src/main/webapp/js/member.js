var check = false;
function email3(find_email){
	var email2 = document.getElementById("memail2");
	var find_email = document.getElementById("find_email");
	var value = (find_email.options[find_email.selectedIndex].value);
	email2.value=value;
}
$(function(){
	$("#search_post").postcodifyPopUp({
		insertPostcode5 : "#mpost",
		insertAddress : "#maddress1",
		hideOldAddresses : false
	});	
	$("#checkId").click(function(){
		var $mid = $("#mid").val();
		var $json = {mid:$mid};
		$.ajax({
			method:"GET",
			url:"./idcheck",
			data:$json,
			cache:false,
			datatype:"json",
			contentType:"application/json; charset=UTF-8",
			success: function(data){ 
				if(data["sign"]==true){
					document.getElementById("id_info").innerText="사용가능한 아이디입니다.";
					check = true;
				}
				else if(data["sign"]==false){
					document.getElementById("id_info").innerText="사용불가능한 아이디입니다.";
				}
			},
			error: function(){
				alert("중복체크 오류");
			}
		});
	});
	
});
function allcheck(){
	var code= /[^a-z|A-Z|0-9]/g;
	var code2= /[^ㄱ-ㅎ가-힣ㅏ-ㅣ]/gi;
	var code3 = /[^a-zA-Z0-9ㄱ-ㅎ가-힣ㅏ-ㅣ]/g;
	var idcheck= frm.mid.value.match(code);
	var passcheck = frm.mpassword.value.match(code3);
	var nameck = /[^a-zA-Z가-힣]/g;
	frm.memail.value = frm.memail1.value+"@"+frm.memail2.value;
	var emailck =/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/g;
	var telck = /[^0-9]/;
	var telck2 = /[^0-9]\d{2,3}/;
	if(idcheck !=null && frm.mid.value.length() != idcheck.length()){
		alert("올바른 아이디를 입력해 주세요.");
		frm.mid.focus();
		return false;
	}
	else if(code2.test(frm.mpassword.value)==false){
		alert("패스워드는 한글을 입력할 수 없습니다.");
		frm.mpassword.focus();
		return false;
	}
	else if(passcheck == null || passcheck.length <2){
		alert("패스워드는 특수문자 2자 이상 포함해야 합니다.");
		frm.mpassword.focus();
		return false;
	}
	else if(frm.mpassword.value != frm.mpassword2.value){
		alert("동일한 패스워드를 입력해 주세요.");
		frm.mpassword.focus();
		return false;
	}
	else if(nameck.test(frm.mname.value)==true){
		alert("이름은 영어, 한글만 입력하셔야 합니다.");
		frm.mname.focus();
		return false;
	}
	else if(emailck.test(frm.memail.value)==false){
		alert(frm.memail.value);
		alert("올바른 이메일 형식을 입력하셔야 합니다.");
		frm.memail1.focus();
		return false;
	}
	else if(telck.test(frm.mtel1.value)==true){
		alert("연락처는 숫자만 입력하셔야 합니다.");
		frm.mtel1.focus();
		return false;
	}
	else if(telck2.test(frm.mtel2.value)==true){
		alert("연락처는 숫자만 입력하셔야 합니다.");
		frm.mtel2.focus();
		return false;
	}
	else if(telck2.test(frm.mtel3.value)==true){
		alert("연락처는 숫자만 입력하셔야 합니다.");
		frm.mtel3.focus();
		return false;
	}
	else{		
	frm.mtel.value = frm.mtel1.value+frm.mtel2.value+frm.mtel3.value;
	return true;
	}
}
function write_fin(){
	if(frm.mid.value==""){
		alert("아이디를 입력해 주세요.");
		frm.mid.focus();
	}
	else if(frm.mpassword.value==""){
		alert("패스워드를 입력해 주세요.");
		frm.mpassword.focus();
	}
	else if(frm.mpassword2.value==""){
		alert("패스워드를 입력해 주세요.");
		frm.mpassword2.focus();
	}
	else if(frm.mname.value==""){
		alert("이름를 입력해 주세요.");
		frm.mname.focus();
	}else if(frm.memail1.value==""){
		alert("이메일을 입력해 주세요.");
		frm.memail1.focus();
	}else if(frm.memail2.value==""){
		alert("나머지 이메일을 입력해 주세요.");
		frm.memail2.focus();
	}else if(frm.mtel1.value==""){
		alert("전화번호를 입력해 주세요.");
		frm.mtel1.focus();
	}
	else if(frm.mtel2.value==""){
		alert("전화번호를 입력해 주세요.");
		frm.mtel2.focus();
	}
	else if(frm.mtel3.value==""){
		alert("전화번호를 입력해 주세요.");
		frm.mtel3.focus();
	}
	else if(frm.mpost.value==""){
		alert("주소 찾기를 진행해 주세요.");
		frm.mpost.focus();
	}
	else if(frm.maddress2.value==""){
		alert("나머지 주소를 입력해 주세요.");
		frm.maddress2.focus();
	}
	else{
		if(check==false){
			alert("중복 체크를 먼저 진행해 주세요.");
			frm.mid.focus();
		}
		else if(allcheck()==false){
			
		}
		else{
		frm.method="post";
		frm.enctype="application/x-www-form-urlencoded";
		frm.action="./member";
		frm.submit();
		}
	}
}