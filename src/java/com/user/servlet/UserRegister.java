/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.user.servlet;

import com.dao.UserDao;
import com.db.DBconnect;
import com.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
 * @author rudra
 */
public class UserRegister extends HttpServlet {
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			
			String fullName = request.getParameter("fullname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			User u = new User(fullName, email, password);
			
			UserDao dao = new UserDao(DBconnect.getconn());
			
			HttpSession session = request.getSession();
			boolean f = dao.register(u);
			
			if(f) {
				
				session.setAttribute("sucMsg","Register successfully");
                                response.sendRedirect("signup.jsp");
			}
			else {
				session.setAttribute("errorMsg","Something Wrong On Server");
                                response.sendRedirect("signup.jsp");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
       
    }

   

}
