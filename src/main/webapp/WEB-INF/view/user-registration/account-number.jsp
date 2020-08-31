<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">  
<jsp:include page="../common/_css.jsp"></jsp:include>
<title>Account Details</title>
</head>
<body>

		<div class="container">
	
		  	  <div class="row">
		  	  	<div class="col-md-6 col-md-offset-3 text-center">
		  	  		<h2>Account Details</h2>
		  	  	</div>
		  	  </div>
  	  
  	  
  	  
	  	  <div class="form-group row col-md-offset-3">
	      	<label for="name" class="col-sm-3 col-form-label">Account Number:</label>
	      	<div class="col-sm-4">
	       		${accountnumber}
	      	</div>
	     </div>
	     <div class="form-group row col-md-offset-3">
	      	<label for="name" class="col-sm-3 col-form-label">Balance:</label>
	      	<div class="col-sm-4">
	       		${balance}
	      	</div>
	     </div>
	     
	     <div class="form-group row col-md-offset-3">
	      	<label for="name" class="col-sm-3 col-form-label"></label>
	      	<div class="col-sm-4">
	       		<a href="${contextPath}/" >Go to home</a>
	      	</div>
	     </div>
	     
	 </div>    

	<jsp:include page="../common/_external-script.jsp"/>	
</body>
</html>