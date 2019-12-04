<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/30
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<title>成功付款</title>
<style>
    div.payedDiv{
        border: 1px solid #D4D4D4;
        max-width: 1013px;
        margin: 10px auto 20px auto;
    }
    div.payedTextDiv{
        height: 61px;
        background-color: #ECFFDC;
        padding: 17px 0px 0px 25px;
    }
    div.payedTextDiv span{
        font-weight: bold;
        font-size: 14px;
        margin-left: 10px;
    }
    div.payedAddressInfo{
        padding: 26px 35px;
    }
    div.payedAddressInfo li{
        background-image:url(https://how2j.cn/tmall/img/site/li_dot.png);
        background-repeat: no-repeat;
        background-color: transparent;
        background-attachment: scroll;
        background-position: 0px 13px;
        list-style-type: none;
        color: #333333;
        padding-left: 15px;
        padding-top: 5px;
    }
    span.payedInfoPrice{
        color: #B10000;
        font-weight: bold;
        font-size: 14px;
        font-family: arial;
    }
    div.paedCheckLinkDiv{
        margin-left: 38px;
    }
    a.payedCheckLink{
        color: #2D8CBA;
    }
    a.payedCheckLink:hover{
        color: #2D8CBA;
        text-decoration: underline;
    }
    div.payedSeperateLine{
        border-top: 1px dotted #D4D4D4;
        margin: 0px 31px;
    }
    div.warningDiv{
        margin: 23px 45px;
        color:black;
    }
    body{
        font-size: 12px;
        font-family: Arial;
    }
    .boldWord{
        font-weight: bold;
    }
    .redColor{
        color: #C40000;
    }
</style>
<div class="payedDiv">
    <div class="payedTextDiv">
        <img src="img/pay/paySuccess.png">
        <span>您已成功付款</span>

    </div>
    <div class="payedAddressInfo">
        <ul>
            <li>挂号人：${order.receiver} ${order.mobile }</li>
            <li>实付款：<span class="payedInfoPrice">
            ￥<fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/>
            </li>
        </ul>

        <div class="paedCheckLinkDiv">
            您可以
            <a class="payedCheckLink" href="forebought">查看已付款挂号单</a>
            <a class="payedCheckLink" href="forebought">查看交易详情 </a>
        </div>
    </div>
    <div class="payedSeperateLine">
    </div>

</div>