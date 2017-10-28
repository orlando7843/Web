package com.tainan.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

//��@����
public class LoginDAO implements IDao<LoginBean,String>{
	//attribute
	private DataSource dataSource;
	private String password;
	//�K�X�ĥ��ݩʪ`�J
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
		//1.�P�_�O�_�`�JDataSource
		LoginBean login=null;
		if(dataSource==null)
		{
			throw new SQLException("��ƨӷ�����|���`�J!!");
		}
		//2.�z�LDataSource�u�t���ͤ@���}�Ҹ�Ƴs��
		Connection connection=dataSource.getConnection();
		//3.�ǳƬd�߱ԭz(PreparedStatement)
		String sql="select * from users where username=? and password=?";
		//4.�z�L�s�����󲣥ͩR�O���� 
		PreparedStatement st=connection.prepareStatement(sql);
		//5.�]�w�ѼƤ��e
		st.setString(1, userName);
		st.setString(2, this.password);
		//6�d�� ���͵��G��
		ResultSet rs=st.executeQuery(); //�u�WŪ��
		//�P�_�O�_���O��
		if(rs.next())
		{
			//�|��
			//�ʸ˪���
			login=new LoginBean();
			login.setUserName(rs.getString("username"));
			login.setPassword(rs.getString("password"));
			connection.close(); //�N�s�����^����
		}
		return login;
	}

}
