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
		//�غc�@�ӫȤ᪫��
		customers=new Customers();
		//�]�w��l��
		customers.setCustomerid("9999");
		customers.setCompanyname("���K");
		customers.setAddress("");
		customers.setPhone("");
		customers.setEmail("");
		customers.setContactname("�i�T��");
		
		
		
	}

	//���Хi�H���� �i��w���ʱ���
	@Override
	public String intercept(ActionInvocation arg) throws Exception {
		//�i�J���A�޲z???(Action�ӷ�...http://xxx.xxx.xxx/xxx/xxx.action
		ActionContext context=arg.getInvocationContext();
		//�ϥ�get(�`��) ���X�A�nServlet���h
		 HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		request.setAttribute("customers",customers);
		//����ШDaction�ؼ�
		//return arg.invoke();
		return arg.invoke();
	}

}
