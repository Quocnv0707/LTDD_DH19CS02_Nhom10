package com.ltdd.ltdd_dh19cs02_nhom10.model;

public class LoaiSP {
    private int id;
    private String productName;
    private String image;

    public LoaiSP(int id, String productName, String image) {
        this.id = id;
        this.productName = productName;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LoaiSP() {
    }
}
