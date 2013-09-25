package com.ryan.manage.servlet;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ryan.manage.entity.DeliverEntity;
import com.ryan.manage.entity.FeedbackEntity;
import com.ryan.manage.link.DeliverLink;
import com.ryan.manage.entity.ItemEntity;
import com.ryan.manage.entity.UserEntity;
import com.ryan.manage.link.FeedbackLink;
import com.ryan.manage.link.UserLink;
import java.io.PrintWriter;

public class GetServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

//        String name = new String(nameParameter.getBytes("ISO-8859-1"), "UTF-8");
//        String age = new String(ageParameter.getBytes("ISO-8859-1"), "UTF-8");
        String type = req.getParameter("send_type");
        if ("order".equals(type)) {
            String info_param = req.getParameter("user_info");
            String item_param = req.getParameter("order_info");
            JSONObject user_json = JSONObject.fromObject(info_param);
            JSONArray item_arr = JSONArray.fromObject(item_param);
            JSONObject item_json = new JSONObject();
            List list = new ArrayList();
            double price = 0;
            for (int i = 0; i < item_arr.size(); i++) {
                ItemEntity item = new ItemEntity();
                item_json = item_arr.getJSONObject(i);
                item.setDishName(item_json.getString("DishName"));
                item.setDishCount(item_json.getInt("DishCount"));
                price += item_json.getDouble("DishPrice");
                list.add(item);
            }
            DeliverEntity deliver = new DeliverEntity();
            deliver.setUserName(user_json.getString("UserName"));
            deliver.setAddress(user_json.getString("Address"));
            deliver.setTelephone(user_json.getString("Telephone"));
            deliver.setOrderTime(user_json.getString("OrderTime"));
            deliver.setOrderState("等待送餐");
            deliver.setOrderPrice(price);
            new DeliverLink().addDeliver(deliver, list);
        } else if ("register".equals(type)) {
            String info_param = req.getParameter("register_info");
            JSONObject reg_json = JSONObject.fromObject(info_param);
            UserEntity user = new UserEntity();
            user.setUserName(reg_json.getString("UserName"));
            user.setPassword(reg_json.getString("Password"));
            user.setUserState("正常");
            boolean flag = new UserLink().addUser(user);
            String res_word;
            if (flag) {
                res_word = "注册许可";
            } else {
                res_word = "重复";
            }
            res.setCharacterEncoding("UTF-8");
            PrintWriter out = res.getWriter();
            out.write(res_word);
            out.close();
        } else if ("logon".equals(type)) {
            String name = req.getParameter("user_name");
            String pwd = req.getParameter("user_password");
            int flag = new UserLink().findUserIsExist(name, pwd);
            String res_word;
            if (flag == 1) {
                res_word = "登陆许可";
            } else {
                res_word = "不可登陆";
            }
            res.setCharacterEncoding("UTF-8");
            PrintWriter out = res.getWriter();
            out.write(res_word);
            out.close();
        } else if ("feedback".equals(type)) {
            String info_param = req.getParameter("feedback_info");
            JSONObject fb_json = JSONObject.fromObject(info_param);
            FeedbackEntity fb = new FeedbackEntity();
            fb.setUserName(fb_json.getString("UserName"));
            fb.setSendNote(fb_json.getString("SendNote"));
            fb.setSendTime(fb_json.getString("SendTime"));
            new FeedbackLink().addFeedbackMsg(fb);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
