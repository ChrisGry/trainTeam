<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="utils.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'worker.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap的HTML标准模板</title>
    <!-- Bootstrap -->
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">

    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>

    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

  </head>
  
  <body>
	<br/>
	<ul class="nav nav-tabs">
   		<li><a href="worker.jsp">工人安全情况</a></li>
   		<li><a href="train.jsp">列车安全情况</a></li>
 		<li class="active"><a href="sign.jsp">签到情况</a></li>
 		<li><a href="about.jsp">关于开发者</a></li>
	</ul>
	
	<br/>
	
<table class="table table-striped">
   <thead>
     <tr>
       <th>工号</th>
       <th>姓名</th>
       <th>签到日期</th>
       <th>签退日期</th>
       <th>状态</th>
     </tr>
   </thead>
   <tbody>
    
     <%
     	List<sign> slist=workerDAO.allSign();
    	
     	for(int i=0;i<slist.size();i++)
     	{
     		
     		String condition="";
     		
     		switch(slist.get(i).getsCondition())
     		{
     			case 0: condition="已签退";break;
     			case 1: condition="已签到";break;
     			default:break;
     		}
     		
     		%>
     		<tr>
     			<td><%=slist.get(i).getsWid()%></td>
     			<td><%=slist.get(i).getwName()%></td>
     			<td><%=slist.get(i).getsOnTime()%></td>
     			<td><%=slist.get(i).getsOffTime()%></td>
     			<td><%=condition%></td>
     		</tr>
     		<%
     	}
    
      %>
   </tbody>
 </table>  
	

  </body>
  
</html>
