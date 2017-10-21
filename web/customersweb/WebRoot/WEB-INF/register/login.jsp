<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>登入作業</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 	<jsp:include page="/banner.jsp"></jsp:include>
    <!-- 靜態網頁標籤 -->
    <s:form method="post" action="uservalid.action">
    	<fieldset>
    		<legend>登入作業</legend>
    		<s:textfield label="使用者名稱" name="login.userName"></s:textfield>
    		<br/>
    		<s:password label="使用者密碼" name="login.password"></s:password>
    		<s:submit value="登入"></s:submit>
    	</fieldset>
    </s:form>
  </body>
</html>
