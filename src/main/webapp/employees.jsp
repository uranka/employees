<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Employees</title>	
	<link rel="stylesheet" href="css/table.css" type="text/css" />
</head>
<body>
<h1>Showing employees from the database</h1>
Number of pages: ${numberOfPages}<br/>

<br/> <br/>  
<c:forEach var = "employee" items = "${employees}" >
	First Name: ${employee.firstName}<br/>
	Last Name: ${employee.lastName}<br/>
	Sex: ${employee.sex}<br/>
	Degree: ${employee.degree}<br/>
	<img src="http://localhost:8080/employees/photo?id=${employee.id}" height="100" />
	<c:forEach var="language" items="${employee.languages}" >
		${language},				
	</c:forEach>
	<br/><br/>	
</c:forEach>

<c:forEach var = "i" begin = "1" end = "5">
	<a href="showemployees?page=${i}">Show ${i}. page</a>         
</c:forEach>


</body>
</html>