<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">  
<jsp:include page="../common/_css.jsp"></jsp:include>
<title>Withdraw Request</title>
</head>
<body>

	<div class="container">
  	  
	  	  <div class="row">
	  	  	<div class="col-md-6 col-md-offset-3 text-center">
	  	  		<h2>Transaction message</h2>
	  	  	</div>
	  	  </div>
		  	  
		  	  
	  	  <div class="form-group row col-md-offset-3">
		      	<label class="col-sm-7 col-form-label"> ${msg} </label>
		  </div>
		  <div class="form-group row col-md-offset-3">
		      	<label class="col-sm-7 col-form-label"><a href="${contextPath}/" >Go to home</a></label>
		  </div>
		  
		  
  	</div>
<jsp:include page="../common/_external-script.jsp"></jsp:include>
</body>
</html>