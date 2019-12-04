<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/28
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<style>
    div.imgAndInfo{
        margin: 40px 20px;
    }
    div.imgInimgAndInfo{
        width: 400px;
        float: left;
    }

    div.imgAndInfo img.bigImg{
        width: 400px;
        height: 400px;
        padding: 20px;
        border: 1px solid #F2F2F2;
    }

    div.infoInimgAndInfo{
        padding: 0px 20px;
        overflow: hidden;
    }

    div.infoInimgAndInfo div.doctorname{
        color: #2b669a;
        font-size: 20px;
        font-weight: bold;
        margin: 0px 10px;
    }
    div.infoInimgAndInfo div.doctorsubtitle{
        color: #999999;
        font-size: 20px;
        font-weight: bold;
        margin: 0px 10px;
    }

    div.infoInimgAndInfo span.promotionPriceYuan{
        font-family: Arial;
        font-size: 18px;
        color: #C40000;
    }
    div.infoInimgAndInfo span.promotionPrice{
        color: #c40000;
        font-family: Arial;
        font-size: 30px;
        font-weight: bold;
    }
    div.infoInimgAndInfo span.promotionPriceDesc{
        color: #999999;
        font-size: 20px;
        font-weight: bold;
        margin: 0px 10px;
    }

    div.infoInimgAndInfo div.productSaleAndReviewNumber{
        margin: 20px 0px;
        border-top-style: dotted;
        border-top-color: #C9C9C9;
        border-top-width: 1px;
        border-bottom-style: dotted;
        border-bottom-color: #C9C9C9;
        border-bottom-width: 1px;
        padding: 10px;
    }



</style>
<title>Title</title>

<div class="imgAndInfo">
    <div class="imgInimgAndInfo">

        <img class="bigImg" width="100px"  src="img/doctor/${doctorimageid}.jpg" >
        <div class="img4load hidden" ></div>
    </div>

    <div class="infoInimgAndInfo">
        <div class="doctorname">
            <span>医生姓名：</span>${doctor.name}
        </div>
        <div class="doctorsubtitle"> <span>医生简介</span>
            ${doctor.subTile}
        </div>

        <div class="promotionDiv">
            <span class="promotionPriceDesc">挂号费</span>
            <span class="promotionPriceYuan"> ￥</span>
            <span class="promotionPrice">
                <fmt:formatNumber type="number" value="${doctor.promotePrice}" minFractionDigits="2"/>
            </span>

        </div>

        <div class="productSaleAndReviewNumber">
            <div>挂号数量<span class="redColor blodWord">${doctor.stock}</span></div>
            <div>累计评论 <span class="redColor boldWord">${doctor.reviewCount}</span></div>
        </div>
        <div class=""> <a class="" href="forebuyone?pid=${doctor.id}"><button class="">立即挂号</button></a></div>
    </div>
    <div style="clear: both"></div>
    <br>
    <br>
    <br>
    <br>
</div>

