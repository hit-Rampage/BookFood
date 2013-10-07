/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryan.manage.db;

import java.sql.*;
/**
 *
 * @author ryan
 */
public class DBLink {

    public Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection("jdbc:mysql://localhost/manage?user=root&password=1&useUnicode=true&characterEncoding=utf8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeAll(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (con != null && (!con.isClosed())) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
