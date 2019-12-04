<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/26
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@include file="include/header.jsp"%>
<%@include file="include/top.jsp"%>


<script>
</script>

<title>医生订单</title>

<h1>1111${userdid}</h1>
<div class="workingArea">
    <div style="font-size: 20px" class="label label-info"><a href="admin_order_doctorlist?userdid=${userdid}">医生看病</a></div>
    <a href="forelogout">退出</a>
    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>挂号单号</th>
                <th>病人</th>
                <th>手机号</th>
                <th>备注</th>
                <th>日期</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${doctororders}" var="u">

                <tr>
                    <td>${u.id}</td>
                    <td>${u.orderCode}</td>
                    <th>${u.receiver}</th>
                    <th>${u.mobile}</th>
                    <th>${u.userMessage}</th>
                    <th>${u.createDate}</th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="include/admin/adminPage.jsp" %>
    </div>

</div>

<%@include file="include/footer.jsp"%>







