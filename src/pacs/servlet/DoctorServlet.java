package pacs.servlet;

import pacs.bean.Category;
import pacs.bean.Doctor;
import pacs.bean.Property;
import pacs.bean.PropertyValue;
import pacs.util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @program: PACS
 * @Date: 2019/11/24 22:46
 * @Author: Mr.Liu
 * @Description:
 */
@WebServlet("/doctorServlet")
public class DoctorServlet extends BaseBackServlet {
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        Category c = categoryDAO.get(cid);

        String name= request.getParameter("name");
        String subTitle= request.getParameter("subTitle");
        float orignalPrice = Float.parseFloat(request.getParameter("orignalPrice"));
        float promotePrice = Float.parseFloat(request.getParameter("promotePrice"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        Doctor p = new Doctor();

        p.setCategory(c);
        p.setName(name);
        p.setSubTile(subTitle);
        p.setOrignalPrice(orignalPrice);
        p.setPromotePrice(promotePrice);
        p.setStock(stock);
        p.setCreateDate(new Date());

        doctorDAO.add(p);
        return "@admin_doctor_list?cid="+cid;
    }

    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        System.out.println("调用DoctorServlet的delete功能。。");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("删除医生编号为"+id);
        Doctor p = doctorDAO.get(id);
        System.out.println("获取此医生科室编号为"+p.getName());
        doctorDAO.delete(id);
        System.out.println("删除此医生。。");
        System.out.println("跳转到科室医生管理界面");
        return "@admin_doctor_list?cid="+p.getCategory().getId();
    }

    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id = Integer.parseInt(request.getParameter("id"));
        Doctor p = doctorDAO.get(id);
        request.setAttribute("p", p);
        return "editDoctor.jsp";
    }

    public String editPropertyValue(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id = Integer.parseInt(request.getParameter("id"));
        Doctor p = doctorDAO.get(id);
        request.setAttribute("p", p);

        List<Property> pts= propertyDAO.list(p.getCategory().getId());
        propertyValueDAO.init(p);

        List<PropertyValue> pvs = propertyValueDAO.list(p.getId());

        request.setAttribute("pvs", pvs);

        return "editDoctorValue.jsp";
    }

    public String updatePropertyValue(HttpServletRequest request, HttpServletResponse response, Page page) {
        int pvid = Integer.parseInt(request.getParameter("pvid"));
        String value = request.getParameter("value");

        PropertyValue pv =propertyValueDAO.get(pvid);
        pv.setValue(value);
        propertyValueDAO.update(pv);
        return "%success";
    }

    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
        System.out.println("开始调用DoctorServlet的update方法....");
        int cid = Integer.parseInt(request.getParameter("cid"));
        System.out.println("修改医生编号为"+cid);
        Category c = categoryDAO.get(cid);

        int id = Integer.parseInt(request.getParameter("id"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        float orignalPrice = Float.parseFloat(request.getParameter("orignalPrice"));
        float promotePrice = Float.parseFloat(request.getParameter("promotePrice"));
        String subTitle= request.getParameter("subTitle");
        String name= request.getParameter("name");

        System.out.println(name+"..............................................");

        Doctor p = new Doctor();

        p.setName(name);
        p.setSubTile(subTitle);
        p.setOrignalPrice(orignalPrice);
        p.setPromotePrice(promotePrice);
        p.setStock(stock);
        p.setId(id);
        p.setCategory(c);

        doctorDAO.update(p);
        System.out.println("成功修改医生信息。。");
        System.out.println("返回医生界面。。");
        return "@admin_doctor_list?cid="+p.getCategory().getId();
    }

    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
        System.out.println("DoctorList开始执行"+"method"+"方法");
        int cid = Integer.parseInt(request.getParameter("cid"));
        System.out.println("从参数域中取出科室id为"+cid);
        Category c = categoryDAO.get(cid);
        System.out.println("从数据库中取出科室信息"+c.getName());
        List<Doctor> ps = doctorDAO.list(cid, page.getStart(),page.getCount());
        System.out.println("根据科室id取出科室里的医生信息");

        int total = doctorDAO.getTotal(cid);
        System.out.println("医生总数wei"+total);
        page.setTotal(total);
        System.out.println("将医生信息放入Page里");
        page.setParam("&cid="+c.getId());
        System.out.println("将科室id放入Page的param里面");
        request.setAttribute("ps", ps);
        System.out.println("将医生信息传入参数域");
        request.setAttribute("c", c);
        System.out.println("将科室信息传入参数域");
        request.setAttribute("page", page);
        System.out.println("将Page信息传入参数域");

        return "listDoctor.jsp";
    }
}

