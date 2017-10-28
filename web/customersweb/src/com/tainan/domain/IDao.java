package com.tainan.domain;

import java.sql.SQLException;

import javax.sql.DataSource;

//使用Generic 沒有型 實作介面再來決定吧
public interface IDao<T,K> {
	//注入與DataSource(連接工廠)的依賴關係setter
	public void setDataSource(DataSource dataSource);
	//單筆查詢
	public T queryForObject(K key) throws SQLException;
	
	
}
