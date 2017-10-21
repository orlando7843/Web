package com.tainan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

//撰寫一個Servlet(Server side let-小程式)
public class LoginValidController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.擷取表單傳遞進來的資訊(Request)
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
//		PrintWriter out=response.getWriter();
//		out.println(username);
		
		//2.進行驗證(membership)
		if(username.toLowerCase().equals("eric"))
		{
			//拒絕 持續request and response兩個物件週期
			RequestDispatcher disp=request.getRequestDispatcher("failure.jsp");
			
			disp.forward(request, response);
			
		}else
		{
			//3.驗證成功與否 派送到不同的View(jsp Page)
			RequestDispatcher disp=request.getRequestDispatcher("ok.jsp");
			//產生額外資訊(送個點數 )
			int point=1;
			//請那一個request帶一個拖油瓶
			request.setAttribute("point",point); //進行基本型別被封裝成Integer物件(Autoboxing)
			
			disp.forward(request, response);
		}
		
		
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("我活過來!!");
	}
	
	
	

}
