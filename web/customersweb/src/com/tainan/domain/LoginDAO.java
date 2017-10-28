package com.tainan.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

//實作介面
public class LoginDAO implements IDao<LoginBean,String>{
	//attribute
	private DataSource dataSource;
	private String password;
	//密碼採用屬性注入
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//setter
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource=dataSource;
		
	}

	@Override
	public LoginBean queryForObject(String userName) throws SQLException{
		//1.判斷是否注入DataSource
		LoginBean login=null;
		if(dataSource==null)
		{
			throw new SQLException("資料來源物件尚未注入!!");
		}
		//2.透過DataSource工廠產生一條開啟資料連接
		Connection connection=dataSource.getConnection();
		//3.準備查詢敘述(PreparedStatement)
		String sql="select * from users where username=? and password=?";
		//4.透過連接物件產生命令物件 
		PreparedStatement st=connection.prepareStatement(sql);
		//5.設定參數內容
		st.setString(1, userName);
		st.setString(2, this.password);
		//6查詢 產生結果集
		ResultSet rs=st.executeQuery(); //線上讀取
		//判斷是否有記錄
		if(rs.next())
		{
			//會員
			//封裝物件
			login=new LoginBean();
			login.setUserName(rs.getString("username"));
			login.setPassword(rs.getString("password"));
			connection.close(); //將連接收回集區
		}
		return login;
	}

}
