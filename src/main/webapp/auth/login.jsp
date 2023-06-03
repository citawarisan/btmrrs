<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>BTM Login</title>
</head>
<body>

<form>
    <input type="text" name="username" value="user"/>
    <input type="password" name="password" value="password"/>
    <input type="submit" value="Login"/>
</form>

<a href="${pageContext.request.contextPath}/signup">Signup</a>

${errorMessage}

</body>
</html>
