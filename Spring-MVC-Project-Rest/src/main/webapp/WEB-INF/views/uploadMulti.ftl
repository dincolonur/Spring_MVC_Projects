<html>

<body>
<h1>Phone Book Multi files upload </h1>

<form method="POST"  enctype="multipart/form-data">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <label for="file1">User File: </label><br/><input type="file" id="file1" name="files" /><br/><br/>
    <label for="file2">Phone File: </label><br/><input type="file" id="file2" name="files" /><br/><br/>
    <label for="file3">Company File: </label><br/><input type="file" id="file3" name="files" /><br/><br/>
    <input type="submit" value="Submit" />
</form>


</body>
</html>