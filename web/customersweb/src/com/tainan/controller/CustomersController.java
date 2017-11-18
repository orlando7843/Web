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


	//派送一個客戶資料維護表單
	public String execute() {
		//從攔截器取出物件模組
		this.customers=(Customers)ServletActionContext.getRequest().getAttribute("customers");
		return SUCCESS;
	}
	
}