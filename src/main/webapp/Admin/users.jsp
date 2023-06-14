<%-- 
    Document   : users
    Created on : Jun 15, 2023, 4:36:15 AM
    Author     : Omar Alomory(S63955)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users(Admin)</title>
    </head>
    <body>
         <%@include file="/comp/nav.jsp" %>
        <div class="row">
            <div class="container">
                <h3 class="text-center">List of Users</h3>
                <hr>
                <div class="container text-letf">
                    <a href="EmployeeServlet?action=new" class="btn btn-success">Add new User</a>
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
                    <c:forEach var="u" items="${listUsers}">
                        <tr>
                            <td>
                        <c:out value="${u.username}" />
                        </td>
                        <td>
                        <c:out value="${u.password}" />
                        </td>
                        <td>
                        <c:out value="${u.name}" />
                        </td>
                        <td>
                        <c:out value="${u.phone}" />
                        </td>
                        <td>
                        <c:out value="${u.email}" />
                        </td>
                        <td>
                        <c:out value="${u.type}" />
                        </td>
                        <td>
                            <a href="EmployeeServlet?action=edit&id=<c:out value='${u.username}'/>">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="EmployeeServlet?action=delete&id=<c:out value='${u.username}'/>">Delete</a>
                        </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="/comp/footer.jsp"%>
    </body>
</html>
