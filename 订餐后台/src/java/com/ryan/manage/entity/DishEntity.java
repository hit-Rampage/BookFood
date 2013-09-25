/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryan.manage.entity;

/**
 *
 * @author ryan
 */
public class DishEntity {

    private int dishId;
    private String dishName;
    private String dishSeries;
    private String dishImg;
    private double dishPrice;

    /**
     * @return the dishId
     */
    public int getDishId() {
        return dishId;
    }

    /**
     * @param dishId the dishId to set
     */
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    /**
     * @return the dishName
     */
    public String getDishName() {
        return dishName;
    }

    /**
     * @param dishName the dishName to set
     */
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    /**
     * @return the dishSeries
     */
    public String getDishSeries() {
        return dishSeries;
    }

    /**
     * @param dishSeries the dishSeries to set
     */
    public void setDishSeries(String dishSeries) {
        this.dishSeries = dishSeries;
    }

    /**
     * @return the dishImg
     */
    public String getDishImg() {
        return dishImg;
    }

    /**
     * @param dishImg the dishImg to set
     */
    public void setDishImg(String dishImg) {
        this.dishImg = dishImg;
    }

    /**
     * @return the dishPrice
     */
    public double getDishPrice() {
        return dishPrice;
    }

    /**
     * @param dishPrice the dishPrice to set
     */
    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }

}
