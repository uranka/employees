<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Employee Data</title>
</head>
<body>
First Name: <c:out value="${employee.firstName}"/><br/>
Last Name: <c:out value="${employee.lastName}"/><br/>
Sex: <c:out value="${employee.sex}"/><br/>
Languages:<br/>
<c:forEach var="language" items="${employee.languages}">
	<c:out value="${language}"/><br/>
</c:forEach>
Degree: <c:out value="${employee.degree}"/>

<p>List of all employees in the map:</p>
<c:forEach var="employee" items="${listEmployees}">
	<c:out value="${employee.id}"/>. - 
	<c:out value="${employee.firstName}"/> <c:out value="${employee.lastName}"/>,
	<c:out value="${employee.sex}"/><br/>	 
	Languages:<br/>
	<c:forEach var="language" items="${employee.languages}">
		<c:out value="${language}"/><br/>
	</c:forEach>
	<c:out value="${employee.degree}"/><br/>
</c:forEach>
</body>
</html>