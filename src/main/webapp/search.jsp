<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Employees by language</title>	
	<link rel="stylesheet" href="css/table.css" type="text/css" />
</head>
<body>
<h1>Showing employees who translate ${language} language</h1>

<c:forEach var = "employee" items = "${employees}" >
	${employee.firstName} ${employee.lastName}<br/>		
	<!-- stavi linkove ka detaljnijem opisu zaposlenog-->
</c:forEach>

</body>
</html>