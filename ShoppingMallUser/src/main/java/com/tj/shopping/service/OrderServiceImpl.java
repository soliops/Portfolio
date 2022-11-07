package com.tj.shopping.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.inicis.std.util.SignatureUtil;
import com.tj.shopping.domain.CartDTO;
import com.tj.shopping.domain.ItemDTO;
import com.tj.shopping.domain.OrderDTO;
import com.tj.shopping.persistence.OrderMapper;

@Service
@Primary
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderMapper orderMapper;
	
	@Override
	public List<CartDTO> getProduct(String product_code,String ship_pay,String product_ea) {
		String[] code = product_code.split(",");
		String[] ship = ship_pay.split(",");
		String[] ea = product_ea.split(",");
		
		List<CartDTO> list = new ArrayList<CartDTO>();
		for(int w=0;w<code.length;w++) {	
			CartDTO dtos= orderMapper.getProduct(code[w]);
			dtos.setProduct_ea(ea[w]);
			dtos.setShip_pay(ship[w]);
			list.add(dtos);
		}
		return list;
	}
	
	@Override
	public Map<String,String> getlist(OrderDTO dto, String orderNumber) throws Exception{
		String[] ea = dto.getProduct_ea().split(",");
		String[] price = dto.getProduct_price().split(",");
		String[] ship = dto.getShip_pay().split(",");
		int sumPrice =0 ;
		int sumShip = 0;
		Map<String,String> list = new HashMap<String,String>();
		list.put("total",dto.getProduct_total());
		list.put("product_nm",dto.getProduct_nm());
		list.put("product_code",dto.getProduct_code());
		list.put("product_ea",dto.getProduct_ea());
		
		for(int e=0;e<ea.length;e++) {
			sumPrice +=  Integer.parseInt(price[e]);
			sumShip +=  Integer.parseInt(ship[e]);
			
		}
		list.put("sumPrice", Integer.toString(sumPrice));
		list.put("sumShip", Integer.toString(sumShip));
		String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey
		String mKey = SignatureUtil.hash(signKey, "SHA-256");
		
		list.put("mKey", mKey);
		String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
		list.put("timestamp", timestamp);
		//이니시스 제공 코드
		Map<String, String> signParam = new HashMap<String, String>();
		list.put("oid", orderNumber);
		signParam.put("oid", orderNumber);
		signParam.put("price", dto.getProduct_total());
		signParam.put("timestamp", timestamp);
		String signature = SignatureUtil.makeSignature(signParam);
		list.put("signature",signature);
		
		return list;
	}

	@Override
	public String orderNumber(String mid) {
		String code = "";
		for(int t=0;t<9;t++) {
			int random = (int) (Math.random()*10);
			code += random;
		}
		return mid+"_"+code;
	}
	
	@Override
	public String getDate() {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter dateFormat= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return datetime.format(dateFormat);
	}

	@Override
	public List<CartDTO> selectCart(String idx) {
		return orderMapper.getList(idx);
	}

	@Override
	public void InsertCart(OrderDTO orderDTO,String check) {
		
		ItemDTO item = orderMapper.getItem(Integer.toString(orderDTO.getProduct_idx())); 
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		int point = (int) (Integer.parseInt(item.getProduct_disprice())*0.01);
		CartDTO cartDTO = new CartDTO();
		cartDTO.setProduct_idx(orderDTO.getProduct_idx());
		cartDTO.setProduct_nm(orderDTO.getProduct_nm());
		cartDTO.setProduct_dtc(orderDTO.getProduct_dtc());
		cartDTO.setProduct_price(orderDTO.getProduct_price());
		cartDTO.setProduct_disprice(item.getProduct_disprice());		
		cartDTO.setProduct_point(Integer.toString(point));
		cartDTO.setProduct_total(orderDTO.getProduct_total());
		cartDTO.setProduct_code(orderDTO.getProduct_code());
		cartDTO.setProduct_ea(orderDTO.getProduct_ea());
		cartDTO.setProduct_stock(item.getProduct_stock());
		cartDTO.setProduct_check(check);
		cartDTO.setProduct_img1(item.getProduct_img1());
		cartDTO.setIndate(datetime.format(dateFormat));
		cartDTO.setId_use("N");
		cartDTO.setShip_pay(orderDTO.getShip_pay());
		
		orderMapper.InsertCart(cartDTO);
	}

}
