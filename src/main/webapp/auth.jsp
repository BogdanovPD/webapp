<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Authorization</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
      <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
  </head>
  <body>
  <div style="font-size: medium; margin: 5px; color: red">
      <c:if test="${not empty userNotFound}">
          <h3><c:out value="${userNotFound}"/></h3>
      </c:if>
  </div>
  <c:choose>
      <c:when test="${empty sessionScope.authUser}">
  <form method="post">
      <div class="form-group" style="width: 50%; margin: 5px">
          <label for="login">Name:</label>
          <input type="text" class="form-control" name="login" id="login" required>
      </div>
      <div class="form-group" style="width: 50%; margin: 5px">
          <label for="pass">Password:</label>
          <input type="password" class="form-control" name="pass" id="pass" required>
      </div>
    <button formaction="/auth" type="submit" class="btn btn-default btn-lg">Sign In</button>
  </form>
      </c:when>
      <c:otherwise>
  <div style="font-size: medium; margin: 5px; color: red">
      <h3>You're already logged in</h3>
      <form method="post">
          <button formaction="/logout" type="submit" class="btn btn-default btn-lg">Log Out</button>
      </form>
  </div>
      </c:otherwise>
  </c:choose>
  </body>
</html>
