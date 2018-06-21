<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Admin panel</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
      <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
  </head>
  <body>
  <table class="table" style="width: 50%">
      <thead>
      <tr>
          <th scope="col">id</th>
          <th scope="col">Login</th>
          <th scope="col">First name</th>
          <th scope="col">Last name</th>
          <th scope="col">Role</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${users}" var="user">
        <tr>
          <th scope="row"><c:out value="${user.id}"/></th>
          <td><c:out value="${user.login}"/></td>
          <td><c:out value="${user.firstName}"/></td>
          <td><c:out value="${user.lastName}"/></td>
          <td><c:out value="${user.role}"/></td>
            <form method="get">
          <td>
              <input type="hidden" name="userId" value="${user.id}">
              <button formaction="/updateUser" type="submit" class="btn btn-default"><span class="glyphicon glyphicon-edit" style="font-size: small"></span> </button>
          </td>
            </form>
            <form method="post">
          <td>
              <input type="hidden" name="userId" value="${user.id}">
              <button formaction="/deleteUser" type="submit" class="btn btn-default"><span class="glyphicon glyphicon-remove" style="font-size: small"></span> </button>
          </td>
            </form>
      </tr>
      </c:forEach>
      </tbody>
  </table>
  <form>
    <button formaction="/addUser" type="submit" class="btn btn-default btn-lg">Add</button>
  </form>
  </body>
</html>
