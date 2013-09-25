/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryan.manage.servlet;

import java.util.*;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ryan.manage.entity.DishEntity;
import com.ryan.manage.link.DishLink;

/**
 *
 * @author ryan
 */
public class AddDishServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        String destinationfileName = "";
        String uploadPath = req.getSession().getServletContext().getRealPath("/") + "DishImg/";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024);
        factory.setRepository(new File(uploadPath));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");
        List<String> strList = new ArrayList<String>();
        try {
            List fileItems = upload.parseRequest(req);
            Iterator i = fileItems.iterator();
            while (i.hasNext()) {
                FileItem fileitem = (FileItem) i.next();
                if (fileitem.isFormField()) {
                    strList.add(fileitem.getString("utf-8"));
                } else {
                    String fileName = fileitem.getName();
                    if (fileName != null) {
                        Random rd = new Random();
                        Calendar time = Calendar.getInstance();
                        String tmp = "" + time.get(Calendar.YEAR) + time.get(Calendar.MONTH)
                                + time.get(Calendar.DAY_OF_MONTH) + time.get(Calendar.HOUR_OF_DAY)
                                + time.get(Calendar.MINUTE) + time.get(Calendar.SECOND);
                        destinationfileName = tmp + rd.nextInt(100) + fileName.substring(fileName.length() - 4);
                        File file = new File(uploadPath + destinationfileName);
                        fileitem.write(file);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String dishname = strList.get(0);
        String dishseries = strList.get(1);
        String strprice = strList.get(2);
        String imgaddress = destinationfileName;
        if (dishname.length() == 0 || dishseries.length() == 0) {
            req.getRequestDispatcher("dishAdd.jsp").forward(req, res);
        } else {
            DishEntity dish = new DishEntity();
            dish.setDishName(dishname);
            dish.setDishSeries(dishseries);
            try {
                double dishprice = Double.parseDouble(strprice);
                dish.setDishPrice(dishprice);
            } catch (Exception e) {
                dish.setDishPrice(0);
            }
            if (imgaddress.length() == 0) {
                dish.setDishImg("DishImg/00.jpg");
            } else {
                imgaddress = "DishImg/" + imgaddress;
                dish.setDishImg(imgaddress);
            }
            try {
                new DishLink().addDish(dish);
            } catch (Exception e) {
                e.printStackTrace();
            }

            req.getRequestDispatcher("dishAdd.jsp").forward(req, res);
        }

    }
}
