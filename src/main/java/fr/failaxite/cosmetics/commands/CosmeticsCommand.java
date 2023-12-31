package fr.failaxite.cosmetics.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.failaxite.cosmetics.gui.CosmeticsGui;

public class CosmeticsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (sender instanceof Player) {
            new CosmeticsGui((Player) sender);
        }

        return false;
    }
    
}
