/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryan.manage.link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ryan.manage.db.DBLink;
import com.ryan.manage.entity.UserEntity;
import java.sql.SQLException;

/**
 *
 * @author ryan
 */
public class UserLink {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List findUserAll() {
        List list = new ArrayList();

        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("select * from User");
            rs = ps.executeQuery();
            while (rs.next()) {
                UserEntity user = new UserEntity();
                user.setUserId(rs.getInt("UserId"));
                user.setUserName(rs.getString("UserName"));
                user.setPassword(rs.getString("Password"));
                user.setUserState(rs.getString("UserState"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBLink().closeAll(con, ps, rs);
        }
        return list;
    }

    public void modifyUserState(int userId, String userState) {
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("update User set UserState=? where UserId=?");

            ps.setString(1, userState);
            ps.setInt(2, userId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delUserById(int userId) {
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("delete from User where UserId=?");
            ps.setInt(1, userId);
            ps.executeUpdate();

            ps = con.prepareStatement("delete from Feedback where UserId=?");
            ps.setInt(1, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(UserEntity user) {
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("select * from User where UserName = ?");
            ps.setString(1, user.getUserName());
            rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
            ps = con.prepareStatement("insert into User(UserName,Password,UserState) values(?,?,?)");
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserState());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int findUserIsExist(String name, String pwd) {
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("select * from User where UserName = ? and Password = ?");
            ps.setString(1, name);
            ps.setString(2, pwd);
            rs = ps.executeQuery();
            if (rs.next()) {
                String state = rs.getString("UserState");
                if ("Õý³£".equals(state)) {
                    return 1;
                }
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
