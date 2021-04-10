package com.example.registerfirebase;

public class WishlistModel {
    private String productImage;
    private String productTitle;
    private String productPrice;
    private String speciality;
    private String experience;

    public WishlistModel(String productImage, String productTitle, String productPrice, String speciality, String experience) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.experience = experience;
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
