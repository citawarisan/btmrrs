<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Registration Form</title>
        <link rel="stylesheet" href="styles/main.css"/>
        <script>
            function validatePassword() {
                var password = document.form.password.value;

                if (password.length < 8) {
                    alert("Password must be at least 8 characters long.");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <div class="signup-form">
            <form name="form" action="UserController" method="POST" onsubmit="return validatePassword()">
                <h1>Registration Form</h1>
                <input type="text" placeholder="Username" class="form-control" name="username" id="username">
                <input type="password" placeholder="Password" class="form-control" nname="password" id="password">
                <input type="text" placeholder="Name" class="form-control" name="name" id="name">
                <input type="text" placeholder="Phone" class="form-control" name="phone" id="phone">
                <input type="Email" placeholder="Email" class="form-control" name="email" id="email">
                <input type="submit" value="Signup" class="btn">

                <p>Already have an account? <a href="login.jsp">Sign Up</a></p>
            </form>

        </div>

        ${errorMessage}

    </body>
</html>
