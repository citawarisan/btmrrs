<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>BTM Signup</title>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
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
    <body class="container">

        <h1>BTM Signup</h1>

        <form name="form" action="<%=request.getContextPath()%>/UserController" method="POST" onsubmit="return validatePassword()">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="username" class="form-control" name="username" id="username" placeholder="Enter username"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password"/>
            </div>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Name"/>
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="Phone Number"/>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Email"/>
            </div>
            <input type="submit" value="Sign Up"/>
        </form>

        <a href="${pageContext.request.contextPath}/login">Login instead</a>

        ${errorMessage}

    </body>
</html>
