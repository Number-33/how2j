package pacs.bean;

import java.util.Date;
import java.util.List;
/**
 * @program: PACS
 * @Date: 2019/11/22 15:28
 * @Author: Mr.Liu
 * @Description:
 */
public class Doctor {

    private String name;
    private String subTile;
    private float orignalPrice; //原价
    private float promotePrice;//会员价
    private int stock; //今日看病名额
    private Date createDate;//入职日期
    private Category category;
    private int id;
    private DoctorImage firstDoctorImage;
    private List<DoctorImage> doctorImages;

    private int saleCount;
    private int reviewCount;

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTile() {
        return subTile;
    }

    public void setSubTile(String subTile) {
        this.subTile = subTile;
    }

    public float getOrignalPrice() {
        return orignalPrice;
    }

    public void setOrignalPrice(float orignalPrice) {
        this.orignalPrice = orignalPrice;
    }

    public float getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(float promotePrice) {
        this.promotePrice = promotePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public DoctorImage getFirstDoctorImage() {
        return firstDoctorImage;
    }

    public void setFirstDoctorImage(DoctorImage firstDoctorImage) {
        this.firstDoctorImage = firstDoctorImage;
    }

    public List<DoctorImage> getDoctorImages() {
        return doctorImages;
    }

    public void setDoctorImages(List<DoctorImage> doctorImages) {
        this.doctorImages = doctorImages;
    }
}
