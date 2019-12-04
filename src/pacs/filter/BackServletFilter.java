package pacs.filter;

import com.sun.org.glassfish.gmbal.Description;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "backservletfilter",urlPatterns = "/*")
public class BackServletFilter implements Filter {
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req; //类型转换
        HttpServletResponse response = (HttpServletResponse) res;
        request.setCharacterEncoding("UTF-8");
        System.out.println("开始BackSercletFilter.....");
        String contextPath=request.getServletContext().getContextPath();//获取访问的目录，即httP://localhost:8080/test
        String uri = request.getRequestURI();//访问的地址，完整目录
        uri = StringUtils.remove(uri, contextPath);//得到目标目录，类似于admin_category_**的servlet访问目录
        if(uri.startsWith("/admin_")){//.admin_开头，表示的是管理员界面
            String servletPath = StringUtils.substringBetween(uri,"_", "_") + "Servlet";//取出category，拼接Servlet得到categoryServlet
            String method = StringUtils.substringAfterLast(uri,"_" );//取出Crud方法，例如list
            request.setAttribute("method", method);//将方法出给categoryServlet调用其中的list方法
            System.out.println("后台调用的方法为"+method+"，将其传入参数域方便"+servletPath+"调用");
            System.out.println(servletPath);
            req.getRequestDispatcher("/" + servletPath).forward(request, response);

            return;
        }
        System.out.println("结束BackServletFilter.....");
        chain.doFilter(request, response);//进行下一个拦截
    }

    public void init(FilterConfig arg0) throws ServletException {

    }

}
