package me.moontimer.bewerbung.manager;

import lombok.Getter;
import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL {

    private final String host;
    private final String database;
    private final String user;
    private final String password;
    @Getter
    private Connection connection;


    public MySQL(String host1, String database1, String username1, String password1) {
        this.host = host1;
        this.database = database1;
        this.user = username1;
        this.password = password1;
    }


    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + "3306" + "/" + database + "?useSSL=false", user, password);
            Bukkit.getConsoleSender().sendMessage("§aMySQL wurde erfolgreich verbunden");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void createTable() {
        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS ZombieKill (UUID TEXT, KILLS int)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void close() {
        if (connection != null) {
            try {
                connection.close();
                Bukkit.getConsoleSender().sendMessage("§aDie Verbindung wurde getrennt.");
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("§cDie Verbindung konnte nicht getrennt werden.");
            }
        }
    }

    public void update(String qry) {
        try {
            connection.createStatement().executeUpdate(qry);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }
}
