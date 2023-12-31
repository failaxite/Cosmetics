package fr.failaxite.cosmetics.instance.hats;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import fr.failaxite.cosmetics.Cosmetics;
import fr.failaxite.cosmetics.instance.Cosmetic;

public class Hat extends Cosmetic {

    private HatType type;

    public Hat(Cosmetics cosmetics, Player player, HatType type) {
        super(cosmetics,CosmeticType.HAT ,player);
        this.type = type;
    }

    @Override
    public void enable() {
        ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta meta = (SkullMeta) is.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", type.getHeadTexture()));
        Field field;
        try {
            field = meta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(meta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return;
        }

        is.setItemMeta(meta);
        player.getInventory().setHelmet(is);
    }

    @Override
    public void disable() {
        player.getInventory().setHelmet(null);
    }

    public HatType getType() { return type; }
    
}
