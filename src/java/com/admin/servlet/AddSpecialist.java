/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.admin.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.dao.SpecialistDao;
import com.db.DBconnect;
import com.entity.User;
import jakarta.servlet.http.HttpSession;
/**
 *
 * @author rudra
 */
public class AddSpecialist extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String specName = req.getParameter("specName");
         SpecialistDao dao = new SpecialistDao(DBconnect.getconn());
         boolean f = dao.addSpecialist(specName);
          
         HttpSession session = req.getSession();
         
             if(f){
                session.setAttribute("succMsg","Specialist Added Successfully");
                 resp.sendRedirect("admin/index.jsp");
             }
             else{
                 session.setAttribute("errorMsg","Something Wrong on server");
                 resp.sendRedirect("admin_login.jsp");
             }
    
    }
    
}
