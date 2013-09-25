package com.ryan.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ryan.manage.entity.DishEntity;
import com.ryan.manage.link.DishLink;

public class PostServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
//        String type = req.getParameter("type");
//        String item_param = req.getParameter("order_info");
//        String name = new String(nameParameter.getBytes("ISO-8859-1"), "UTF-8");
//        String age = new String(ageParameter.getBytes("ISO-8859-1"), "UTF-8");

        JSONArray dish_arr = new JSONArray();
        List list = new ArrayList();
        list = new DishLink().findDishAll();
        for (int i = 0; i < list.size(); i++) {
            JSONObject dish_json = new JSONObject();
            DishEntity dish = (DishEntity) list.get(i);
            dish_json.put("DishName", dish.getDishName());
            dish_json.put("DishSeries", dish.getDishSeries());
            dish_json.put("DishImg", dish.getDishImg());
            dish_json.put("DishPrice", dish.getDishPrice());
            dish_arr.add(dish_json);
        }
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        out.write(dish_arr.toString());
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
