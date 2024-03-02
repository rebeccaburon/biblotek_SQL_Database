package org.example;

import entities.Books;
import entities.Laaner;
import entities.Lender;
import persistence.LibaryMapper;
import persistence.Database;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static String USER = "postgres";

    private final static String PASSWORD = "postgres";

    private final static String URL = "jdbc:postgresql://localhost:5432/bibliotek?serverTimezone=CET&useSSL=false&allowPublicKeyRetrieval=true";

    Books b;

    public static void main(String[] args) {

        Database db = new Database(USER, PASSWORD, URL);
        LibaryMapper libaryMapper = new LibaryMapper(db);

        //Find en låner ud fra et specifikt laaner_id.
        System.out.println("\n**** Printing ALL 'Laaan from LaanerID'****");
        String resultatLaanerID = String.valueOf(libaryMapper.getLaanById(2));
        System.out.println(resultatLaanerID);


        //Find alle lånere, og vis deres data inklusive postnummer og by.
        List<Laaner> laanerResult1 = libaryMapper.getAllLaaner();
        System.out.println("\n**** Printing ALL 'Laaner'****");
        for (Laaner laanerliste : laanerResult1) {

            System.out.println("\n" + laanerliste);
        }

        // Find alle bøger, og deres forfattere

        List<Books> booksResult = libaryMapper.getAllBooksAndAuthors();
        System.out.println("\n**** Printing Books w. authors ****");

        for (Books booksList : booksResult) {
            System.out.println("\n" + booksList);
        }


        // Find alle lånere og de bøger de har lånt. Medtag også bogtitler og evt. forfatter

        List <Lender> lender = libaryMapper.getLenders();
        System.out.println("\n**** Printing Lender and books'****");
        for (Lender lenderList : lender){
            System.out.println("\n" + lenderList);
        }


        //  // Indsæt en ny låner (insert)
        insertMember(libaryMapper);

    }
    //  // Indsæt en ny låner (insert)
       private static void insertMember(LibaryMapper libaryMapper) {
        Laaner l1 = new Laaner("Lukas Zeimer", "Østerbrograde 117", 8520);
        Laaner insertedLaaner = libaryMapper.insertMember(l1);

        System.out.println("\n**** Inserted Member ****");
        System.out.println(insertedLaaner);
    }



}