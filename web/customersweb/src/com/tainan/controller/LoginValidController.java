package com.tainan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

//���g�@��Servlet(Server side let-�p�{��)
public class LoginValidController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.�^�����ǻ��i�Ӫ���T(Request)
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
//		PrintWriter out=response.getWriter();
//		out.println(username);
		
		//2.�i������(membership)
		if(username.toLowerCase().equals("eric"))
		{
			//�ڵ� ����request and response��Ӫ���g��
			RequestDispatcher disp=request.getRequestDispatcher("failure.jsp");
			
			disp.forward(request, response);
			
		}else
		{
			//3.���Ҧ��\�P�_ ���e�줣�P��View(jsp Page)
			RequestDispatcher disp=request.getRequestDispatcher("ok.jsp");
			//�����B�~��T(�e���I�� )
			int point=1;
			//�Ш��@��request�a�@�ө�o�~
			request.setAttribute("point",point); //�i��򥻫��O�Q�ʸ˦�Integer����(Autoboxing)
			
			disp.forward(request, response);
		}
		
		
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("�ڬ��L��!!");
	}
	
	
	

}
