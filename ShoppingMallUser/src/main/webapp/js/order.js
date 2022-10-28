function check(){
if(orderFrm.cname.value==""){
	alert("주문자 이름을 입력해 주세요.");
	orderFrm.cname.focus();
}
else if(orderFrm.chp1.value==""){
	alert("휴대폰 번호를 입력해 주세요.");
	orderFrm.chp1.focus();
}
else if(orderFrm.chp2.value==""){
	alert("휴대폰 번호를 입력해 주세요.");
	orderFrm.chp2.focus();
}
else if(orderFrm.chp3.value==""){
	alert("휴대폰 번호를 입력해 주세요.");
	orderFrm.chp3.focus();
}	
else if(orderFrm.cemail.value==""){
	alert("주문자 이메일을 입력해 주세요.");
	orderFrm.cemail.focus();
}
else if(orderFrm.person_nm.value==""){
	alert("받으실 분의 이름을 입력해 주세요.");
	orderFrm.person_nm.focus();
}
else if(orderFrm.person_post.value==""){
	alert("우편번호 검색을 진행해 주세요.");
	orderFrm.person_post.focus();
}
else if(orderFrm.person_addrdtc.value==""){
	alert("상세 주소를 입력해 주세요.");
	orderFrm.person_addrdtc.focus();
}
else if(orderFrm.person_hp1.value==""){
	alert("받으실 분의 휴대폰 번호를 입력해 주세요.");
	orderFrm.person_hp1.focus();
}
else if(orderFrm.person_hp2.value==""){
	alert("받으실 분의 휴대폰 번호를 입력해 주세요.");
	orderFrm.person_hp2.focus();
}
else if(orderFrm.person_hp3.value==""){
	alert("받으실 분의 휴대폰 번호를 입력해 주세요.");
	orderFrm.person_hp3.focus();
}
else if(orderFrm.all_agree.checked==false){
	alert("약관 동의를 해주셔야만 주문이 가능합니다.");
	orderFrm.all_agree.focus();
}
orderFrm.method="POST";
		orderFrm.enctype="application/x-www-form-urlencoded";
		orderFrm.action="./complet";
		orderFrm.submit();
}