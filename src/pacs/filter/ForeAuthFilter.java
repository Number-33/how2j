package pacs.filter;

import org.apache.commons.lang.StringUtils;
import pacs.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @program: PACS
 * @Date: 2019/11/27 12:20
 * @Author: Mr.Liu
 * @Description:
 */
@WebFilter(filterName = "ForeAuthFilter",urlPatterns = "/*")
public class ForeAuthFilter implements Filter {
    @Override
    public void destroy() {


    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("开始ForesAuthFilter.....");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String contextPath=request.getServletContext().getContextPath();//取出跟路径

        String[] noNeedAuthPage = new String[]{
                "home",
                "checkLogin",
                "register",
                "loginAjax",
                "login",
                "doctor",
                "category",
                "search"};


        String uri = request.getRequestURI();//获取完整网址
        uri = StringUtils.remove(uri, contextPath);//取出访问路径
        if(uri.startsWith("/fore")&&!uri.startsWith("/foreServlet")){//如果路径以/fore开头且不以/foreServlet开头，即此次为前端访问
            String method = StringUtils.substringAfterLast(uri,"/fore" );//取出/fore之后的信息
            if(!Arrays.asList(noNeedAuthPage).contains(method)){//查询前端访问方法中是否包含此次的访问方法
                User user =(User) request.getSession().getAttribute("user");//将session中的用户信息取出来
                if(null==user){
                    response.sendRedirect("login.jsp");//如果信息为空，木有登陆，转到登陆界面，这样的话，就可以保证某些方法需要的权限
                    return;
                }
            }
        }
        System.out.println("结束ForeAuthFilter......");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }


}
