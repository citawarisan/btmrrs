<%@ page import="com.citawarisan.util.ModelChronos" %>
<%@ page import="com.citawarisan.model.Course" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%--handle request logic here--%>
<%
    User u = (User) session.getAttribute("user");
    ModelChronos c = new ModelChronos(u.getUsername());
    session.setAttribute("c", c);
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Courses(Admin)</title>
</head>
<body>
<%@include file="/comp/nav.jsp" %>
<c:choose>
    <c:when test="${param.action == 'new'}">
        <form>
            <input type="hidden" name="action" value="create">
            <label>
                Course Code:
                <p><input type="text" name="coursecode"/></p>
            </label>
            <label>
                Course Name:
                <p><input type="text" name="coursename"/></p>
            </label>
            <label>
                Group No:
                <p><input type="number" name="groupno"/></p>
            </label>
            <label>
                Number of Student:
                <p><input type="number" name="numberofstudent"/></p>
            </label>
            <label>
                Exam Hours:
                <p><input type="number" name="examhours"/></p>
            </label>
            <input type="submit" value="Add"/>
        </form>
    </c:when>
    <c:when test="${param.action == 'create'}">
        <%
            String courseCode = request.getParameter("coursecode");
            String courseName = request.getParameter("coursename");
            int groupNo = Integer.parseInt(request.getParameter("groupno"));
            int numberOfStudent = Integer.parseInt(request.getParameter("numberofstudent"));
            int examHours = Integer.parseInt(request.getParameter("examhours"));
            c.daoAddCourse(courseCode, courseName, groupNo, numberOfStudent, examHours);
            System.out.println("Course Added");
            response.sendRedirect("courses.jsp");
        %>
    </c:when>
    <c:when test="${param.action == 'edit'}">
        <%
            String courseCode = request.getParameter("id");
            Course course = c.daoGetCourse(courseCode);
        %>
        <form>
            <input type="hidden" name="action" value="update">
            <label>
                Course Code:
                <p><input type="text" name="coursecode" value="<%=course.getCourseCode()%>"/></p>
            </label>
            <label>
                Course Name:
                <p><input type="text" name="coursename" value="<%=course.getCourseName()%>"/></p>
            </label>
            <label>
                Group No:
                <p><input type="number" name="groupno" value="<%=course.getGroupNumber()%>"/></p>
            </label>
            <label>
                Number of Student:
                <p><input type="number" name="numberofstudent" value="<%=course.getNumberOfStudents()%>"/></p>
            </label>
            <label>
                Exam Hours:
                <p><input type="number" name="examhours" value="<%=course.getExamHours()%>"/></p>
            </label>
            <input type="submit" value="Update"/>
        </form>
    </c:when>
    <c:when test="${param.action == 'update'}">
        <%
            String courseCode = request.getParameter("coursecode");
            String courseName = request.getParameter("coursename");
            int groupNo = Integer.parseInt(request.getParameter("groupno"));
            int numberOfStudent = Integer.parseInt(request.getParameter("numberofstudent"));
            int examHours = Integer.parseInt(request.getParameter("examhours"));
            c.daoUpdateCourse(courseCode, courseName, groupNo, numberOfStudent, examHours);
            System.out.println("Course Updated");
            response.sendRedirect("courses.jsp");
        %>
    </c:when>
    <c:when test="${param.action == 'delete'}">
        <%
            String courseCode = request.getParameter("id");
            c.daoDeleteCourse(courseCode);
            System.out.println("Course Deleted");
            response.sendRedirect("courses.jsp");
        %>
    </c:when>
    <c:otherwise>
        <div class="row">
            <div class="container">
                <h3 class="text-center">List of Courses</h3>
                <hr>
                <div class="container text-left">
                    <a href="?action=new" class="btn btn-success">Add new Course</a>
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
                    <c:forEach var="m" items="${c.daoGetCourses()}">
                        <tr>
                            <td>
                                <c:out value="${m.courseCode}"/>
                            </td>
                            <td>
                                <c:out value="${m.courseName}"/>
                            </td>
                            <td>
                                <c:out value="${m.groupNumber}"/>
                            </td>
                            <td>
                                <c:out value="${m.numberOfStudents}"/>
                            </td>
                            <td>
                                <c:out value="${m.examHours}"/>
                            </td>
                            <td>
                                <a href="?action=edit&id=<c:out value='${m.courseCode}'/>">Edit</a>
                                <a href="?action=delete&id=<c:out value='${m.courseCode}'/>">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<%@include file="/comp/footer.jsp" %>
</body>
</html>
