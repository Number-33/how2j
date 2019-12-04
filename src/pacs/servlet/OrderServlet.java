package pacs.servlet;

import pacs.bean.Order;
import pacs.util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: PACS
 * @Date: 2019/11/24 18:53
 * @Author: Mr.Liu
 * @Description:
 */
@WebServlet("/orderServlet")
public class OrderServlet extends BaseBackServlet {

    @Override
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
        return null;
    }

    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
        System.out.println("开始执行OrderServlet的list方法");
        List<Order> orders = orderDAO.list();
        int total = orders.size();
        System.out.println("成功取出"+orders.size()+"条挂号单");
        page.setTotal(total);
        System.out.println("成功将挂号单条数加载进Page");
        System.out.println("将科室总数加载进Page里面");
        request.setAttribute("orders", orders);
        System.out.println("科室信息全部加载进参数域");
        request.setAttribute("page", page);
        System.out.println("Page信息加载进参数域");
        System.out.println("跳转到Listorder.jsp页面。。。");
        return "listorder.jsp";
    }

    public String doctorlist(HttpServletRequest request, HttpServletResponse response, Page page) {
        System.out.println("开始执行OrderServlet的doctorlist方法");
        int did = Integer.parseInt(request.getParameter("userdid"));
        System.out.println("从参数域中取出医生id为"+did);
        List<Order> orders = orderDAO.doctorlist(did);
        int total = orders.size();
        System.out.println("成功取出"+orders.size()+"条挂号单");
        page.setTotal(total);
        System.out.println("成功将挂号单条数加载进Page");
        System.out.println("将科室总数加载进Page里面");
        request.setAttribute("doctororders", orders);
        System.out.println("科室信息全部加载进参数域");
        request.setAttribute("page", page);
        System.out.println("Page信息加载进参数域");
        System.out.println("跳转到Listdoctororder.jsp页面。。。");
        return "listdoctororder.jsp";
    }

}
