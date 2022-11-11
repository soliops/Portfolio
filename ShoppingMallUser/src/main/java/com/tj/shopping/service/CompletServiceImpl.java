package com.tj.shopping.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

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
	public CompletDTO setCompletDTO(Map<String, String> resultMap) {
		CompletDTO dto = new CompletDTO();
		dto.setCname(resultMap.get("buyerName"));
		dto.setChp(resultMap.get("buyerTel"));
		dto.setCemail(resultMap.get("buyerEmail"));
		dto.setOrder_code(resultMap.get("MOID").replace("INIpayTest_", ""));
		dto.setPayment(resultMap.get("payMethod"));
		return dto;
	}
}
