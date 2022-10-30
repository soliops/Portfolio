package com.tj.shopping.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tj.shopping.domain.CartDTO;
import com.tj.shopping.domain.ItemDTO;
import com.tj.shopping.persistence.CartMapper;

@Primary
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartMapper cartMapper;
	
	@Override
	public void deleteCart(String pidx) {
		cartMapper.deleteCart(pidx);
	}

	@Override
	public CartDTO getItem(String pidx) {
		ItemDTO item = cartMapper.getItem(pidx); 
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		int point = (int) (Integer.parseInt(item.getProduct_disprice())*0.01);
		CartDTO cartDTO = new CartDTO(); 
		cartDTO.setProduct_idx(item.getPidx());
		cartDTO.setProduct_nm(item.getProduct_name());
		cartDTO.setProduct_dtc(item.getProduct_addexplain());
		cartDTO.setProduct_price(item.getProduct_price());
		cartDTO.setProduct_disprice(item.getProduct_disprice());		
		cartDTO.setProduct_point(Integer.toString(point));
		cartDTO.setProduct_total(item.getProduct_disprice());
		cartDTO.setProduct_code(item.getProduct_code());
		cartDTO.setProduct_ea("1");
		cartDTO.setProduct_stock(item.getProduct_stock());
		cartDTO.setProduct_check("Y");
		cartDTO.setProduct_img1(item.getProduct_img1());
		cartDTO.setIndate(datetime.format(dateFormat));
		cartDTO.setId_use("N");
		cartDTO.setShip_pay("0");
		return cartDTO;
	}
	@Override
	public void InsertCart(CartDTO cartDTO) {
		cartMapper.InsertCart(cartDTO);
	}

	@Override
	public List<CartDTO> getCartList() {
		return cartMapper.getCartList();
	}
}
