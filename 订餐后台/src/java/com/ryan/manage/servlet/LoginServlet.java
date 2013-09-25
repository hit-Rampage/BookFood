/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryan.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ryan.manage.link.AdminLink;
import com.ryan.manage.entity.AdminEntity;

/**
 *
 * @author ryan
 */
public class LoginServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String adminname = req.getParameter("adminname");
        String adminpwd = req.getParameter("adminpwd");
        boolean flag = new AdminLink().findAdminIsExist(adminname, adminpwd);
        if (!flag) {
            req.getSession().setAttribute("errormsg", "对不起，登录名或密码错误，请重新输入！");
            res.sendRedirect("login.jsp");
        } else {
            req.getSession().setAttribute("adminname", adminname);
            req.getRequestDispatcher("index.html").forward(req, res);
        }
    }
}
