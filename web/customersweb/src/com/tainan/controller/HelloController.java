package com.tainan.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tainan.domain.EngHello;
import com.tainan.domain.IHello;
import com.tainan.domain.TWHello;

public class HelloController extends ActionSupport {
	//Javabean
	private String helloMessage;
	//工廠
	private ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");;
	
	
	

	//當Action不指定 預設執行這一個execute method
	public String execute() {
		//1.台客打招呼 介面多型化
		IHello hello=new TWHello("eric");
		helloMessage=hello.helloWorld();
		return SUCCESS;
	}
	
	//使用Spring bean架構來產生物件
	public String helloBean()
	{
		//1.Bean工廠?
		//2.透過Bean Factory指定id 產生一個物件
		IHello hello=factory.getBean("helloworld3",IHello.class);
		//打招呼
		helloMessage=hello.helloWorld();
		return SUCCESS;
	}

	//設定屬性Property setter and getter
	public String getHelloMessage() {
		return helloMessage;
	}


	public void setHelloMessage(String helloMessage) {
		this.helloMessage = helloMessage;
	}
}