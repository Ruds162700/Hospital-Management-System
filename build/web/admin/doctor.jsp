<%-- 
    Document   : doctor.jsp
    Created on : 17-Apr-2024, 10:08:57 am
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
        
        <div class="col-md-5 offset-md-4">
            <div class="card paint-card">
                <div class="card-body">
                    <p class="fs-3 text-center">Add Doctor</p>
                    <c:if test="${not empty errorMsg}">
                        <p class="fs-3 text-center text-danger">${errorMsg}</p>
                        <c:remove var="errorMsg" scope="session" />
                    </c:if>
                    <c:if test="${not empty succMsg}">
                        <p class="fs-3 text-center text-success">${succMsg}</p>
                        <c:remove var="succMsg" scope="session" />
                    </c:if>
                    <form action="../AddDoctor" method="post">
                        <div class="mb-3">
                            <label class="form-label">Full Name</label>
                            <input type="text" required name="fullname" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">DOB</label>
                            <input type="date" required name="dob" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Qualification</label>
                            <input required name="qualification" type="text" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Specialist</label>
                            <select name="spec" required class="form-control">
                                <option>--select--</option>
                                <% SpecialistDao dao = new SpecialistDao(DBconnect.getconn());
                                List<Specialist> list = dao.getAllSpecialist();
                                for(Specialist s: list) { %>
                                    <option><%= s.getSpecialistName() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="text" required name="email" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Mob No</label>
                            <input type="text" required name="mobno" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input required name="password" type="password" class="form-control">
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    
    </div>
</div>
</body>
</html>
