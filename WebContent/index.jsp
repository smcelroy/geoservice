<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
<title>GeoService</title>
</head>
<body>

	<h1>Geolocation</h1>
	<form action="result" method="post">
		Address: <input type="text" name="address"><br>
		Range: <input type="text" name="distance"><br>
		<input type="submit" value="Submit">
	</form> 
	
	<h1>Geolocation</h1>
    <form action="/GeoService/result" method="post">
    	<p>Address: <input type="text" th:field="*{address}" /></p>
        <p>Distance: <input type="text" th:field="*{distance}" /></p>
        <p><input type="submit" value="Submit" /></p>
        
    
    <a href="welcome.html">Click here to See Welcome Message... </a>(to
			check Spring MVC Controller... @RequestMapping("/welcome"))
</body>
</html>
</body>
</html>