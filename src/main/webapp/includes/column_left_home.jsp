<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside id="sidebarA">
<ul>
	<li><a href = "<c:url value='/index.jsp'/>">Home page</a></li>	
	<li><a href="<c:url value='/form.jsp'/>">Insert new employee</a></li>	
	<li><a href= "<c:url value='/search_by_employee_id.jsp'/>">Search by employee id</a></li>	
	<li><a href = "<c:url value='/search_by_language.jsp'/>">Search by language</a></li>
	<li><a href="showemployees?page=1">Show all employees</a></li>
</ul>
</aside>