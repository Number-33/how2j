<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/26
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>主页轮播图</title>
<style>
    div.categoryWithCarousel{
        width: 100%;
        position:relative;
    }
    div.categoryWithCarousel div.headbar{
        background-color: #DD2727;
    }
    div.categoryWithCarousel div.head{
        width: 200px;
        background-color: #C60A0A;
        height: 36px;
        line-height: 36px;
        font-size: 16px;
        font-weight: bold;
        color: white;
        margin-left: 20px;
        display: inline-block;
    }
    div.categoryWithCarousel div.rightMenu a{
        font-size: 16px;
        color: white;
        text-decoration:none;
    }
    div.categoryWithCarousel div.rightMenu span{
        margin: 0px 20px 0px 20px;
    }
    div.categoryWithCarousel div.rightMenu img{
        height: 30px;
    }
    div.carousel-of-product{
        height: 510px;
        width:1024px;
        margin:0px auto;
    }
    img.carouselImage{
        height: 510px !important;
    }
    div.carouselBackgroundDiv{
        width:100%;
        height:510px;
        background-color:#E8E8E8;
        position:absolute;
        top:36px;
        z-index:-1;
    }
</style>

<div class="categoryWithCarousel">
    <div class="headbar">
        <div class="head ">
            <span class="glyphicon glyphicon-th-list" style="margin-left:10px"></span>
            <span style="margin-left:10px">快速挂号</span>
        </div>
    </div>
    <div data-ride="carousel" class="carousel-of-product carousel slide" id="carousel-of-product">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li class="active" data-slide-to="0" data-target="#carousel-of-product"></li>
            <li data-slide-to="1" data-target="#carousel-of-product" class=""></li>
            <li data-slide-to="2" data-target="#carousel-of-product" class=""></li>
            <li data-slide-to="3" data-target="#carousel-of-product" class=""></li>
        </ol>
        <!-- Wrapper for slides -->
        <div role="listbox" class="carousel-inner">
            <div class="item active">
                <img src="img/lunbo/50.jpg" class="carousel carouselImage">
            </div>
            <div class="item">
                <img src="img/lunbo/51.jpg" class="carouselImage">
            </div>
            <div class="item">
                <img src="img/lunbo/52.jpg" class="carouselImage">
            </div>
            <div class="item">
                <img src="img/lunbo/53.jpg" class="carouselImage">
            </div>
        </div>
    </div>
    <div class="carouselBackgroundDiv">
    </div>
</div>
