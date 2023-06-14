<%@page contentType="text/html" pageEncoding="UTF-8" import="com.citawarisan.model.*, java.util.List" %>

<% // no trespassing
    if (session.getAttribute("user") == null)
        response.sendRedirect("/login");
%>

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

        /* right styling */
        .main-side-right {
            width: 70%;
        }

    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>

<body>

<%-- this will include the header with the css  --%>
<%@include file="comp/nav.jsp" %>

<div class="containerBody">

    <%--retriveing user object to the dashboard--%>
    <input type="hidden" id="type" name="userType" value="${user.type}"/>

    <div class="main-header staff">
        <div class="main-side-left">
            <p>Subject</p>
            <hr>
            <div class="subjects">
                <% List<Course> courses = (List<Course>) request.getAttribute("courses"); %>

                <% for (Course course : courses) {%>
                <div class="subcode">
                    <p><%=course.getCourseCode()%>
                    </p>
                </div>
                <% }%>
            </div>

        </div>
        <div class="main-side-right">
            <p>Reserved Rooms</p>
            <hr>
            <%
                List<Faculty> f = (List<Faculty>) request.getAttribute("f");
                List<Reservation> rs = (List<Reservation>) request.getAttribute("rs");
                List<Room> r = (List<Room>) request.getAttribute("r");

                for (int i = 0; i < rs.size(); i++) {
            %>
            <div class="roomdesc">
                <%-- fix this shit, how to add packages --%>

                <p class="facultyroom"><b><%= f.get(i).getFacultyName() %>-<%= r.get(i).getRoomName() %>
                </b></p>
                <p class="date">Time: <%= rs.get(i).getStartDateTime().toLocalTime() %>
                    -<%= rs.get(i).getEndDateTime().toLocalTime() %>
                </p>
                <p class="time">Date: <%= rs.get(i).getStartDateTime().toLocalDate() %>
                </p>
                <p class="status">Status: <%= rs.get(i).getStatus() %>
                </p>
                <a href="UserController?action=deleteReserve&rsid='<%= rs.get(i).getId() %>'">Delete</a> <a
                    href="UserController?action=update&rsid='<%= rs.get(i).getId() %>'">Update</a>
            </div>
            <%}%>

        </div>
    </div>
    <div class="adminSide">
        this is the admin side
    </div>
</div>
<%@include file="comp/footer.jsp" %>
</body>
<script>
    $(document).ready(function () {
        var type = $('#type').val();
        if (type == "3") {
            $('.staff').hide();
            $('.adminSide').show();
        } else {
            $('.staff').show();
            $('.adminSide').hide();
        }
    });
</script>
</html>
