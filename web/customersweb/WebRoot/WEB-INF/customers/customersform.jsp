<%@ page language="java" import="java.util.*" pageEncoding="BIG5"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>My JSP 'customersform.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		input[type='text']{
			width:300px;
			color:blue;
			font-size:18px;
		}
	</style>
  </head>
  
  <body>
  <fieldset>
  	<legend>客戶資料維護</legend>
  	<s:form>
  		<s:textfield label="客戶編號" name="customers.customerid"></s:textfield>
  		<s:textfield label="公司行號" name="customers.companyname"></s:textfield>
  		<s:textfield label="聯絡人" name="customers.contactname"></s:textfield>
  		<s:textfield label="聯絡地址" name="customers.address"></s:textfield>
  		<s:textfield label="連絡電話" name="customers.phone"></s:textfield>
  		<s:textfield label="EMAIL" name="customers.email"></s:textfield>
  		<s:submit value="新增"></s:submit>
  		<s:reset value="取消"></s:reset>
  	</s:form>
  </fieldset>
  </body>
</html>
