package pacs.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: PACS
 * @Date: 2019/11/24 21:47
 * @Author: Mr.Liu
 * @Description:
 */
@WebFilter(filterName = "EncodingFilter",urlPatterns = "/*")
public class EncodingFilter implements Filter {
    public void destroy() {
    }

@Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request =(HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        System.out.println("编码设置");
        request.setCharacterEncoding("UTF-8");//设置编码方式为utf-8,在拦截器里设置，科室省去为每个servlet设置的麻烦

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
