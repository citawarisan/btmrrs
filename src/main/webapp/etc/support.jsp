<%-- 
    Document   : help
    Created on : 12 Jun 2023, 9:22:53 PM
    Author     : azimm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Help</title>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <style>
        * {
            box-sizing: border-box;
            padding: 0;
            margin: 0;
        }

        body {
            /*                font-family: Arial, Helvetica, sans-serif;*/
            overflow-x: hidden;
            background-image: url("https://wallpaperaccess.com/full/4685960.jpg");
        }

        .title {
            color: grey;
            font-size: 18px;
        }

        .button {
            border: none;
            outline: 0;
            display: inline-block;
            padding: 8px;
            color: white;
            background-color: #000;
            text-align: center;
            cursor: pointer;
            width: 100%;
            font-size: 18px;
        }

        a {
            text-decoration: none;
            font-size: 22px;
            color: black;
        }

        button:hover,
        a:hover {
            opacity: 0.7;
        }

        .info {
            text-align: justify;
            letter-spacing: 0.2rem;
            font-size: 1.2rem;
            text-align: center;
            background-color: rgba(255, 255, 255, 0.779);
            margin: 5rem;
            padding: 5rem;
            border-radius: 30px;
        }

        .info p {
            text-transform: uppercase;
            padding-left: 2rem;
            text-align: justify;
            text-align: center;
            margin-top: 2rem;
        }

        .map {
            text-align: justify;
            letter-spacing: 0.2rem;
            font-size: 22px;
            text-align: center;
            background-color: rgba(255, 255, 255, 0.779);
            margin: 5rem;
            padding: 5rem;
            border-radius: 30px;
            line-height: 50px;
        }
    </style>
</head>
<body>
<%@include file="../comp/nav.jsp" %>
<div class="container">
    <div class="info">
        <a>HELP</a>
        <p>Email : ftkki@umt.edu.my</p>
        <p>Telephone : +609-6683320/+609-6683374</p>
        <p>Fax : +609-6684660</p>
    </div>
    <div class="map">
        <p>GOOGLE MAPS</p>
        <iframe
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d4458.481383883736!2d103.0871738007031!3d5.408474325575257!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31b7bca5e7ca5707%3A0x461b1036a195327d!2sUMT!5e0!3m2!1sen!2smy!4v1686577431396!5m2!1sen!2smy"
                width="480" height="480" style="border:10px;" allowfullscreen="" loading="lazy"
                referrerpolicy="no-referrer-when-downgrade"></iframe>
    </div>
</div>
<%@include file="../comp/footer.jsp" %>
</body>
</html>
