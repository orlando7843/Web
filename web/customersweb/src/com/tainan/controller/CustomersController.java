package com.tainan.controller;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tainan.domain.Customers;

public class CustomersController extends ActionSupport {
	//attribute javabean
	private Customers customers;
	private String message;
	
	//setter and getter as Property
	public Customers getCustomers() {
		return customers;
	}


	public void setCustomers(Customers customers) {
		this.customers = customers;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	//���e�@�ӫȤ��ƺ��@���
	public String execute() {
		//�q�d�I�����X����Ҳ�
		this.customers=(Customers)ServletActionContext.getRequest().getAttribute("customers");
		return SUCCESS;
	}
	
}