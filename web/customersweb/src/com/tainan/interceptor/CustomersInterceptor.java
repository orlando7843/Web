package com.tainan.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.tainan.domain.Customers;

public class CustomersInterceptor implements Interceptor {
	private Customers customers;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		//建構一個客戶物件
		customers=new Customers();
		//設定初始值
		customers.setCustomerid("9999");
		customers.setCompanyname("巨匠");
		customers.setAddress("");
		customers.setPhone("");
		customers.setEmail("");
		customers.setContactname("張三豐");
		
		
		
	}

	//反覆可以執行 進行安全性控管
	@Override
	public String intercept(ActionInvocation arg) throws Exception {
		//進入狀態管理???(Action來源...http://xxx.xxx.xxx/xxx/xxx.action
		ActionContext context=arg.getInvocationContext();
		//使用get(常數) 取出你要Servlet底層
		 HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		request.setAttribute("customers",customers);
		//持續請求action目標
		//return arg.invoke();
		return arg.invoke();
	}

}
