package com.ryan.manage.link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ryan.manage.db.DBLink;
import com.ryan.manage.entity.AdminEntity;

public class AdminLink {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Boolean findAdminIsExist(String aname, String apwd) {
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("select AdminId,LoginName,LoginPwd from Admin where LoginName=? and LoginPwd=?");
            ps.setString(1, aname);
            ps.setString(2, apwd);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
