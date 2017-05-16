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
		Degree: ${employee.degree}<br/>	
	</c:otherwise>
</c:choose>
</body>
</html>