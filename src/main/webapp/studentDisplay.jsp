<%-- 
    Document   : studentDisplay
    Created on : Jun 13, 2023, 5:39:14 PM
    Author     : Omar Alomory(S63955)
--%>
<%@ page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@page import="com.citawarisan.model.CourseInformation, java.util.List" %>
<html>
    <head>
        <title>Course Information</title>

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

            .table-container {
                text-align: center;
                letter-spacing: 0.2rem;
                font-size: 1.2rem;
                text-align: center;
                background-color: rgba(255, 255, 255, 0.779);
                margin: 5rem;
                padding: 5rem;
                border-radius: 30px;
                border:1px solid black;
            }

            .table-title{
                text-align: center;
                padding-bottom: 50px;
            }

        </style>
    </head>
    <body>
        <%@include file="comp/nav.jsp" %>

        <div class="table-container">
            <div class="table-title"><h1>Course Information</h1></div>
            <div class="table-data">
                <table border="2" style="width: 100%">
                    <tr>
                        <th>Course Code</th>
                        <th>Course Name</th>

                        <th>Number of Students</th>
                        <th>Date</th>
                        <th>Day</th>
                        <th>Number of Hours</th>
                        <th>Start Time</th>
                        <th>End Time</th>
                        <th>Faculty</th>
                        <th>Room</th>
                    </tr>
                    <% for (CourseInformation info : (List<CourseInformation>) request.getAttribute("studentInfo")) {%>
                    <tr> 

                        <td><%= info.getCourseCode()%></td>
                        <td><%= info.getCourseName()%></td>
                        <td><%= info.getNumberOfStudents()%></td>
                        <td><%= info.getDate()%></td>
                        <td><%= info.getDay()%></td>
                        <td><%= info.getNumberOfHours()%></td>
                        <td><%= info.getStartTime()%></td>
                        <td><%= info.getEndTime()%></td>
                        <td><%= info.getFaculty()%></td>
                        <td><%= info.getRoom()%></td>
                    </tr>
                    <% }%>
                </table>
            </div>
        </div>

        <%@include file="comp/footer.jsp" %>
    </body>
</html>

