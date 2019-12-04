<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/23
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>



<style>
    body{
        font-size: 12px;
        font-family: Arial;
    }
    a{
        color:#999;
    }
    .redColor{
        color: #C40000 !important;
    }
    nav.top{
        background-color: #f2f2f2;
        padding-top: 5px;
        padding-bottom: 5px;
        border-bottom:1px solid  #e7e7e7;
    }
    nav.top span, nav.top a{
        color: #999;
        margin: 0px 10px 0px 10px;
    }
    nav.top a:hover{
        color: #C40000;
        text-decoration: none;
    }
</style>

<nav class="top ">
    <a href="forehome">
        <span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-home redColor"></span>
        PACS首页
    </a>

    <span>亲，欢迎来PACS挂号系统</span>

    <c:if test="${!empty user}">
        <a href="login.jsp">${user.name}</a>
        <a href="forelogout">退出</a>
    </c:if>

    <c:if test="${empty user}">
        <a href="login.jsp">请登录</a>
        <a href="register.jsp">免费注册</a>
    </c:if>

    <span style="float:right !important;">
            <a href="forebought">我的挂号单</a>

    </span>

</nav>
