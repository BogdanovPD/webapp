<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<form method="post">
    <input type="hidden" name="userId" value="${user.id}">
    Login: <input type="text" name="login" value="${user.login}" required>
    Password: <input type="password" name="pass" value="${user.password}" required>
    First name:<input type="text" name="firstName" value="${user.firstName}">
    Last Name: <input type="text" name="lastName" value="${user.lastName}">
    Role:
    <select name='role' required>
        <option value="${selectedRole}" selected>${selectedRole}</option>
        <c:forEach items="${roles}" var="role">
            <c:if test="${role != selectedRole}">
                <option value="${role}">${role}</option>
            </c:if>
        </c:forEach>
    </select>
    <br>
    <br>
    <button type="submit" formaction="/updateUser">Submit</button>
</form>
</body>
</html>
