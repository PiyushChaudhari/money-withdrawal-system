<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta name="viewport" content="width=device-width, initial-scale=1">  
<jsp:include page="../common/_css.jsp"></jsp:include>
<title>Home</title>
<title>User Registration</title>
</head>
<body>

	
	<div class="container">
	
  	  <div class="row">
  	  	<div class="col-md-6 col-md-offset-3 text-center">
  	  		<h2>User Registration</h2>
  	  	</div>
  	  </div>
  	  
  	 <form:form method="POST" action="${contextPath}/user/registration" modelAttribute="userRegistration" > 
  	  
	  	  <div class="form-group row col-md-offset-3">
	      	<label for="name" class="col-sm-2 col-form-label">Name</label>
	      	<div class="col-sm-4">
	       		<input type="text" class="form-control" id="name" name="name" placeholder="Please enter name" value="${userRegistration.name}" />
	      	</div>
	      	
	      	<c:if test = "${userRegistration.errorMap.containsKey('name')}">
		      	<div class="col-sm-6">
		        	<small id="passwordHelp" class="text-danger">
		          		${userRegistration.errorMap.get('name')}
		        	</small>      
		      	</div>
	      	</c:if>
	      	
	     </div>
	     
	     <div class="form-group row col-md-offset-3">
	      	<label for="email" class="col-sm-2 col-form-label">Email</label>
	      	<div class="col-sm-4">
	       		<input type="text" class="form-control" id="email" name="email" placeholder="Please enter email" value="${userRegistration.email}" />
	      	</div>
	      	
	      	<c:if test = "${userRegistration.errorMap.containsKey('email')}">
		      	<div class="col-sm-6">
		        	<small id="passwordHelp" class="text-danger">
		          		${userRegistration.errorMap.get('email')}
		        	</small>      
		      	</div>
	      	</c:if>
	      	
	     </div>
	     
	     <div class="form-group row col-md-offset-3">
	      	<label for="mobile" class="col-sm-2 col-form-label">Mobile</label>
	      	<div class="col-sm-4">
	       		<input type="text" class="form-control" id="mobile" name="mobile" maxlength="10" onkeypress="return onlyNumberKey(event)" placeholder="Please enter mobile number" value="${userRegistration.mobile}" />
	      	</div>
	      	<c:if test = "${userRegistration.errorMap.containsKey('mobile')}">
		      	<div class="col-sm-6">
		        	<small id="passwordHelp" class="text-danger">
		          		${userRegistration.errorMap.get('mobile')}
		        	</small>      
		      	</div>
	      	</c:if>
	     </div>
	     
	     <div class="form-group row col-md-offset-3">
	      	<label for="age" class="col-sm-2 col-form-label">Age</label>
	      	<div class="col-sm-4">
	       		<input type="number" data-bind="value:replyNumber" min="10" step="1" max="150" class="form-control" id="age" name="age" placeholder="Please enter age" value="${userRegistration.age}" />
	      	</div>
	      	<c:if test = "${userRegistration.errorMap.containsKey('age')}">
		      	<div class="col-sm-6">
		        	<small id="passwordHelp" class="text-danger">
		          		${userRegistration.errorMap.get('age')}
		        	</small>      
		      	</div>
	      	</c:if>
	     </div>
	     
	     <div class="form-group row col-md-offset-3">
	      	<label for="gender" class="col-sm-2 col-form-label">Gender</label>
	      	<div class="col-sm-4">
	       		<!-- <input type="text" class="form-control" id="gender" name="gender" placeholder="Please enter gender"> -->
	       		<select id="gender" name="gender" class="form-control">
				    <option value="">--Select--</option>
				    <option value="Male" ${'Male'.equals(userRegistration.gender) ? 'selected' : ''} >Male</option>
				    <option value="Female" ${'Female'.equals(userRegistration.gender) ? 'selected' : ''} >Female</option>
				</select>
	      	</div>
	      	<c:if test = "${userRegistration.errorMap.containsKey('gender')}">
		      	<div class="col-sm-6">
		        	<small id="passwordHelp" class="text-danger">
		          		${userRegistration.errorMap.get('gender')}
		        	</small>      
		      	</div>
	      	</c:if>
	     </div>
	     
	     <div class="form-group row col-md-offset-3">
	      	<label for="monthlySalary" class="col-sm-2 col-form-label">Monthly Salary</label>
	      	<div class="col-sm-4">
	       		<input type="text" class="form-control" id="monthlySalary" maxlength="10" name="monthlySalary" onkeypress="return onlyNumberKey(event)" placeholder="Please enter monthly salary" value="${userRegistration.monthlySalary}" />
	      	</div>
	      	<c:if test = "${userRegistration.errorMap.containsKey('monthlySalary')}">
		      	<div class="col-sm-6">
		        	<small id="passwordHelp" class="text-danger">
		          		${userRegistration.errorMap.get('monthlySalary')}
		        	</small>      
		      	</div>
	      	</c:if>
	     </div>
     
	     <div class="form-group row col-md-offset-3">
	      	<label class="col-sm-2 col-form-label"></label>
	      	<div class="col-sm-4">
	       		<button type="submit" class="btn btn-primary">Submit</button>
	       		<button type="button" class="btn btn-secondary" onclick="home();">Cancel</button>
	      	</div>
	     </div>
	     
    </form:form>
   
 </div>
 
 <jsp:include page="../common/_external-script.jsp"></jsp:include>
 <script type="text/javascript">
 function home(){
 	window.location.href = "${contextPath}/";
 }

 function onlyNumberKey(evt) { 
     
     // Only ASCII charactar in that range allowed 
     var ASCIICode = (evt.which) ? evt.which : evt.keyCode 
     if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57)) 
         return false; 
     return true; 
 }
</script>
</body>
</html>