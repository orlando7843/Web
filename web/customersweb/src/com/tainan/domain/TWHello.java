package com.tainan.domain;

public class TWHello implements IHello{
	private String who;
	public TWHello(){}
	

	@Override
	public String helloWorld() {
		// TODO Auto-generated method stub
		return who+" ¦Y¹¡¨S";
	}
	public TWHello(String who){
		this.who=who;
	}
	//setter getter as Property
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
		

}
