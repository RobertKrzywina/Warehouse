package main.java.dao;

import main.java.model.Product;

import java.util.List;

public interface ProductDao {

    void create(Product product);

    List<Product> read();

    void update(Product product);

    void delete(Product product);

}
