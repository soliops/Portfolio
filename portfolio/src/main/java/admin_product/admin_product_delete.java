package admin_product;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import admin_configure.dbconfig;

public class admin_product_delete {
	String msg =null;
	public void product_delete(String data1,String realpath) {
		Connection ct = null;
		try { 
			String data[] = data1.split(",");
			dbconfig db = new dbconfig();
			ct = db.cafe24();
			int n = 0;
			int t = 0;
			String sql ="";
			PreparedStatement findps = null;
			ResultSet rs = null;
			ArrayList<String> ar = null;
			File fe = null;
			do {
				String findsql  ="select product_img1, product_img2, product_img3 from product where pidx='"+data1+"'";
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
				
				PreparedStatement ps = null;
				sql = "delete from product where pidx=?;";					
				ps = ct.prepareStatement(sql);
				ps.setString(1, data[t]);
				n=ps.executeUpdate();
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
