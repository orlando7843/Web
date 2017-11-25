package com.tainan.domain;

import java.sql.SQLException;

import javax.sql.DataSource;

//資料異動(增刪修)DAO模式
public interface IUpdateDao<T> {
	public boolean update(String sqlString,T...args) throws SQLException;
	//強迫注入 一個資料來源(連接物件的工廠)
	public void setDataSource(DataSource dataSource);

}
