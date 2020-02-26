<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
	<body>
		<h1 id="banner">Phone Book Login Page</h1>
		<form name="f" action="<c:url value='/j_spring_security_check'/>"
					method="POST">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

			<table>
				<tr>
					<td>Username:</td>
					<td><input type='text' name='j_username' /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='j_password'></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit">&nbsp;<input name="reset" type="reset"></td>
				</tr>
				<tr>
                		<td></td>
                		<td>Remember Me: <input type="checkbox" name="remember-me" /></td>
                </tr>
			</table>
		</form>

	</body>
</html>