/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryan.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ryan.manage.entity.UserEntity;
import com.ryan.manage.link.UserLink;

/**
 *
 * @author ryan
 */
public class UserServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List list = new UserLink().findUserAll();
        req.setAttribute("userlist", list);
        req.getRequestDispatcher("userManage.jsp").forward(req, res);
    }
}
