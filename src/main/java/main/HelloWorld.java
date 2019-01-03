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

    private static final String HOME_PAGE_HTML = "<!DOCTYPE html>" +
            "<html>" +
            "<meta charset=\"UTF-8\">" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
            "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/3/w3.css\">" +
            "<body>" +
            "<!-- Content will go here -->" +
            "HELLO WORLD!" +
            "</body>" +
            "</html>";
    private static final String KEY_STORE_FILE_PATH = "deploy/keystore.jks";
    private static final String KEY_STORE_PASSWORD = "password";
    private static final String TRUST_STORE_FILE_PATH = null;
    private static final String TRUST_STORE_PASSWORD = null;
    private static final int THREAD_TIMEOUT_MILLIS = 100000;
    private static final int MIN_THREADS = 1;
    private static final int MAX_THREADS = 10;
    private static final int PORT_BY_DEFAULT = 4567;


    private static void mainPage() {
        get("/home", (req, res) -> HOME_PAGE_HTML);
    }

    private static int getHerokuAssignedPort() {
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
    public static void main(String[] args) {
        initExceptionHandler((e) -> {
            //Logger.class.error("ignite failed", e);
            System.out.println("Exception --> ->");
            e.printStackTrace();
            System.exit(100);
        });
        secure(KEY_STORE_FILE_PATH, KEY_STORE_PASSWORD, TRUST_STORE_FILE_PATH, TRUST_STORE_PASSWORD);
        port(getHerokuAssignedPort());
        mainPage();
    }
}