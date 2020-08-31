<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta name="viewport" content="width=device-width, initial-scale=1">  
<jsp:include page="../common/_css.jsp"></jsp:include>
<title>Check balance</title>
</head>
<body>
	<div class="container">
	
  	  <div class="row">
  	  	<div class="col-md-6 col-md-offset-3 text-center">
  	  		<h2>Account Balance</h2>
  	  	</div>
  	  </div>
  	  
  	  
  	  <div class="row">
	  	  <c:choose>
		    <c:when test="${null != userAccount.accountNumber}">
		    	
		    	<div class="form-group row col-md-offset-3">
			      	<label for="name" class="col-sm-3 col-form-label">Account Number:</label>
			      	<div class="col-sm-4">
			       		${userAccount.accountNumber}
			      	</div>
			     </div>
			     <div class="form-group row col-md-offset-3">
			      	<label for="name" class="col-sm-3 col-form-label">Balance:</label>
			      	<div class="col-sm-4">
			       		${userAccount.balance}
			      	</div>
			     </div>
			     
			     <div class="form-group row">
			      	
			      	<div class="col-sm-12">
			       		<table class="table">
						  <thead>
						    <tr>
						      <th scope="col">Id</th>
						      <th scope="col">Date</th>
						      <th scope="col">Transaction Type</th>
						      <th scope="col">Amount</th>
						      <th scope="col">Remarks</th>
						    </tr>
						  </thead>
						  <tbody>
						  	<c:forEach items="${userAccount.transaction}" var="transaction">
						      <th scope="row"><c:out value="${transaction.id}"/></th>
						      <td><c:out value="${transaction.transactionDate}"/></td>
						      <td><c:out value="${transaction.transactionType}"/></td>
						      <td><c:out value="${transaction.amount}"/></td>
						      <td><c:out value="${transaction.remark}"/></td>
						    </tr>
						    </c:forEach>
						    
						  </tbody>
						</table>
			      	</div>
			     </div>
			     
			     <div class="form-group row col-md-offset-3">
			      	<label for="name" class="col-sm-3 col-form-label"></label>
			      	<div class="col-sm-4">
			       		<a href="${contextPath}/" >Go to home</a>
			      	</div>
		  		</div>
			     
			  </div>
		       
		    </c:when>
		    <c:otherwise>
		        <div class="form-group row col-md-offset-3">
			      	<label for="name" class="col-sm-8 col-form-label">Account information not found. <a href="${contextPath}/" >Go to home</a></label>
			  	</div>
		    </c:otherwise>
	  	  </c:choose>
  	  </div>
  	  
  	  
  	
	<jsp:include page="../common/_external-script.jsp"></jsp:include>
</body>
</html>