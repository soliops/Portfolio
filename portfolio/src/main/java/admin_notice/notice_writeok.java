package admin_notice;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import admin_configure.timer;

@MultipartConfig(
		fileSizeThreshold = 1024*1024*2,
		maxFileSize = 1024*1024*2,
		maxRequestSize = 1024*1024*2*3
		)
public class notice_writeok extends HttpServlet {
	PrintWriter pw = null;
	private static final long serialVersionUID = 1L;
       
    
    public notice_writeok() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		String realpath = request.getServletContext().getRealPath("") + "notice_img/";
		String projectPath = request.getServletContext().getContextPath();
		String createFile = "/notice_img/";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		timer time = new timer();
		String times = sdf.format(date);
		ArrayList<String> notice_list = new ArrayList<>();
		notice_list.add("admin_notice");
		Collection <Part> parts =request.getParts();
		for(Part file : parts) {
			String noticeCheck = file.getName();
			String noticeText = request.getParameter(noticeCheck);
			if(noticeCheck.equals("notice_file")) {
				String originName = file.getSubmittedFileName();
				if(originName==null || originName =="") {
					noticeText = "";
				}
				else {
					noticeText = realpath+times+originName;
					File fe = new File(realpath);
					if(!fe.exists()) {fe.mkdir();}
					file.write(noticeText);
					noticeText = "http://opete95.cafe24.com"+projectPath+createFile+times+originName;
				}
				notice_list.add(noticeText);
				notice_list.add(times+originName);
				continue;
			}
			notice_list.add(noticeText);
		}	
		notice_list.add(time.now_datetime());
		if(notice_list.size()==9) {notice_list.remove(2);}
		String msg=null;
			notice_write_insert nwi = new notice_write_insert();
			nwi.notice_insert(notice_list);
			msg=nwi.call_sign();
			if(msg=="success") {
				this.pw.print("<script>alert('공지사항 등록이 완료되었습니다.'); location.href='./admin/admin_notice.html';</script>");
			}
			else {
				throw new Exception("error");
			}
		}
		catch(Exception e) {
			this.pw.print("<script>alert('공지사항 등록을 실패했습니다.'); history.go(-1);</script>");			
		}
	}

}
