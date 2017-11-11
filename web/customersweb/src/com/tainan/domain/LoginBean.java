package com.tainan.domain;
//JavaBean 三個規則(實作序列化介面/空參數建構子/封裝欄位---設定setter and getter)
public class LoginBean implements java.io.Serializable {
	//attribute
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
