package com.tj.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ViewDTO {
	private String order_code;
	private String mid;
	private String orderer;
	private String orderer_mobile;
	private String orderer_tel;
	private String orderer_email;
	
	private String receiver;
	private String receiver_post;
	private String receiver_addr;
	private String receiver_addrdtc;
	private String receiver_mobile;
	private String receiver_tel;
	
	private String ship;
	private String ship_pay;
	private String ship_memo;
	private String ship_sum;
	
	private String product_idx;
	private String product_nm;
	private String product_code;
	private String product_ea;
	private String product_price;
	private String product_point;
	private String product_total;
	private String payment;
	private String bank_list;	
	private String regDate;
	private String modDate;
}
