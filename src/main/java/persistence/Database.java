package persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;
    private final String USER;
    private final String PASSWORD;
    private final String URL;


    public Database(String user, String password, String url) {
        USER = user;
        PASSWORD = password;
        URL = url;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERRO: Installing Driver class");
        }

    }

    public Connection connection (){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection (URL,USER,PASSWORD);
            // could make own  throwable exeption = throw "msg";
        } catch (SQLException trowables) {
            trowables.printStackTrace();
            System.out.println("\nERRO: Connection to the database");

        }
        return connection;
    }
}
