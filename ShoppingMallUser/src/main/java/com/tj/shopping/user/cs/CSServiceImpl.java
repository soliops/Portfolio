package com.tj.shopping.user.cs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Primary
public class CSServiceImpl implements CSService {

	@Autowired
	private CSMapper csMapper;
	
	@Override
	public List<CSDTO> getList() {
		return csMapper.getFAQ();
	}

	@Override
	public List<CSDTO> selectList(String cate) {
		List<CSDTO> list = csMapper.selectFAQ(cate);
		for(int w=0;w<list.size();w++) {		
			switch(list.get(w).getFcategory()) {
			case "1":
				list.get(w).setFcategory("배송문의");
				break;
			case "2":
				list.get(w).setFcategory("반품/교환문의");
				break;
			case "3":
				list.get(w).setFcategory("상품문의");
				break;
			case "4":
				list.get(w).setFcategory("쿠폰내역");
				break;
			case "5":
				list.get(w).setFcategory("마일리지내역");
				break;
			default:
				list.get(w).setFcategory("기타문의");
				break;
			}
		
		}
		return list;
	}

	
}
