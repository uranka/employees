<html>
<head>
	<meta charset ="utf-8">
	<title>Choose employee</title>
	<link rel="stylesheet" href="css/main.css" type="text/css" />
	<link rel="stylesheet" href="css/stylesheet.css" type="text/css" />
</head>
<body>

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />

<section>
	<form method="POST" action="showemployee">
	Employee ID: <input type="text" name="employeeId"><br/>
	<input type="submit" value="submit">
	</form>
</section>

<jsp:include page="/includes/column_right_home.jsp" />
<jsp:include page="/includes/footer.jsp" />

</body></html>