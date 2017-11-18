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
	//�ʸ˩�Attribute(Javabean����)
	private LoginBean login=new LoginBean();
	//�\�u�t
	private ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");
	

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
//		BasicDataSource dataSource=new BasicDataSource();
//		//�`�J��T
//		dataSource.setDriverClassName(info.getDriverClassName());
//		dataSource.setUrl(info.getUrl());
//		dataSource.setUsername(info.getUserName());
//		dataSource.setPassword(info.getPassword());
		
		//�ϥΤu�t�h�n(����)�@��DataSource����
		//BasicDataSource dataSource=factory.getBean("dataSource",BasicDataSource.class);
		//2.�غc�ۭqDAO���� �����h����??
		//LoginDAO dao=new LoginDAO();
		//3.�]�wDAO�PDataSource����(DI)
		//dao.setDataSource(dataSource); //Property Injection �ݩʪ`�J
		
		LoginDAO dao=factory.getBean("dao",LoginDAO.class);
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
		//�O�@�ӷ|��?
		if(log!=null)
		{
			//�|�� �o�X�@�i�O�O��
			//�z�LHttpServletRequest����h�n�@�ӷs��Session �Ϊ̬J����Session
			HttpServletRequest request=ServletActionContext.getRequest();
			//�ѦҥX�Ϊ̷s��Session
			HttpSession session=request.getSession();
			//�Ѧ��ѧO��(�P�ɵo�X�@�ӫe��Session ID��Cookie)
			session.setAttribute("cred",login.getUserName());
			
			return SUCCESS;
		}else
		{
			//���O�|��
			return "nouser";
		}
		
	}
}