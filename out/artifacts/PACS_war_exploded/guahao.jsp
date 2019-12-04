<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/27
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<title>挂号演示页面</title>

<script>


    function changeDoctor(val){

        //7.获取第二个下拉列表
        var cityEle = document.getElementById("doctor1");

        //9.清空第二个下拉列表的option内容
        cityEle.options.length=0;

        //2.遍历二维数组中的省份
        for(var i=0;i<cities.length;i++){
            //注意，比较的是角标
            if(val==i){
                //3.遍历用户选择的省份下的城市
                for(var j=0;j<cities[i].length;j++){
                    //alert(cities[i][j]);
                    //4.创建城市的文本节点
                    var textNode = document.createTextNode(cities[i][j]);
                    //5.创建option元素节点
                    var opEle = document.createElement("option");
                    //6.将城市的文本节点添加到option元素节点
                    opEle.appendChild(textNode);
                    //8.将option元素节点添加到第二个下拉列表中去
                    cityEle.appendChild(opEle);
                }
            }
        }
    }

    var a = ${categorys};
    function changdoctor(val){
        //获取第二个下拉列表
        var doctorEle = document.getElementById("doctor");
        //清空第二个下拉列表的option内容
        doctorEle.options.length=0;
        //遍历二维数组中的科室
        for(var j=0;j<a.length;j++){
            //遍历科室
            for(var i=0 ;i<a[j].length;i++)
            var textNode = document.createTextNode(a[j][i]);
            alert(a[j][i]);
            //5.创建option元素节点
            var opEle = document.createElement("option");
            //6.将城市的文本节点添加到option元素节点
            opEle.appendChild(textNode);
            //8.将option元素节点添加到第二个下拉列表中去
            cityEle.appendChild(opEle);
        }

    }
</script>

<form action="" method="post">

    <ul>
        <li>A</li>
        <li>B</li>

    </ul>


    <select onchange="changeDoctor(this.value)">
        <option>--请选择科室--</option>
        <option value="0">脑科</option>
        <option value="1">眼科</option>
        <option value="2">内科</option>
        <option value="3">外科</option>
    </select>
    <select>

    </select>

    <select>
        <option onchang="changedoctor(this.value)">--请选择科室--</option>
            <c:forEach items="${categorys}" var="category" >
                <option value="$category.id">${category.name}+${category.id}</option>
            </c:forEach>


    </select id="doctor">
    <select>

    </select>

</form>


<div>
    <div>开始</div>
    <c:forEach items="${categorys}" var="category">
        <div>${category.name}</div>

    </c:forEach>
</div>

