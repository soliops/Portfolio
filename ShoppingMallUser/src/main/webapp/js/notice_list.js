function search_reset(){
	location.href="./notice?page=1";
}
function search(){
	if(frm.category.value=="긴급공지"){
		frm.p_check.value="Y";
		}
	frm.method="get";
//	frm.enctype="application/x-www-form-urlencoded";
	frm.action="./notice";
	frm.submit();
}