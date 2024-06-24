/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.user.servlet;

/**
 *
 * @author rudra
 */
import com.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import com.dao.UserDao;
import com.entity.User;
import com.db.DBconnect;
public class UserLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
  try{
             String email = req.getParameter("email");
             String password = req.getParameter("password");
             HttpSession session = req.getSession();
             
             UserDao dao = new UserDao(DBconnect.getconn());
             User user =  dao.login(email,password);
             
             if(user!=null){
                 
                 session.setAttribute("userObj",user);
                 resp.sendRedirect("index.jsp");
                 
             }
             else{
                 session.setAttribute("errorMsg","invalid email & password");
                 resp.sendRedirect("user_login.jsp");
                 
             }   
         }catch(Exception e){
             
         }
    }
    
    
    
}
