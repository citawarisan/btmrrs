<%-- 
    Document   : editCourse
    Created on : Jun 15, 2023, 5:46:21 AM
    Author     : Omar Alomory(S63955)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Course</title>
    <link href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css" rel="stylesheet">
</head>
<body>

<nav class="container-fluid">
    <h2><a href="/List">Back to List</a></h2><!-- can you change the list to the specific place sorry man -->
</nav>

<main class="container">
    ${error}
    <h1>Edit Course</h1>
    <form method="POST">
        <label>
            Course Code:
            <input type="text" name="courseCode" value="${courseCode}" readonly/>
        </label>
        <label>
            Course Name:
            <input type="text" name="courseName" value="${courseName}" required/>
        </label>
        <label>
            Group Number:
            <input type="number" name="groupNumber" value="${groupNumber}" required/>
        </label>
        <label>
            Number of Students:
            <input type="number" name="numberOfStudents" value="${numberOfStudents}" required/>
        </label>
        <label>
            Exam Hours(Default 2):
            <input type="number" name="examHours" value="${examHours}" required/>
        </label>
        <input type="submit"/>
    </form>
</main>

<header>
    <%@include file="/comp/footer.jsp" %>
</header>

</body>
</html>
