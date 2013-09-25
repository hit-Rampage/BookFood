/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryan.manage.servlet;

import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ryan.manage.link.DeliverLink;
import com.ryan.manage.link.DishLink;
import com.ryan.manage.link.FeedbackLink;
import com.ryan.manage.link.UserLink;

/**
 *
 * @author ryan
 */
public class ExStateServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String strId = req.getParameter("id");
        char type = strId.charAt(0);
        int Id = 0;
        if (type != 'i') {
            strId = strId.substring(1);
            Id = Integer.parseInt(strId);
        }

        if (type == 'a') {
            DeliverLink ex = new DeliverLink();
            ex.modifyDeliverState(Id, "已配送");
            req.getRequestDispatcher("DeliverServlet").forward(req, res);
        } else if ((type == 'b') || (type == 'e')) {
            DeliverLink ex = new DeliverLink();
            ex.delDeliverById(Id);
            if (type == 'b') {
                req.getRequestDispatcher("DeliverServlet").forward(req, res);
            } else {
                req.getRequestDispatcher("DeliverMServlet").forward(req, res);
            }
        } else if (type == 'c') {
            DishLink ex = new DishLink();
            String strPrice = req.getParameter("dishprice");
            try {
                double price = Double.parseDouble(strPrice);
                ex.modifyDishPrice(Id, price);
            } catch (NumberFormatException e) {
            }
            req.getRequestDispatcher("DishServlet").forward(req, res);
        } else if (type == 'd') {
            DishLink ex = new DishLink();
            String tmp = ex.delDishById(Id);
            if (tmp.length() != 0) {
                String path = req.getSession().getServletContext().getRealPath("/") + tmp;
                File file = new File(path);
                file.delete();
            }
            req.getRequestDispatcher("DishServlet").forward(req, res);
        } else if (type == 'f') {
            UserLink ex = new UserLink();
            ex.modifyUserState(Id, "被禁");
            req.getRequestDispatcher("UserServlet").forward(req, res);
        } else if (type == 'g') {
            UserLink ex = new UserLink();
            ex.modifyUserState(Id, "正常");
            req.getRequestDispatcher("UserServlet").forward(req, res);
        } else if (type == 'h') {
            UserLink ex = new UserLink();
            ex.delUserById(Id);
            req.getRequestDispatcher("UserServlet").forward(req, res);
        } else if (type == 'i') {
            FeedbackLink ex = new FeedbackLink();
            ex.truncateMsg();
            req.getRequestDispatcher("FeedbackServlet").forward(req, res);
        }
    }
}
