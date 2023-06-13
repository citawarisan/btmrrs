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
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />

        <style>
            * {
                box-sizing: border-box;
                padding: 0;
                margin: 0;
            }

            body {

                overflow-x: hidden;
                background-image: url("https://wallpaperaccess.com/full/4685960.jpg");
            }

            .title {
                color: grey;
                font-size: 18px;

            }

            .formname{
                text-align: center;
            }

            .container{

                background-color: rgba(255, 255, 255, 0.779);
                margin: 5rem;
                padding: 5rem;
                border-radius: 30px;

                .btn {
                    font-size: 1em;
                    height: 50px;
                    display: block;
                    width: 20%;
                    margin-top: 10px;
                    margin-left: auto;
                    margin-right: auto;
                    background: rgb(75, 15, 145);
                    color: rgb(255, 255, 255);
                    border-radius: 40px;
                    cursor: pointer;
                    transition: 0.8s;
                }

                .btn:hover {
                    transform: scale(0.96);
                }
                @media screen and (max-width: 1000px) {
                    .container {
                        height: fit-content;
                        width: 100%;

                    }
                </style>

            </head>
            <body>

                <jsp:include page="../comp/nav.jsp"/>

            <body>
                <div class="container">
                    <div class="formname"><h1>User Profile</h1></div>
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
                        <button type="submit" class="btn">Update</button>
                    </form>

                </div>
                <jsp:include page="../comp/footer.jsp"/>
            </body>
        </html>
