<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta name="viewport" content="width=device-width, initial-scale=1">  
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> -->
<jsp:include page="common/_css.jsp"></jsp:include>
<title>Home</title>
</head>
<body>
<div class="container">
	
	<div class="form-group row col-md-offset-5">
			<label class="col-md-4 col-form-label">1) <a href="${contextPath}/user/" > Register a user</a></label>
	</div>
	
	<div class="form-group row col-md-offset-5">
			<label class="col-md-4 col-form-label">2) <a href="${contextPath}/account/india"> Withdraw INR</a></label>
	</div>
	
	<div class="form-group row col-md-offset-5">
			<label class="col-md-5 col-form-label">3) <a href="${contextPath}/account/international"> Withdraw international currency</a></label>
	</div>
	
	<div class="form-group row col-md-offset-5">
			<label class="col-md-4 col-form-label">4) <a href="${contextPath}/account/" > Check balance</a></label>
	</div>
</div>
		
<jsp:include page="common/_external-script.jsp"></jsp:include>  
</body>
</html>