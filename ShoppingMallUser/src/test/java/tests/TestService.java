package tests;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tj.shopping.domain.CSDTO;
import com.tj.shopping.domain.CartDTO;
import com.tj.shopping.domain.InfoDTO;
import com.tj.shopping.domain.ItemDTO;
import com.tj.shopping.domain.LoginDTO;
import com.tj.shopping.domain.MemberDTO;
import com.tj.shopping.domain.NoticeDTO;
import com.tj.shopping.domain.OrderDTO;
import com.tj.shopping.service.CSService;
import com.tj.shopping.service.CartService;
import com.tj.shopping.service.InfoService;
import com.tj.shopping.service.ItemService;
import com.tj.shopping.service.LoginService;
import com.tj.shopping.service.MemberService;
import com.tj.shopping.service.NoticeService;
import com.tj.shopping.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class TestService {
	
	@Autowired
	private DataSource dataSource;

//	@Test
	public void TestSetConnection() throws Exception {
		try (Connection con = dataSource.getConnection()) {
			System.out.println(con);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
//	@Test
	public void testFactory() {
		System.out.println(sqlSessionFactory);
	}
	
//	@Test
	public void testSession() throws Exception {
		try(SqlSession session = sqlSessionFactory.openSession()){
			System.out.println(session);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Autowired
	private MemberService memberService;
	
//	@Test
	public void testInsertMember()throws Exception{
		MemberDTO memberDTO = new MemberDTO();
		String password = "test1234!@";
		password = memberService.Hashing(password);
		memberDTO.setMid("test");
		memberDTO.setMpassword(password);
        memberDTO.setMname("테스트");
        memberDTO.setMemail("test@naver.com");
        memberDTO.setMtel("01012341234");
        memberDTO.setMpost("12345");
        memberDTO.setMaddress1("경기도 군포시 번영로 123");
        memberDTO.setMaddress2("1층");
        memberService.createMember(memberDTO);
        System.out.println(memberDTO);
	}
	
//	@Test
	public void testMemberCheck() throws Exception{
		String mid = "test";
		Boolean check = memberService.selectId(mid)!=null ? false : true;	
		System.out.println(check);
	}
	@Autowired
	private LoginService loginService;
	
//	@Test
	public void testLoginService() throws Exception{
		LoginDTO dto = new LoginDTO();
		dto.setMid("test");
		dto.setMpassword("test1234!@");
		LoginDTO check = loginService.getId(dto);
		System.out.println(check);
		String id = check.getMid();
		loginService.insertHistory(id);
	}
	
	@Autowired
	private ItemService itemService;
	
//	@Test
	public void testItemService() {
		ItemDTO list = itemService.getProduct("뉴캐슬 Navy - 8D 서랍장");
		System.out.println(list);
		ItemDTO list2 = itemService.getItem(1);
		System.out.println(list2);
	}
	
//	@Test
	public void testIndex() {
		List<ItemDTO> item = itemService.getList("1");
		System.out.println(item);
		List<ItemDTO> newItem = itemService.getNewList();
		System.out.println(newItem);
		List<ItemDTO> bestItem = itemService.getBestList();
		System.out.println(bestItem);
	}
	
	@Autowired
	private NoticeService noticeService;
	
//	@Test
	public void testNoticeService() {
		Integer total = noticeService.getCount();
		System.out.println(total);
		int pageview = 10;
		String pgno = "1";
		int startpage=((Integer.parseInt(pgno)-1))*pageview;
		double pagenumber = total%pageview==0 ? total/pageview : (total/pageview)+1;
		List<NoticeDTO> list = noticeService.getSearchList("테스트");
		System.out.println(list);
		List<NoticeDTO> searchList = noticeService.getNoticeList("N", "테스트", startpage, pageview);
		System.out.println(searchList);
	} 
	@Autowired
	private CSService csService;
	
//	@Test
	public void testCSService() {
		List<CSDTO> list = csService.getList();
		System.out.println(list);
		List<CSDTO> selectList = csService.selectList("1");
		System.out.println(selectList);
	}
	
	@Autowired
	private InfoService infoService;
	
//	@Test
	public void testInfoService() {
		InfoDTO dto = infoService.getInfo();
		System.out.println(dto);
	}
	
	@Autowired
	private CartService cartService;
	
//	@Test
	public void testCartService() {
		List<CartDTO> list = cartService.getCartList();
		System.out.println(list);
//		cartService.deleteCart("2");
		CartDTO dto = cartService.selectCart("3");
		System.out.println(dto);
		List<CartDTO> check = cartService.getCart("3");
		System.out.println(check);
		CartDTO cart = cartService.getItem("1");
		System.out.println(cart);
//		cartService.InsertCart(cart);
	}
	
	@Autowired
	OrderService orderService;
	
//	@Test
	public void testOrderService() throws Exception{
		List<CartDTO> select  = orderService.selectCart("3");
		System.out.println(select);
		OrderDTO dto = new OrderDTO();
		dto.setProduct_code("3");
		dto.setProduct_nm("코티지 스타일 White-6-Drawer");
		dto.setProduct_dtc("코티지 스타일로 깨끗한 화이트 색상");
		dto.setProduct_price("1920000");
		dto.setProduct_point("12288");
		dto.setProduct_total("1228800");
		dto.setProduct_code("000003");
		dto.setProduct_ea("1");
		dto.setShip_pay("0");
		System.out.println(dto);
//		orderService.InsertCart(dto,"Y");
		List<CartDTO> cart = orderService.getProduct(dto.getProduct_code(), dto.getShip_pay(), dto.getProduct_ea());
		System.out.println(cart);
		String orderNumber = orderService.orderNumber("INIpayTest");
		System.out.println(orderNumber);
		Map<String,String> list = orderService.getlist(dto,orderNumber);
		System.out.println(list);
		String regDate=orderService.getDate();
		System.out.println(regDate);
		String modDate=orderService.getDate();
		System.out.println(modDate);
	}
}
