package fr.aurelien30000.surroundingteleporter.listeners;

import fr.aurelien30000.surroundingteleporter.SurroundingTeleporter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerListener implements Listener {

    private final SurroundingTeleporter instance = SurroundingTeleporter.getInstance();

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerTeleportEvent(PlayerTeleportEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) return;
        final Location from = event.getFrom().clone();
        final Location to = event.getTo().clone();

        Bukkit.getScheduler().runTaskLater(instance, () -> {
            for (LivingEntity nearbyLivingEntity : from.getNearbyLivingEntities(instance.getRadius())) {
                if (nearbyLivingEntity instanceof Player) continue;
                nearbyLivingEntity.teleport(to);
            }
        }, 10L);
    }

}
