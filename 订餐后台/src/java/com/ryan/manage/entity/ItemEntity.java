/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryan.manage.entity;

/**
 *
 * @author ryan
 */
public class ItemEntity {

    private int deliverId;
    private String dishName;
    private int dishCount;

    /**
     * @return the deliverId
     */
    public int getDeliverId() {
        return deliverId;
    }

    /**
     * @param deliverId the deliverId to set
     */
    public void setDeliverId(int deliverId) {
        this.deliverId = deliverId;
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
     * @return the dishCount
     */
    public int getDishCount() {
        return dishCount;
    }

    /**
     * @param dishCount the dishCount to set
     */
    public void setDishCount(int dishCount) {
        this.dishCount = dishCount;
    }
}
