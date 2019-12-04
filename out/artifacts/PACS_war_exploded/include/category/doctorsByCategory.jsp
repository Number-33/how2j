<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/29
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>




<title>科室医生信息界面</title>

<div>这里是科室医生信息界面</div>
<div>${category.name}</div>
<div class="">
    <c:forEach items="${category.doctors}" var="p" varStatus="stc">
        <div class="" price = "$P.promotePrice">
            <div class="">
                <a href="foredoctor?pid=${p.id}">
                    <img class="" src="img/doctor/">
                </a>
                <span class="productPrice">¥<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/></span>
                <a class="productLink" href="foredoctor?pid=${p.id}">
                        ${p.name}
                </a>
            </div>
        </div>
    </c:forEach>
</div>

