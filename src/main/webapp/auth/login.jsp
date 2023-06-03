<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>BTM Login</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
</head>
<body class="container">

<h1>BTM Login</h1>

<form>
    <input type="text" name="username" value="user" required/>
    <input type="password" name="password" value="password" required/>
    <input type="submit" value="Login"/>
</form>

<a href="${pageContext.request.contextPath}/signup">Create account</a>

${errorMessage}

</body>
</html>
