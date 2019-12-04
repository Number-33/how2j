package pacs.dao;

import com.sun.org.glassfish.gmbal.Description;
import pacs.bean.Doctor;
import pacs.bean.Order;
import pacs.bean.OrderItem;
import pacs.bean.User;
import pacs.util.DBUtil;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: PACS
 * @Date: 2019/11/22 18:45
 * @Author: Mr.Liu
 * @Description:
*/

public class OrderItemDAO {

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from OrderItem";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public void add(OrderItem bean) {

        String sql = "insert into orderitem values(null,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, bean.getDoctor().getId());

            //订单项在创建的时候，是没有订单信息的
            if(null==bean.getOrder())
                ps.setInt(2, 1);
            else
                ps.setInt(2, bean.getOrder().getId());

            ps.setInt(3, bean.getUser().getId());
            ps.setInt(4,bean.getStatus());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        System.out.println("订单项成功添加到数据库");
    }

    public void update(OrderItem bean) {

        String sql = "update OrderItem set pid= ?, oid=?, uid=? ,status=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, bean.getDoctor().getId());
            if(null==bean.getOrder())
                ps.setInt(2, 1);
            else
                ps.setInt(2, bean.getOrder().getId());
            ps.setInt(3, bean.getOrder().getId());

            ps.setInt(4, bean.getId());
            ps.setInt(5,bean.getStatus());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from OrderItem where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public OrderItem get(int id) {
        OrderItem bean = new OrderItem();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from OrderItem where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                int pid = rs.getInt("pid");
                int oid = rs.getInt("oid");
                int uid = rs.getInt("uid");
                Doctor doctor = new DoctorDAO().get(pid);
               // User user = new UserDAO().get(uid);
                bean.setDoctor(doctor);
                //bean.setUser(user);

                if(-1!=oid){
                   // Order order= new OrderDAO().get(oid);
                 //   bean.setOrder(order);
                }

                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    public List<OrderItem> listByUser(int uid) {
        return listByUser(uid, 0, Short.MAX_VALUE);
    }

    public List<OrderItem> listByUser(int uid, int start, int count) { //根据用户列出所有的订单项
        List<OrderItem> beans = new ArrayList<OrderItem>();

        String sql = "select * from OrderItem where uid = ? and oid=-1 order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, uid);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem bean = new OrderItem();
                int id = rs.getInt(1);

                int pid = rs.getInt("pid");
                int oid = rs.getInt("oid");
                int number = rs.getInt("number");


                Doctor doctor = new DoctorDAO().get(pid);
                //Product product = new ProductDAO().get(pid);
                if(-1!=oid){
                    Order order= new OrderDAO().get(oid);
                    bean.setOrder(order);
                }

                User user = new UserDAO().get(uid);
                bean.setDoctor(doctor);
                bean.setUser(user);
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }
    public List<OrderItem> listByOrder(int oid) { //理出某个订单的所有订单项
        return listByOrder(oid, 0, Short.MAX_VALUE);
    }

    public List<OrderItem> listByOrder(int oid, int start, int count) {//根据订单列出所有的订单项
        List<OrderItem> beans = new ArrayList<OrderItem>();

        String sql = "select * from OrderItem where oid = ? order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, oid);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem bean = new OrderItem();
                int id = rs.getInt(1);

                int pid = rs.getInt("pid");
                int uid = rs.getInt("uid");


                Doctor doctor = new DoctorDAO().get(pid);
                if(-1!=oid){
                    Order order= new OrderDAO().get(oid);
                    bean.setOrder(order);
                }

                User user = new UserDAO().get(uid);
                bean.setDoctor(doctor);

                bean.setUser(user);
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public void fill(List<Order> os) {//为订单填充订单表项信息
        for (Order o : os) {
            List<OrderItem> ois=listByOrder(o.getId());//根据订单id获取所有订单表项
            float total = 0;//c此订单价钱
            int totalNumber = 0;//此订单里所有的订单项一共有多少
            for (OrderItem oi : ois) {
                total+=1*oi.getDoctor().getPromotePrice();
                totalNumber+=1;
            }
            o.setTotal(total);
            o.setOrderItems(ois);
            o.setTotalNumber(totalNumber);
        }



    }

    public void fill(Order o) {
        List<OrderItem> ois=listByOrder(o.getId());
        float total = 0;
        for (OrderItem oi : ois) {
            total+=1*oi.getDoctor().getPromotePrice();
        }
        o.setTotal(total);
        o.setOrderItems(ois);
    }

    public List<OrderItem> listByDoctor(int pid) { //
        return listByProduct(pid, 0, Short.MAX_VALUE);
    }

    public List<OrderItem> listByProduct(int pid, int start, int count) {
        List<OrderItem> beans = new ArrayList<OrderItem>();

        String sql = "select * from OrderItem where pid = ? order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, pid);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem bean = new OrderItem();
                int id = rs.getInt(1);

                int uid = rs.getInt("uid");
                int oid = rs.getInt("oid");


                Doctor doctor = new DoctorDAO().get(pid);
                if(-1!=oid){
                    Order order= new OrderDAO().get(oid);
                    bean.setOrder(order);
                }

                User user = new UserDAO().get(uid);
                bean.setDoctor(doctor);
                bean.setUser(user);
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public int getSaleCount(int pid) {//获取某个医生的看病总数
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select sum(number) from OrderItem where pid = " + pid;

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

}
