package Controllers;
import APIConnection.*;

import static spark.Spark.get;
import spark.Request;
import spark.Response;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "main.vm");
        },new VelocityTemplateEngine());

        get("/answer", (req, res) -> {
            APIConnection connection = new APIConnection();
            Answer answer = connection.getAnswerFromApi();
            Map<String, Object> model = new HashMap<>();
            model.put("answer", answer.getText());
            return new ModelAndView(model, "answer.vm");
        },new VelocityTemplateEngine());
    }
}
