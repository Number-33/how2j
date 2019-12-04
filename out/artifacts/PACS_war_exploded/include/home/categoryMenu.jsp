<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/26
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<style>
    div.categoryMenu{
        width: 200px;
        background-color: #e2e2e3;
        margin-left: 20px;
        position: absolute;
        left: 0;
        top: 0;
        z-index: 1;
    }
    div.categoryMenu a{
        color: #000;
    }
    div.categoryMenu a:hover{
        color: lightskyblue;
        text-decoration: none;
    }

</style>
<title>侧栏</title>

<link href="../../css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<div class="categoryMenu">

    <c:forEach items="${categorys}" var="c">
        <div cid="${c.id}" class="eachCategory">
            <span class="glyphicon glyphicon-link"></span>
            <a href="forecategory?cid=${c.id}" style="font-size: 30px">
                    ${c.name}
            </a>
<%--            <a>--%>
<%--                <c:forEach items="c" var="cd">--%>
<%--                    <span>${cd.name}</span>--%>
<%--                </c:forEach>--%>
<%--            </a>--%>
        </div>
    </c:forEach>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../../js/jquery/2.0.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../js/bootstrap/3.3.7/bootstrap.min.js"></script>
