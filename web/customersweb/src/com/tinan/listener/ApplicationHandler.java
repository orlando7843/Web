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
		//�N��T�ʸ˦b�@��Javabean
		AppInformation info=new AppInformation();
		info.setWebName(webName);
		info.setAddress(address);
		//�i�J���Ψt�Ϊ��A�޲z
		application.setAttribute("info",info);
		
		
	}

}
