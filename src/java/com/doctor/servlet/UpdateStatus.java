/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doctor.servlet;

/**
 *
 * @author rudra
 */
import com.dao.AppointmentDao;
import com.db.DBconnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
public class UpdateStatus extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   try{
    AppointmentDao dao = new AppointmentDao(DBconnect.getconn());
    int id =Integer.parseInt(req.getParameter("id"));
    int docid = Integer.parseInt(req.getParameter("did"));
    String comm = req.getParameter("comm");
    boolean f = dao.updateCommentStatus(id, docid, comm);
    HttpSession session=req.getSession();
   
    if(f){
        session.setAttribute("succMsg","Comment Added succesfully");
        resp.sendRedirect("doctor/patient.jsp");
    }
    else{
        session.setAttribute("errorMsg","Something Wrong on server");
        resp.sendRedirect("doctor/patient.jsp");
    }
   }catch(Exception e){
       e.printStackTrace();
   }
    
    
    }
    
    
}
