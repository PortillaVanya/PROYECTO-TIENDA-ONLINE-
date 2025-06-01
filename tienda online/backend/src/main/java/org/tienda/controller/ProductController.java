package org.tienda.controller;

import com.google.gson.Gson;
import org.tienda.dao.ProductDAO;
import org.tienda.model.Product;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class ProductController {
    private ProductDAO productDAO = new ProductDAO();
    private Gson gson = new Gson();

    public Route getAll = (Request req, Response res) -> {
        List<Product> products = productDAO.getAll();
        res.type("application/json");
        return gson.toJson(products);
    };
}
