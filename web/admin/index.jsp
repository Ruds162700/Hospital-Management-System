<%-- 
    Document   : index
    Created on : 15-Apr-2024, 5:13:15 pm
    Author     : rudra
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.dao.DoctorDao" %>
<%@page import="com.db.DBconnect" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../component/allcss.jsp" %>
    <style>
	.paint-card{
		box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3)
	}
</style>
    </head>
    <body>
        <!--Including the new navbar which we have desgined for admin -->
        <%@include file="navbar.jsp" %>   
        
        
        <!--it will help that no person can directly access the admin page without login (object) -->
        <c:if test="${empty adminObj }">
		<c:redirect url="../admin_login.jsp"></c:redirect>
	</c:if>	

- 	<c:if test="${ empty adminObj }">
		<c:redirect url="../admin_login.jsp"></c:redirect>
	</c:if>
        
        <%DoctorDao dao = new DoctorDao(DBconnect.getconn());%>
        
	<div class="container p-5">
		<p class="text-center fs-3">Admin Dashboard</p>
               
		<c:if test="${not empty errorMsg}">
			<p class="fs-3 text-center text-danger">${errorMsg}</p>
			<c:remove var="errorMsg" scope="session" />
		</c:if>
		<c:if test="${not empty succMsg}">
			<div class="fs-3 text-center text-success" role="alert">${succMsg}</div>
			<c:remove var="successMsg" scope="session" />
		</c:if>
		
		
		
               <!-- This Will display the data of doctor in terms of numbers-->
                <div class="row">
			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-md fa-3x"></i><br>
						<p class="fs-4 text-center">
							Doctor <br><%=dao.countDoctor()%>
						</p>
					</div>
				</div>
			</div>
                    
			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="fas fa-user-circle fa-3x"></i><br>
						<p class="fs-4 text-center">
							User <br><%=dao.countUser()%>
						</p>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-success">
						<i class="far fa-calendar-check fa-3x"></i><br>
						<p class="fs-4 text-center">
							Total Appointment <br><%=dao.countAppointment()%>
						</p>
					</div>
				</div>
			</div>

			<div class="col-md-4 mt-2">

				<div class="card paint-card " data-bs-toggle="modal" 
                                     data-bs-target="#exampleModal">
					<div class="card-body text-center text-success">
						<i class="far fa-calendar-check fa-3x"></i><br>
						<p class="fs-4 text-center">
							Specialist <br><%=dao.countSpecialist()%>
						</p>
					</div>
				</div>

			</div>

		</div>
	</div>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form action="../AddSpecialist" method="post">
              
              <div class="form-group">
                  <label>Enter Specialist Name</label>
                  <input type="text" name="specName" class="form-control">
              </div>
              <div class="text-center mt-3">
                  <button type="submit" class="btn btn-primary">Add</button>
              </div> 
          </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
	

    </body>
</html>
