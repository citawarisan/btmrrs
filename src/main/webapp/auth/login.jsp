<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login Form</title>
        <style>
               *{
                padding: 0;
                margin: 0;
                box-sizing: border-box;
            }

            .container{
                width: 100%;
                height: 100vh;
                background: url(https://mcdn.wallpapersafari.com/medium/31/28/9duO7g.jpg);
                background-repeat: no-repeat;
                background-size: cover;
               
            }

            .login-form {
                height: fit-content;
                width: 30%;
                padding: 50px;
                text-align: center;
                background-color: rgba(255, 255, 255, 0.779);
                margin: 0 auto 0 auto;
                border-radius: 5px;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
            }
            
            .login-form img {
                width: 50%;
                height: 45%;
            }

            input[type="text"],
            input[type="password"]{
                display: block;
                width: 100%;
                padding-left: 30px;
                height: 75px;

                outline: none;
                border: none;
                font-family: Arial, Helvetica, sans-serif;
                font-size: 1em;
            }
            input[type="radio"]{

                width: 3rem;

            }
            input[type="text"]{
                border-radius: 20px 20px 0 0;
            }
            input[type="password"]{
                border-radius:0 0  20px 20px;
            }

            .txt {
                margin: 20px 0px;
                border-radius: 05px;
            }

            .btn {
                font-size: 1em;
                height: 75px;
                display: block;
                width: 100%;
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
                font-size: 1rem;
            }

            .btn:hover {
                transform: scale(0.85);
            }
            @media screen and (max-width: 1000px) {
               .login-form {
                height: fit-content;
                width: 100%;
                
            } 
            }

        </style>
    </head>

    <body>
        <div class="container">
            <div class="login-form">
           
            <form action="UserController" method="post">
                 <img alt="UMT Logo" src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Logo_Rasmi_UMT.png/1599px-Logo_Rasmi_UMT.png?20180522033258">
            <br><br>
                <input type="text" placeholder="Username" class="form-control" id="usernamame" name="username" >
                <input type="password" placeholder="Password" class="form-control" id="password" name="password">
                <input type="hidden" name="action" value="save"/>
                <br>
                <input type="radio" name="type" id="type" value="1"/>Student
                <input type="radio" name="type" id="type" value="2  "/>Staff

                <input type="submit" value="Login" class="btn">

                <p>Didn't have an account? <a href="signup.jsp">Sign Up</a></p>
            </form>

            ${errorMessage}
        </div>
        </div>

    </body>

</html>

