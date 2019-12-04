<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@include file="include/header.jsp"%>
<%@include file="include/top.jsp"%>
<script>
</script>

<title>我的挂号单</title>

<div class="workingArea">
    <%--    <h1 class="label label-info"style="font-size: 20px" ><a href="admin_user_list">用户管理</a></h1>--%>
    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>Ordercode</th>
                <th>病人</th>
                <th>手机号</th>
                <th>用户</th>
                <th>备注信息</th>
                <th>日期</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${os}" var="o">


                <tr>
                    <td>${o.id}</td>
                    <td>${o.orderCode}</td>
                    <td>${o.receiver}</td>
                    <td>${o.mobile}</td>
                    <td>${o.user.name}</td>
                    <td>${o.userMessage}</td>
                    <td>${o.createDate}</td>


                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
<%--
        <div class="pageDiv">
            <%@include file="include/admin/adminPage.jsp" %>
        </div>
--%>


</div>


<%@include file="include/footer.jsp"%>


