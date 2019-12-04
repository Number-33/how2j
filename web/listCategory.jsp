<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/24
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
<%--         pageEncoding="UTF-8" isELIgnored="false" %>--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@include file="include/header.jsp"%>

<html>
<head>   <meta charset="utf-8">
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
    <title>登陆界面</title>

    <!-- 引入Bootstrap核心样式文件 -->

    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果，必须放置到web服务器中，暂时不必掌握 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->





<script>

    $(function(){

        $("#addForm").submit(function(){
            if(!checkEmpty("name","科室名称"))
                return false;
           // if(!checkEmpty("categoryPic","科室图片"))
           //     return false;
            return true;
        });
    });

</script>

    <title>科室分类管理</title>
</head>
<body>
    <div class="workingArea">
        <h1 class="label label-info" style="font-size: 20px"><a  href="admin_category_list">科室分类管理</a></h1>
        <h1 class="label label-info"style="font-size: 20px" ><a href="admin_user_list">用户管理</a></h1>
        <a href="forelogout">退出</a>
        <br>
        <br>

        <div class="listDataTableDiv">
            <table class="table table-striped table-bordered table-hover table-condensed">
                <thead>
                    <tr class="success">
                        <th>ID</th>
                        <th>图片</th>
                        <th>科室名称</th>
                        <th>属性管理</th>
                        <th>医生管理</th>
                        <th>编辑</th>
                        <th>删除</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${allcategory}" var="c">


                        <tr>
                            <td>${c.id}</td>
                            <td><img height="40px" src="img/doctor/1.jpg"></td>
                            <td>${c.name}</td>

                            <td><a href="admin_property_list?cid=${c.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
                            <td><a href="admin_doctor_list?cid=${c.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
                            <td><a href="#nowhere"><span class="glyphicon glyphicon-edit"></span></a></td>
                            <td><a deleteLink="true" href="admin_category_delete?id=${c.id}"><span class="glyphicon glyphicon-trash"></span></a></td>

                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>
        <div class="pageDiv">
            <%@include file="include/admin/adminPage.jsp" %>
        </div>

        <div class="panel panel-warning addDiv">
            <div class="panel-heading">新增科室</div>
            <div class="panel-body">
                <form method="post" id="addForm" action="admin_category_add" enctype="multipart/form-data">
                    <table class="addTable">
                        <tr>
                            <td>科室名称</td>
                            <td><input  id="name" name="name" type="text" class="form-control"></td>
                        </tr>
                        <tr>
                            <td>科室图片</td>
                            <td>
                                <input id="categoryPic" accept="image/*" type="file" name="filepath" />
                            </td>
                        </tr>
                        <tr class="submitTR">
                            <td colspan="2" align="center">
                                <button type="submit" class="btn btn-success">提 交</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>

    <%@include file="include/footer.jsp"%>

</body>
</html>
