package pacs.bean;

/**
 * @program: PACS
 * @Date: 2019/11/22 15:02
 * @Author: Mr.Liu
 * @Description:
 */
public class User {

    private String name;
    private String password;
    private  int id;
    private  String nicheng;
    private  String gender;
    private String adress;
    private String phone;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNicheng() {
        return nicheng;
    }

    public void setNicheng(String nicheng) {
        this.nicheng = nicheng;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //获得匿名
    public String getAnonymousName(){
        if (null == name) {
            return null;
        }
        if (name.length()<=1) {
            return "*";
        }
        if(name.length()==2) {
            return name.substring(0,1)+"*";
        }
        char[] cs=name.toCharArray();
        for (int i=1;i<cs.length-1;i++) {
            cs[i]='*';
        }
        return new String(cs);
    }

}
