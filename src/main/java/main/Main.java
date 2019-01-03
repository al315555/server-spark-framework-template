package main;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        initExceptionHandler((e) -> {
            //Logger.class.error("ignite failed", e);
            System.out.println("Exception --> ->");
            e.printStackTrace();
            System.exit(100);
        });
        /*secure(HelloWorld.KEY_STORE_FILE_PATH,
                HelloWorld.KEY_STORE_PASSWORD,
                HelloWorld.TRUST_STORE_FILE_PATH,
                HelloWorld.TRUST_STORE_PASSWORD);
        */
        port(HelloWorld.getHerokuAssignedPort());
        HelloWorld.mainPage();
    }
}
