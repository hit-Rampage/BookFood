/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryan.manage.entity;

public class AdminEntity {

    private int adminId;
    private String adminName;
    private String adminPwd;

    public AdminEntity() {
        super();
    }

    public AdminEntity(int adminId, String adminName, String adminPwd) {
        super();
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPwd = adminPwd;
    }

    /**
     * @return the adminId
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * @param adminId the adminId to set
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    /**
     * @return the adminName
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * @param adminName the adminName to set
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * @return the adminPwd
     */
    public String getAdminPwd() {
        return adminPwd;
    }

    /**
     * @param adminPwd the adminPwd to set
     */
    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

}
