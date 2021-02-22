import kong.unirest.GenericType;
import kong.unirest.Unirest;
import model.Course;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static spark.Spark.*;

public class WebServer {
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getHerokuAssignedPort());
        final String KEY = System.getenv("siskey");
        Unirest.config().defaultBaseUrl("https://sis.jhu.edu/api");

        get("/", (req, res) -> {
            return new ModelAndView(null, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/search", (req, res) -> {
            String query = req.queryParams("query");
            Set<Course> courses = Unirest.get("/classes")
                    .queryString("Key", KEY)
                    .queryString("CourseTitle", query)
                    .asObject(new GenericType<Set<Course>>() {
                    })
                    .getBody();
            Map<String, Object> model = Map.of("query", query, "courses", courses);
            return new ModelAndView(model, "search.hbs");
        }, new HandlebarsTemplateEngine());

        post("/search", (req, res) -> {
            String query = req.queryParams("query");
            res.redirect("/search?query=" + query);
            return null;
        }, new HandlebarsTemplateEngine());

    }

    private static int getHerokuAssignedPort() {
        // Heroku stores port number as an environment variable
        String herokuPort = System.getenv("PORT");
        if (herokuPort != null) {
            return Integer.parseInt(herokuPort);
        }
        //return default port if heroku-port isn't set (i.e. on localhost)
        return 4567;
    }

}