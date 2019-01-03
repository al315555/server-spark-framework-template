package main;


import org.eclipse.jetty.server.AbstractNCSARequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import spark.embeddedserver.jetty.EmbeddedJettyFactory;

import java.io.IOException;
import java.util.function.IntUnaryOperator;
import java.util.logging.Logger;

import static spark.Spark.*;

public class HelloWorld {

    public static final String HOME_PAGE_HTML = "<!DOCTYPE html>" +
            "<html>" +
            "<meta charset=\"UTF-8\">" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+
            "<body>" +
            "<!-- Content will go here -->" +
            "HELLO WORLD!" +
            "</body>" +
            "</html>";
    public static final String KEY_STORE_FILE_PATH = "deploy/keystore.jks";
    public static final String KEY_STORE_PASSWORD = "password";
    public static final String TRUST_STORE_FILE_PATH = null;
    public static final String TRUST_STORE_PASSWORD = null;
    public static final int THREAD_TIMEOUT_MILLIS = 100000;
    public static final int MIN_THREADS = 1;
    public static final int MAX_THREADS = 10;
    public static final int PORT_BY_DEFAULT = 4567;


    static void mainPage() {
        get("/home", (req, res) -> HOME_PAGE_HTML);
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return PORT_BY_DEFAULT; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    /*

        get("/", (request, response) -> {
            // Show something
        });

        post("/", (request, response) -> {
            // Create something
        });

        put("/", (request, response) -> {
            // Update something
        });

        delete("/", (request, response) -> {
            // Annihilate something
        });

        options("/", (request, response) -> {
            // Appease something
        });
    */

}