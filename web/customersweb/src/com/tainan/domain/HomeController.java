package com.tainan.domain;
//POJO(Plain Old Java Object)復刻板
//結合一個JavaBean(實體類別)
public class HomeController {
	//private封裝性
	private String company;
	//存取子setter and getter
	public void setCompany(String company)
	{
		this.company=company;
	}
	public String getCompany()
	{
		company="中華電信";
		return company;
	}
	
	public String home()
	{
		//控制流程....
		//設定一個公司行號字串 如何派送到首頁呈現???
		company="巨匠台南認證中心";
		return "success"; //成功這一支旗
	}

}
