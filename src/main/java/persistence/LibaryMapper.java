package persistence;

import entities.Laaner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibaryMapper {

    private Database database;

    public LibaryMapper(Database database) {
        this.database = database;
    }

    public List<Laaner> getAllLaaner() {
        List<Laaner> laanerList = new ArrayList<>();

        String sql = "SELECT laaner.laaner_id, navn, adresse, postnr, udlaan.bog_id FROM laaner \n" +
                "INNER JOIN udlaan ON laaner.laaner_id = udlaan.laaner_id\n" +
                "ORDER BY laaner.navn";
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


    public Laaner getLaanById(int laanerId) {
        Laaner laaner = null;

        String sql = "select laaner_id, name, adress from laaner where id =?";

        try (Connection connection = database.connection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, laanerId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    laanerId = rs.getInt("laaner_id");
                    String name = rs.getString("name");
                    String adress = rs.getString("adress");
                    int zip = rs.getInt("zip");

                    laaner = new Laaner(laanerId, name, adress, zip);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return laaner;
    }

}


