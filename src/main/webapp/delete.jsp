<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
    <link rel='stylesheet' href='webjars/bootstrap/4.1.3/css/bootstrap.css'/>
    <link href='css/style.css' rel='stylesheet' type='text/css'/>
</head>
<body>

    <p>Delete product</p>

    <div>
        <form action="modifyProduct" method="get">
            name<br>        <input type="text" placeholder="name" name="name" required><br><br>
            <input type="submit" name="option" value="Delete">
        </form>
    </div>

    <jsp:include page="WEB-INF/fragment/footer.html"/>

</body>
</html>
