<%-- 
    Document   : about-us
    Created on : Jun 12, 2023, 3:39:23 AM
    Author     : Omar Alomory(S63955)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About us</title>
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


            .card {
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
                width: 300px;
                margin: auto;
                text-align: center;
                font-family: arial;
                height: min-content;
                padding: 1rem;
                border-radius: 20px;
                background-image: linear-gradient(to bottom,  #ECF5EE ,  rgba(255, 255, 255, 0.779));
                margin-bottom: 2rem;
            }
            .card img{
                object-fit:contain;
                width: 100%;
                height: 200px;

            }
            .card h1 {
                font-size: 20px;
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

            button:hover, a:hover {
                opacity: 0.7;
            }
            .info{
                text-align: justify;
                letter-spacing: 0.2rem;
                font-size: 1.2rem;
                text-align: center;
                background-color: rgba(255, 255, 255, 0.779);
                margin: 5rem;
                padding: 5rem;
                border-radius: 30px;

            }
            .info hr{
                overflow-x: hidden;
                max-width: 30rem;
                border: none;
                height: 1px;
                background-image: linear-gradient(to right,  #002d72,  rgba(2, 104, 246, 0.487));
            }
            .info p{
                text-transform: uppercase;
                padding-left: 2rem ;
                text-align: justify;
                margin-top: 2rem;
            }
            .profile{

                display: flex;
                flex-direction: row;
                flex-wrap: nowrap;
                justify-content: space-evenly;
                background-color: rgba(255, 255, 255, 0.779);
                margin: 5rem;
                padding: 5rem;
                border-radius: 30px;
                flex-wrap: wrap;
            }
            .head-of-profile{
                margin-top: 2rem;
            }
            



        </style>
    </head>
    <body>
        <%@include file="comp/nav.jsp" %>
        <div class="container">
            <div class="info">
                <div
                    style="
                    text-align: left;
                    text-transform: uppercase;
                    font-weight: bolder;
                    "
                    >
                    This is team "CITAWARISAN":
                </div>
                <hr />
                <p>
                    We are student of University Malaysia Terengganu. Bachelor of Computer
                    Science(Mobile Computing) with Honours. We are the lovely Student of
                    <b><i>Dr.&nbsp;DR. FAIZAH BINTI APLOP</i></b
                    >.
                </p>
            </div>
            <div class="profile">
                <div class="card-container">
                    <h2 class="head-of-profile" style="text-align: center">Profile Card</h2>

                    <div class="card">
                        <img src="images/Omar.png" alt="Omar" style="width: 100%" />
                        <h1>OMAR ALOMORY</h1>
                        <p class="title">Programmer</p>
                        <p>UMT</p>
                        <div style="margin: 24px 0">
                            <a href="https://github.com/Alomory" target="_blank"><i class="fa fa-dribbble"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-linkedin"></i></a>
                            <a href="#"><i class="fa fa-facebook"></i></a>
                        </div>
                        <p><a class="button" href="tel:0182850579">Contact</a></p>
                    </div>
                </div>
                <div class="card-container">
                    <h2 class="head-of-profile" style="text-align: center">Profile Card</h2>

                    <div class="card">
                        <img src="images/Azim.jpg" alt="Hazim" style="width: 100%" />
                        <h1>ABDULAZIM</h1>
                        <p class="title">Designer</p>
                        <p>UMT</p>
                        <div style="margin: 24px 0">
                            <a href="#"><i class="fa fa-dribbble"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-linkedin"></i></a>
                            <a href="#"><i class="fa fa-facebook"></i></a>
                        </div>
                        <p><a class="button" href="tel:+601125444953">Contact</a></p>
                    </div>
                </div>
                <div class="card-container">
                    <h2 class="head-of-profile"  style="text-align: center">Profile Card</h2>

                    <div class="card">
                        <img
                            src="images/Gary.jpg"
                            alt="Arun"
                            style="width: 100%"
                            />
                        <h1>GARY LIM</h1>
                        <p class="title">Non-Working Staff</p>
                        <p>UMT</p>
                        <div style="margin: 24px 0">
                            <a href="#"><i class="fa fa-dribbble"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-linkedin"></i></a>
                            <a href="#"><i class="fa fa-facebook"></i></a>
                        </div>
                        <p><a class="button" href="tel:+60122170869">Contact</a></p>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="comp/footer.jsp" %>
    </body>
</html>
