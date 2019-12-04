<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/24
  Time: 3:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/information";
        request.setAttribute("basePath",basePath);
    %>

<%--<script>--%>
<%--    $('#recharge').on('click', function() {--%>
<%--    window.location.href=contextPath+"/get?money=moeny";--%>
<%--    });--%>

<%--</script>--%>
    <script type="text/javascript">
        function doctor(){
        windows.location.href(${basePath});
        }
    </script>

    <title>医生信息介绍</title>
</head>
<body>
<%--<button id="recharge" >获取名字</button>--%>
<%--
<form action="information" method="post">
    <input type="button" onclick="doctor()" name = look value = "查看">
</form>--%>
<input type="button" onclick="doctor()" name = look value = "查看">


<%
    String s =(String)request.getAttribute( "name ");
%>
<%=basePath%>
<%--获取医生名字：<c:out value="${name}"/>--%>
name:${name}
</body>
</html>
