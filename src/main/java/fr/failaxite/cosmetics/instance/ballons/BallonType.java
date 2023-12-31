package fr.failaxite.cosmetics.instance.ballons;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;

public enum BallonType {

    TOP_HAT(
        ChatColor.RED + "Top Hat", 
        Arrays.asList(ChatColor.GRAY + "Fancy!"),
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzE1YzVjOWYyNzliMTE2NGVjZDVlNjdlMTQ0Nzg4MDIxODRiYjAzMDMxZDFhOWZmMzVlNDZiODYzYTljMDZiYyJ9fX0="),
        
    BALLOON_TEST(
        ChatColor.GOLD + "Tiger Hat", 
        Arrays.asList(ChatColor.GRAY + "Keep away!"),
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzM5NTZkMDdjZTMxMzc3YTMwZmMxZDdhMTE0Y2FhNDc0ZGRhNjE0ZjkxMmIzZjI1N2JjN2Y5OTVkY2VkOTE5OCJ9fX0="),
    DOG_HAT(
        ChatColor.YELLOW + "Dog Hat", 
        Arrays.asList(ChatColor.GRAY + "Careful I bite!"),
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWU4NDZiZjY5MmI1ODkyOGI0NTYxMWJlNzQzMjk5NmI4MDY4NDZhNjc3MGQ3YjkxZjE4MTcwOWNiYmVlMTY3MCJ9fX0=");

    private String display, headTexture;
    private List<String> description;

    BallonType(String display, List<String> description, String headTexture) {
        this.display = display;
        this.description = description;
        this.headTexture = headTexture;
    }

    public String getDisplay() { return display; }
    public List<String> getDescription() { return description; }
    public String getHeadTexture() { return headTexture; }
    
}
