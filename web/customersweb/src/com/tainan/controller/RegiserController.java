package com.tainan.controller;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tainan.domain.AppInformation;
import com.tainan.domain.LoginBean;
import com.tainan.domain.LoginDAO;

public class RegiserController extends ActionSupport {
	//封裝性Attribute(Javabean物件)
	private LoginBean login=new LoginBean();
	//蓋工廠
	private ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
	

	//進入ValueStack
	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	//default action
	public String execute() {
		//空的
		login.setUserName("零零七");
		return SUCCESS; //直接回應旗標 進行jsp網頁配送(Action result)
	}
	
	//使用者登入驗證action 必須保留空參數
	public String userValid()
	{
		//界接Action與Java Web Servlet所有物件
		//HttpServletRequest request=ServletActionContext.getRequest();
		//HttpServletResponse response=ServletActionContext.getResponse();
		ServletContext application=ServletActionContext.getServletContext();
		AppInformation info=(AppInformation)application.getAttribute("info");
		//使用表單欄位就是參數概念
//		@SuppressWarnings("unused")
//		String userName=request.getParameter("userName");
//		String password=request.getParameter("password");
		
		//1.建構DataSource物件
//		BasicDataSource dataSource=new BasicDataSource();
//		//注入資訊
//		dataSource.setDriverClassName(info.getDriverClassName());
//		dataSource.setUrl(info.getUrl());
//		dataSource.setUsername(info.getUserName());
//		dataSource.setPassword(info.getPassword());
		
		//使用工廠去要(正轉)一個DataSource物件
		//BasicDataSource dataSource=factory.getBean("dataSource",BasicDataSource.class);
		//2.建構自訂DAO物件 介面多型化??
		//LoginDAO dao=new LoginDAO();
		//3.設定DAO與DataSource互動(DI)
		//dao.setDataSource(dataSource); //Property Injection 屬性注入
		
		LoginDAO dao=factory.getBean("dao",LoginDAO.class);
		//4.進行會員資料查詢
		dao.setPassword(login.getPassword());
		LoginBean log=null;
		try {
			log = dao.queryForObject(login.getUserName());
			//5.yes or no 不同的派送
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//是一個會員?
		if(log!=null)
		{
			//會員 發出一張是別證
			//透過HttpServletRequest物件去要一個新的Session 或者既有的Session
			HttpServletRequest request=ServletActionContext.getRequest();
			//參考出或者新的Session
			HttpSession session=request.getSession();
			//參考識別證(同時發出一個前端Session ID給Cookie)
			session.setAttribute("cred",login.getUserName());
			
			return SUCCESS;
		}else
		{
			//不是會員
			return "nouser";
		}
		
	}
}