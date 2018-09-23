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
    
     <style>
            /*web background*/
            .container{
                display:table;
                height:100%;
            }

            .row{
                display: table-cell;
                vertical-align: middle;
            }
            /* centered columns styles */
            .row-centered {
                text-align:center;
            }
            .col-centered {
                display:inline-block;
                float:none;
                text-align:left;
                margin-right:-4px;
            }
            
        </style>

  </head>
  
  <body>
	<br/>
	<ul class="nav nav-tabs">
   		<li><a href="worker.jsp">工人安全情况</a></li>
   		<li><a href="train.jsp">列车安全情况</a></li>
 		<li><a href="sign.jsp">签到情况</a></li>
 		<li class="active"><a href="about.jsp">关于开发者</a></li>
	</ul>
	
	<div class="container">
  	<div class="row row-centered">
  		<p>受铁路局委托，开发此铁路施工队智能预警原型系统</p>
  		<p>这个通过可携带设备、手机和服务器构建的多维度预警系统，旨在解决铁路施工工人伤亡率较高的问题</p>
  		<p>开发者：关润宇 洪思虹 李逸文</p>
  		<p>信息科学与工程学院 湖南大学 2018.9</p>
  	</div>
  </div>   

  </body>
  
</html>
