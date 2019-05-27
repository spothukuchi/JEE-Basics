<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action="RegistrationServlet" method="post">
		<fieldset>
			<legend>User Login Form</legend>
			<label id="userlbl" for="userid">User ID</label> 
			<input type="text" name="userid" value="" /><br /> 
			<label id="passwordlbl"	for="password">Password</label> 
			<input type="password" name="password" value="" /><br /> 
			<label id="passwordlbl2"	for="password2">Confirm Password</label> 
			<input type="password" name="password2" value="" /><br /> 			
			<input type="submit" name="register" value="Register" /> 
			<input type="button" name="cancel" value="Cancel" />
		</fieldset>
	</form>
</body>
</html>