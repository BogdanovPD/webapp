<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add New User</title>
</head>
<body>
<form method="post">
    <input type="hidden" name="userId">
    Login: <input type="text" name="login" required>
    Password: <input type="password" name="pass" required>
    First name:<input type="text" name="firstName">
    Last Name: <input type="text" name="lastName">
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
    <button type="submit" formaction="/addUser">Submit</button>
</form>
</body>
</html>
