<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="styles/main.css"/>
</head>

<body>

<%-- this will include the header with the css  --%>
<%@include file="comp/nav.jsp" %>

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
