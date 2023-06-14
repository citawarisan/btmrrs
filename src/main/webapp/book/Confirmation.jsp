<%-- 
    Document   : Confirmation
    Created on : Jun 15, 2023, 2:24:39 AM
    Author     : Omar Alomory(S63955)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservation Confirmation</title>
        <style>
            *{
                padding: 0;
                margin: 0;
                box-sizing: border-box;
            }
            body{
                background: url(https://mcdn.wallpapersafari.com/medium/31/28/9duO7g.jpg);
                background-repeat: no-repeat;
                background-size: cover;
            }
            .confrimBody{

                width: 100%;
            }
            .confirmContent{
                width: 80%;
                height: fit-content;
                background-color: rgba(255, 255, 255, 0.779);
                margin: auto;
                margin-top: 50px;
            }

            input[type=text], select, textarea {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 4px;
                resize: vertical;
            }

            label {
                padding: 12px 12px 12px 0;
                display: inline-block;
            }

            input[type=submit] {
                background-color: #04AA6D;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                float: right;
            }

            input[type=submit]:hover {
                background-color: #45a049;
            }

            .container {
                border-radius: 5px;
                background-color: #f2f2f2;
                padding: 20px;
            }

            .col-25 {
                float: left;
                width: 25%;
                margin-top: 6px;
            }

            .col-75 {
                float: left;
                width: 75%;
                margin-top: 6px;
            }

            /* Clear floats after the columns */
            .row:after {
                content: "";
                display: table;
                clear: both;
            }
            .btn {
                font-size: 1em;
                height: 50px;
                width: 10%;

                margin-top: 60px;
                margin-bottom: 20px;
                background: rgb(75, 15, 145);
                color: rgb(255, 255, 255);
                border-radius: 40px;
                cursor: pointer;
                transition: 0.8s;
            }
            .btn:hover {
                transform: scale(0.96);
            }
            /* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
            @media screen and (max-width: 600px) {
                .col-25, .col-75, input[type=submit] {
                    width: 100%;
                    margin-top: 0;
                }
            }
        </style>
    </head>
    <body>

        <div class="confirmBody">
            <div class="confirmContent">
                <div class="container">

                    <div class="row">
                        <div class="col-25">
                            <label for="course">Your Course: </label>
                        </div>
                        <div class="col-75">
                            <input type="text" name="course" value="${course}" readonly">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="room">Venue (Building): </label>
                        </div>
                        <div class="col-75">
                            <input type="text" id="room" name="room" readonly placeholder="Your room is reserved in ${course}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="seats">No of Seats: </label>
                        </div>
                        <div class="col-75">
                            <input type="text" id="seat" name="seat" readonly placeholder="Number of Seats: ${seat}">
                        </div>
                    </div>
                        <div class="row">
                        <div class="col-25">
                            <label for="seats">Date & Time: </label>
                        </div>
                        <div class="col-75">
                            <input type="text" id="room" name="room" readonly placeholder="Your Reserved room is at : ${date} - ${time}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="comment">Leave Comment: </label>
                        </div>
                        <div class="col-75">
                            <textarea id="comment" name="comment" placeholder="Additional Information to be considered..." style="height:100px"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <button class="btn" onclick="window.location.href = 'dashboard.jsp'">Continue</button>
                    </div>

                </div>
            </div>

        </div>
    </body>
</html>
