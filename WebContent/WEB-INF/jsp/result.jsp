<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
<title>Result</title>
<style type="text/css">
</style>
</head>
<body>
	<h1>Result</h1>
    <p th:text="'Address: ' + ${greeting.address}" />
    <p th:text="'Distance: ' + ${greeting.distance}" />
</body>
</html>