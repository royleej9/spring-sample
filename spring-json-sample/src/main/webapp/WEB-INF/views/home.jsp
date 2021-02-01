<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
</head>
<body>
	<div>
		<label>Id: </label><input id="id" type="text" />
		<label>password:</label><input id="pwd" type="text" />
	</div>
	<div>
		<button id="getBtn">Get</button>
		<button id="postStrBtn">post-str</button>
		<button id="postMapBtn">post-map</button>
		<button id="postVOBtn">post-VO</button>
		
	</div>
	<div>
		<label>Return: </label><label id="resultLbl"></label>
	</div>s

	<script src="<c:url value="/js/libs/jquery.js" />"></script>
	<script src="<c:url value="/js/main.js" />"></script>
</body>
</html>
