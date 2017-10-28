package com.tinan.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tainan.domain.AppInformation;

//ť�������Ψt�αҰʤ��� �i���l�Ƹ�T(JavaBean)
public class ApplicationHandler implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg) {
		//���X��ť�ӷ�
		ServletContext application=arg.getServletContext();
		String webName=application.getInitParameter("webname");
		String address=application.getInitParameter("address");
		//��������^��
		String driverClassName=application.getInitParameter("driverClassName");
		String url=application.getInitParameter("url");
		String userName=application.getInitParameter("userName");
		String password=application.getInitParameter("password");
		
		//�N��T�ʸ˦b�@��Javabean
		AppInformation info=new AppInformation();
		info.setWebName(webName);
		info.setAddress(address);
		//�`�J������Ҹ�T
		info.setDriverClassName(driverClassName);
		info.setUrl(url);
		info.setUserName(userName);
		info.setPassword(password);
		//�i�J���Ψt�Ϊ��A�޲z
		application.setAttribute("info",info);
		
		
	}

}
