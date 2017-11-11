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
	//�u�t
	private ApplicationContext factory=new ClassPathXmlApplicationContext("applicationContext.xml");;
	
	
	

	//��Action�����w �w�]����o�@��execute method
	public String execute() {
		//1.�x�ȥ��۩I �����h����
		IHello hello=new TWHello("eric");
		helloMessage=hello.helloWorld();
		return SUCCESS;
	}
	
	//�ϥ�Spring bean�[�c�Ӳ��ͪ���
	public String helloBean()
	{
		//1.Bean�u�t?
		//2.�z�LBean Factory���wid ���ͤ@�Ӫ���
		IHello hello=factory.getBean("helloworld3",IHello.class);
		//���۩I
		helloMessage=hello.helloWorld();
		return SUCCESS;
	}

	//�]�w�ݩ�Property setter and getter
	public String getHelloMessage() {
		return helloMessage;
	}


	public void setHelloMessage(String helloMessage) {
		this.helloMessage = helloMessage;
	}
}