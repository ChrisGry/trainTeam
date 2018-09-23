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
   		<li class="active"><a href="train.jsp">列车安全情况</a></li>
 		<li><a href="sign.jsp">签到情况</a></li>
 		<li><a href="about.jsp">关于开发者</a></li>
	</ul>  
	
	<br/>
	
<table class="table table-striped">
   <thead>
     <tr>
       <th>工号</th>
       <th>姓名</th>
       <th>密码</th>
       <th>身份</th>
       <th>纬度</th>
       <th>经度</th>
       <th>状态</th>
       <th>最后更新时间</th>
     </tr>
   </thead>
   <tbody>
    
     <%
     	List<worker> wlist=workerDAO.allTrain();
    	
     	for(int i=0;i<wlist.size();i++)
     	{
     		String type="";
     		String condition="";
     		
     		if(wlist.get(i).getwType()==0)
     		{
     			type="工人";
     		}
     		else
     		{
     			type="列车员";
     		}
     		
     		switch(wlist.get(i).getwCondition())
     		{
     			case 0: condition="未开启预警";break;
     			case 1: condition="安全";break;
     			case 2: condition="位置预警";break;
     			case 3: condition="求救";break;
     			default:break;
     		}
     		
     		%>
     		<tr>
     			<td><%=wlist.get(i).getwId() %></td>
     			<td><%=wlist.get(i).getwName() %></td>
     			<td><%=wlist.get(i).getwPass() %></td>
     			<td><%=type %></td>
     			<td><%=wlist.get(i).getwLat() %></td>
     			<td><%=wlist.get(i).getwLon() %></td>
     			<%
     				if(wlist.get(i).getwCondition()==0||wlist.get(i).getwCondition()==1) 
     				{
     			%>
     				<td><%=condition %></td>
     			<% 
     				}
     				else
     				{
     			%>
     				<td><font color="red"><%=condition %></font></td>		
     			<% 
     				}
     				
     			%>
     			<td><%=wlist.get(i).getwTime() %></td>
     		</tr>
     		<%
     	}
    
      %>
   </tbody>
 </table>  


  </body>
  
</html>
