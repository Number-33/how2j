package pacs.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.glassfish.gmbal.Description;
import org.apache.commons.lang.StringUtils;

import pacs.bean.Category;
import pacs.bean.Doctor;
import pacs.bean.OrderItem;
import pacs.bean.User;
import pacs.dao.CategoryDAO;
import pacs.dao.DoctorDAO;

/**
 * @program: PACS
 * @Date: 2019/11/26 18:01
 * @Author: Mr.Liu
 * @Description:
 */



@WebFilter(filterName = "foreServletFilter",urlPatterns = "/*")
public class foreServletFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println("开始foreServletFilter.....");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String contextPath = request.getServletContext().getContextPath();//获取主路径  .../test/
        request.getServletContext().setAttribute("contextPath",contextPath);
        User user =(User) request.getSession().getAttribute("user");//把传递过来的用户信息提取出来
        //int cartTotalItemNumber= 0;



        List<Category> cs=(List<Category>) request.getAttribute("categorys");//将页面中的科室医生信息提取出来
        if(null==cs){//如果信息为空，初次访问主页面
            cs=new CategoryDAO().list();//从数据库调出科室信息
            //new DoctorDAO().fill(cs);
            request.setAttribute("categorys", cs);//将科室信息放入参数域中
            System.out.println("科室信息初始化完成");
/*            for(Category category:cs) {
                List<Doctor> doctors = category.getDoctors();
                for(Doctor d : doctors){
                    System.out.println(d.getName());
                }
            }*/
        }

        String uri = request.getRequestURI();//获取网址信息，完整的网址
        uri =StringUtils.remove(uri, contextPath);//提取出mapper路径，此次的访问路径
        if(uri.startsWith("/fore")&&!uri.startsWith("/foreServlet")){//路径以/fore开头，且不以/foreServlet开头
            String method = StringUtils.substringAfterLast(uri,"/fore" );//取出/fore之后的字符串，也就是方法
            System.out.println("提取出方法为"+method);
            request.setAttribute("method", method);//将方法放入参数域
            System.out.println("准备跳转到BaseForeServlet调用"+method+"方法");


            req.getRequestDispatcher("/foreServlet").forward(request, response);//跳转到ForeServlet调用具体方法
            return;
        }
        System.out.println("结束foreServletFilter....");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
