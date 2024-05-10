package fr.farmeurimmo.coreskyblock.purpur.chests;

import fr.farmeurimmo.coreskyblock.utils.ColorUtils;
import org.bukkit.ChatColor;

public enum SellChestTiers {

    COMMON("§7Commun", ColorUtils.translate("#F5F5F5Commun"), 0.8),
    UNCOMMON("§aPeu commun", ColorUtils.translate("#228B22Peu commun"), 0.9),
    RARE("§9Rare", ColorUtils.translate("#4169e1Rare"), 1.0),
    EPIC("§5Épique", ColorUtils.translate("#561494Épique"), 1.1),
    LEGENDARY("§6Légendaire", ColorUtils.translate("#f0c810Légendaire"), 1.2),
    MYTHIC("§dMythique", ColorUtils.translate("#ab0505Mythique"), 1.3);

    private final String normalName;
    private final String hexName;
    private final double sellMultiplier;

    SellChestTiers(String normalName, String hexName, double sellMultiplier) {
        this.normalName = normalName;
        this.hexName = hexName;
        this.sellMultiplier = sellMultiplier;
    }

    public static SellChestTiers getTierByName(String name) {
        String withoutColor = ChatColor.stripColor(name);
        for (SellChestTiers tier : values()) {
            if (tier.getWithoutColor().equalsIgnoreCase(withoutColor)) {
                return tier;
            }
        }
        return null;
    }

    public String getNormalName() {
        return normalName;
    }

    public String getHexName() {
        return hexName;
    }

    public double getSellMultiplier() {
        return sellMultiplier;
    }

    public String getWithoutColor() {
        return ChatColor.stripColor(normalName);
    }
}
