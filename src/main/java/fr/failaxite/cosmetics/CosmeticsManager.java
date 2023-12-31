package fr.failaxite.cosmetics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.failaxite.cosmetics.instance.Cosmetic;
import fr.failaxite.cosmetics.instance.Cosmetic.CosmeticType;

public class CosmeticsManager {
    
    private Map<UUID, List<Cosmetic>> activeCosmetics = new HashMap<>();

    public void addPlayer(Player player){
        activeCosmetics.put(player.getUniqueId(), new ArrayList<>());
    }

    public void removePlayer(Player player) {
        activeCosmetics.remove(player.getUniqueId());
    }

    public List<Cosmetic> getCosmeticsForPlayer(Player player){
        return activeCosmetics.get(player.getUniqueId());
    }

    public void addCosmeticForPlayer(Player player, Cosmetic cosmetic){
        getCosmeticsForPlayer(player).add(cosmetic);
    }

    public void removeCosmeticForPlayer(Player player, Cosmetic cosmetic) {
        getCosmeticsForPlayer(player).remove(cosmetic);
    }

    public Cosmetic getCosmeticByType(Player player, CosmeticType type){
        return getCosmeticsForPlayer(player).stream()
            .filter(cos-> cos.getCosmeticType().equals(type))
            .findFirst()
            .orElse(null);
    }

    public void onDisable(){
        for(List<Cosmetic> cosmetics : activeCosmetics.values()){
            cosmetics.forEach(Cosmetic::disable);
        }
    }

    public void onEnable(){
        Bukkit.getOnlinePlayers().forEach(this::addPlayer);
    }
    
}
