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

        <jsp:include page="../comp/nav.jsp"/>

       

    <body>
        <div class="container">
            <div class="header">
                <h1>User Profile</h1>
            </div>
            <form action="UserController" method="POST">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" name="username" value="${user.username}" readonly>
                </div>
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" name="name" value="${user.name}">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" name="email" value="${user.email}">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="text" class="form-control" name="password" value="${user.password}">
                </div>
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="number" class="form-control" name="phone" value="${user.phone}">
                </div>
                <input type="hidden" name="id" value="${user.type}">
                <input type="hidden" name="action" value="update">
                <button type="submit">Save</button>
            </form>

        </div>
   <jsp:include page="../comp/footer.jsp"/>
</body>
</html>
