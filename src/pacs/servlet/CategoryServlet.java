package pacs.servlet;

import pacs.bean.Category;
import pacs.util.ImageUtil;
import pacs.util.Page;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: PACS
 * @Date: 2019/11/24 21:06
 * @Author: Mr.Liu
 * @Description:
 */
@WebServlet("/categoryServlet")
public class CategoryServlet extends BaseBackServlet {

    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        Map<String,String> params = new HashMap<>();
        InputStream is = super.parseUpload(request, params);

        String name= params.get("name");
        Category c = new Category();
        c.setName(name);
        categoryDAO.add(c);

        File imageFolder= new File(request.getSession().getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,c.getId()+".jpg");

        try {
            if(null!=is && 0!=is.available()){
                try(FileOutputStream fos = new FileOutputStream(file)){
                    byte b[] = new byte[1024 * 1024];
                    int length = 0;
                    while (-1 != (length = is.read(b))) {
                        fos.write(b, 0, length);
                    }
                    fos.flush();
                    //通过如下代码，把文件保存为jpg格式
                    BufferedImage img = ImageUtil.change2jpg(file);
                    ImageIO.write(img, "jpg", file);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "@admin_category_list";
    }


    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("获取到要删除科室的id"+id);
        categoryDAO.delete(id);
        System.out.println("成功删除，跳转到科室管理界面");
        return "@admin_category_list";
    }


    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id = Integer.parseInt(request.getParameter("id"));
        Category c = categoryDAO.get(id);
        request.setAttribute("c", c);
        return "admin/editCategory.jsp";
    }


    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
        Map<String,String> params = new HashMap<>();
        InputStream is = super.parseUpload(request, params);
        String name= params.get("name");
        int id = Integer.parseInt(params.get("id"));

        Category c = new Category();
        c.setId(id);
        c.setName(name);
        categoryDAO.update(c);

        File  imageFolder= new File(request.getSession().getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,c.getId()+".jpg");
        file.getParentFile().mkdirs();

        try {
            if(null!=is && 0!=is.available()){
                try(FileOutputStream fos = new FileOutputStream(file)){
                    byte b[] = new byte[1024 * 1024];
                    int length = 0;
                    while (-1 != (length = is.read(b))) {
                        fos.write(b, 0, length);
                    }
                    fos.flush();
                    //通过如下代码，把文件保存为jpg格式
                    BufferedImage img = ImageUtil.change2jpg(file);
                    ImageIO.write(img, "jpg", file);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "@admin_category_list";

    }


    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
        System.out.println("CategoryServlet中的list方法被调用。。。");
        List<Category> cs = categoryDAO.list(page.getStart(),page.getCount());//获取科室信息
        System.out.println("从数据库中获取了科室信息。。。");
        int total = categoryDAO.getTotal();//科室总数
        System.out.println("科室总数为"+total);
        page.setTotal(total);
        System.out.println("将科室总数加载进Page里面");
        request.setAttribute("allcategory", cs);
        System.out.println("科室信息全部加载进参数域");
        request.setAttribute("page", page);
        System.out.println("Page信息加载进参数域");
        System.out.println("跳转到ListCategory.jsp页面。。。");
        return "listCategory.jsp";
    }
}

