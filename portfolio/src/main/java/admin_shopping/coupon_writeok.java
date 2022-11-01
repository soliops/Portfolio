package admin_shopping;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
		fileSizeThreshold = 1024*1024*2,
		maxFileSize = 1024*1024*2,
		maxRequestSize = 1024*1024*2
		)
public class coupon_writeok extends HttpServlet {
	PrintWriter pr = null;
	private static final long serialVersionUID = 1L;
       
  
    public coupon_writeok() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		Collection<Part> parts = request.getParts();
		ArrayList<String> ar = new ArrayList<String>();
		this.pr = response.getWriter();
		String realpath= request.getServletContext().getRealPath("");
		String projectPath = request.getServletContext().getContextPath();
		String createFile = "/coupon_img/";
		String path = realpath+ "coupon_img/";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(date);
		String originName = "";
		for(Part part : parts) {
			String partcheck = part.getName();
			String parttext = request.getParameter(partcheck);
			if(partcheck.equals("coupon_publish_date")) {
				parttext = parttext+" 00:00:00";
			}
			else if(partcheck.equals("coupon_expiration_date")) {
				parttext = parttext+" 23:59:59";
			}
			else if(partcheck.equals("coupon_img")) {
			originName = part.getSubmittedFileName();
			parttext = path+time+originName;
			File fe = new File(path);
			if(!fe.exists()) {
				fe.mkdir();
			}
			part.write(parttext);
			parttext="http://opete95.cafe24.com"+projectPath+createFile+time+originName;
			}
			ar.add(parttext);
		}
		ar.add(time+originName);
		try {
			coupon_check cc = new coupon_check();
			cc.coupon_check(ar.get(0), ar.get(2), ar.get(3));
			String check_msg = cc.call_sign().intern();
			if(check_msg=="success") {
			coupon_insert ci = new coupon_insert();
			ci.coupon_insert(ar);
			String msg = ci.call_sign().intern();
			if(msg=="success") {
				this.pr.write("<script>alert('쿠폰 등록이 완료되었습니다.'); location.href='./admin_shopping.html';</script>");
			}
			else {
				this.pr.write("<script>alert('쿠폰 등록을 실패했습니다.'); location.href='./admin_coupon_config.html';</script>");			
			}
			}
			else {
				this.pr.write("<script>alert('이미 존재하고 있는 쿠폰입니다. 다른 쿠폰을 등록시켜 주세요'); location.href='./admin_coupon_config.html';</script>");
			}
		} 
		catch (Exception e) {
			this.pr.write("<script>alert('DB 저장 오류'); location.href='./admin_coupon_config.html';</script>");
		}
	}

}
