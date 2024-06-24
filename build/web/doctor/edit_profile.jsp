<%-- 
    Document   : edit_profile
    Created on : 19-Apr-2024, 9:19:15 am
    Author     : rudra
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.dao.SpecialistDao"%>
<%@ page import="com.db.DBconnect" %>
<%@ page import="com.entity.Specialist" %>
<%@ page import="com.entity.Doctor" %>
<%@ page import="com.dao.DoctorDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Profile</title>
    <%@include file="../component/allcss.jsp"%>
    <style type="text/css">
        .paint-card {
            box-shadow: 0 0 10px 0 rgba(0,0,0,0.3);
        }
    </style>
</head>
<body>
<c:if test="${empty docObj}">
    <c:redirect url="../doctor_login.jsp"></c:redirect>
</c:if>

<%@include file="navbar.jsp" %> 

<div class="container p-4">
    <div class="row">
        <div class="col-md-4">
            <div class="card paint-card">
                <p class="text-center fs-3">Change Password</p>
                <c:if test="${not empty succMsgd}">
                    <p class="text-center text-success fs-3">${succMsgd}</p>
                    <c:remove var="succMsgd" scope="session" />
                </c:if>

                <c:if test="${not empty errorMsgd}">
                    <p class="text-center text-danger fs-5">${errorMsgd}</p>
                    <c:remove var="errorMsgd" scope="session" />
                </c:if>
                <div class="card-body">
                    <form action="../DoctorPasswordChange" method="post">
                        <div class="mb-3">
                            <label>Enter New Password</label>
                            <input type="text" name="newPassword" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label>Enter Old Password</label>
                            <input type="text" name="oldPassword" class="form-control" required>
                        </div>
                        <input type="hidden" value="${docObj.id}" name="uid">
                        <button class="btn btn-success col-md-12">Change Password</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-5 offset-md-2">
            <div class="card paint-card">
                <p class="text-center fs-3">Edit Profile</p>
                
                <!-- Message display code -->
                <c:if test="${not empty sucMsgd}">
                    <p class="text-center text-success fs-3">${sucMsgd}</p>
                    <c:remove var="sucMsgd" scope="session" />
                </c:if>

                <c:if test="${not empty erroMsgd}">
                    <p class="text-center text-danger fs-5">${erroMsgd}</p>
                    <c:remove var="erroMsgd" scope="session" />
                </c:if>

                
                <div class="card-body">
                    <form action="../EditProfile" method="post">
                        <div class="mb-3">
                            <label class="form-label">Full Name</label>
                            <input type="text" required name="fullname" class="form-control" value="${docObj.getFullName()}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">DOB</label>
                            <input type="date" required name="dob" class="form-control" value="${docObj.getDob()}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Qualification</label>
                            <input required name="qualification" type="text" class="form-control" value="${docObj.getQualification()}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Specialist</label>
                            <select name="spec" required class="form-control">
                                <option>${docObj.specialist}</option>
                                <% SpecialistDao dao = new SpecialistDao(DBconnect.getconn());
                                List<Specialist> list = dao.getAllSpecialist();
                                for(Specialist s: list) { %>
                                    <option><%= s.getSpecialistName() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="text" required name="email" class="form-control" readonly="" value="${docObj.getEmail()}" >
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Mob No</label>
                            <input type="text" required name="mobno" class="form-control" value="${docObj.getMobNo()}">
                        </div>
                        <input type="hidden" name="id" value="${docObj.getId()}">
                        <button type="submit" class="btn btn-primary">Update</button>
                    </form>
                </div>

            </div>       
        </div>   
    </div>
</div>
</body>
</html>

