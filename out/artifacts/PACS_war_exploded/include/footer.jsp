<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/23
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<head>
    <title>页面底端</title>
    <style>
        div.footer{
            margin: 0px 0px;
            border-top-style: solid;
            border-top-width: 1px;
            border-top-color: #e7e7e7;
        }
        div.footer_ensure{
            margin-top: 24px;
            margin-bottom: 24px;
            text-align: center;
        }
        div.footer_desc{
            border-top-style: solid;
            border-top-width: 1px;
            border-top-color: #e7e7e7;
            padding-top: 30px;
            margin: 0px 20px;
        }
        div.footer_desc div.descColumn{
            width: 20%;
            float: left;
            padding-left: 15px;
        }
        div.footer_desc div.descColumn span.descColumnTitle{
            color: #646464;
            font-weight: bold;
            font-size: 16px;
        }
        div.footer_desc a{
            display: block;
            padding-top: 3px;
        }
        body{
            font-size: 12px;
            font-family: Arial;
        }
        a{
            color:#999;
        }
        a:hover{
            text-decoration:none;
            color: #C40000;
        }
    </style>
</head>
<body>
<div style="display: block;" class="footer" id="footer">
    <div class="footer_ensure" id="footer_ensure">

    </div>
    <div class="footer_desc" id="footer_desc">
        <div class="descColumn">
            <span class="descColumnTitle">看病指南</span>
            <a href="#nowhere">免费注册</a>
            <a href="#nowhere">使用教程</a>
            <a href="#nowhere">挂号不排队</a>
        </div>
        <div class="descColumn">
            <span class="descColumnTitle">医疗保障</span>
            <a href="#nowhere">三甲医院</a>
            <a href="#nowhere">顶尖专家</a>
            <a href="#nowhere">实力强劲</a>
        </div>
        <div class="descColumn">
            <span class="descColumnTitle">扩展服务</span>
            <a href="#nowhere">院边停车</a>
            <a href="#nowhere">院边美食</a>
            <a href="#nowhere">城市风景</a>
            <a href="#nowhere">人文历史</a>
        </div>

        <div class="descColumn">
            <span class="descColumnTitle"></span>
            <a href="#nowhere"><img src="https://how2j.cn/tmall/img/site/ma.png"></a>
        </div>

    </div>

    <div style="clear:both"></div>
</div>
<div  style="color: #999999 ;font-size: 30px;text-align:center">@CopyRight 刘彪-叶鑫</div>

</body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery/2.0.0/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap/bootstrap.min.js"></script>
</html>
