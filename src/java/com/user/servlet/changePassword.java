/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.user.servlet;

/**
 *
 * @author rudra
 */
import com.dao.UserDao;
import com.db.DBconnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class changePassword extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    String oldpass = req.getParameter("oldPassword");
    String newpass = req.getParameter("newPassword");
    
    int id =Integer.parseInt(req.getParameter("uid"));
    UserDao dao = new UserDao(DBconnect.getconn());
    HttpSession session = req.getSession();
     
    boolean f = dao.checkOldPassword(id, oldpass);
    if(f){
       boolean k  = dao.changePassword(id, newpass);
       if(k){
        session.setAttribute("succMsg","Password changed successfully");
        resp.sendRedirect("changepassword.jsp");

       }else{
        session.setAttribute("errorMsg","Something Wrong on server");
        resp.sendRedirect("changepassword.jsp");

       }
    }else{
        session.setAttribute("errorMsg","Old Password is incorrect");
        resp.sendRedirect("changepassword.jsp");
    }
    
    }
    
    
    
}
