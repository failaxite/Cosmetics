package fr.failaxite.cosmetics;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.failaxite.cosmetics.commands.CosmeticsCommand;
import fr.failaxite.cosmetics.commands.TestCommand;
import fr.failaxite.cosmetics.listeners.CosmeticsListener;

public class Cosmetics extends JavaPlugin {

    
    private static Cosmetics instance;
    private CosmeticsManager cosmeticsManager;
    
    @Override
    public void onEnable() {
        instance = this;
        cosmeticsManager = new CosmeticsManager();
        getCommand("cosmetics").setExecutor(new CosmeticsCommand());
        getCommand("testhat").setExecutor(new TestCommand(this));

        Bukkit.getPluginManager().registerEvents(new CosmeticsListener(this), this);
        cosmeticsManager.onEnable();
    }

    @Override
    public void onDisable() {
        cosmeticsManager.onDisable();
    }

    public static Cosmetics get() {
        return instance;
    }

    public CosmeticsManager getCosmeticsManager(){
        return cosmeticsManager;
    }
    

}
