package pacs.bean;

/**
 * @program: PACS
 * @Date: 2019/11/22 15:38
 * @Author: Mr.Liu
 * @Description:
 */
public class DoctorImage {

    private String type;
    private Doctor doctor;
    private int id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
