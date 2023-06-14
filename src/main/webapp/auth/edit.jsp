<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Profile Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

    <style>
        * {
            box-sizing: border-box;
            padding: 0;
            margin: 0;
        }

        body {

            overflow-x: hidden;
            background-image: url("https://wallpaperaccess.com/full/4685960.jpg");
        }

        .title {
            color: grey;
            font-size: 18px;

        }

        .formname {
            text-align: center;
        }

        .container-usp {
            padding: 5em;
            margin: 5em;
            background-color: rgba(255, 255, 255, 0.779);
            border-radius: 30px;
        }

        .btn {
            font-size: 1em;
            height: 50px;
            display: block;
            width: 20%;
            margin-top: 25px;
            margin-left: auto;
            margin-right: auto;
            background: rgb(75, 15, 145);
            color: rgb(255, 255, 255);
            border-radius: 40px;
            cursor: pointer;
            transition: 0.8s;
        }

        .btn:hover {
            transform: scale(0.96);
        }

        @media screen and (max-width: 1000px) {
            .container {
                height: fit-content;
                width: 100%;

            }
        }
    </style>

</head>
<body>

<jsp:include page="/comp/nav.jsp"/>

<body>
<div class="container-usp">
    <div class="formname"><h1>User Profile</h1></div>
    <form action="/update" method="POST">
        <input type="hidden" name="username" value="${user.username}">
        <input type="hidden" name="type" value="${user.type}">
        <div class="form-group">
            <label>
                Username:
                <input type="text" class="form-control" value="${user.username}" readonly/>
            </label>
        </div>
        <div class="form-group">
            <label>
                Name:
                <input type="text" class="form-control" name="name" value="${user.name}"/>
            </label>
        </div>
        <div class="form-group">
            <label>
                Email:
                <input type="email" class="form-control" name="email" value="${user.email}"/>
            </label>
        </div>
        <div class="form-group">
            <label>
                Password:
                <input type="password" class="form-control" name="password" placeholder="(Existing Password)"/>
            </label>
        </div>
        <div class="form-group">
            <label>
                Phone:
                <input type="text" class="form-control" name="phone" value="${user.phone}"/>
            </label>
        </div>
        <button type="submit" class="btn">Update</button>
    </form>
</div>

<jsp:include page="/comp/footer.jsp"/>

</body>
</html>
