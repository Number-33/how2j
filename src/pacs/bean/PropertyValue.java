package pacs.bean;

/**
 * @program: PACS
 * @Date: 2019/11/22 15:41
 * @Author: Mr.Liu
 * @Description:
 */
public class PropertyValue {

    private String value;
    private Doctor doctor;
    private Property property;
    private int id;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
