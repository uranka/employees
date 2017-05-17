<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Employee Data</title>
</head>
<body>
<h1>Showing employee data from the database</h1>
<c:choose>
	<c:when test="${empty requestScope.employee}">
		<p>Employee not found in the database!</p>
	</c:when>
	<c:otherwise>
		First Name: ${employee.firstName}<br/>
		Last Name: ${employee.lastName}<br/>
		Sex: ${employee.sex}<br/>
		Languages:<br/>
		<c:forEach var='language' items="${employee.languages}">
			${language}<br/>
		</c:forEach>			
		Degree: ${employee.degree}<br/>	
		Picture from folder images (not from the database)<img src="images/e2.jpg" alt="esign" /><br/>
		<img src="http://localhost:8080/employees/photo?id=${employee.id}" />		
		<!-- neke promenljive stavi ovde umesto localhost employees
		pageContext i ta cudesa-->
	</c:otherwise>
</c:choose>
</body>
</html>