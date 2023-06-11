<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login Form</title>
        <style>
            body {
                width: 100%;
                height: 100%;
                margin: 0;
                padding: 0;
                overflow-x: hidden;
                background-image: url(sea.jpg);
                background-repeat: no-repeat;
                background-size: cover;
                background-attachment: fixed;
            }

            .login-form {
                height: 750px;
                width: 750px;
                padding: 50px;
                text-align: center;
                background-color: rgba(255, 255, 255, 0.779);
                margin: 8% auto 0;
                border-radius: 05px;
            }

            .login-form img {
                width: 50%;
                height: 45%;
            }

            .login-form input {
                display: block;
                width: 100%;
                padding-left: 30px;
                height: 75px;
                box-sizing: border-box;
                outline: none;
                border: none;
                font-family: Arial, Helvetica, sans-serif;
                font-size: 30px;
            }

            .txt {
                margin: 20px 0px;
                border-radius: 05px;
            }

            .btn {
                margin-top: 60px;
                margin-bottom: 20px;
                background: rgb(75, 15, 145);
                color: rgb(255, 255, 255);
                border-radius: 40px;
                cursor: pointer;
                transition: 0.8s;
            }

            p {
                padding-top: 25px;
                text-align: center;
                font-size: 30px;
            }

            .btn:hover {
                transform: scale(0.96);
            }
        </style>
    </head>

    <body>
        <div class="login-form">
            <img src="logo umt.png">
            <form action="login.jsp" method="post">
                <input type="Username" placeholder="Username" class="form-control" id="usernamame" name="username" >
                <input type="password" placeholder="Password" class="form-control" id="password" name="password">
                <input type="submit" value="Login" class="btn">

                <p>Didn't have an account? <a href="signup.jsp">Sign Up</a></p>
            </form>

            ${errorMessage}
        </div>

    </body>

</html>

