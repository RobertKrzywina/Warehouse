<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

    <div class="container">
        <h1>Click the button!</h1>
        <form action="modifyProduct" method="get">
            <input type="submit" name="option" value="Show">
        </form>
    </div>

    <jsp:include page="WEB-INF/fragment/footer.html"/>

</body>
</html>
