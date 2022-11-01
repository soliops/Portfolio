package admin_notice;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import admin_configure.dbconfig;

public class notice_list_delete {
	String msg = null;
	public void notice_list (String datas,String realpath) {
		Connection ct = null;
		try {
			String[] data= datas.split(",");
			dbconfig db = new dbconfig();
			ct = db.cafe24();
			int t =0;
			int n =0;
			File fe = null;
			ResultSet rs = null;
			do {
				String name = "";
				String findsql = "select notice_file_name from admin_notice where idx='"+data[t]+"';";
				PreparedStatement findps = ct.prepareStatement(findsql);
				rs = findps.executeQuery();
				while(rs.next()) {
					name = rs.getString("notice_file_name");
				}
				fe = new File(realpath+name);
				if(name!=""&&fe.exists()) {fe.delete();}
				String sql = "delete from admin_notice where idx=?;";
				PreparedStatement ps = ct.prepareStatement(sql);
				
				ps.setString(1, data[t]);
				n=ps.executeUpdate();
				t++;
			}while(t<data.length);
			if(n>0) {
				this.msg="success";
				ct.close();
				
			}
			else {
				throw new Exception();
			}
		}catch (Exception e) {
			this.msg = "fail";
		}		
	}
	public String call_sign() {
		return this.msg;
	}
}
