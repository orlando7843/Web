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
  	<legend>�Ȥ��ƺ��@</legend>
  	<s:form>
  		<s:textfield label="�Ȥ�s��" name="customers.customerid"></s:textfield>
  		<s:textfield label="���q�渹" name="customers.companyname"></s:textfield>
  		<s:textfield label="�p���H" name="customers.contactname"></s:textfield>
  		<s:textfield label="�p���a�}" name="customers.address"></s:textfield>
  		<s:textfield label="�s���q��" name="customers.phone"></s:textfield>
  		<s:textfield label="EMAIL" name="customers.email"></s:textfield>
  		<s:submit value="�s�W"></s:submit>
  		<s:reset value="����"></s:reset>
  	</s:form>
  </fieldset>
  </body>
</html>
