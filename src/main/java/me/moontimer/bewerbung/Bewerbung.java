package me.moontimer.bewerbung;

import me.moontimer.bewerbung.listener.JoinListener;
import me.moontimer.bewerbung.listener.ZombieKillListener;
import me.moontimer.bewerbung.manager.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Zombie;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bewerbung extends JavaPlugin {

    private static Bewerbung instance;
    private MySQL mySQL;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();

        mySQL = new MySQL(getConfig().getString("host"), getConfig().getString("database"), getConfig().getString("username"), getConfig().getString("password"));
        mySQL.connect();
        mySQL.createTable();

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new ZombieKillListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        mySQL.close();
    }

    public static Bewerbung getInstance() {
        return instance;
    }

    public MySQL getMySQL() {
        return mySQL;
    }
}
