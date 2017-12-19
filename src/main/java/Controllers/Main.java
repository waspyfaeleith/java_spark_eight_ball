package Controllers;
import APIConnection.*;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

//import spark.Request;
//import spark.Response;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {
        staticFileLocation("/public");

        String layout = "templates/layout.vtl";
        //get("/hello", (req, res) -> "Hello World");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/main.vm" );
            return new ModelAndView(model, layout);
        },new VelocityTemplateEngine());

        get("/answer", (req, res) -> {
            APIConnection connection = new APIConnection();
            Answer answer = connection.getAnswerFromApi();
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/answer.vm" );
            model.put("answer", answer);
            return new ModelAndView(model, layout);
        },new VelocityTemplateEngine());
    }
}
