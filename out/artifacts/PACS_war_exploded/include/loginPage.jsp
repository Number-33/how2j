<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/27
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<style>
    div.simpleLogo{
        padding: 32px 0px;
    }
    img.loginBackgroundImg{
        display: block;
        margin: 0px auto;
    }
    div.loginSmallDiv{
        background-color: white;
        position: absolute;
        right: 80px;
        top: 180px;
        width: 350px;
        height: 400px;
        padding: 60px 25px 80px 25px;
    }
    div.login_acount_text{
        color: #3C3C3C;
        font-size: 16px;
        font-weight: bold;
    }
    div.loginInput{
        border: 1px solid #CBCBCB;
        margin: 20px 0px;
    }
    div.loginInput span.loginInputIcon{
        margin: 0px;
        background-color: #CBCBCB;
        width: 40px;
        height: 40px;
        display:inline-block;
    }
    span.loginInputIcon span.glyphicon{
        font-size: 22px;
        position: relative;
        left: 9px;
        top: 9px;
        color: #606060;
    }
    div.loginInput input{
        display: inline-block;
        border: 0px solid transparent;
        width: 244px;
        height: 30px;
        position: relative;
        left: 6px;
        top: 6px;
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
    button.redButton{
        color: white;
        background-color: #C40000;
        font-size: 14px;
        font-weight: bold;
    }
</style>

<form class="" action="forelogin" src="">
    <div class="loginSmallDiv" id="loginSmallDiv">
    <div class="login_acount_text">账户登陆</div>
        <div class="loginInput ">
            <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-user"></span>
                </span>
            <input id="name" name="name" placeholder="手机/会员名/邮箱" type="text"><br>
        </div>
        <div class="loginInput ">
            <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-lock"></span>
            </span>
            <input id="password" name="password" placeholder="请输入密码" type="password"><br>
        </div>
        <div style="color: red">${wrongmessage}</div>
        <div>
            <a href="#nowhere" class="notImplementLink">忘记登录密码</a>
            <a class="pull-right" href="#nowhere">免费注册</a>
        </div>

        <div style="margin-top:20px">
            <button class="btn btn-block redButton" type="submit">登录</button>
        </div>
    </div>

</form>




