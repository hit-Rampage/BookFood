/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryan.manage.entity;

/**
 *
 * @author ryan
 */
public class DeliverEntity {

    /**
     * DeliverId int auto_increment primary key UserName varchar(50) Address
     * varchar(255) OrderTime datetime OrderState varchar(20) OrderPrice double
     */
    private int deliverId;
    private String userName;
    private String address;
    private String telephone;
    private String orderTime;
    private String orderState;
    private Double orderPrice;
    private String orderItem;

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
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the orderTime
     */
    public String getOrderTime() {
        return orderTime;
    }

    /**
     * @param orderTime the orderTime to set
     */
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * @return the orderState
     */
    public String getOrderState() {
        return orderState;
    }

    /**
     * @param orderState the orderState to set
     */
    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    /**
     * @return the orderPrice
     */
    public Double getOrderPrice() {
        return orderPrice;
    }

    /**
     * @param orderPrice the orderPrice to set
     */
    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * @return the orderItem
     */
    public String getOrderItem() {
        return orderItem;
    }

    /**
     * @param orderItem the orderItem to set
     */
    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }

}
