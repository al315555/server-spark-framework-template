package main;

import persistence.connection.DatabaseConnection;

import static spark.Spark.initExceptionHandler;
import static spark.Spark.port;

public class Main {
    public static void main(String[] args) {
        initExceptionHandler((e) -> {
            //Logger.class.error("ignite failed", e);
            System.out.println("Exception --> ->");
            e.printStackTrace();
            try {
                DatabaseConnection.closeInstance();
            }catch(Exception ex){
                ex.printStackTrace();
                System.err.println("Error: Cannot close database.");
            }finally {
                System.exit(100);
            }
        });
        /*secure(ServerConfiguration.KEY_STORE_FILE_PATH,
                ServerConfiguration.KEY_STORE_PASSWORD,
                ServerConfiguration.TRUST_STORE_FILE_PATH,
                ServerConfiguration.TRUST_STORE_PASSWORD);
        */
        port(ServerConfiguration.getHerokuAssignedPort());
        ServerConfiguration.initRESTMethods();
        /*try {
            DatabaseConnection.testConnection();
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
