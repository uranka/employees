<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Employees</title>	
	<link rel="stylesheet" href="css/table.css" type="text/css" />
</head>
<body>
<h1>Showing employees from the database</h1>
TEST: pageContext.request.contextPath = ${pageContext.request.contextPath}<br/>

Number of pages: ${numberOfPages}<br/>

<br/> <br/>  
<c:forEach var = "employee" items = "${employees}" >
	First Name: <c:out value="${employee.firstName}"/><br/>
	Last Name: <c:out value="${employee.lastName}"/><br/>
	Sex: <c:out value="${employee.sex}"/><br/>
	Degree: <c:out value="${employee.degree}"/><br/>
	<img src="http://localhost:8080/employees/photo?id=${employee.id}" height="100" />
	<c:forEach var="language" items="${employee.languages}" >
		<c:out value="${language}"/>,				
	</c:forEach>
	<br/><br/>	
</c:forEach>

<c:forEach var = "i" begin = "1" end = "${numberOfPages}">
	<a href="showemployees?page=${i}">Show ${i}. page</a>         
</c:forEach>

</body>
</html>