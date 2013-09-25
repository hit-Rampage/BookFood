/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryan.manage.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ryan.manage.link.FeedbackLink;
import com.ryan.manage.entity.FeedbackEntity;

/**
 *
 * @author ryan
 */
public class FeedbackServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List listMsg = new FeedbackLink().findFeedbackMsg();
        req.setAttribute("listMsg", listMsg);
        req.getRequestDispatcher("feedback.jsp").forward(req, res);
    }
}
