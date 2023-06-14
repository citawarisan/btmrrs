<%@ page contentType="text/html" pageEncoding="UTF-8" import="java.time.LocalDateTime" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="com.citawarisan.model.User" scope="session"/>
<jsp:useBean id="rs" class="com.citawarisan.view.ReservationView" scope="session"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <style>
        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }

        .containerBody {
            width: 100%;
            max-height: max-content;
            background-image: url("https://wallpaperaccess.com/full/4685960.jpg");
            background-repeat: no-repeat;
            background-size: cover;
        }

        /*the end of the combined body for both*/
        /*starting of staff css --------------------------------------------------------*/
        .main-header {
            display: flex;
            width: 80%;
            margin: auto;

        }

        /* left styling */
        .main-side-left, .main-side-right {
            width: 30%;
            background-color: rgba(255, 255, 255, 0.779);
            padding: 20px;
            border-radius: 20px;
            margin: 10px 5px 10px 0;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
            text-align: center;
            min-height: 80vh;
        }

        .main-side-left > p, .main-side-right > p {
            font-size: 1.5rem;
            font-weight: bolder;

            margin-bottom: 1rem;
        }

        .subcode, .roomdesc {
            background-color: #e3e3e3;
            padding: 15px;
            border-radius: 20px;
            font-size: 1.2rem;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
            margin-bottom: 5px;
            font-weight: bold;
        }

        .subcode > p, .roomdesc > p {
            margin: 0;
        }

        .main-side-right {
            width: 100%;
        }

    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>

<%@include file="comp/nav.jsp" %>

<div class="containerBody">
    <div class="main-header staff">
        <div class="main-side-right">
            <p>Latest Reservations</p>
            <hr>
            <c:forEach var="r" items="${rv.getList()}">
                <div class="roomdesc">
                    <p class="facultyroom"><b>${r.room}</b></p>
                    <p class="date">Date: ${r.getDate()}</p>
                    <p class="time">Time: ${r.time}</p>
                    <p class="status">Status: ${r.status}</p>

                    <c:set var="now" value="<%= LocalDateTime.now() %>" />
                    <c:if test="${r.status != 'cancelled' && r.date > now}">
                        <a href="/cancel?id=${r.id}">Cancel</a>
                        <a href="/revise?id=${r.id}">Update</a>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<%@include file="comp/footer.jsp" %>

</body>
</html>
