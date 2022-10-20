package com.tj.shopping.user.cs;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
public class CSController {
	
	@Autowired
	private CSService csService;
	
	@RequestMapping(value="cs",method=RequestMethod.GET)
	public String CSPage(
//			CSDTO dto,
			@RequestParam(name="cate",defaultValue = "") String cate,
			Model model
			) {		 
		List<CSDTO> list =null;
		System.out.println(cate);
		if(cate.equals("") || cate==null) {
			list = csService.getList();
		
		}else {
			list = csService.selectList(cate);
		}
			System.out.println(list);
			model.addAttribute("list",list);
		return "user/cs/cs";
	}
//	public String page(HttpServletRequest req, Model m) throws Exception {
//		String cate = req.getParameter("cate");
//		Connection ct = dataSource.getConnection();
//		String sql = null;
//		if(cate==null||cate=="") {
//			sql = "select * from faq_list where f_check='Y' limit 0,5";			
//		}
//		else {
//			sql = "select * from faq_list where f_check='Y' and fcategory='" + cate + "'";
//		}
//		PreparedStatement ps = ct.prepareStatement(sql);
//		ResultSet rs = ps.executeQuery();
//		ArrayList<Map<String,String>> data = new ArrayList<Map<String,String>>();
//		while (rs.next()) {
//			Map<String,String> mp = new HashMap<String,String>();
//			mp.put("fidx", rs.getString("fidx"));
//			String msg="";
//			switch(rs.getString("fcategory")) {
//			case "1":
//				msg="배송문의";
//				break;
//			case "2":
//				msg="반품/교환문의";
//				break;
//			case "3":
//				msg="상품문의";
//				break;
//			case "4":
//				msg="쿠폰내역";
//				break;
//			case "5":
//				msg="마일리지내역";
//				break;
//			default:
//				msg="기타문의";
//				break;
//			}
//			mp.put("fcategory", msg);
//			mp.put("fname", rs.getString("fname"));
//			mp.put("f_qtext", rs.getString("f_qtext"));
//			mp.put("f_atext", rs.getString("f_atext"));
//			data.add(mp);		
//		}
//		m.addAttribute("data",data);
//		ct.close();
//		ps.close();
//		return "cs";
//	}
}