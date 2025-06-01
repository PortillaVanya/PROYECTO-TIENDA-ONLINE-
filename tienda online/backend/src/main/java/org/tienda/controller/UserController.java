package org.tienda.controller;

import org.tienda.dao.UserDAO;
import org.tienda.model.User;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

public class UserController {
    private UserDAO userDAO = new UserDAO();
    private Gson gson = new Gson();

    public Route register = (Request req, Response res) -> {
        User user = gson.fromJson(req.body(), User.class);
        boolean success = userDAO.register(user);
        res.type("application/json");
        return gson.toJson(new StandardResponse(success ? "OK" : "ERROR"));
    };

    public Route login = (Request req, Response res) -> {
        User loginReq = gson.fromJson(req.body(), User.class);
        User user = userDAO.login(loginReq.getEmail(), loginReq.getPassword());
        res.type("application/json");
        if (user != null) {
            return gson.toJson(new StandardResponse("OK", gson.toJsonTree(user)));
        } else {
            res.status(401);
            return gson.toJson(new StandardResponse("ERROR", "Invalid credentials"));
        }
    };

    private class StandardResponse {
        private final String status;
        private Object data;

        public StandardResponse(String status) {
            this.status = status;
        }

        public StandardResponse(String status, Object data) {
            this.status = status;
            this.data = data;
        }

        public String getStatus() {
            return status;
        }

        public Object getData() {
            return data;
        }
    }
}

