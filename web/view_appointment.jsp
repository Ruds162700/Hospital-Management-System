<%-- 
    Document   : view_sppointment
    Created on : 18-Apr-2024, 9:58:06 am
    Author     : rudra
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.entity.User"%>
<%@page import="com.entity.Doctor"%>
<%@page import="com.dao.DoctorDao"%>
<%@page import="com.entity.Appointment"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.Class"%>
<%@page import="com.db.DBconnect"%>
<%@page import="com.dao.AppointmentDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@ include file="component/allcss.jsp" %>
         <style type="text/css">
    .paint-card{
        box-shadow: 0 0 10px 0 rgba(0,0,0,0.3);
    }
    .backImg {
		background: linear-gradient(rgba(0, 0, 0, .4), rgba(0, 0, 0, .4)),
			url("img/main_pg.jpg");
		height: 20vh;
		width: 100%;
		background-size: cover;
		background-repeat: no-repeat;
	}
          </style>
    </head>
    <body>
        
        
	<c:if test="${ empty userObj }">
		<c:redirect url="user_login.jsp"></c:redirect>
	</c:if>

        <%@include file="component/navbar.jsp" %>

        
	<div class="container-fulid backImg p-5">
		<p class="text-center fs-2 text-white"></p>
	</div>
	<div class="container p-3">
		<div class="row">
			<div class="col-md-9">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 fw-bold text-center text-success">Appointment List</p>
						<table class="table">
							<thead>
								<tr>
                                                                    
                                                                    <!-- here we will call get appoinment function in appoinment dao servlet -->
									<th scope="col">Full Name</th>
									<th scope="col">Gender</th>
									<th scope="col">Age</th>
									<th scope="col">Appoint Date</th>
									<th scope="col">Diseases</th>
									<th scope="col">Doctor Name</th>
									<th scope="col">Status</th>

								</tr>
							</thead>
							<tbody>
							<%  
                                                           User user = (User)session.getAttribute("userObj");
                                                           AppointmentDao dao = new AppointmentDao(DBconnect.getconn());
                                                           DoctorDao dao2 = new DoctorDao(DBconnect.getconn());
                                                            List<Appointment> list =  dao.getAllAppointmentByLoginUser(user.getId());
                                                           for(Appointment ap:list)
                                                           {
                                                           Doctor d = dao2.getDoctorById(ap.getDoctorId());
                                                           %> 
                                                           <tr>
                                                                        <th><%=ap.getFullName()%></th>
									<td><%=ap.getGender()%></td>
									<td><%=ap.getAge()%></td>
									<td><%=ap.getAppoinDate()%></td>
									<td><%=ap.getDiseases()%></td>
									<td><%=d.getFullName()%></td>
									<td>
                                                                            
                                                                        <%
										if ("pending".equals(ap.getStatus())) {
										%> <a href="#" class="btn btn-sm btn-warning">Pending</a> <%
 										} else {
 											%> <%=ap.getStatus()%> <%
 										}
 										%>
                                                                        </td>
                                                               </tr>
                                                           
                                                            <%
                                                                
                                                                
                                                         
                                                                }
                                                        
                                                        
                                                        %>
                                                            
                                                            
                                                            
                                                            
                                                            
								<tr>
									<th></th>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>

			<div class="col-md-3 p-3">
				<img alt="" src="img/doc_mainpg.jpg" style="height: 300px">
			</div>
		</div>
	</div>
    </body>	
</html>
