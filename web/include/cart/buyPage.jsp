<%--
  Created by IntelliJ IDEA.
  User: 刘
  Date: 2019/11/30
  Time: 0:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<style>
    div.addressTip, div.productListTip{
        color: #333333;
        font-size: 16px;
        font-weight: bold;
        text-align: left;
        margin-bottom: 30px;
    }

    table.addressTable{
        margin: 20px 20px;
        width: 600px;
        font-size:20px;
    }
</style>
<title>结算页面</title>

<div class="buyPageDiv">
    <form action="forecreateorder" method="post">
        <div>
        <div class="">
            <img>
            <img>
            <div style="clear: both"></div>

        </div>
        <div class="addressTip">
            请输入个人信息：
        </div>
        <div >
            <table class="addressTable">
            <tr>
                <td class=""> 病人姓名<span class="">*</span></td>

                <td><input type="text" name="receiver" placeholder="请输入病人的真实姓名"></td>
            </tr>
            <tr>
                <td class="">联系方式</td>
                <td><input  type="text" name="mobile" placeholder="请输入联系方式"></td>

            </tr>
            <tr class="">
                <td>备注信息</td>
                <td><input type="text" name="userMessage" placeholder="病人备注信息，如过往病史"></td>
            </tr>
            </table>
        </div>
        </div>

        <br>
        <br>
        <br>
        <br>
        <div class="">
            <div style="font-size: 20px">确认医生信息</div>
            <table>
                <thead>
                    <tr>
                        <th>
                            <a>PACS挂号系统</a>

                    </tr>
                    <tr class="rowborder">
                        <td  colspan="2" ></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </thead>
                <tbody >
                <div style="font-size: 30px">医生名称:${dingdanxiang.doctor.name}</div>
                <div style="font-size: 30px">挂号费用:<span style="color: red">${dingdanxiang.doctor.promotePrice}</span></div>

                </tbody>
            </table>
        </div>
        <div class="submitOrderDiv">
            <button type="submit" class="submitOrderButton">提交账单 </button>
        </div>
    </form>
</div>



</div>


