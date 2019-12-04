<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/28
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix='fmt' %>
<title>Title</title>

<c:if test="${empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="100"/>
</c:if>

<c:if test="${!empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>
<div>这里是医生信息展示界面</div>
<div class="categoryDoctors">
    <c:forEach items="${category.doctors}" var="p" varStatus="stc">
        <c:if test="${stc.count<=categorycount}">
            <div class="doctorUnit" price="${p.promotePrice}">
                <div class="productUnitFrame">
                    <a href="foredoctor?pid=${p.id}">
                        <img class="doctorImage" src=""><div>这里放一张医生图片</div>
                    </a>
                    <span class="productPrice">￥<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/></span>
                    <a class="productLink" href="foredoctor?pid=${p.id}">
<%--                        ${fn:substring(p.name,0,20)}--%>
                    </a>

                    <div class="">
                        <span class="monthDeal">月挂号数<span class="productDealNumber">${p.saleCount}次</span></span>
                        <<span class="productReview">评价<span class="productReviewNumber">${p.reviewCount}</span></span>
                        <span class="aixing">
                            <a class="" href="#nowhere">
                                <img><span>爱心</span>
                            </a>
                        </span>
                    </div>
                </div>

            </div>
        </c:if>

    </c:forEach>

</div>


