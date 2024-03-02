package persistence;

import entities.Books;
import entities.Laaner;
import entities.Lender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibaryMapper {

    private Database database;

    public LibaryMapper(Database database) {
        this.database = database;
    }

    // Find alle lånere, og vis deres data inklusive postnummer og by.
    public List<Laaner> getAllLaaner() {
        List<Laaner> laanerList = new ArrayList<>();

        String sql = "SELECT laaner_id, navn, adresse,postnr FROM laaner \n" +
                "ORDER BY navn ";
        try (Connection connection = database.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int laanerId = rs.getInt("laaner_id");
                    String name = rs.getString("navn");
                    String address = rs.getString("adresse");
                    int zip = rs.getInt("postnr");

                    laanerList.add(new Laaner(laanerId,name,address,zip));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return laanerList;
    }

    //Find en låner ud fra et specifikt laaner_id.
    public Laaner getLaanById(int laanerId) {
    Laaner laaner = null;


        String sql = "select laaner_id, navn, adresse, postnr FROM laaner \n" +
                "WHERE laaner_id = ? " + "ORDER by navn";


        try (Connection connection = database.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, laanerId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    laanerId = rs.getInt("laaner_id");
                    String name = rs.getString("navn");
                    String adress = rs.getString("adresse");
                    int zip = rs.getInt("postnr");

                    laaner = (new Laaner(laanerId, name, adress, zip));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return laaner;

    }

    //Find alle bøger, og deres forfattere
    public List<Books> getAllBooksAndAuthors() {
        List<Books> booksList = new ArrayList<>();

        String sql = "SELECT bog_id, titel, forfatter.navn FROM bog\n" +
                "INNER JOIN forfatter ON bog.forfatter_id = forfatter.forfatter_id ";
        try (Connection connection = database.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int bookId = rs.getInt("bog_id");
                    String title = rs.getString("titel");
                    String authorName = rs.getString("navn");

                    booksList.add(new Books(bookId,title,authorName));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return booksList;
    }

    // Find alle lånere og de bøger de har lånt. Medtag også bogtitler og evt. forfatter

    public List<Lender> getLenders() {
        List<Lender> lenderList = new ArrayList<>();

        String sql = "SELECT udlaan.laaner_id, laaner.navn, bog_id FROM udlaan\n" +
                "INNER Join laaner ON laaner.laaner_id = udlaan.laaner_id ";
        try (Connection connection = database.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int lenderId = rs.getInt("laaner_id");
                    String lenderName = rs.getString("navn");
                    int bookId = rs.getInt("bog_id");

                    lenderList.add(new Lender(bookId, lenderId, lenderName));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lenderList;
    }

   // Indsæt en ny låner (insert)
   public Laaner insertMember(Laaner laaner){
       boolean result = false;
       int newId = 0;
       String sql = "INSERT INTO laaner (navn, adresse, postnr)  values (?,?,?)";
       try (Connection connection = database.connection()) {
           // Vi laver PreparedStatemnt, for at sørger for at den SQL vi koder ind
           // ikke kan blive manipuleret.
           try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS )) {
               ps.setString(1, laaner.getName());
               ps.setString(2, laaner.getAddress());
               ps.setInt(3, laaner.getZip());

               int rowsAffected = ps.executeUpdate();
               if (rowsAffected == 1){
                   result = true;
               }
               ResultSet idResultset = ps.getGeneratedKeys();
               if (idResultset.next()){
                   newId = idResultset.getInt(1);
                   laaner.setLaanerId(newId);
               } else {
                   laaner = null;
               }
           } catch (SQLException throwables) {
               // TODO: Make own throwable exception and let it bubble upwards
               throwables.printStackTrace();
           }
       } catch (SQLException throwables) {
           // TODO: Make own throwable exception and let it bubble upwards
           throwables.printStackTrace();
       }
       return laaner;
   }


    // Fjern et udlån (delete) - Har ikke kørt den!
    public boolean deleteLaaner(int laanerId){
        boolean result = false;
        String sql = "delete from member where member_id = ?";
        try (Connection connection = database.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, laanerId);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1){
                    result = true;
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            // TODO: Make own throwable exception and let it bubble upwards
            throwables.printStackTrace();
        }
        return result;
    }

   /*/ Rediger en bogtitel (update) OBS ændre i koden!!!
   public boolean updateMember(Member member) {
       boolean result = false;
       String sql =    "update member " +
               "set name = ?, address = ?, zip = ?, gender = ?, year = ? " +
               "where member_id = ?";
       try (Connection connection = database.connect()) {
           try (PreparedStatement ps = connection.prepareStatement(sql)) {
               ps.setString(1, member.getName());
               ps.setString(2, member.getAddress());
               ps.setInt(3, member.getZip());
               ps.setString(4, member.getGender());
               ps.setInt(5, member.getYear());
               ps.setInt(6, member.getMemberId());
               int rowsAffected = ps.executeUpdate();
               if (rowsAffected == 1){
                   result = true;
               }
           } catch (SQLException throwables) {
               // TODO: Make own throwable exception and let it bubble upwards
               throwables.printStackTrace();
           }
       } catch (SQLException throwables) {
           // TODO: Make own throwable exception and let it bubble upwards
           throwables.printStackTrace();
       }
       return result;
   }*/



}


