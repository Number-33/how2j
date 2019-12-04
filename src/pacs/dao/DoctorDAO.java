package pacs.dao;

import pacs.bean.Category;
import pacs.bean.Doctor;
import pacs.util.DBUtil;
import pacs.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//crud测试完成
/**
 * @program: PACS
 * @Date: 2019/11/22 18:44
 * @Author: Mr.Liu
 * @Description:
 */
public class DoctorDAO {

    /**
    * @Description:
    * @param ：cid为Doctor与Category的关联外建
    * @Return:
    * @Author: Mr.liu
    * @Date:  20:33
    */
    public int getTotal(int cid) {
        int total = 0;
        try(Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement();) {

            String sql = "select count(*) from Doctor where cid = " + cid;

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                total = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Doctor bean) {
        String sql = "insert into doctor values(null,?,?,?,?,?,?,?)";
        try(Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1,bean.getName());//姓名
            preparedStatement.setString(2,bean.getSubTile());//简介
            preparedStatement.setFloat(3,bean.getOrignalPrice());//原价
            preparedStatement.setFloat(4,bean.getPromotePrice());//会员价
            preparedStatement.setInt(5,bean.getStock());//挂号名额
//            preparedStatement.setInt(6,bean.getCategory().getId());//与科室关联的外建
            preparedStatement.setInt(6,1);//与科室关联的外建
            preparedStatement.setTimestamp(7, DateUtil.d2t(bean.getCreateDate()));//创建日期
            preparedStatement.execute();


            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                        bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void update(Doctor bean) {

            String sql = "update doctor set name = ? ,subTitle = ? ,orignalPrice = ?,promotePrice = ?,stock = ?, cid = ?,createDate = ? where id = ?";
            try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
                preparedStatement.setString(1, bean.getName());
                preparedStatement.setString(2, bean.getSubTile());
                preparedStatement.setFloat(3, bean.getOrignalPrice());
                preparedStatement.setFloat(4, bean.getPromotePrice());
                preparedStatement.setInt(5, bean.getStock());
                preparedStatement.setInt(6, bean.getCategory().getId());
                preparedStatement.setTimestamp(7, (Timestamp) bean.getCreateDate());
                preparedStatement.setInt(8, bean.getId());
                preparedStatement.execute();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


        public void delete(int id) {

            try(Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement();) {

                String sql = "delete from Doctor where id =" + id;

                statement.execute(sql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public Doctor get(int id) {
            Doctor bean = new Doctor();

            try(Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement();) {

                String sql = "select * from Doctor where id =" + id;

                ResultSet resultSet = statement.executeQuery(sql);

                if(resultSet.next()) {
                    String name = resultSet.getString("name");
                    String subTitle = resultSet.getString("subTitle");
                    float orignalprice = resultSet.getFloat("orignalPrice");
                    float promoteprice = resultSet.getFloat("promotePrice");
                    int stock = resultSet.getInt("stock");
                    int cid =resultSet.getInt("cid");
                    Timestamp creatDate =  resultSet.getTimestamp("createDate");

                    bean.setName(name);
                    bean.setSubTile(subTitle);
                    bean.setOrignalPrice(orignalprice);
                    bean.setPromotePrice(promoteprice);
                    bean.setStock(stock);
                    Category category = new CategoryDAO().get(cid);
                    bean.setCategory(category);
                    bean.setCreateDate(creatDate);
                    bean.setId(id);
                    //setFirstDoctorImage(bean);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return bean;
        }


        public List<Doctor> list(int cid) {
            return list(cid,0,Short.MAX_VALUE);
        }

        public List<Doctor> list(int cid,int start,int count) {

            List<Doctor> beans = new ArrayList<Doctor>();
            Category category = new CategoryDAO().get(cid);

            String sql = "select * from Doctor where cid = ? order by id desc limit ?,? ";

            try(Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
                preparedStatement.setInt(1,cid);
                preparedStatement.setInt(2,start);
                preparedStatement.setInt(3,count);

                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    Doctor bean = new Doctor();
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString("name");
                    String subTitle = resultSet.getString("subTitle");
                    float orignalPrice = resultSet.getFloat("orignalPrice");
                    float promotePrice = resultSet.getFloat("promotePrice");
                    int stock = resultSet.getInt("stock");
                    Timestamp createDate = resultSet.getTimestamp("createDate");

                    bean.setName(name);
                    bean.setSubTile(subTitle);
                    bean.setOrignalPrice(orignalPrice);
                    bean.setPromotePrice(promotePrice);
                    bean.setStock(stock);
                    bean.setCreateDate(createDate);
                    bean.setId(id);

                    Category category1 = new CategoryDAO().get(cid);
                    bean.setCategory(category1);
                    beans.add(bean);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return beans;
        }

/*
        public void setFirstDoctorImage(Doctor d) {
            List<Doctor> dis = new DoctorImageDAO().list(d,DoctorImageDAO.type_single);
            if(!dis.isEmpty()){
                d.setFirstDoctorImage(dis.get(0));
            }
*/
public void fill(List<Category> cs) {
    for (Category c : cs)
        fill(c);
}
    public void fill(Category c) {
        List<Doctor> ps = this.list(c.getId());
        c.setDoctors(ps);
    }

    public void fillByRow(List<Category> cs) {
        int productNumberEachRow = 8;
        for (Category c : cs) {
            List<Doctor> products =  c.getDoctors();//
            List<List<Doctor>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Doctor> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setDoctorsByRow(productsByRow);
        }
    }


}









