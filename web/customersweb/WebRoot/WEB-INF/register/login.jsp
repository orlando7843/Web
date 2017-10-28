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
	<script src="../js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		//驗證jquery引用是否OK?  $() selector ready() Event事件程序點
		$(document).ready(
			//埋入事件程序
			function(){
				//alert('hi jquery...');
				//使用選擇器應對到username password兩個文字輸入方塊物件
				var username=$('#userName');
				var password=$('#password');
				$('#btnLogin').click(
					function(e)
					{
						var result=false;
						//alert('HI Click');
						//動態解除事件程序Click
						//$(this).unbind('click');
						if(username.val().length==0)
						{
							alert('使用者名稱必須要輸入!!');
							result=true;
						}
						if(password.val().length==0)
						{
							alert('使用者密碼必須要輸入!!');
							result=true;
						}
						if(result)
						{
							e.preventDefault();
						}
					}	
						
				); //埋一個事件程序點click
			}
		);
		
		//取出元素(物件)
		function load(){
			var userName=document.getElementById('userName');
			alert(userName);
		}
		//按鈕要觸發事件程序
		function valid()
		{
			var status=true;
			//驗證使用者名稱
			var userName=document.getElementById('userName');
			var password=document.getElementById("password");
			if(userName.value.length==0)
			{
				alert('使用者名稱必須要輸入!!');
				status=false;
			}
			if(password.value.length==0)
			{
				alert('使用者密碼必須要輸入!!');
				status=false;
			}
			return status;
		}
		
	</script>
  </head>
  
  <body>
 	<jsp:include page="/banner.jsp"></jsp:include>
    <!-- 靜態網頁標籤 -->
    <s:form method="post" action="uservalid.action" >
    	<fieldset>
    		<legend>登入作業</legend>
    		<s:textfield label="使用者名稱" name="login.userName" id="userName"></s:textfield>
    		<br/>
    		<s:password label="使用者密碼" name="login.password" id="password"></s:password>
    		<s:submit value="登入" id="btnLogin"></s:submit>
    	</fieldset>
    </s:form>
  </body>
</html>
