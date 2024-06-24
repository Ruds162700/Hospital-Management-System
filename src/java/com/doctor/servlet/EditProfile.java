/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doctor.servlet;

/**
 *
 * @author rudra
 */
import com.dao.DoctorDao;
import com.db.DBconnect;
import com.entity.Doctor;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
public class EditProfile extends HttpServlet{

    @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   try{
        
        String fullName = req.getParameter("fullname");
        String dob = req.getParameter("dob");
        String qualification = req.getParameter("qualification");
        String spec = req.getParameter("spec");
        String email= req.getParameter("email");
        String mobno = req.getParameter("mobno");
        int id = Integer.parseInt(req.getParameter("id"));
        
        Doctor d = new Doctor(id,fullName,dob,qualification,spec,email,mobno,"");
        
        DoctorDao dao = new DoctorDao(DBconnect.getconn());
        
        HttpSession session = req.getSession();
        
        boolean f = dao.editDoctorProfile(d);
             if(f){
                session.setAttribute("sucMsgd","Data Updated Successfully of Doctor");
                Doctor updateDoctor  = dao.getDoctorById(id);
                session.setAttribute("docObj",updateDoctor);
                resp.sendRedirect("doctor/edit_profile.jsp");
             }
             else{
                 session.setAttribute("erroMsgd","Something Wrong on server");
                 resp.sendRedirect("doctor/edit_profile.jsp");
             }

        
        
        
    }catch(Exception e){
        e.printStackTrace();
    }
    }
}
