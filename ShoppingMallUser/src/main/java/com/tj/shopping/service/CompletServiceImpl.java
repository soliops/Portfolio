package com.tj.shopping.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tj.shopping.domain.CompletDTO;
import com.tj.shopping.domain.ItemDTO;
import com.tj.shopping.persistence.CompletMapper;

@Service
@Primary
public class CompletServiceImpl implements CompletService {
	@Autowired
	CompletMapper completMapper;
	
	@Override
	public void insertOrder(CompletDTO completDTO) {
		completMapper.insertOrder(completDTO);
	}
	@Override
	public ItemDTO getItem(String code) {
		return completMapper.getProduct(code);
	}
	@Override
	public String calDate(String date) throws Exception{
		SimpleDateFormat  transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date transDate = transFormat.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(transDate);
		cal.add(Calendar.DATE, 14);
		return transFormat.format(cal.getTime());
	}

	@Override
	public String checkStock(String code, String ea) {
		String[] codes = code.split(",");
		String[] eas = ea.split(",");
		String msg = "success";
		for(int Number=0; Number<codes.length; Number++) {
			int product_ea = completMapper.getProductStock(codes[Number]);
			if(product_ea<Integer.parseInt(eas[Number])) {
				msg="fail";
			}
		}
		return msg;
	}
	@Override
	public void updateProductStock(String product_ea, String product_code) {
		String codeData[] = product_code.split(",");
		String eaData[] = product_ea.split(",");
		for(int i=0;i<codeData.length;i++) {
			completMapper.updateProductStock(eaData[i], codeData[i]);
		}
	}
	@Override
	public void updateOrderMember(String point, String mid) {
		completMapper.updateOrderMember(point, mid);
		
	}
	@Override
	public void deleteCartOrder(String product_code) {
		String codeData[] = product_code.split(",");
		for(int i=0;i<codeData.length;i++) {
			completMapper.deleteCartOrder(codeData[i]);
		}
	}
}
