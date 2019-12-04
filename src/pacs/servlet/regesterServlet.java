package pacs.servlet;

import pacs.bean.Doctor;
import pacs.bean.User;
import pacs.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: PACS
 * @Date: 2019/11/24 2:27
 * @Author: Mr.Liu
 * @Description:
 */
//测试Ok

@WebServlet("/login")
public class regesterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
        User user = new User();
        String name = request.getParameter("name");
        String nicheng = request.getParameter("nicehng");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String address = request.getParameter("adress");
        String Age = request.getParameter("age");
        int age = Integer.parseInt(Age);

        user.setName(name);
        user.setPassword(password);
        user.setAdress(address);
        user.setAge(age);
        user.setNicheng(nicheng);
        user.setPhone(phone);
        user.setGender(gender);
        UserDAO userdao = new UserDAO();
        userdao.add(user);

        System.out.println("successful");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
