<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>BTM Signup</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
</head>
<body class="container">

<h1>BTM Signup</h1>

<form>
    <input type="text" name="username" value="user" required/>
    <input type="password" name="password" value="password" required/>
    <input type="password" name="confirmPassword" value="password" required/>
    <input type="text" name="name" value="someone" required/>
    <input type="text" name="phone" value="0123456789" required/>
    <input type="email" name="email" value="someone@somewhere.com" required/>
    <input type="submit" value="Login"/>
</form>

<a href="${pageContext.request.contextPath}/login">Login instead</a>

${errorMessage}

</body>
</html>
