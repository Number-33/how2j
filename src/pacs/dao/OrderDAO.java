package pacs.dao;

import pacs.bean.Order;
import pacs.bean.User;
import pacs.util.DBUtil;
import pacs.util.DateUtil;
import sun.security.krb5.internal.tools.Klist;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: PACS
 * @Date: 2019/11/22 18:45
 * @Author: Mr.Liu
 * @Description:
 */
public class OrderDAO {

    public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from Order_";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public void add(Order bean) {//一测试可以运行

        String sql = "insert into order_ values(null,?,?,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, bean.getOrderCode());
            ps.setString(2, bean.getReceiver());
            ps.setString(3, bean.getMobile());
            ps.setString(4, bean.getUserMessage());

            ps.setTimestamp(5,  DateUtil.d2t(bean.getCreateDate()));
            ps.setTimestamp(6,  DateUtil.d2t(bean.getPayDate()));
            ps.setInt(7, bean.getUser().getId());
            ps.setString(8, bean.getStatus());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }




    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from Order_ where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    public Order get(int id) {
        Order bean = new Order();



        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from Order_ where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                String orderCode =rs.getString("orderCode");
                String mobile = rs.getString("mobile");
                String receiver =rs.getString("receiver");
                String userMessage = rs.getString("userMessage");
                String status = rs.getString("status");
                int uid =rs.getInt("uid");//用户
                java.util.Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                java.util.Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));

                bean.setOrderCode(orderCode);
                bean.setMobile(mobile);
                bean.setUserMessage(userMessage);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);

                User user = new UserDAO().get(uid);
                bean.setUser(user);
                bean.setStatus(status);

                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    public void update(Order bean) {//测试正常，可以顺利运行


        String sql = "update order_ set  orderCode =?,receiver=?,mobile=?,userMessage=? ,createDate = ? , payDate =?, uid=?, status=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {


            ps.setString(1, bean.getOrderCode());
            ps.setString(2, bean.getReceiver());
            ps.setString(3, bean.getMobile());
            ps.setString(4, bean.getUserMessage());
            ps.setTimestamp(5, DateUtil.d2t(bean.getCreateDate()));;
            ps.setTimestamp(6, DateUtil.d2t(bean.getPayDate()));;
            ps.setInt(7, bean.getUser().getId());
            ps.setString(8, bean.getStatus());
            ps.setInt(9, bean.getId());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

/*    public List<Order> list() {
        return list(0, Short.MAX_VALUE);
    }*/
    public List<Order> list() {
        List<Order> beans = new ArrayList<Order>();

        String sql = "select * from order_";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order bean = new Order();
                String orderCode =rs.getString("orderCode");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                String userMessage = rs.getString("userMessage");
                String status = rs.getString("status");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                int uid =rs.getInt("uid");
                int id = rs.getInt("id");
                bean.setId(id);
                bean.setOrderCode(orderCode);
                bean.setReceiver(receiver);
                bean.setMobile(mobile);
                bean.setUserMessage(userMessage);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                User user = new UserDAO().get(uid);
                bean.setUser(user);
                bean.setStatus(status);
                beans.add(bean);

            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public List<Order> list(int uid,String excludedStatus) {
        return list(uid,excludedStatus,0, Short.MAX_VALUE);
    }


    public List<Order> list(int uid, String excludedStatus, int start, int count) {
        List<Order> beans = new ArrayList<Order>();

        String sql = "select * from Order_ where uid = ? and status = ? order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, uid);
            ps.setString(2, excludedStatus);
            ps.setInt(3, start);
            ps.setInt(4, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order bean = new Order();
                String orderCode =rs.getString("orderCode");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                String userMessage = rs.getString("userMessage");
                String status = rs.getString("status");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                int id = rs.getInt("id");
                bean.setId(id);
                bean.setOrderCode(orderCode);
                bean.setReceiver(receiver);
                bean.setMobile(mobile);
                bean.setUserMessage(userMessage);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                User user = new UserDAO().get(uid);
                bean.setStatus(status);
                bean.setUser(user);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    public List<Order> doctorlist(int did) {
        List<Order> beans = new ArrayList<Order>();
        List<Integer> numbers = new ArrayList<Integer>();
        String sql2 = "select oid from orderitem where pid=?";
        try (Connection c2 = DBUtil.getConnection(); PreparedStatement ps2 = c2.prepareStatement(sql2);) {

            ps2.setInt(1, did);
            ResultSet rs2 = ps2.executeQuery();

            while(rs2.next()) {
                int number = rs2.getInt("oid");
                numbers.add(number);
                System.out.println(number);//取出了所需订单的id
            }

            } catch(SQLException e){
                e.printStackTrace();
            }

        String sql = "select * from order_ ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("uid"));
                if(numbers.contains(rs.getInt("id"))) {
                    Order bean = new Order();
                    String orderCode = rs.getString("orderCode");
                    String receiver = rs.getString("receiver");
                    String mobile = rs.getString("mobile");
                    String userMessage = rs.getString("userMessage");
                    String status = rs.getString("status");
                    Date createDate = DateUtil.t2d(rs.getTimestamp("createDate"));
                    Date payDate = DateUtil.t2d(rs.getTimestamp("payDate"));
                    int uid = rs.getInt("uid");
                    int id = rs.getInt("id");
                    bean.setId(id);
                    bean.setOrderCode(orderCode);
                    bean.setReceiver(receiver);
                    bean.setMobile(mobile);
                    bean.setUserMessage(userMessage);
                    bean.setCreateDate(createDate);
                    bean.setPayDate(payDate);
                    User user = new UserDAO().get(uid);
                    bean.setUser(user);
                    bean.setStatus(status);
                    beans.add(bean);
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }
}


