package com.ecommerce.shakil.e_commerce_buy;

public class Food {
    private String foodtitle;
    private double foodprice;
    private int foodimage;


    public Food(String title,double foodprice,int foodimage) {
        this.foodtitle=title;
        this.foodprice = foodprice;
        this.foodimage = foodimage;
    }

    public String getFoodtitle() {
        return foodtitle;
    }

    public void setFoodtitle(String foodtitle) {
        this.foodtitle = foodtitle;
    }

    public double getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(double foodprice) {
        this.foodprice = foodprice;
    }

    public int getFoodimage() {
        return foodimage;
    }

    public void setFoodimage(int foodimage) {
        this.foodimage = foodimage;
    }
}
