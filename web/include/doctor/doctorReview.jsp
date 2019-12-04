<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/28
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<title>医生评论</title>
<style>

    div.productReviewDiv{
        width: 790px;
        margin: 20px auto;
    }
    div.productReviewTopPart{
        border: 1px solid #DFDFDF;
    }
    a.productReviewTopPartSelectedLink{
        padding: 0px 20px;
        color: #333333;
    }
    a.selected{
        border-left: 1px solid #cfbfb1;
        border-right: 1px solid #cfbfb1;
        color: #b10000;
        display: inline-block;
        font-weight: bold;
        line-height: 46px;
        width: 90px;
        text-align: center;
        position: relative;
    }
    a.selected:before{
        content: "";
        display: block;
        border-width: 1px;
        border-style: solid;
        border-color: #b00000;
        width: 90px;
        height: 0;
        position: absolute;
        top: -1px;
        margin-left: -1px;
    }
    a.selected:after{
        content: "";
        display: block;
        border-color: #b00000 transparent transparent;
        border-style: solid;
        border-width: 5px;
        width: 0;
        height: 0;
        position: absolute;
        top: -1px;
        left: 50%;
        margin-left: -5px;
    }
    div.productReviewContentPart{
        padding-top: 50px;
    }
    div.productReviewItem{
        border-bottom: 1px solid #E3E3E3;
        margin: 10px 0px;
    }
    div.productReviewItem div.productReviewItemDesc{
        width: 80%;
        display: inline-block;
        color: #333333;
        margin: 5px 20px;
        float: left;
    }
    div.productReviewItemDate{
        margin: 15px 0px 0px 0px;
        color: #CCCCCC;
    }
    div.productReviewItem div.productReviewItemUserInfo{
        color: #404040;
        margin: 5px 20px;
        padding: 20px 0;
    }
    span.userInfoGrayPart{
        color: #999999;
    }
    a:hover{
        color:#C40000;
        text-decoration:none;
    }
</style>
<div class="productPageDiv">
    <div class="productReviewDiv" style="display: block;">
        <div class="productReviewTopPart">
            <a href="#nowhere"class="">累计评论<span class="productReviewTopPartSelectedLink">${doctor.reviewCount}累计评论数量</span></a>
        </div>
    </div>

    <div class="productReviewContentPart">
        <c:forEach items="${reviews}" var="r">
            <div class="productReviewItem">

                <div class="productReviewItemDesc">
                    <div class="productReviewItemContent">
                            ${r.content }
                    </div>
                    <div class="productReviewItemDate"><fmt:formatDate value="${r.createDate}" pattern="yyyy-MM-dd"/></div>
                </div>
                <div class="productReviewItemUserInfo">

                        ${r.user.anonymousName}<span class="userInfoGrayPart">（匿名）</span>
                </div>

                <div style="clear:both"></div>

            </div>
        </c:forEach>
    </div>


</div>


