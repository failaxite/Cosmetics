package fr.failaxite.cosmetics.instance;

import org.bukkit.entity.Player;

import fr.failaxite.cosmetics.Cosmetics;

public abstract class Cosmetic {

    protected Cosmetics cosmetics;
    protected CosmeticType cosmeticType;
    protected Player player;

    public Cosmetic(Cosmetics cosmetics,CosmeticType cosmeticType, Player player) {
        this.cosmetics = cosmetics;
        this.cosmeticType = cosmeticType;
        this.player = player;
    }

    public enum CosmeticType {
        BALLOON, HAT;
    }

    public CosmeticType getCosmeticType(){
        return cosmeticType;
    }

    public Player getPlayer(){
        return player;
    }

    public abstract void enable();

    public abstract void disable();
    
    
}
