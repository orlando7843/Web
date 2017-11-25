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
		//���P�_DataSource
		if(dataSource==null)
		{
			throw new SQLException("��ƨӷ�����|���`�J!!");
		}
		try{
			//1.�z�L��ƨӷ�������@���s������(�㦳�}��)
			Connection connection=dataSource.getConnection();
			//2.�n�@��prepared Statement
			PreparedStatement st=connection.prepareStatement(sqlString);
			//�v�@�]�w�Ѽ�
			for(int s=0;s<args.length;s++)
			{
				//�]�w�۹�ѼƦ�m���e
				st.setString(s+1, args[s]);
			}
			//����W�R��
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
