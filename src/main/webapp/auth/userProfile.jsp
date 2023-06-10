<%-- 
    Document   : userProfile
    Created on : 10 Jun 2023, 11:13:52 AM
    Author     : azimm
--%>
<%@page import="com.citawarisan.model.User"%>
<%@page import="com.citawarisan.dao.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<%@page import="java.sql.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile Page</title>
    </head>
    <body>

        <jsp:include page=""/>

        <%
            User user = (User) request.getSession().getAttribute("user");
        %>

    <body>
        <div class="container">
            <div class="header">
                <h1>User Profile</h1>
            </div>
            <form action="UserController" method="POST">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" name="username" value="<%=user.getUsername()%>">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" name="email" value="<%=user.getEmail()%>">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" name="password" value="">
                </div>
                <input type="hidden" name="id" value="<%=user.getUsername()%>">
                <button type="submit">Save</button>
            </form>

        </div>
    </div>
</body>
</html>
