<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>User Account Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Edit User Account - Phone Company
</h1>

<c:url var="addAction" value="/userAccount/add" ></c:url>

<form:form action="${addAction}" commandName="userAccount">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<table>
	<tr>
		<td>
			<form:label path="accountId">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="accountId" readonly="true" size="8"  disabled="true" />
			<form:hidden path="accountId" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="accountName">
				<spring:message text="Account Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="accountName" readonly="true" disabled="true"  />
			<form:hidden path="accountName" />
		</td>
	</tr>
	<tr>
        <td>
            <form:label path="userName">
                <spring:message text="User Name"/>
            </form:label>
        </td>
        <td>
            <form:input path="userName" readonly="true" disabled="true" />
            <form:hidden path="userName" />
        </td>
    </tr>
    <tr>
        <td>
            <form:label path="amount">
                <spring:message text="Account Balance"/>
            </form:label>
        </td>
        <td>
            <form:input path="amount"  readonly="true" disabled="true" />
            <form:hidden path="amount" />
        </td>
    </tr>
	<tr>
		<td>
			<form:label path="phoneCompanyId">
				<b><spring:message text="Phone Company ID"/></b>
			</form:label>
		</td>
		<td>
			<b><form:input path="phoneCompanyId" /></b>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty userAccount.accountName}">
				<input type="submit"
					value="<spring:message text="Edit User Account"/>" />
			</c:if>
		</td>
	</tr>
</table>
</form:form>
<br>
<h3>User Accounts List</h3>
<c:if test="${!empty listUserAccounts}">
	<table class="tg">
	<tr>
		<th width="80">User Account ID</th>
		<th width="120">User Name</th>
		<th width="120">Phone Company ID</th>
		<th width="120">Account Balance ID</th>
		<th width="60">Edit</th>
	</tr>
	<c:forEach items="${listUserAccounts}" var="userAccount">
		<tr>
			<td>${userAccount.accountId}</td>
			<td>${userAccount.userName}</td>
			<td>${userAccount.phoneCompanyId}</td>
			<td>${userAccount.amount}</td>
			<td><a href="<c:url value='/edit/${userAccount.accountId}' />" >Edit</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
