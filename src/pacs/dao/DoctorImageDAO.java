package pacs.dao;

import pacs.bean.Doctor;
import pacs.bean.DoctorImage;
import pacs.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: PACS
 * @Date: 2019/11/22 18:45
 * @Author: Mr.Liu
 * @Description:
 */
public class DoctorImageDAO {


    public static final String type_single = "type_single";
    public static final String type_detail = "type_detail";

    public int getTol() {
        int total = 0;
        try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from DoctorImage";

            ResultSet rs = s.executeQuery(sql);
            if(rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(DoctorImage bean) {

        String sql = "insert into DoctorImage values(null,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, bean.getDoctor().getId());
            ps.setString(2, bean.getType());
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

    public void update(Doctor bean) {

    }

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from DoctorImage where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public DoctorImage get(int id) {
        DoctorImage bean = new DoctorImage();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from DoctorImage where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                int pid = rs.getInt("pid");
                String type = rs.getString("type");
                Doctor Doctor = new DoctorDAO().get(pid);
                bean.setDoctor(Doctor);
                bean.setType(type);
                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    public List<DoctorImage> list(Doctor p, String type) {
        return list(p, type,0, Short.MAX_VALUE);
    }

    public List<DoctorImage> list(Doctor p, String type, int start, int count) {
        List<DoctorImage> beans = new ArrayList<DoctorImage>();

        String sql = "select * from DoctorImage where pid =? and type =? order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, p.getId());
            ps.setString(2, type);

            ps.setInt(3, start);
            ps.setInt(4, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                DoctorImage bean = new DoctorImage();
                int id = rs.getInt(1);

                bean.setDoctor(p);
                bean.setType(type);
                bean.setId(id);

                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

}

