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
	  	  		<h2>Withdrawal Request</h2>
	  	  	</div>
	  	  </div>
	  	  
	  	  <form:form method="POST" action="${contextPath}/account/note" modelAttribute="requestVO" >
	  	  	  <input type="hidden" name="withdrawalType" value="${requestVO.withdrawalType}" />
	  	  	  <input type="hidden" name="currencyType" value="${requestVO.currencyType}" />
		  	  <div class="form-group row col-md-offset-3">
			      	<label for="amount" class="col-sm-2 col-form-label">Amount</label>
			      	<div class="col-sm-4">
			       		<input type="text" class="form-control" id="amount" name="amount" placeholder="Please enter amount" value="${requestVO.amount}" />
			      	</div>
			      	
			      	<c:if test = "${requestVO.errorMap.containsKey('amount')}">
				      	<div class="col-sm-6">
				        	<small id="passwordHelp" class="text-danger">
				          		${requestVO.errorMap.get('amount')}
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