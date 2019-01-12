package main;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import com.google.gson.Gson;
import io.jsonwebtoken.*;

import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import model.usuario.ApplicationUsuario;
import persistence.connection.Auth;
import persistence.dao.InstanceFactory;
import persistence.model.ApplicationMapper;
import persistence.model.ApplicationMapperList;

import static spark.Spark.*;

public class ServerConfiguration {

    public static final String HOME_PAGE_HTML = "<!DOCTYPE html>" +
            "<html>" +
            "<meta charset=\"UTF-8\">" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
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


    static void initRESTMethods() {

        before("/protected/*", (request, response) -> {
            try {
                JJWT.verifyToken(request.headers("X-API-TOKEN"));
            } catch (Exception e) {
                halt(401, "You are not welcome here");
                //don't trust the JWT!
            }
        });

        post("/login", (req, res) -> {
            Gson gson = new Gson();
            ApplicationUsuario user = gson.fromJson(req.body(), ApplicationUsuario.class);
            System.out.println(user.getUsername());
            if (user == null || user.getUsername() == null || user.getPassword() == null ||
                    !Auth.checkCredentials(user.getUsername(), user.getPassword())){
                halt(401, "You are not welcome here");
            }
            String jwt =
                    JJWT.generateToken(user.getUsername(), user.getUsername());
            res.header("X-API-TOKEN", jwt);
            return res;
        });

        get("/home", (req, res) -> HOME_PAGE_HTML);
        get("/protected/user/:idUser", (req, res) -> {
            ApplicationMapper resultado= InstanceFactory.getInstance(
                    InstanceFactory.USUARIO_DAO)
                    .selectOne(
                            Long.parseLong(
                                    req.params(":idUser")
                            )
                    );
            res.type("application/json");
            return resultado.getJSON();
        });
        get("/protected/allUsers", (req, res) -> {
            ApplicationMapperList resultado= InstanceFactory.getInstance(
                    InstanceFactory.USUARIO_DAO)
                    .selectList(null );
            res.type("application/json");
            return resultado.getJSON();
        });
        get("/showCase", (req, res) -> {

            return "/home;</br>/user/{id};</br>/showCase;</br>/allUsers;";
        });
        // Using string/html
        // Using Route
        notFound((req, res) -> {
            res.type("application/json");
            return "{\"message\":\"Custom 404\"}";
        });
        // notFound("<html><body><h1>Custom 404 handling</h1></body></html>");
        // Using string/html
        // Using Route
        internalServerError((req, res) -> {
            res.type("application/json");
            return "{\"message\":\"Custom 500 handling\"}";
        });
        // internalServerError("<html><body><h1>Custom 500 handling</h1></body></html>");
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