<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Employee Data</title>	
	<link rel="stylesheet" href="css/table.css" type="text/css" />
</head>
<body>
<h1>Showing employee data from the database</h1>
<c:choose>
	<c:when test="${empty requestScope.employee}">
		<p>Employee not found in the database!</p>
	</c:when>
	<c:otherwise>	
		<table>
			<tr>
				<td class="what">First Name:</td>
				<td><c:out value="${employee.firstName}"/></td>				
				<td rowspan="3">					
					<img src = "<c:url value='/photo?id=${employee.id}'/>" height="100" />
				</td>
			</tr>
			<tr>
				<td class="what">Last Name:</td>
				<td><c:out value="${employee.lastName}"/></td>							
			</tr>
			<tr>
				<td class="what">Sex:</td>
				<td><c:out value="${employee.sex}"/></td>					
			</tr>
			<tr>
				<td class="what"> Languages:</td>
				<td><c:out value="${employee.languages[0]}"/></td>					
			</tr>
			
			<c:forEach var="language" items="${employee.languages}" begin="1">
				<tr>
					<td></td>
					<td><c:out value="${language}"/></td>					
				</tr>
			</c:forEach>
			<tr>
				<td class="what">Degree:</td>
				<td><c:out value="${employee.degree}"/></td>				
			</tr>			
		</table>		
	</c:otherwise>
</c:choose>

<p>Thank you for using our services<p>	
<img src = "<c:url value='/images/ta-logo.jpg'/>" alt="ta-logo" /> <br/>	

</body>
</html>