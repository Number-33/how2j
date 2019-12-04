<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/26
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<link href="../css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script>
</script>

<title>用户管理界面</title>


<div class="workingArea">
    <h1 class="label label-info" ><a href="admin_user_list">用户管理</a></h1>

    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>用户名称</th>
                <th>手机号码</th>
                <th>性别</th>
                <th>年龄</th>
                <th>账号</th>
                <th>密码</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${us}" var="u">

                <tr>
                    <td>${u.id}</td>
                    <td>${u.nicheng}</td>
                    <th>${u.phone}</th>
                    <th>${u.gender}</th>
                    <th>${u.age}</th>
                    <th>${u.name}</th>
                    <th>${u.password}</th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>

</div>

<%@include file="../include/admin/adminFooter.jsp"%>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery/2.0.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap/bootstrap.min.js"></script>