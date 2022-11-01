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
		maxRequestSize = 1024*1024*2
		)
public class admin_notice_viewok extends HttpServlet {
	PrintWriter pr = null;
	private static final long serialVersionUID = 1L;
       
    public admin_notice_viewok() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		try {
		ArrayList<String> ar = new ArrayList<String>();
		timer time = new timer();
		String date = time.now_datetime();
		String realpath = request.getServletContext().getRealPath("")+"notice_img/";
		String projectPath = request.getServletContext().getContextPath();
		String createFile = "/product_img/";
		Date date2 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String times = sdf.format(date2);
		String path = "http://opete95.cafe24.com"+projectPath+createFile;
		Collection<Part> parts = request.getParts();
		for(Part file : parts){
			String filecheck = file.getName();
			String filetext = request.getParameter(filecheck);
		if(filecheck.equals("notice_file")) {
			String originName = file.getSubmittedFileName();
			String originfile = request.getParameter("notice_file_origin");
			if(originName==null||originName=="") {						
				filetext="";
			}
			else {
				File fe = new File(realpath+originfile);
				if(fe.exists()) {
					fe.delete();
				}
				filetext=realpath+times+originName;
				file.write(filetext);
				filetext=path+times+originName;
			}
			ar.add(filetext);
			ar.add(times+originName);
			continue;
		}
		else if(filecheck.equals("notice_file_origin")) {
			continue;
		}
		ar.add(filetext);
		}
		ar.add(date);
		if(ar.size()==9) {ar.remove(2);}
		String update_msg = "";
		this.pr = response.getWriter();
		admin_notice_view_update anvu = new admin_notice_view_update();
		anvu.view_update(ar);
		update_msg = anvu.call_sign().intern();
		if(update_msg=="success") {
			this.pr.write("<script>alert('공지사항이 정상적으로 수정되었습니다.'); location.href='./admin_notice.html';</script>");
		}
		else {
			throw new Exception("error");
		}
		}
		catch(Exception e) {
			this.pr.write("<script>alert('공지사항 수정 오류'); history.go(-1);</script>");
		}
	}

}
