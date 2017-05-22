<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Employees</title>	
	<link rel="stylesheet" href="css/main.css" type="text/css" />
	<link rel="stylesheet" href="css/table.css" type="text/css" />
</head>
<body>

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />

<section>
<h1>Showing employees from the database</h1>

<c:forEach var = "employee" items = "${employees}" >
	First Name: <c:out value="${employee.firstName}"/><br/>
	Last Name: <c:out value="${employee.lastName}"/><br/>
	Sex: <c:out value="${employee.sex}"/><br/>
	Degree: <c:out value="${employee.degree}"/><br/>
	
	<img src = "<c:url value='/photo?id=${employee.id}'/>" height="100" />
	<c:forEach var="language" items="${employee.languages}" >
		<c:out value="${language}"/>,				
	</c:forEach>
	<br/><br/>	
</c:forEach>

<c:forEach var = "i" begin = "1" end = "${numberOfPages}">
	<a href ="<c:url value="/showemployees?page=${i}" />">Show ${i}. page</a>  	
</c:forEach>
</section>

<jsp:include page="/includes/column_right_home.jsp" />
<jsp:include page="/includes/footer.jsp" />

</body>
</html>