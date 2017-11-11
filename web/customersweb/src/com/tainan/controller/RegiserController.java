package com.tainan.controller;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tainan.domain.AppInformation;
import com.tainan.domain.LoginBean;
import com.tainan.domain.LoginDAO;

public class RegiserController extends ActionSupport {
	//�ʸ˩�Attribute(Javabean����)
	private LoginBean login=new LoginBean();
	

	//�i�JValueStack
	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	//default action
	public String execute() {
		//�Ū�
		login.setUserName("�s�s�C");
		return SUCCESS; //�����^���X�� �i��jsp�����t�e(Action result)
	}
	
	//�ϥΪ̵n�J����action �����O�d�ŰѼ�
	public String userValid()
	{
		//�ɱ�Action�PJava Web Servlet�Ҧ�����
		//HttpServletRequest request=ServletActionContext.getRequest();
		//HttpServletResponse response=ServletActionContext.getResponse();
		ServletContext application=ServletActionContext.getServletContext();
		AppInformation info=(AppInformation)application.getAttribute("info");
		//�ϥΪ�����N�O�ѼƷ���
//		@SuppressWarnings("unused")
//		String userName=request.getParameter("userName");
//		String password=request.getParameter("password");
		
		//1.�غcDataSource����
		BasicDataSource dataSource=new BasicDataSource();
		//�`�J��T
		dataSource.setDriverClassName(info.getDriverClassName());
		dataSource.setUrl(info.getUrl());
		dataSource.setUsername(info.getUserName());
		dataSource.setPassword(info.getPassword());
		//2.�غc�ۭqDAO����
		LoginDAO dao=new LoginDAO();
		
		//3.�]�wDAO�PDataSource����(DI)
		dao.setDataSource(dataSource);
		//4.�i��|����Ƭd��
		dao.setPassword(login.getPassword());
		LoginBean log=null;
		try {
			log = dao.queryForObject(login.getUserName());
			//5.yes or no ���P�����e
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(log!=null)
		{
			return SUCCESS;
		}else
		{
			return "nouser";
		}
		
	}
}