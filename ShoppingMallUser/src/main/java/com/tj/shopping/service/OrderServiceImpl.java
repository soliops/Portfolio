package com.tj.shopping.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tj.shopping.domain.ItemDTO;
import com.tj.shopping.persistence.OrderMapper;

@Service
@Primary
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderMapper orderMapper;
	
	@Override
	public ItemDTO getProduct(String product_code) {
		return orderMapper.getProduct(product_code);
	}
	
	@Override
	public String getNumber() {
		String number ="";
		for(int w=0;w<9;w++) {
			int random = (int)(Math.random()*10);
			number+=random;
		}
		return number;
	}

	@Override
	public String getDate() {
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter dateFormat= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return datetime.format(dateFormat);
	}

}
