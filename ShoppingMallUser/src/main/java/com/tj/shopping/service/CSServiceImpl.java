package com.tj.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tj.shopping.domain.CSDTO;
import com.tj.shopping.persistence.CSMapper;

import lombok.RequiredArgsConstructor;

@Service
@Primary
public class CSServiceImpl implements CSService {

	@Autowired
	private CSMapper csMapper;
	
	public List<CSDTO> toChange(List<CSDTO> list){
		for(int w=0;w<list.size();w++) {		
			switch(list.get(w).getFcategory()) {
			case "1":
				list.get(w).setFcategory("[배송문의]");
				break;
			case "2":
				list.get(w).setFcategory("[반품/교환문의]");
				break;
			case "3":
				list.get(w).setFcategory("[상품문의]");
				break;
			case "4":
				list.get(w).setFcategory("[쿠폰내역]");
				break;
			case "5":
				list.get(w).setFcategory("[마일리지내역]");
				break;
			default:
				list.get(w).setFcategory("기타문의");
				break;
			}
		}
		return list;
	}
	
	@Override
	public List<CSDTO> getList() {
		List<CSDTO> list = toChange(csMapper.getFAQ());
		return list;
	}

	@Override
	public List<CSDTO> selectList(String cate) {
		List<CSDTO> list = toChange(csMapper.selectFAQ(cate));
		return list;
	}

	
}
