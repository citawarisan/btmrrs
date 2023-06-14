<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="r" class="com.citawarisan.model.Reservation" scope="request"/>
<jsp:useBean id="c" class="com.citawarisan.util.ModelChronos" scope="request"/>

<html>
<head>
    <title>Edit Reservation</title>
    <link href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css" rel="stylesheet">
</head>
<body>

<nav class="container-fluid">
    <h2><a href="/home">Back to Dashboard</a></h2>
</nav>

<main class="container">
    ${error}
    <h1>Edit Reservation</h1>
    <form method="POST">
        <input type="hidden" name="id" value="${r.id}"/>
        <input type="hidden" name="user" value="${r.user}"/>
        <label>
            Room:
            <select name="room">
                <c:forEach items="${c.daoGetRooms()}" var="room">
                    <option value="${room.roomId}" ${r.room == room.roomId ? 'selected' : ''}>${room.roomName}</option>
                </c:forEach>
            </select>
        </label>
        <label>
            PAX:
            <input type="number" name="seats" value="${r.seats}" required/>
        </label>
        <label>
            Start Datetime:
            <input type="datetime-local" name="start_datetime" value="${r.startDateTime.toString()}" required/>
        </label>
        <label>
            End Datetime:
            <input type="datetime-local" name="end_datetime" value="${r.endDateTime.toString()}" required/>
        </label>
        <label>
            Course:
            <select name="course">
                <c:forEach items="${c.daoGetCourses()}" var="course">
                    <option value="${course.courseCode}" ${r.details == course.courseCode ? 'selected' : ''}>${course.courseName}</option>
                </c:forEach>
            </select>
        </label>
        <input type="submit"/>
    </form>
</main>

<header>
    <%@include file="/comp/footer.jsp" %>
</header>

</body>
</html>
