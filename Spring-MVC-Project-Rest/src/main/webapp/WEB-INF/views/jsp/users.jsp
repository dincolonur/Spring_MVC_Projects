<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Users Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Edit Users - Phone Company
</h1>

<c:url var="addAction" value="/users/add" ></c:url>

<form:form action="${addAction}" commandName="user">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<table>
	<tr>
		<td>
			<form:label path="userId">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="userId" size="8" />

		</td>
	</tr>
	<tr>
		<td>
			<form:label path="username">
				<spring:message text="User Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="username" />
		</td>
	</tr>
	<tr>
        <td>
            <form:label path="firstName">
                <spring:message text="User First     Name"/>
            </form:label>
        </td>
        <td>
            <form:input path="firstName" />

        </td>
    </tr>
    <tr>
        <td>
            <form:label path="lastName">
                <spring:message text="User Last Name"/>
            </form:label>
        </td>
        <td>
            <form:input path="lastName" />

        </td>
    </tr>
    <tr>
        <td>
            <form:label path="userAddress">
                <spring:message text="User Address"/>
            </form:label>
        </td>
        <td>
            <form:input path="userAddress" />

        </td>
    </tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty user.userId}">
				<input type="submit"
					value="<spring:message text="Edit User"/>" />
			</c:if>
			<c:if test="${empty user.userId}">
                <input type="submit"
                    value="<spring:message text="Add User"/>" />
            </c:if>
		</td>
	</tr>
</table>
</form:form>
<br>
<h3>User Accounts List</h3>
<c:if test="${!empty listUsers}">
	<table class="tg">
	<tr>
		<th width="80">User ID</th>
		<th width="120">User Name</th>
		<th width="120">User First Name</th>
		<th width="120">User Last Name</th>
		<th width="120">User Address</th>
		<th width="60">Edit</th>
	</tr>
	<c:forEach items="${listUsers}" var="user">
		<tr>
			<td>${user.userId}</td>
			<td>${user.username}</td>
			<td>${user.firstName}</td>
			<td>${user.lastName}</td>
			<td>${user.userAddress}</td>
			<td><a href="<c:url value='/users/edit/${user.userId}' />" >Edit</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
