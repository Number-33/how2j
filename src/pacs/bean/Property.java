package pacs.bean;

/**
 * @program: PACS
 * @Date: 2019/11/22 15:19
 * @Author: Mr.Liu
 * @Description:
 */
public class Property {

    private String name;
    private Category category;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
