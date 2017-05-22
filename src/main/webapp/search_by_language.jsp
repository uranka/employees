<html>
<head>
	<meta charset ="utf-8">
	<title>Search employees</title>
	<link rel="stylesheet" href="css/main.css" type="text/css" />
	<link rel="stylesheet" href="css/stylesheet.css" type="text/css" />
</head>
<body>

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />

<section>
	<h2>Search by language</h2>
	<form method="POST" action="search">
	Language: <input type="text" name="language"><br/>
	<input type="submit" value="submit">
	</form>
</section>

<jsp:include page="/includes/column_right_home.jsp" />
<jsp:include page="/includes/footer.jsp" />

</body></html>