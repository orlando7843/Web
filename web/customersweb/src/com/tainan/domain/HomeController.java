package com.tainan.domain;
//POJO(Plain Old Java Object)�_��O
//���X�@��JavaBean(�������O)
public class HomeController {
	//private�ʸ˩�
	private String company;
	//�s���lsetter and getter
	public void setCompany(String company)
	{
		this.company=company;
	}
	public String getCompany()
	{
		company="���عq�H";
		return company;
	}
	
	public String home()
	{
		//����y�{....
		//�]�w�@�Ӥ��q�渹�r�� �p�󬣰e�쭺���e�{???
		company="���K�x�n�{�Ҥ���";
		return "success"; //���\�o�@��X
	}

}
