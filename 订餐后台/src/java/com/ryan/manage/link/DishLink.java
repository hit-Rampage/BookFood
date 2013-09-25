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
import com.ryan.manage.entity.DishEntity;
import java.sql.SQLException;

/**
 *
 * @author ryan
 */
public class DishLink {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List findDishAll() {
        List list = new ArrayList();

        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("select * from Dish");
            rs = ps.executeQuery();
            while (rs.next()) {
                DishEntity dish = new DishEntity();
                dish.setDishId(rs.getInt("DishId"));
                dish.setDishName(rs.getString("DishName"));
                dish.setDishSeries(rs.getString("DishSeries"));
                dish.setDishPrice(rs.getDouble("DishPrice"));
                dish.setDishImg(rs.getString("DishImg"));

                list.add(dish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBLink().closeAll(con, ps, rs);
        }
        return list;
    }

    public void addDish(DishEntity dish) {
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("insert into Dish(DishName,DishSeries,DishImg,DishPrice) values(?,?,?,?)");
            ps.setString(1, dish.getDishName());
            ps.setString(2, dish.getDishSeries());
            ps.setString(3, dish.getDishImg());
            ps.setDouble(4, dish.getDishPrice());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyDishPrice(int dishId, Double price) {
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("update Dish set DishPrice=? where DishId=?");

            ps.setDouble(1, price);
            ps.setInt(2, dishId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String delDishById(int dishId) {
        String path = "";
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("select * from Dish where DishId=?");
            ps.setInt(1, dishId);
            rs = ps.executeQuery();
            while (rs.next()) {
                path = rs.getString("DishImg");
            }
            if (path.equals("DishImg/00.jpg")) {
                path = "";
            }
            ps = con.prepareStatement("delete from Dish where DishId=?");
            ps.setInt(1, dishId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return path;
    }
}
