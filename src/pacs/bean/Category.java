package pacs.bean;

import java.util.List;

/**
 * @program: PACS
 * @Date: 2019/11/22 15:23
 * @Author: Mr.Liu
 * @Description:
 */
public class Category {

    private int id;
    private String name;
    List<Doctor> doctors;
    List<List<Doctor>> doctorsByRow;

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<List<Doctor>> getDoctorsByRow() {
        return doctorsByRow;
    }

    public void setDoctorsByRow(List<List<Doctor>> doctorsByRow) {
        this.doctorsByRow = doctorsByRow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category [name=" + name +"]";
    }
}
