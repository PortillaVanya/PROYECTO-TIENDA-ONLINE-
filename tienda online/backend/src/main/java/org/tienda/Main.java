package org.tienda;

import org.tienda.controller.UserController;
import org.tienda.controller.ProductController;
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(4567);
        staticFiles.location("/public"); // For serving frontend if needed

        UserController userController = new UserController();
        ProductController productController = new ProductController();

        // User routes
        post("/api/register", userController.register);
        post("/api/login", userController.login);

        // Product routes
        get("/api/products", productController.getAll);
    }
}
