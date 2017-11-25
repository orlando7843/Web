package com.tainan.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.opensymphony.xwork2.ActionSupport;
import com.tainan.domain.Customers;
import com.tainan.domain.CustomersUpdateDao;
import com.tainan.domain.IUpdateDao;

public class CustomersController extends ActionSupport {
	//attribute javabean
	private Customers customers;
	private String message;
	//ApplicationContext
	private ApplicationContext applicationContext=
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
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
	//�i��Ȥ����x�s�@�~
	public String customersSave()
	{
		//���ҳW�h �Ȥ�O�_����???
		//�i���x�s
		BasicDataSource dataSource=new BasicDataSource();
		//�`�J��T
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/webdemo");
		dataSource.setUsername("root");
		dataSource.setPassword("1111");
		//�غc�ۭq��Dao
		IUpdateDao<String> dao=new CustomersUpdateDao();
		//�`�JDataSource���󤬰�
		dao.setDataSource(dataSource);
		//�]�w�s�W�ԭz
		String sqlString="insert into customers(customerid,companyname,contactname,address,phone,email) values(?,?,?,?,?,?)";
		//�I�s��smethod
		try {
			dao.update(sqlString,
					customers.getCustomerid(),
					customers.getCompanyname(),
					customers.getContactname(),
					customers.getAddress(),
					customers.getPhone(),
					customers.getEmail()
					);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.message=e.getMessage();
			return "failure";
		}
		
		
		
		return SUCCESS;
	}
	
	
	//�ĥ�Sprig bean�i��Ȥ���@
	
	public String customersBeanSave()
	{
		//1.�z�L�u�t�h�@��Bean(����)
		@SuppressWarnings("unchecked")
		IUpdateDao<String> dao=applicationContext.getBean("customersDAO",IUpdateDao.class);
		String sqlString="insert into customers(customerid,companyname,contactname,address,phone,email) values(?,?,?,?,?,?)";
		//�I�s��smethod
		try {
			dao.update(sqlString,
					customers.getCustomerid(),
					customers.getCompanyname(),
					customers.getContactname(),
					customers.getAddress(),
					customers.getPhone(),
					customers.getEmail()
					);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.message=e.getMessage();
			return "failure";
		}
		return SUCCESS;
	}
	//�ϥ�JdbcTemplate�t�X DataSource�i��s�W�O��
	public String jdbcTemplateUpdate(){
		//��u�t�nDataSource����
		BasicDataSource dataSource=applicationContext.getBean("dataSource",BasicDataSource.class);
		//�غcJdbcTemplate����
		JdbcTemplate dao=new JdbcTemplate();
		//�`�JDataSource
		dao.setDataSource(dataSource);
		//�p��s�W�@�~ 
		String sqlString="insert into customers(customerid,companyname,contactname,address,phone,email) values(?,?,?,?,?,?)";
		try{
		int affect=dao.update(sqlString,
				customers.getCustomerid(),
				customers.getCompanyname(),
				customers.getContactname(),
				customers.getAddress(),
				customers.getPhone(),
				customers.getEmail()
				);
		}catch(DataAccessException ex)
		{
			message=ex.getMessage();
			return "failure";
		}
		return SUCCESS;
	}

	//�ϥ�JdbcTemplate �Ȼs�Ʒs�W�{��
	public String jdbcTemplatePSS()
	{
		//��u�t�nDataSource����
		//final BasicDataSource dataSource=applicationContext.getBean("dataSource",BasicDataSource.class);
		String sqlString="insert into customers(customerid,companyname,contactname,address,phone,email) values(?,?,?,?,?,?)";
		//�غcJdbcTemplate
		//JdbcTemplate dao=new JdbcTemplate();
		//�`�JDataSource
		//dao.setDataSource(dataSource);
		//�ϥΤu�t�Ҧ��n�X��
		JdbcTemplate dao=applicationContext.getBean("jdbctemplate",JdbcTemplate.class);
		//upate method
		try{
		dao.update(sqlString,
				//�غc��@�����ΦW���O����
				new PreparedStatementSetter(){

					@SuppressWarnings("rawtypes")
					@Override
					public void setValues(PreparedStatement st)
							throws SQLException {
						//�P�_�O�_�w�g���ƽs��??
						//�Ҷq�t�@��JdbcTemplate
						JdbcTemplate dao2=applicationContext.getBean("jdbctemplate",JdbcTemplate.class);
						//dao2.setDataSource(dataSource);
						//�d��
						List<Customers> data=dao2.query("select * from customers where customerid='"+customers.getCustomerid()+"'",
								new BeanPropertyRowMapper(Customers.class));
						if(data.size()>0)
						{
							throw new SQLException("�Ȥ�s������!!");
						}
						
						//�B�~�B�z
						st.setString(1, customers.getCustomerid());
						st.setString(2, customers.getCompanyname());
						st.setString(3,customers.getContactname());
						st.setString(4, customers.getAddress());
						st.setString(5, customers.getPhone());
						st.setString(6, customers.getEmail());
						
					}
				
				}
		
				);
		}catch(DataAccessException ex)
		{
			message=ex.getMessage();
			return "failure";
		}
		return SUCCESS;
		
	}
}