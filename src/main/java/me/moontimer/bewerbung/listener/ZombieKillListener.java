package me.moontimer.bewerbung.listener;

import me.moontimer.bewerbung.manager.ActionBar;
import me.moontimer.bewerbung.manager.ZombieManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class ZombieKillListener implements Listener {

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        Player player = event.getEntity().getKiller();
        if (event.getEntity().getType().equals(EntityType.ZOMBIE)) {
            if (event.getEntity().getKiller() == player) {
                //MySQL add 1
                //ActionBar
                ZombieManager.addKill(String.valueOf(player.getUniqueId()));
                //new ActionBar("§cDu hast: §a" + ZombieManager.getKills(String.valueOf(player.getUniqueId()))  + " §cgekillt").sendToPlayer(player);
            }
        }
    }
}
