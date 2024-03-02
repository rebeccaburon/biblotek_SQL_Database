package org.example;

import entities.Laaner;
import persistence.LibaryMapper;
import persistence.Database;

import java.util.List;

public class Main {

    private final static String USER = "postgres";

    private final static String PASSWORD = "postgres";

    private final static String URL = "jdbc:postgresql://localhost:5432/bibliotek?serverTimezone=CET&useSSL=false&allowPublicKeyRetrieval=true";


    public static void main(String[] args) {

        Database db = new Database(USER,PASSWORD,URL);
        LibaryMapper libaryMapper = new LibaryMapper(db);

        List<Laaner> laanerResult = libaryMapper.getAllLaaner();
        System.out.println("\n**** Printing ALL 'Laaner'****");
        for(Laaner laanerliste : laanerResult){

            System.out.println("\n"+ laanerliste);
        }





    }
}