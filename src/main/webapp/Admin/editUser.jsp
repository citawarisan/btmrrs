<%-- 
    Document   : edit
    Created on : Jun 15, 2023, 5:23:27 AM
    Author     : Omar Alomory(S63955)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
           <title>Edit User</title>
    <link href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css" rel="stylesheet">
</head>
<body>

<nav class="container-fluid">
    <h2><a href="/List">Back to List</a></h2><!-- can you change the list to the specific place sorry man -->
</nav>

<main class="container">
    ${error}
    <h1>Edit User</h1>
    <form method="POST">
        <label>
            Username:
            <input type="text" name="username" value="${username}" readonly/>
        </label>
        <label>
            Password:
            <input type="password" name="password" value="${password}" required/>
        </label>
        <label>
            Name:
            <input type="text" name="name" value="${name}" required/>
        </label>
        <label>
            Phone:
            <input type="text" name="phone" value="${phone}" required/>
        </label>
        <label>
            Email:
            <input type="email" name="email" value="${email}" required/>
        </label>
        <label>
            User Type:
            <input type="number" name="type" value="${type}" required/>
        </label>
        <input type="submit"/>
    </form>
</main>

<header>
    <%@include file="/comp/footer.jsp" %>
</header>

</body>
</html>
