<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/26
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>侧栏扩充显示栏</title>

<%--<script>
    $(function(){
        $("div.productsAsideCategorys div.row a").each(function(){
            var v = Math.round(Math.random() *6);
            if(v == 1)
                $(this).css("color","#87CEFA");
        });
    });

</script>--%>
<div > 以医生侧栏</div>
<c:forEach items="${categorys}" var="c">
    <div cid="${c.id}" class="productsAsideCategorys">
        ${c}
        <c:forEach items="${c.doctors}" var="ps">
            <div class="row show1">
                <c:forEach items="${ps}" var="p">
<%--                    <c:if test="${!empty p.subTitle}">--%>
                        <a href="foreproduct?pid=${p.id}">
<%--                            <c:forEach items="${fn:split(p.subTitle, ' ')}" var="title" varStatus="st">--%>
<%--                                <c:if test="${st.index==0}">--%>
<%--                                    ${title}--%>
<%--                                </c:if>--%>
<%--                            </c:forEach>--%>
                            ${p.name}

<%--                    </c:if>--%>
                </c:forEach>
                <div class="seperator"></div>
            </div>
        </c:forEach>
    </div>
</c:forEach>
