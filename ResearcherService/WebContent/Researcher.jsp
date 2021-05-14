<%@ page import="com.Researcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Researcher Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/research.js"></script>  
</head>
<body>

<div class="container"> 
	<div class="row">   
		<div class="col-6"> 
			<h1>RESEARCHER SERVICE</h1>
				<form id="formResearcher" name="formResearcher" method="post" action="Researcher.jsp">  
					Researcher Name:  
 	 				<input id="rname" name="rname" type="text"  class="form-control form-control-sm">
					<br> Description :   
  					<input id="rDesp" name="rDesp" type="text" class="form-control form-control-sm">   
  					<br> Date:   
  					<input id="rdate" name="rdate" type="date"  class="form-control form-control-sm">
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidResearchIDSave" name="hidResearchIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divResearchGrid">
					<%
						Researcher researchObj = new Researcher();
						out.print(researchObj.readResearcher());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>