/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryan.manage.link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author ryan
 */
import com.ryan.manage.db.DBLink;
import com.ryan.manage.entity.DeliverEntity;
import com.ryan.manage.entity.ItemEntity;

public class DeliverLink {

    Connection con;
    PreparedStatement ps, ps2;
    ResultSet rs, rs2;

    public List findDeliverAll(String state) {
        List list = new ArrayList();

        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("select * from Deliver where OrderState = ?");
            ps.setString(1, state);
            rs = ps.executeQuery();
            while (rs.next()) {
                DeliverEntity deliver = new DeliverEntity();
                deliver.setDeliverId(rs.getInt("DeliverId"));
                deliver.setUserName(rs.getString("UserName"));
                deliver.setAddress(rs.getString("Address"));
                deliver.setTelephone(rs.getString("Telephone"));
                deliver.setOrderTime(rs.getString("OrderTime"));

                deliver.setOrderState(rs.getString("OrderState"));
                deliver.setOrderPrice(rs.getDouble("OrderPrice"));

                ps2 = con.prepareStatement("select DishName,DishCount from OrderItem "
                        + "where DeliverId =?");
                ps2.setInt(1, deliver.getDeliverId());
                rs2 = ps2.executeQuery();
                String OrderItem = "";
                while (rs2.next()) {
                    OrderItem += rs2.getString("DishName") + "x" + rs2.getInt("DishCount") + " \n";
                }
                deliver.setOrderItem(OrderItem);
                list.add(deliver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void modifyDeliverState(int deliverId, String state) {
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("update Deliver set OrderState=? where DeliverId=?");

            ps.setString(1, state);
            ps.setInt(2, deliverId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delDeliverById(int deliverId) {
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("delete from Deliver where DeliverId=?");
            ps.setInt(1, deliverId);
            ps.executeUpdate();

            ps = con.prepareStatement("delete from OrderItem where DeliverId =?");
            ps.setInt(1, deliverId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDeliver(DeliverEntity deliver, List list) {
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("insert into Deliver(UserName,Address,Telephone,OrderTime,OrderState,OrderPrice) values(?,?,?,?,?,?)");
            ps.setString(1, deliver.getUserName());
            ps.setString(2, deliver.getAddress());
            ps.setString(3, deliver.getTelephone());
            ps.setString(4, deliver.getOrderTime());
            ps.setString(5, deliver.getOrderState());
            ps.setDouble(6, deliver.getOrderPrice());
            ps.executeUpdate();

            int deliverid = 0;
            ps = con.prepareStatement("select max(DeliverId) from Deliver");
            rs = ps.executeQuery();
            if(rs.next()) {
                deliverid = rs.getInt("max(DeliverId)");
            }
            for (int i = 0; i < list.size(); i++) {
                ItemEntity item = (ItemEntity) list.get(i);
                item.setDeliverId(deliverid);
                ps = con.prepareStatement("insert into OrderItem(DeliverId,DishName,DishCount) values(?,?,?)");
                ps.setInt(1, deliverid);
                ps.setString(2, item.getDishName());
                ps.setInt(3, item.getDishCount());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
