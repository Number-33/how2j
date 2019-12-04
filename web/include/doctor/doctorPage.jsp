<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/28
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>




<title>医生信息界面${p.name}</title>
<div class="imgInimgAndInfo">
    <img class="bogImg" width="100px" src="img/category/${p.category.id}.jpg">
</div>
<div class="productPageDiv">
    <%@include file="imgAndInfo.jsp"%>
    <%@include file="doctorReview.jsp"%>

</div>






