<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Employees by language</title>	
	<link rel="stylesheet" href="css/main.css" type="text/css" />
	<link rel="stylesheet" href="css/table.css" type="text/css" />
</head>
<body>

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />

<section>
<h1>Showing employees who translate ${language} language</h1>

<c:forEach var = "employee" items = "${employees}" >		
	<a href = "<c:url value='/employee?id=${employee.id}'/>" >
		<c:out value="${employee.firstName}"/> <c:out value="${employee.lastName}"/>
	</a>
	<br/>
</c:forEach>
</section>

<jsp:include page="/includes/column_right_home.jsp" />
<jsp:include page="/includes/footer.jsp" />

</body>
</html>