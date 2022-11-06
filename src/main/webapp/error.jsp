<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="assets/css/error.css" rel="stylesheet"/>
<title>Error</title>
</head>
<body>

<div>
	<h2>Errors page</h2>
	<h3>Please contact your system administrator</h3>
	<h4><a href="mailto:mateusifg16@gmail.com">Click here</a></h4>
	<p>Details of error: ${error}</p>
	<a class="btn btn-danger" href="<%= request.getContextPath() %>">Home</a>
</div>
	

</body>
</html>