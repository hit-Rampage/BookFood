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
import com.ryan.manage.db.DBLink;
import com.ryan.manage.entity.FeedbackEntity;

/**
 *
 * @author ryan
 */
public class FeedbackLink {

    Connection con;
    PreparedStatement ps, ps2;
    ResultSet rs, rs2;

    public List findFeedbackMsg() {
        List list = new ArrayList();
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("select * from Feedback order by MsgId desc");
            rs = ps.executeQuery();
            while (rs.next()) {
                FeedbackEntity msg = new FeedbackEntity();
                msg.setMsgId(rs.getInt("MsgId"));
                msg.setUserName(rs.getString("UserName"));
                msg.setSendNote(rs.getString("SendNote"));
                msg.setSendTime(rs.getString("SendTime"));
                list.add(msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void addFeedbackMsg(FeedbackEntity fb) {
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("insert into Feedback(UserName,SendNote,SendTime) values(?,?,?)");
            ps.setString(1, fb.getUserName());
            ps.setString(2, fb.getSendNote());
            ps.setString(3, fb.getSendTime());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void truncateMsg() {
        try {
            con = new DBLink().getConn();
            ps = con.prepareStatement("truncate Feedback");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
