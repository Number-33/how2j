package pacs.servlet;

import pacs.dao.*;
import pacs.util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @program: PACS
 * @Date: 2019/11/24 1:04
 * @Author: Mr.Liu
 * @Description:
 */
@WebServlet("/BaseForeServlet")
public class BaseForeServlet extends HttpServlet {
    protected CategoryDAO categoryDAO = new CategoryDAO();
    protected OrderDAO orderDAO = new OrderDAO();
    protected OrderItemDAO orderItemDAO = new OrderItemDAO();
    protected DoctorDAO doctorDAO = new DoctorDAO();
    protected DoctorImageDAO doctorImageDAO = new DoctorImageDAO();
    protected PropertyDAO propertyDAO = new PropertyDAO();
    protected PropertyValueDAO propertyValueDAO = new PropertyValueDAO();
    protected ReviewDAO reviewDAO = new ReviewDAO();
    protected UserDAO userDAO = new UserDAO();

    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {

            int start= 0;
            int count = 10;
            try {
                start = Integer.parseInt(request.getParameter("page.start"));//获取session里的page参数，不过不一定会有此参数，所以抛出错误

            } catch (Exception e) {

            }

            try {
                count = Integer.parseInt(request.getParameter("page.count"));//获取page
            } catch (Exception e) {
            }

            Page page = new Page(start,count);

            String method = (String) request.getAttribute("method");//取得方法信息
            System.out.println("BaseForeServlet获得了此次将调用的方法为"+method);

            Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
                    javax.servlet.http.HttpServletResponse.class,Page.class);//运用反射来调用

            String redirect = m.invoke(this,request, response,page).toString();

            System.out.println("获得了反射路径"+redirect);
            if(redirect.startsWith("@"))
                response.sendRedirect(redirect.substring(1));
            else if(redirect.startsWith("%"))
                response.getWriter().print(redirect.substring(1));
            else
                request.getRequestDispatcher(redirect).forward(request, response);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
