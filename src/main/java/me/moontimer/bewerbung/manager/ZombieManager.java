package me.moontimer.bewerbung.manager;

import me.moontimer.bewerbung.Bewerbung;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ZombieManager {

    public static boolean playerExists(String uuid) {
        ResultSet resultSet = Bewerbung.getInstance().getMySQL().query("SELECT * FROM ZombieKill WHERE UUID='" + uuid + "'");
        try {
            if (resultSet.next())
                return (resultSet.getString("UUID") != null);
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createPlayer(String uuid) {
        if (!playerExists(uuid)) {
            try {
                PreparedStatement preparedStatement = Bewerbung.getInstance().getMySQL().getConnection().prepareStatement("INSERT INTO ZombieKill(UUID, KILLS) VALUES (?, ?)");
                preparedStatement.setString(1, uuid);
                preparedStatement.setInt(2, 0);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int getKills(String uuid) {
        ResultSet resultSet = Bewerbung.getInstance().getMySQL().query("SELECT KILLS FROM ZombieKill WHERE UUID='" + uuid + "'");
        try {
            if (resultSet.next()) {
                return resultSet.getInt("KILLS");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static void addKill(String uuid) {
        Bewerbung.getInstance().getMySQL().update("UPDATE ZombieKill SET KILLS='" + (getKills(uuid) + 1) + "' WHERE UUID='" + uuid + "'");
    }
}
