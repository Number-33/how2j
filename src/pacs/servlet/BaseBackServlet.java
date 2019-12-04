package pacs.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pacs.dao.*;
import pacs.util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @program: PACS
 * @Date: 2019/11/24 1:05
 * @Author: Mr.Liu
 * @Description:
 */

/*服务端跳转/categoryServlet就到了CategoryServlet这个类里

1. 首先CategoryServlet继承了BaseBackServlet，而BaseBackServlet又继承了HttpServlet
2. 服务端跳转过来之后，会访问CategoryServlet的doGet()或者doPost()方法
3. 在访问doGet()或者doPost()之前，会访问service()方法
4. BaseBackServlet中重写了service() 方法，所以流程就进入到了service()中
5. 在service()方法中有三块内容
5.1 第一块是获取分页信息
5.2 第二块是根据反射访问对应的方法
5.3 第三块是根据对应方法的返回值，进行服务端跳转、客户端跳转、或者直接输出字符串。
6. 第一块和第三块放在后面讲解，这里着重讲解第二块是根据反射访问对应的方法
6.1 取到从BackServletFilter中request.setAttribute()传递过来的值 list
6.2 根据这个值list，借助反射机制调用CategoryServlet类中的list()方法
这样就达到了CategoryServlet.list()方法被调用的效果*/



public abstract class BaseBackServlet extends HttpServlet {

    public abstract String add(HttpServletRequest request, HttpServletResponse response, Page page) ;
    public abstract String delete(HttpServletRequest request, HttpServletResponse response, Page page) ;
    public abstract String edit(HttpServletRequest request, HttpServletResponse response, Page page) ;
    public abstract String update(HttpServletRequest request, HttpServletResponse response, Page page) ;
    public abstract String list(HttpServletRequest request, HttpServletResponse response, Page page) ;


    protected CategoryDAO categoryDAO = new CategoryDAO();
    protected OrderDAO orderDAO = new OrderDAO();
   // protected OrderItemDAO orderItemDAO = new OrderItemDAO();
    protected DoctorDAO doctorDAO = new DoctorDAO();
    protected DoctorImageDAO doctorImageDAO = new DoctorImageDAO();
    protected PropertyDAO propertyDAO = new PropertyDAO();
    protected PropertyValueDAO propertyValueDAO = new PropertyValueDAO();
    protected ReviewDAO reviewDAO = new ReviewDAO();
    protected UserDAO userDAO = new UserDAO();

    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {

            /*获取分页信息*/
            int start= 0;
            int count = 5;
            try {
                start = Integer.parseInt(request.getParameter("page.start"));
            } catch (Exception e) {

            }
            try {
                count = Integer.parseInt(request.getParameter("page.count"));
            } catch (Exception e) {
            }
            Page page = new Page(start,count);

            /*借助反射，调用对应的方法*/
            String method = (String) request.getAttribute("method");
            Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
                    javax.servlet.http.HttpServletResponse.class,Page.class);
            String redirect = m.invoke(this,request, response,page).toString();

            /*根据方法的返回值，进行相应的客户端跳转，服务端跳转，或者仅仅是输出字符串*/
            if(redirect.startsWith("@"))
                response.sendRedirect(redirect.substring(1));//客户端跳转
            else if(redirect.startsWith("%"))
                response.getWriter().print(redirect.substring(1));//输出内容
            else
                request.getRequestDispatcher(redirect).forward(request, response);//服务器跳转

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public InputStream parseUpload(HttpServletRequest request, Map<String, String> params) {
        InputStream is =null;
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 设置上传文件的大小限制为10M
            factory.setSizeThreshold(1024 * 10240);

            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
                    // item.getInputStream() 获取上传文件的输入流
                    is = item.getInputStream();
                } else {
                    String paramName = item.getFieldName();
                    String paramValue = item.getString();
                    paramValue = new String(paramValue.getBytes("ISO-8859-1"), "UTF-8");
                    params.put(paramName, paramValue);
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }




}