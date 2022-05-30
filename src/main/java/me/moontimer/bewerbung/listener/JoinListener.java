package me.moontimer.bewerbung.listener;

import me.moontimer.bewerbung.Bewerbung;
import me.moontimer.bewerbung.manager.ActionBar;
import me.moontimer.bewerbung.manager.ZombieManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        ZombieManager.createPlayer(String.valueOf(event.getPlayer().getUniqueId()));
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bewerbung.getInstance(), () -> {
            new ActionBar("§cDu hast: §a" + ZombieManager.getKills(String.valueOf(event.getPlayer().getUniqueId()))  + " §cgekillt").sendToPlayer(event.getPlayer());
        }, 0L, 20L);
    }
}
