package fr.failaxite.cosmetics.instance.ballons;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import fr.failaxite.cosmetics.Cosmetics;
import fr.failaxite.cosmetics.instance.Cosmetic;
import me.tade.NoAI.API.NoAI;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class Ballon extends Cosmetic {

    private BallonType type;
    private ArmorStand armorStand;
    private LivingEntity bat;
    private Cosmetics main;
    private int id;

    public Ballon(Cosmetics cosmetics, Player player, BallonType type) {
        super(cosmetics, CosmeticType.BALLOON ,player);
        this.main = cosmetics;
        this.type = type;
    }

    @Override
    public void enable() {
        final Location loc = player.getLocation().add(1, 2, 1);
        bat = (LivingEntity) player.getWorld().spawnEntity(loc, EntityType.BAT);
        final Location batt = bat.getLocation().add(0.5, 0, 0.5 );
        armorStand = (ArmorStand) bat.getWorld().spawnEntity(batt, EntityType.ARMOR_STAND);
                    
        
        // Rendre la chauve-souris passive
        NoAI.setNoAI(bat);
        bat.setCanPickupItems(false);
        bat.setRemoveWhenFarAway(false);
        bat.setLeashHolder(player);
        
        
        // Créer l'ArmorStand
        
        armorStand.setSmall(true);
        armorStand.setVisible(false);
        armorStand.setBasePlate(false);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        
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

        armorStand.setHelmet(is);
        
        net.minecraft.server.v1_8_R3.Entity znms = ((CraftEntity) bat).getHandle();
        NBTTagCompound ztag = new NBTTagCompound();
        znms.c(ztag);
        ztag.setBoolean("Silent", true);
        znms.f(ztag);
        
        bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 255));


        BukkitRunnable runnable = new BukkitRunnable() {
            
                

                @Override
                public void run() {
                    Location playerLoc = player.getLocation();
                    Location batLoc = bat.getLocation();

                    double distance = 1.0;
                    double yOffset = 2.0;

                    Vector direction = playerLoc.toVector().subtract(batLoc.toVector()).normalize().multiply(distance);
                    Location newPosition = playerLoc.clone().subtract(direction);

                    double middleX = (playerLoc.getX() + batLoc.getX()) / 2.0;
                    double middleZ = (playerLoc.getZ() + batLoc.getZ()) / 2.0;

                    bat.teleport(new Location(playerLoc.getWorld(), middleX, newPosition.getY() + yOffset, middleZ, playerLoc.getYaw(), 0f));

                    Location armorLoc = batLoc.clone().add(0.5, 0.1, 0.5);
                    armorStand.teleport(armorLoc);
                }
                
        };
        id = runnable.runTaskTimer(main, 0, 1L).getTaskId();  
    }

    @Override
    public void disable() {
        armorStand.remove();
        bat.remove();
        Bukkit.getScheduler().cancelTask(id);
        player.getWorld().playSound(armorStand.getLocation().clone().add(0, 2, 0), Sound.CHICKEN_EGG_POP, 1, 2);

        new ParticleBuilder(ParticleEffect.CLOUD, armorStand.getLocation().clone().add(0, 2, 0))
                .setOffset(0, 0, 0)
                .setSpeed(0.1F)
                .setAmount(10)
                .display();

    }

    public BallonType getType() { return type; }

}
