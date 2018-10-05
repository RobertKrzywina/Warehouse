package controller;

import dao.MysqlProductDao;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/modifyProduct")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String stringQuantity = request.getParameter("quantity");
        String stringPrice = request.getParameter("price");
        String isAvailable = request.getParameter("available");
        String option = request.getParameter("option");

        int quantity = 0;
        double price = 0;

        if (stringQuantity != null && stringPrice != null) {
            quantity = Integer.parseInt(stringQuantity);
            price = Double.parseDouble(stringPrice);
        }

        Product product = new Product(name, quantity, price);

        if (isAvailable != null) {
            if (isAvailable.equals("Yes")) {
                product.setAvailable(true);
            } else {
                product.setAvailable(false);
            }
        }

        String message = null;
        MysqlProductDao productDao = new MysqlProductDao();

        try {
            switch (option) {
                case "Add":
                productDao.create(product);
                message = "added successfully.";
                    break;
                case "Show":
                List<Product> products = productDao.read();
                request.setAttribute("products", products);
                request.getRequestDispatcher("allProducts").forward(request, response);
                    break;
                case "Update":
                productDao.update(product);
                message = "updated successfully, only if u typed correct name.";
                    break;
                case "Delete":
                productDao.delete(product);
                message = "deleted successfully, only if u typed correct name.";
                    break;
            }
            request.setAttribute("option", option);
            request.setAttribute("message", message);
            request.getRequestDispatcher("success").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("option", option);
            request.getRequestDispatcher("error").forward(request, response);
        }

    }
}
