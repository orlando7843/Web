package com.tinan.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tainan.domain.AppInformation;

//聽網站應用系統啟動之後 進行初始化資訊(JavaBean)
public class ApplicationHandler implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg) {
		//取出聆聽來源
		ServletContext application=arg.getServletContext();
		String webName=application.getInitParameter("webname");
		String address=application.getInitParameter("address");
		//資料環境擷取
		String driverClassName=application.getInitParameter("driverClassName");
		String url=application.getInitParameter("url");
		String userName=application.getInitParameter("userName");
		String password=application.getInitParameter("password");
		
		//將資訊封裝在一個Javabean
		AppInformation info=new AppInformation();
		info.setWebName(webName);
		info.setAddress(address);
		//注入資料環境資訊
		info.setDriverClassName(driverClassName);
		info.setUrl(url);
		info.setUserName(userName);
		info.setPassword(password);
		//進入應用系統狀態管理
		application.setAttribute("info",info);
		
		
	}

}
