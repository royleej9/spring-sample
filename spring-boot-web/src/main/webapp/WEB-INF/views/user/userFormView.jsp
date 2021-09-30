<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
</head>
<body>

	<form action="/userForm/insertUser" method="post">
		<p>
			Id: <input type="text" id="id" name="id" />
		</p>
		<p>
			Name: <input type="text" id="name" name="name" />
		</p>
		<p>
			password: <input type="text" id="password" name="password" />
		</p>
		<p>
			<input type="submit" value="Submit" /> <input type="reset"
				value="Reset" />
		</p>
	</form>

	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Created Date</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.createdDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
