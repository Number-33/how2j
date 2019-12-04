<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/30
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<style>
    body{
        font-size: 12px;
        font-family: Arial;
    }
    div.aliPayPageDiv{
        text-align: center;
        padding-bottom: 40px;
        max-width: 1013px;
        margin: 10px auto;
    }
    div.aliPayPageLogo{
        margin: 20px;
    }
    span.confirmMoneyText{
        color: #4D4D4D;
    }
    span.confirmMoney{
        display: block;
        color: #FF6600;
        font-weight: bold;
        font-size: 20px;
        margin: 10px;
    }
    button.confirmPay{
        background-color: #00AAEE;
        border: 1px solid #00AAEE;
        text-align: center;
        line-height: 31px;
        font-size: 14px;
        font-weight: 700;
        color: white;
        width: 107px;
        margin-top: 20px;
</style>


<title>支付页面</title>
<div class="aliPayPageDiv">
    <div class="aliPayPageLogo">
<%--        <img class="pull-left" src="img/site/simpleLogo.png">--%>
        <div style="clear:both"></div>
    </div>

    <div>
        <div> PACS挂号</div>
        <span class="confirmMoneyText">扫一扫付款（元）</span>
        <span class="confirmMoney">
        ￥ <fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/>
        </span>

    </div>
    <div>
        <img class="aliPayImg" src="img/pay/100.jpg">
    </div>

    <div>
        <a href="forepayed?oid=${param.oid}&total=${param.total}"><button class="confirmPay">确认支付</button></a>
    </div>

</div>



