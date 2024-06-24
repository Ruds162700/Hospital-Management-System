package com.doctor.servlet;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.dao.DoctorDao;
import com.dao.UserDao;
import com.db.DBconnect;
import com.entity.Doctor;
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
public class DoctorLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   
         try{
             String email = req.getParameter("email");
             String password = req.getParameter("password");
             HttpSession session = req.getSession();
             
             DoctorDao dao = new DoctorDao(DBconnect.getconn());
             Doctor d =  dao.Login(email,password);
             
             if(d!=null){
                 
                 session.setAttribute("docObj",d);
                 resp.sendRedirect("doctor/index.jsp");
                 
             }
             else{
                 session.setAttribute("errorMsg","invalid email & password");
                 resp.sendRedirect("doctor_login.jsp");
                 
             }   
         }catch(Exception e){
             
         }
        
    
    }
    
    
    
    
}
