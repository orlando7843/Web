package com.tainan.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tainan.domain.LoginBean;

public class RegiserController extends ActionSupport {
	//封裝性
	private LoginBean login=new LoginBean();
	

	//進入ValueStack
	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	/**
	 * @return
	 */
	public String execute() {
		//空的
		login.setUserName("零零七");
		return SUCCESS; //直接回應旗標 進行jsp網頁配送(Action result)
	}
	
	//使用者登入驗證action 必須保留空參數
	public String userValid()
	{
		//界接Action與Java Web Servlet所有物件
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		ServletContext application=ServletActionContext.getServletContext();
		//使用表單欄位就是參數概念
//		@SuppressWarnings("unused")
//		String userName=request.getParameter("userName");
//		String password=request.getParameter("password");
		
		return SUCCESS;
	}
}