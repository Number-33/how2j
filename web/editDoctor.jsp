<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/24
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="include/header.jsp"%>

    <!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
    <!--视口的作用：在移动浏览器中，当页面宽度超出设备，浏览器内部虚拟的一个页面容器，会将页面缩放到设备这么大来展示-->
    <!--width 	设置layout viewport  的宽度，为一个正整数，或字符串"width-device"(表示采用设备的宽度)
        initial-scale 	设置页面的初始缩放值，为一个数字，可以带小数
        minimum-scale 	允许用户的最小缩放值，为一个数字，可以带小数
        maximum-scale 	允许用户的最大缩放值，为一个数字，可以带小数
        height 	设置layout viewport  的高度，这个属性对我们并不重要，很少使用
        user-scalable 	是否允许用户进行缩放，值为"no"或"yes", no 代表不允许，yes代表允许
        如果设置"user-scalable=no",那么"minimum-scale"和"maximum-scale"无效
    -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>医生编辑界面</title>

    <!-- 引入Bootstrap核心样式文件 -->

    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果，必须放置到web服务器中，暂时不必掌握 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->



<script>
    $(function() {
        $("#editForm").submit(function() {
            if (!checkEmpty("name", "医生名称"))
                return false;
//          if (!checkEmpty("subTitle", "小标题"))
//              return false;
            if (!checkNumber("orignalPrice", "原价格"))
                return false;
            if (!checkNumber("promotePrice", "优惠价格"))
                return false;
            if (!checkInt("stock", "库存"))
                return false;
            return true;
        });
    });
</script>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有科室</a></li>
        <li><a href="admin_doctor_list?cid=${p.category.id}">${p.category.name}</a></li>
        <li class="active">${p.name}</li>
        <li class="active">编辑医生</li>
    </ol>

    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑医生</div>
        <div class="panel-body">
            <form method="post" id="editForm" action="admin_doctor_update">
                <table class="editTable">
                    <tr>
                        <td>医生名称</td>
                        <td><input id="name" name="name" value="${p.name}"
                                   type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>医生描述</td>
                        <td><input id="subTitle" name="subTitle" type="text"
                                   value="${p.subTile}"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>原价格</td>
                        <td><input id="orignalPrice" value="${p.orignalPrice}" name="orignalPrice" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>优惠价格</td>
                        <td><input id="promotePrice"  value="${p.promotePrice}" name="promotePrice" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>库存</td>
                        <td><input id="stock"  value="${p.stock}" name="stock" type="text"
                                   class="form-control"></td>
                    </tr>

                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${p.id}">
                            <input type="hidden" name="cid" value="${p.category.id}">
                            <button type="submit" class="btn btn-success">提 交</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>




    <%@include file="include/footer.jsp"%>

