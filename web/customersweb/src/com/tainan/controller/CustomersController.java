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


	//派送一個客戶資料維護表單
	public String execute() {
		//從攔截器取出物件模組
		this.customers=(Customers)ServletActionContext.getRequest().getAttribute("customers");
		return SUCCESS;
	}
	//進行客戶資料儲存作業
	public String customersSave()
	{
		//驗證規則 客戶是否重複???
		//進行儲存
		BasicDataSource dataSource=new BasicDataSource();
		//注入資訊
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/webdemo");
		dataSource.setUsername("root");
		dataSource.setPassword("1111");
		//建構自訂的Dao
		IUpdateDao<String> dao=new CustomersUpdateDao();
		//注入DataSource物件互動
		dao.setDataSource(dataSource);
		//設定新增敘述
		String sqlString="insert into customers(customerid,companyname,contactname,address,phone,email) values(?,?,?,?,?,?)";
		//呼叫更新method
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
	
	
	//採用Sprig bean進行客戶維護
	
	public String customersBeanSave()
	{
		//1.透過工廠去一個Bean(元件)
		@SuppressWarnings("unchecked")
		IUpdateDao<String> dao=applicationContext.getBean("customersDAO",IUpdateDao.class);
		String sqlString="insert into customers(customerid,companyname,contactname,address,phone,email) values(?,?,?,?,?,?)";
		//呼叫更新method
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
	//使用JdbcTemplate配合 DataSource進行新增記錄
	public String jdbcTemplateUpdate(){
		//跟工廠要DataSource物件
		BasicDataSource dataSource=applicationContext.getBean("dataSource",BasicDataSource.class);
		//建構JdbcTemplate物件
		JdbcTemplate dao=new JdbcTemplate();
		//注入DataSource
		dao.setDataSource(dataSource);
		//如何新增作業 
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

	//使用JdbcTemplate 客製化新增程序
	public String jdbcTemplatePSS()
	{
		//跟工廠要DataSource物件
		//final BasicDataSource dataSource=applicationContext.getBean("dataSource",BasicDataSource.class);
		String sqlString="insert into customers(customerid,companyname,contactname,address,phone,email) values(?,?,?,?,?,?)";
		//建構JdbcTemplate
		//JdbcTemplate dao=new JdbcTemplate();
		//注入DataSource
		//dao.setDataSource(dataSource);
		//使用工廠模式要出來
		JdbcTemplate dao=applicationContext.getBean("jdbctemplate",JdbcTemplate.class);
		//upate method
		try{
		dao.update(sqlString,
				//建構實作介面匿名類別物件
				new PreparedStatementSetter(){

					@SuppressWarnings("rawtypes")
					@Override
					public void setValues(PreparedStatement st)
							throws SQLException {
						//判斷是否已經重複編號??
						//考量另一個JdbcTemplate
						JdbcTemplate dao2=applicationContext.getBean("jdbctemplate",JdbcTemplate.class);
						//dao2.setDataSource(dataSource);
						//查詢
						List<Customers> data=dao2.query("select * from customers where customerid='"+customers.getCustomerid()+"'",
								new BeanPropertyRowMapper(Customers.class));
						if(data.size()>0)
						{
							throw new SQLException("客戶編號重複!!");
						}
						
						//額外處理
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