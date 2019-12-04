package pacs.test;

import pacs.bean.*;
import pacs.dao.*;

import javax.swing.border.AbstractBorder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: PACS
 * @Date: 2019/11/22 19:38
 * @Author: Mr.Liu
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
 /*       CategoryDAO a = new CategoryDAO();
        Category bean = new Category();
        List<Category> beans = new ArrayList<Category>();
        beans = a.list();
        for (Category b:beans) {
            System.out.println(b.toString());
        }*/
 /*       DoctorDAO a = new DoctorDAO();
        //int number = 0;
        //number = a.getTotal(1);
        //System.out.println(number);
        Doctor doctor = new Doctor();
        doctor.setName("L");
        doctor.setSubTile("ll");
        doctor.setPromotePrice(100);
        doctor.setOrignalPrice(80);
        doctor.setStock(10);
//        a.add(doctor);
        //a.delete(12);
       // Doctor b=a.get(11);
        System.out.println();*/
/*        OrderItemDAO orderItemDAO = new OrderItemDAO();
        OrderItem o = new OrderItem();
        DoctorDAO doctorDAO = new DoctorDAO();
        Doctor doctor = doctorDAO.get(1);
        o.setDoctor(doctor);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.get(1);
        o.setUser(user);
        orderItemDAO.add(o);*/
        /* Order order = new Order();
         order.setOrderCode("123123123");
         order.setReceiver("刘");
         order.setMobile("11111111111");
         order.setUserMessage("一切正常");
         order.setCreateDate(new Date());
         order.setPayDate(new Date());
         order.setStatus("nopayed");
         order.setUser(new UserDAO().get(2));
         order.setId(1);
         new OrderDAO().update(order);
*/

         /*DoctorDAO doctorDAO = new DoctorDAO();
         Doctor dortor = doctorDAO.get(5);
        System.out.println(dortor.getSubTile());
         System.out.println("获取了医生"+dortor.getName()+"的信息");
         DoctorImageDAO doctorImageDAO = new DoctorImageDAO();
         List<DoctorImage> doctorImages = doctorImageDAO.list(dortor,"jpg");
         System.out.println(doctorImages.size());*/

        OrderDAO orderDAO = new OrderDAO();
        List<Order> order = new ArrayList<Order>();
        order = orderDAO.list();
        System.out.println(order.size());
    }

}