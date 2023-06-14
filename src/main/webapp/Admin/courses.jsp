<%-- 
    Document   : courses
    Created on : Jun 15, 2023, 4:36:06 AM
    Author     : Omar Alomory(S63955)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Courses(Admin)</title>
    </head>
    <body>
        <%@include file="/comp/nav.jsp" %>
        <div class="row">
            <div class="container">
                <h3 class="text-center">List of Courses</h3>
                <hr>
                <div class="container text-letf">
                    <a href="EmployeeServlet?action=new" class="btn btn-success">Add new Course</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Course Code</th>
                            <th>Name</th>
                            <th>Group No</th>
                            <th>Number of Student</th>
                            <th>Exam Hours</th>
                            <th>Action</th>

                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="c" items="${listCourses}">
                        <tr>
                            <td>
                        <c:out value="${c.courseCode}" />
                        </td>
                        <td>
                        <c:out value="${c.courseName}" />
                        </td>
                        <td>
                        <c:out value="${c.groupNumber}" />
                        </td>
                        <td>
                        <c:out value="${c.numberOfStudent}" />
                        </td>
                        <td>
                        <c:out value="${c.examHours}" />
                        </td>
                        <td>
                            <a href="EmployeeServlet?action=edit&id=<c:out value='${c.course}'/>">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="EmployeeServlet?action=delete&id=<c:out value='${c.course}'/>">Delete</a>
                        </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="/comp/footer.jsp"%>
    </body>
</html>
