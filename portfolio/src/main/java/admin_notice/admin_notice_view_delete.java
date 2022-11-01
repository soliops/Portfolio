package admin_notice;

import java.io.File;
import java.sql.*;

import admin_configure.dbconfig;

public class admin_notice_view_delete {
	String msg=null;
	public void view_delete(String idx,String realpath) {
		Connection ct=null;
		try {
			dbconfig db = new dbconfig();
			ct = db.cafe24();
			String findsql = "select notice_file_name from admin_notice where idx='"+idx+"';";
			PreparedStatement findps = ct.prepareStatement(findsql);
			ResultSet rs = findps.executeQuery();
			String name = "";
			while(rs.next()) {
				name = rs.getString("notice_file_name");
			}
			File fe = new File(realpath+name);
			if(name!=""&&fe.exists()) {fe.delete();}
			String sql = "delete from admin_notice where idx=?";
			PreparedStatement ps = ct.prepareStatement(sql);
			ps.setString(1, idx);
			int n = 0;
			n=ps.executeUpdate();
			if(n>0) {
				this.msg="success";
			}
			else {
				throw new Exception("error");
			}
		} catch (Exception e) {
			this.msg="fail";
		}
	}
	public String call_sign() {
		return this.msg;
	}
}
