/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.admin.servlet;
import com.dao.DoctorDao;
import com.db.DBconnect;
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
public class DeleteDoctor extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = Integer.parseInt(req.getParameter("id"));
    DoctorDao dao = new DoctorDao(DBconnect.getconn());
    HttpSession session = req.getSession();
    boolean f = dao.DeleteDoctor(id);
             if(f){
                session.setAttribute("succMsg","Doctor Deleted Successfully");
                 resp.sendRedirect("admin/view_doctor.jsp");
             }
             else{
                 session.setAttribute("errorMsg","Something Wrong on server");
                 resp.sendRedirect("admin/view_docotor.jsp");
             }

    
    }
    
    
    
    
}
