package com.admin.servlet;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.entity.Doctor;
import com.dao.DoctorDao;
import com.db.DBconnect;
import jakarta.servlet.http.HttpSession;
/**
 *
 * @author rudra
 */
public class AddDoctor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
        
        String fullName = req.getParameter("fullname");
        String dob = req.getParameter("dob");
        String qualification = req.getParameter("qualification");
        String spec = req.getParameter("spec");
        String email= req.getParameter("email");
        String mobno = req.getParameter("mobno");
        String password = req.getParameter("password");
        
        Doctor d = new Doctor(fullName,dob,qualification,spec,email,mobno,password);
        DoctorDao dao = new DoctorDao(DBconnect.getconn());
        HttpSession session = req.getSession();
        boolean f = dao.registerDoctor(d);
             if(f){
                session.setAttribute("succMsg","Doctor Added Successfully");
                 resp.sendRedirect("admin/doctor.jsp");
             }
             else{
                 session.setAttribute("errorMsg","Something Wrong on server");
                 resp.sendRedirect("admin/docotor.jsp");
             }

        
        
        
    }catch(Exception e){
        e.printStackTrace();
    }
    
    }
    
    
}
