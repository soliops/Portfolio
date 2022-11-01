package admin_product;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import admin_configure.dbconfig;

public class admin_category_delete {
	String msg =null;
	public void category_delete(String data1,String realpath) {
		Connection ct = null;
		try { 
			String data[] = data1.split(",");
			dbconfig db = new dbconfig();
			ct = db.cafe24();
			int n = 0;
			int m = 0;
			int t = 0;
			String findsql = "";
			String sql ="";
			String sql2 = "";
			ArrayList<String> ar = null;
			ResultSet rs = null;
			File fe = null;
			File fe2 = null;
			PreparedStatement ps = null;
			PreparedStatement findps = null;
			PreparedStatement ps2 = null;
			PreparedStatement findps2 = null;
			do {
				if(data[t].length()==3) {
					sql = "delete from category where cbcate_code=?;";					
					ps = ct.prepareStatement(sql);
					ps.setString(1, data[t]);
					findsql = "select product_img1, product_img2, product_img3 from product where cbcate_code='"+data[t]+"'";					
					findps = ct.prepareStatement(findsql);
					rs = findps.executeQuery();
					while(rs.next()) {
						ar.add(rs.getString("product_img1").replace("http://opete95.cafe24.com/portfolio/product_img/", ""));
						ar.add(rs.getString("product_img2").replace("http://opete95.cafe24.com/portfolio/product_img/", ""));
						ar.add(rs.getString("product_img3").replace("http://opete95.cafe24.com/portfolio/product_img/", ""));
					}
					if(ar.size()!=0) {
						for(int w=0; w<ar.size();w++) {
							if(ar.get(w) != "" || ar.get(w) !=null) {
								fe = new File(realpath+ar.get(w));
								if(fe.exists()) {fe.delete();}
							}
						}
					}
					sql2 ="delete from product where cbcate_code=?;";
					ps2 = ct.prepareStatement(sql2);
					ps2.setString(1, data[t]);
				}
				else {
					sql = "delete from category where cbcate_code=? and cscate_code=?;";
					ps = ct.prepareStatement(sql);
					ps.setString(1, data[t].substring(0,3));
					ps.setString(2, data[t].substring(3,6));
					findsql = "select product_img1, product_img2, product_img3 from product where cbcate_code='"+data[t].substring(0,3)+"' and "
							+ "cscate_code='"+data[t].substring(3,6)+"'";					
					findps = ct.prepareStatement(findsql);
					rs = findps.executeQuery();
					while(rs.next()) {
						ar.add(rs.getString("product_img1").replace("http://opete95.cafe24.com/portfolio/product_img/", ""));
						ar.add(rs.getString("product_img2").replace("http://opete95.cafe24.com/portfolio/product_img/", ""));
						ar.add(rs.getString("product_img3").replace("http://opete95.cafe24.com/portfolio/product_img/", ""));
					}
					if(ar.size()!=0) {
						for(int w=0; w<ar.size();w++) {
							if(ar.get(w) != "" || ar.get(w) !=null) {
								fe = new File(realpath+ar.get(w));
								if(fe.exists()) {fe.delete();}
							}
						}
					}
					
					sql2 ="delete from product where cbcate_code=? and cscate_code=?;";
					ps2 = ct.prepareStatement(sql2);
					ps2.setString(1, data[t].substring(0,3));
					ps2.setString(2, data[t].substring(3,6));
				}
				n=ps.executeUpdate();
				m=ps2.executeUpdate();
				t++;
				}
			while(t<data.length);
			if(n>0) {
				this.msg="success";
				ct.close();
			}
			else {
				throw new Exception();
			}
		} catch (Exception e) {			
			this.msg = "fail";
		}
	}
	public String call_sign() {
		return this.msg;
	}
	
}
