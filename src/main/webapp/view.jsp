<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Employee Data</title>
</head>
<body>
First Name: ${employee.firstName}<br/>
Last Name: ${employee.lastName}<br/>
Sex: ${employee.sex}<br/>
Languages:<br/>
<c:forEach var="language" items="${employee.languages}">
	${language}<br/>
</c:forEach>
</body>
</html>