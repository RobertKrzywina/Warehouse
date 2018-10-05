<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

    <div class="container" id="allProducts">
        <h1>All products</h1>
        <table border="1" width="50%">
            <tr>
                <td>Name</td>
                <td>Quantity</td>
                <td>Price</td>
                <td>Available</td>
            </tr>
                <%
                    List<Product> products = (List<Product>)request.getAttribute("products");
                    if (products != null)
                        for(Product product : products) {
                %>
            <tr>
                <th><%=product.getName()%></th>
                <th><%=product.getQuantity()%></th>
                <th><%=product.getPrice()%></th>
                <th>
                    <%
                        String isAvailable;
                        if (product.isAvailable()) {
                            isAvailable = "Yes";
                        } else {
                            isAvailable = "No";
                        }
                    %>
                    <%=isAvailable%>
                </th>
            </tr>
                <%
                        }
                %>
        </table>
    </div>

    <jsp:include page="WEB-INF/fragment/footer.html"/>

</body>
</html>
