package com.tainan.domain;

import java.sql.SQLException;

import javax.sql.DataSource;

//��Ʋ���(�W�R��)DAO�Ҧ�
public interface IUpdateDao<T> {
	public boolean update(String sqlString,T...args) throws SQLException;
	//�j���`�J �@�Ӹ�ƨӷ�(�s�����󪺤u�t)
	public void setDataSource(DataSource dataSource);

}
