<%@ page import="com.citawarisan.util.ModelChronos" %>
<%@ page import="com.citawarisan.dao.UserDao" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%--handle request logic here--%>
<%
    User u = (User) session.getAttribute("user");
    UserDao dao = new UserDao();
    ModelChronos c = new ModelChronos(u.getUsername());
    session.setAttribute("dao", dao);
    session.setAttribute("c", c);
    session.setAttribute("u", u);
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users(Admin)</title>
</head>
<body>
<%@include file="/comp/nav.jsp" %>
<c:choose>
    <c:when test="${param.action == 'new'}">
        <form>
            <input type="hidden" name="action" value="create">
            <label>
                Username:
                <p><input type="text" name="username"/></p>
            </label><br>
            <label>
                Password:
                <p><input type="password" name="password"/></p>
            </label><br>
            <label>
                Name:
                <p><input type="text" name="name"/></p>
            </label><br>
            <label>
                Phone:
                <p><input type="text" name="phone"/></p>
            </label><br>
            <label>
                Email:
                <p><input type="text" name="email"/></p>
            </label><br>
            <label>
                User Type:
                <p><input type="number" name="type" minlength="1" maxlength="3" /></p>
            </label><br>
            <input type="submit" value="Add"/>
        </form>
    </c:when>
    <c:when test="${param.action == 'create'}">
        <%
            User user = new User();
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setName(request.getParameter("name"));
            user.setPhone(request.getParameter("phone"));
            user.setEmail(request.getParameter("email"));
            user.setType(Integer.parseInt(request.getParameter("type")));
            dao.createUser(user);
            System.out.println("User Added");
            response.sendRedirect("users.jsp");
        %>
    </c:when>
    <c:when test="${param.action == 'edit'}">
        <%
             User user2 = dao.getUser(request.getParameter("id"));
             System.out.println(user2.getUsername());
             request.setAttribute("user2", user2);
        %>
        <form>
            <input type="hidden" name="action" value="update">
            <label>
                Username:
                <p><input type="text" name="username" value="${user2.username}"/></p>
            </label><br>
            <label>
                Password:
                <p><input type="password" name="password" value="${user2.password}"/></p>
            </label><br>
            <label>
                Name:
                <p><input type="text" name="name" value="${user2.name}"/></p>
            </label><br>
            <label>
                Phone:
                <p><input type="text" name="phone" value="${user2.phone}"/></p>
            </label><br>
            <label>
                Email:
                <p><input type="text" name="email" value="${user2.email}"/></p>
            </label><br>
            <label>
                User Type:
                <p><input type="number" name="type" minlength="1" maxlength="3" value="${user2.type}"/></p>
            </label><br>
            <input type="submit" value="Update"/>
        </form>
    </c:when>
    <c:when test="${param.action == 'update'}">
        <%
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            User user3 = new User();
            user3.setUsername(username);
            user3.setPassword(password);
            user3.setName(name);
            user3.setPhone(phone);
            user3.setEmail(email);
            user3.setType(Integer.parseInt(request.getParameter("type")));
            dao.updateUser(user3);
            System.out.println("User Updated");
            response.sendRedirect("users.jsp");
        %>
    </c:when>
    <c:when test="${param.action == 'delete'}">
        <%
            dao.deleteUser(request.getParameter("id"));
            System.out.println("User Deleted");
            response.sendRedirect("users.jsp");
        %>
    </c:when>
    <c:otherwise>
        <div class="row">
            <div class="container">
                <h3 class="text-center">List of Users</h3>
                <hr>
                <div class="container text-left">
                    <a href="?action=new" class="btn btn-success">Add new User</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>User Type</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="v" items="${dao.getUsers()}">
                        <tr>
                            <td>
                                <c:out value="${v.username}"/>
                            </td>
                            <td>
                                <c:out value="${v.password}"/>
                            </td>
                            <td>
                                <c:out value="${v.name}"/>
                            </td>
                            <td>
                                <c:out value="${v.phone}"/>
                            </td>
                            <td>
                                <c:out value="${v.email}"/>
                            </td>
                            <td>
                                <c:out value="${v.type}"/>
                            </td>
                            <td>
                                <a href="?action=edit&id=<c:out value='${v.username}'/>">Edit</a>
                                <a href="?action=delete&id=<c:out value='${v.username}'/>">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<%@include file="/comp/footer.jsp" %>
</body>
</html>
