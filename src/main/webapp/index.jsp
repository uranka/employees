<html>
<head>
	<meta charset="utf-8">
	<title>Home</title>	
	<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<body>

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />

<section>
	<h1>Home page</h1>
	<p>Welcome to our page. Our employees are translators. 
	They translate from english, german, italian, russian
	and slovak. </p>
	<p>You can browse through our employees. 
	You can add new employee. You can search for a translator by language</p>
	<!--
	<a href="form.html">Insert new employee</a><br/>
	<a href="employee.html">Show data about an employee</a><br/>
	<a href="showemployees?page=1">Show data about all employees</a><br/>
	<a href="search.html">Search by criteria</a>
	-->
</section>

<jsp:include page="/includes/column_right_home.jsp" />
<jsp:include page="/includes/footer.jsp" />

</body>
</html>
