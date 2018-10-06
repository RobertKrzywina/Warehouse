<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
    <link rel='stylesheet' href='webjars/bootstrap/4.1.3/css/bootstrap.css'/>
    <link href='css/style.css' rel='stylesheet' type='text/css'/>
</head>
<body>

    <p>Create - add product</p>

    <div>
        <form action="modifyProduct" method="get">
            name<br>        <input type="text" placeholder="name" name="name" required><br><br>
            quantity<br>    <input type="number" min="1" placeholder="quantity" name="quantity" required><br><br>
            price<br>       <input type="number" step=".01" min="0" placeholder="price for each" name="price" required><br><br>

            <label for="available">available</label><br>
            <select name="available" id="available">
                <option value="Yes">Yes</option>
                <option value="No">No</option>
            </select><br>
            <input type="submit" name="option" value="Add">
        </form>
    </div>

    <jsp:include page="WEB-INF/fragment/footer.html"/>

</body>
</html>
