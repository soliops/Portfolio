package com.tj.shopping.user.cs;

import java.util.List;

public interface CSService {
	public List<CSDTO> getList();
	public List<CSDTO> selectList(String cate);
}
