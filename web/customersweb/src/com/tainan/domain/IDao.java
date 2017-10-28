package com.tainan.domain;

import java.sql.SQLException;

import javax.sql.DataSource;

//�ϥ�Generic �S���� ��@�����A�ӨM�w�a
public interface IDao<T,K> {
	//�`�J�PDataSource(�s���u�t)���̿����Ysetter
	public void setDataSource(DataSource dataSource);
	//�浧�d��
	public T queryForObject(K key) throws SQLException;
	
	
}
