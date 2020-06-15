<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sing Up</title>
<%--    <link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet" type="text/css">--%>
    <link href="<c:url value= "/resources/css/styles.css" />" rel="stylesheet">
</head>
<body>
<div class="form-style-8">

    <div class="form-style-8 header"> Please Sign Up!</div>

    <form method="post" action="/addUser">
        <label for="name">User name
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <label for="username">UserName
            <input class="input-field" type="text" id="username" name="username">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <label for="role">Role
            <input class="input-field" type="text" id="role" name="role">
        </label>
        <input type="submit" value="Sign Up">
    </form>
</div>

</body>
</html>
