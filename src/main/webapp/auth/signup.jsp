<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.2/dist/sweetalert2.min.css">
    <title>Registration Form</title>
    <style>
        body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
            overflow-x: hidden;
            background-image: url(https://mcdn.wallpapersafari.com/medium/31/28/9duO7g.jpg);
            background-repeat: no-repeat;
            background-size: cover;
            background-attachment: fixed;
        }

        .signup-form {
            height: 500px;
            width: 500px;
            padding: 50px;
            text-align: center;
            background-color: rgba(255, 255, 255, 0.779);
            margin: 5% auto 0;
            border-radius: 05px;
        }

        input[type="text"],
        input[type="number"],
        input[type="email"],
        input[type="submit"],
        input[type="password"] {
            display: block;
            width: 100%;
            padding-left: 30px;
            height: 35px;
            box-sizing: border-box;
            outline: none;
            border: none;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 15px;
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
            font-size: 15px;
        }

        .btn:hover {
            transform: scale(0.96);
        }

        #username {
            border-radius: 20px 20px 0 0;
        }

        #email {
            border-radius: 0 0 20px 20px;
        }
    </style>
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
    <form name="form" action="/signup" method="POST" onsubmit="return validatePassword()">
        <h1>Registration Form</h1>
        <input type="text" placeholder="Username" class="form-control" name="username" id="username">
        <input type="password" placeholder="Password" class="form-control" name="password" id="password">
        <input type="text" placeholder="Name" class="form-control" name="name" id="name">
        <input type="text" placeholder="Phone" class="form-control" name="phone" id="phone">
        <input type="Email" placeholder="Email" class="form-control" name="email" id="email">
        <br>
        <input type="radio" name="type" value="3" checked/>Student
        <input type="radio" name="type" value="2"/>Staff
        <input type="hidden" name="action" value="register">
        <input type="submit" value="Signup" class="btn">

        <p>Already have an account? <a href="/login">Login</a></p>
    </form>
    <%--    <button onclick="show()">Show Alert</button> why this thing exist is beyond me--%>
</div>

<input type="hidden" id="errorMessage" value="<%=request.getSession().getAttribute("errorMessage")%>">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.2/dist/sweetalert2.min.js"></script>
<script>

    var status = document.getElementById("errorMessage").value;
    if (status === "false") {
        Swal.fire({
            title: 'Sorry!',
            text: 'Please enter all the info correctly',
            icon: 'error',
            confirmButtonText: 'Try Again'
        });
    }
    if (status === "success") {
        Swal.fire({
            title: 'Success!',
            text: 'Account is Created >_<',
            icon: 'success',
            confirmButtonText: 'OK'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = "auth/login.jsp"; // Replace "another-page.jsp" with the desired URL or JSP page
            }
        });
    }
</script>
</body>
</html>
