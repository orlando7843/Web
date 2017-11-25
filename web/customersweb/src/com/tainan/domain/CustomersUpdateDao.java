package com.tainan.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CustomersUpdateDao implements IUpdateDao<String>{
	//attribute
	private DataSource dataSource;

	@Override
	public boolean update(String sqlString, String... args) throws SQLException {
		boolean r=false;
		//先判斷DataSource
		if(dataSource==null)
		{
			throw new SQLException("資料來源物件尚未注入!!");
		}
		try{
			//1.透過資料來源物件取一條連接物件(具有開啟)
			Connection connection=dataSource.getConnection();
			//2.要一個prepared Statement
			PreparedStatement st=connection.prepareStatement(sqlString);
			//逐一設定參數
			for(int s=0;s<args.length;s++)
			{
				//設定相對參數位置內容
				st.setString(s+1, args[s]);
			}
			//執行增刪修
			if(st.executeUpdate()>0)
			{
				r=true;
			}
		}catch(Exception ex)
		{
			throw new SQLException(ex.getMessage());
		}
		return r;
		
		
		
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource=dataSource;
		
	}

}
