<html>
<head>
	<meta charset ="utf-8">
	<title>Enter employee data</title>
	<link rel="stylesheet" href="css/main.css" type="text/css" />
	<link rel="stylesheet" href="css/stylesheet.css" type="text/css" />
</head>

<body>

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />

<section>
<h1>Provide your information</h1>
<form method="POST" action="employee" enctype="multipart/form-data">
<ul>
	<li>
		<label>Full Name<span class="required" >*</span></label>
		<input type="text" name="firstName"  placeholder="First" class="kratko-polje"/>&nbsp;
		<input type="text" name="lastName"  placeholder="Last" class="kratko-polje"/>		
	</li>
	<li>
		<label>Choose your sex<span class="required" >*</span></label>
		<input type="radio" name="sex" value="f"/>Female<br/>
		<input type="radio" name="sex" value="m"/>Male<br/> 	
	</li>
	<li>
		<label>What languages do you speak?</label>
		<input type="checkbox" name="languages" value="english" />English<br/>
		<input type="checkbox" name="languages" value="german" />German<br/>
		<input type="checkbox" name="languages" value="italian" />Italian<br/>
		<input type="checkbox" name="languages" value="russian" />Russian<br/>
		<input type="checkbox" name="languages" value="slovak" />Slovak<br/>	
	</li>
	<li>
		<label>Select your academic degree</label>
		<select name="degree">
			<option value="Bachelor">Bachelor</option>
			<option value="Master">Master</option>
			<option value="PhD">PhD</option>
		</select> 
	</li>
	<li>
		<label>Select a photo of you:</label>	
		<input type="file" name="photo" /><br/>
	</li>
	<li>		
		<input type="submit" value="submit">
	</li>
</ul>
</form>
</section>

<jsp:include page="/includes/column_right_home.jsp" />
<jsp:include page="/includes/footer.jsp" />

</body></html>
