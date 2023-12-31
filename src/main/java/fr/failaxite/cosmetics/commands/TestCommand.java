package fr.failaxite.cosmetics.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.failaxite.cosmetics.Cosmetics;
import fr.failaxite.cosmetics.CosmeticsManager;
import fr.failaxite.cosmetics.instance.Cosmetic.CosmeticType;
import fr.failaxite.cosmetics.instance.ballons.Ballon;
import fr.failaxite.cosmetics.instance.ballons.BallonType;

public class TestCommand implements CommandExecutor {

    private Cosmetics cosmetics;
	private final CosmeticsManager cosmeticManager;

    public TestCommand(Cosmetics cosmetics) {
        this.cosmetics = cosmetics;
        this.cosmeticManager = cosmetics.getCosmeticsManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être utilisée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("Utilisation: /test <add|remove>");
            return true;
        }

        if (args[0].equalsIgnoreCase("add")) {
            // Hat hat = new Hat(cosmetics, player, HatType.BALLOON_TEST);
            // hat.enable();

            Ballon ballon = new Ballon(cosmetics, player, BallonType.BALLOON_TEST);
            cosmeticManager.addCosmeticForPlayer(player,ballon);
            ballon.enable();
            player.sendMessage(ChatColor.GREEN +"Tu as activé le cosmétique " + ballon.getType().getDisplay());
        } else if (args[0].equalsIgnoreCase("remove")) {
            // Hat hat = new Hat(cosmetics, player, HatType.BALLOON_TEST);
            // hat.disable();

            Ballon ballon = (Ballon) cosmeticManager.getCosmeticByType(player, CosmeticType.BALLOON);
            ballon.disable();
            cosmeticManager.removeCosmeticForPlayer(player, ballon);
            player.sendMessage(ChatColor.RED + "Tu as désactivé le cosmétique " + ballon.getType().getDisplay());
        } else {
            player.sendMessage("Utilisation: /test <add|remove>");
        }

        return true;
    }
}
