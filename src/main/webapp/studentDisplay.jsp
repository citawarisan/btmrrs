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
</head>
<body>
    <h1>Course Information</h1>
    
    <table border="2">
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
         <% for (CourseInformation info : (List<CourseInformation>) request.getAttribute("studentInfo")) { %>
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
         <% } %>
    </table>
</body>
</html>

