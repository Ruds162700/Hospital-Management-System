/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.admin.servlet;
import com.dao.DoctorDao;
import com.db.DBconnect;
import com.entity.Doctor;
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
public class UpdateDoctor extends HttpServlet{

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
        
        int id = Integer.parseInt(req.getParameter("id"));
        
        Doctor d = new Doctor(id,fullName,dob,qualification,spec,email,mobno,password);
        
        DoctorDao dao = new DoctorDao(DBconnect.getconn());
        
        HttpSession session = req.getSession();
        
        boolean f = dao.UpdateDoctor(d);
             if(f){
                session.setAttribute("succMsg","Doctor Updated Successfully");
                 resp.sendRedirect("admin/view_doctor.jsp");
             }
             else{
                 session.setAttribute("errorMsg","Something Wrong on server");
                 resp.sendRedirect("admin/view_docotor.jsp");
             }

        
        
        
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    
}
