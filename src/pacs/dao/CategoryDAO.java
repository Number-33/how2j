package pacs.dao;

import pacs.bean.Category;
import pacs.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//功能测试OK
/**
 * @program: PACS
 * @Date: 2019/11/22 18:43
 * @Author: Mr.Liu
 * @Description:
 */
public class CategoryDAO {

    public int getTotal() {
        int total =0;
        try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "Select count(*) from Category";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs .getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Category bean) {
        String sql ="insert into category values(null,?)";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1,bean.getName());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Category bean) {

        String sql ="update category set name = ? where id = ?";

        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1,bean.getName());
            ps.setInt(2,bean.getId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from Category where id = " + id;
            s.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category get(int id) {
        Category bean = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from Category where id = " + id;
            ResultSet rs = s.executeQuery(sql);

            if(rs.next()) {
                bean =new Category();
                String name = rs.getString(2);
                bean.setId(id);
                bean.setName(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }


    public List<Category> list() {
        
        return list(0,Short.MAX_VALUE);
    }

    public List<Category> list(int start, int count) {
        List<Category> beans = new ArrayList<Category>(); 
        
        String sql = "select * from Category order by id desc limit ?,?";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1,start);
            ps.setInt(2,count);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Category bean = new Category();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                bean.setId(id);
                bean.setName(name);
                beans.add(bean);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }


}







