/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.admin.servlet;

import com.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author rudra
 */
public class AdminLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    
         try{
             String email = req.getParameter("email");
             String password = req.getParameter("password");
             HttpSession session = req.getSession();
             
             
             
             if("rudrapanchal1604@gmail.com".equals(email) && "Rudra@1604".equals(password)){
                 
                 session.setAttribute("adminObj",new User());
                 resp.sendRedirect("admin/index.jsp");
                 
             }
             else{
                 session.setAttribute("errorMsg","invalid email & password");
                 resp.sendRedirect("admin_login.jsp");
                 
             }

             
             
             
             
             
         }catch(Exception e){
             
         }
    }
    
    
}
