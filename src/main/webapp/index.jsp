<%-- 
    Document   : index.jsp
    Created on : Jun 2, 2023, 4:22:40 AM
    Author     : Omar Alomory(S63955)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>  
        <link rel="stylesheet" href="./css/Home.css"/>
    </head>
    <body>
        <%-- this will include the header with the css  --%>
        <%@include file="header.jsp" %>
        <div class="containerBody">
            <div class="main-header">
                <div class="main-side-left">
                    <p>Subject</p>
                    <hr>
                    <div class="subjects">
                        <div class="subcode">
                            <p>CSF0202</p>
                        </div>
                        <div class="subcode">
                            <p>CSF0202</p>
                        </div>
                    </div>
                </div>
                <div class="main-side-right">
                    <p>Reserved Rooms</p>
                    <hr>
                    <div class="roomdesc">
                        <%-- fix this shit, how to add packages --%>
                       
                        <p>something</p>
                    </div>
                    <div class="roomdesc">
                        <p>something</p>
                    </div>
                </div>
            </div>
        </div>

    </body>

</html>
