<%-- 
    Document   : view_doctor
    Created on : 17-Apr-2024, 4:42:01 pm
    Author     : rudra
--%>

<%@ page import="java.util.List" %>
<%@ page import="com.dao.SpecialistDao"%>
<%@ page import="com.db.DBconnect" %>
<%@ page import="com.entity.Specialist" %>
<%@ page import="com.entity.Doctor" %>
<%@ page import="com.dao.DoctorDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <%@include file="../component/allcss.jsp" %>
    <style>
        .paint-card {
            box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
        }
    </style>
</head>
<body>
<%@include file="navbar.jsp" %>

<div class="container-fluid p-3">
    <div class="row">
    
        <div class="col-md-12">
            <div class="card paint-card">
                <div class="card-body">
                    <p class="fs-3 text-center">Doctor Details</p>
                    <c:if test="${not empty errorMsg}">
                        <p class="fs-3 text-center text-danger">${errorMsg}</p>
                        <c:remove var="errorMsg" scope="session" />
                    </c:if>
                    <c:if test="${not empty succMsg}">
                        <p class="fs-3 text-center text-success">${succMsg}</p>
                        <c:remove var="succMsg" scope="session" />
                    </c:if>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Full Name</th>
                                <th scope="col">DOB</th>
                                <th scope="col">Qualification</th>
                                <th scope="col">Specialist</th>
                                <th scope="col">Email</th>
                                <th scope="col">Mob No</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% DoctorDao dao2 = new DoctorDao(DBconnect.getconn());
                            List<Doctor> list2 = dao2.getAllDoctor();
                            for(Doctor d : list2) { %>
                                <tr>
                                    <td><%= d.getFullName() %></td>
                                    <td><%= d.getDob() %></td>
                                    <td><%= d.getQualification() %></td>
                                    <td><%= d.getSpecialist() %></td>
                                    <td><%= d.getEmail() %></td>
                                    <td><%= d.getMobNo() %></td>
                                    <td>
                                        <a href="edit_doctor.jsp?id=<%=d.getId() %>" class="btn btn-sm btn-primary">Edit</a>
                                        <a href="../DeleteDoctor?id=<%=d.getId() %>" class="btn btn-sm btn-danger">Delete</a>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

