package com.omshri.hacktheramp;

class Dresses {

    String imgUrl;
    String dressName;
    Integer price;

    public Dresses(String imgUrl, String dressName, Integer price) {
        this.imgUrl = imgUrl;
        this.dressName = dressName;
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDressName() {
        return dressName;
    }

    public void setDressName(String dressName) {
        this.dressName = dressName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
