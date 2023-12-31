package fr.failaxite.cosmetics.listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.failaxite.cosmetics.Cosmetics;
import fr.failaxite.cosmetics.CosmeticsManager;
import fr.failaxite.cosmetics.instance.Cosmetic;

public class CosmeticsListener implements Listener {

    private final CosmeticsManager cosmeticsManager;

    public CosmeticsListener(Cosmetics cosmetics) {
        this.cosmeticsManager = cosmetics.getCosmeticsManager();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory() != null && e.getCurrentItem() != null) {
            if (e.getView().getTitle().endsWith("Menu des Cosmetiques")) {
                // Handle inventory click for cosmetics menu if needed
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        cosmeticsManager.addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        cosmeticsManager.getCosmeticsForPlayer(player).forEach(Cosmetic::disable);
        cosmeticsManager.removePlayer(player);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.BAT) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntity(EntityDamageEvent event) {
        if (event.getEntity() instanceof ArmorStand) {
            ArmorStand armorStand = (ArmorStand) event.getEntity();
            if (!event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK) && armorStand.getPassenger() == null) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onLeash(PlayerLeashEntityEvent event) {
        if (event.getEntity() instanceof LivingEntity && event.getEntity().getType() == EntityType.BAT) {
            LivingEntity bat = (LivingEntity) event.getEntity();
            if (bat.getLeashHolder() != null && bat.getLeashHolder().getUniqueId().equals(event.getPlayer().getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }
}
