package pacs.bean;

import java.util.Date;

/**
 * @program: PACS
 * @Date: 2019/11/22 15:56
 * @Author: Mr.Liu
 * @Description:
 */
public class Review {
    private String content;
    private Date createDate;
    private User user;
    private Doctor doctor;
    private int id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
