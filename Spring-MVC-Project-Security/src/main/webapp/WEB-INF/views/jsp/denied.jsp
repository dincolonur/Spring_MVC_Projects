<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
	
		<h1 id="banner">Unauthorized Access !!</h1>
	
		<hr />
	
		<c:if test="${not empty error}">
			<div style="color:red">
				Login is not successful, Please Check Username and Password <br />
			</div>
		</c:if>
	
		<p class="message">Access denied!</p>
		<a href="/phonebook-mvc/login">Go back to login page</a>
	</body>
</html>