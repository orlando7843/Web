package com.tainan.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tainan.domain.LoginBean;

public class RegiserController extends ActionSupport {
	//�ʸ˩�
	private LoginBean login=new LoginBean();
	

	//�i�JValueStack
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
		//�Ū�
		login.setUserName("�s�s�C");
		return SUCCESS; //�����^���X�� �i��jsp�����t�e(Action result)
	}
	
	//�ϥΪ̵n�J����action �����O�d�ŰѼ�
	public String userValid()
	{
		//�ɱ�Action�PJava Web Servlet�Ҧ�����
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		ServletContext application=ServletActionContext.getServletContext();
		//�ϥΪ�����N�O�ѼƷ���
//		@SuppressWarnings("unused")
//		String userName=request.getParameter("userName");
//		String password=request.getParameter("password");
		
		return SUCCESS;
	}
}