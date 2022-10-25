function login(){
	if(frm.mid.value==""){
		alert("아이디를 입력해 주세요.");
		frm.mid.focus();
	}
	else if(frm.mpassword.value==""){
		alert("비밀번호를 입력해 주세요.");
		frm.mpassword.focus();
	}
	else{
		frm.method="post";
		frm.enctype="application/x-www-form-urlencoded";
		frm.action="./login";
		frm.submit();
	}
}