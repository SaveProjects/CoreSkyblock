package fr.farmeurimmo.coreskyblock.purpur.items.enchants.enums;

import fr.farmeurimmo.coreskyblock.utils.RomanNumberUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public enum Enchantments {

    GAIN_DE_VIE("Gain de vie", "§8Permet de gagner {value} cœurs supplémentaires.",
            2, false, 1, 3, 0, -1,
            EnchantmentRarity.RARE, List.of(EnchantmentsRecipients.HELMET, EnchantmentsRecipients.CHESTPLATE,
            EnchantmentsRecipients.LEGGINGS, EnchantmentsRecipients.BOOTS)),

    RENVOI("Renvoi", "§8Chance de {value}% de rediriger l’attaque de l’adversaire contre lui-même.",
            2, true, 0.02, 1, 0, -1,
            EnchantmentRarity.EPIC, List.of(EnchantmentsRecipients.HELMET, EnchantmentsRecipients.CHESTPLATE,
            EnchantmentsRecipients.LEGGINGS, EnchantmentsRecipients.BOOTS)),

    LAMPE_TORCHE("Lampe torche", "§8Permet d’avoir l’effet vision nocturne", -1, false,
            0, 0, 0, -1, EnchantmentRarity.UNCOMMON, List.of(EnchantmentsRecipients.HELMET)),

    DECAPITEUR("Décapiteur", "§8Quand on tue un joueur avec une épée avec l’enchantement, il y a {value}% que la tête du joueur tuée drop au sol",
            4, true, 0.025, 1, 0, -1,
            EnchantmentRarity.UNCOMMON, List.of(EnchantmentsRecipients.SWORD)),

    SEISME("Séisme", "§8Permet de repousser votre adversaire et de l’envoyer en l’air en faisant clic droit, cooldown de {value_cooldown} secondes.",
            2, false, 0, 0, 15, 5,
            EnchantmentRarity.UNCOMMON, List.of(EnchantmentsRecipients.SWORD)),

    TEMPETE_DE_FOUDRE("Tempête de foudre", "§8Chance de {value}% que la foudre apparaît pour frapper l’adversaire. La foudre: 2 points de dégâts et inflige 3 dégâts à la durabilité de l’armure.",
            2, true, 0.02, 1, 0, -1,
            EnchantmentRarity.EPIC, List.of(EnchantmentsRecipients.SWORD)),

    GELURE("Gelure", "§8Chance de {value}% de paralyser l'ennemi (effet de slowness extrêmement important) durant {value_effect} secondes.",
            3, true, 0.02, 0.5, 0, 2,
            EnchantmentRarity.RARE, List.of(EnchantmentsRecipients.SWORD)),

    INTIMIDATION("Intimidation", "§8Chance de {value}% d’appliquer l’effet faiblesse à l’adversaire pendant {value_effect} secondes.",
            2, true, 0.01, 0.7, 0, 3,
            EnchantmentRarity.EPIC, List.of(EnchantmentsRecipients.SWORD)),

    XP_TRANSFORMATEUR("Xp transformateur", "§8Permet de transformer le loot des mobs lors de leur mort en xp vanilla. (+10% d’xp).",
            -1, true, 10, 0.0, 0, -1,
            EnchantmentRarity.RARE, List.of(EnchantmentsRecipients.SWORD)),

    AIMANT("Aimant", "§8Permet que blocs minés, cassés ou autre ainsi que le drop des mobs tués (avec un item avec cette enchantement) arrive directement dans l’inventaire.",
            -1, false, 0, 0, 0, -1,
            EnchantmentRarity.UNCOMMON, List.of(EnchantmentsRecipients.SWORD, EnchantmentsRecipients.PICKAXE,
            EnchantmentsRecipients.AXE, EnchantmentsRecipients.HOE, EnchantmentsRecipients.ROD, EnchantmentsRecipients.SHOVEL)),

    GEYSER("Geyser", "§8Chance de {value}% d’envoyer légèrement l’adversaire en l’air en faisant apparaître un geyser.",
            2, true, 0.02, 1, 0, -1,
            EnchantmentRarity.RARE, List.of(EnchantmentsRecipients.SWORD)),

    TIR_EXPLOSIF("Tir explosif", "§8Chance de {value}% que la flèche tirée produise une explosion qui fait 1,5 fois plus puissant que l’attaque normale de l’arc.",
            5, true, 0.02, 1, 0, -1,
            EnchantmentRarity.UNCOMMON, List.of(EnchantmentsRecipients.BOW_AND_CROSSBOW)),

    FLECHE_GELEE("Flèche gelée", "§8Chance de {value}% de paralyser l'ennemi (effet de slowness extrêmement important) durant {value_effect} secondes.",
            3, true, 0.03, 0.5, 0, 2,
            EnchantmentRarity.RARE, List.of(EnchantmentsRecipients.BOW_AND_CROSSBOW)),

    FLECHE_TONNERRE("Flèche tonnerre", "§8Chance de {value}% que la foudre apparaît pour frapper l’adversaire. La foudre: 2 points de dégâts et inflige 3 dégâts à la durabilité de l’armure.",
            2, true, 0.03, 1, 0, -1,
            EnchantmentRarity.EPIC, List.of(EnchantmentsRecipients.BOW_AND_CROSSBOW)),

    SNIPER("Sniper", "§8Chance de {value}% de doubler les dégâts de l’arc.", 3, true,
            0.02, 1, 0, -1, EnchantmentRarity.UNCOMMON, List.of(EnchantmentsRecipients.BOW_AND_CROSSBOW)),

    PLUIE_DE_FLECHES("Pluie de flèches", "§8Chance de {value}% que des flèches apparaissent autour de l’adversaire et foncent sur lui.",
            3, true, 0.02, 0.5, 0, -1,
            EnchantmentRarity.EPIC, List.of(EnchantmentsRecipients.BOW_AND_CROSSBOW)),

    SMELTING("Smelting", "§8Permet de faire fondre automatiquement les minerais minés.",
            -1, false, 0, 0, 0, -1, EnchantmentRarity.UNCOMMON, List.of(EnchantmentsRecipients.PICKAXE)),

    ORE_XP("Ore Xp", "§8Permet de multiplier l’xp reçue en cassant des minerais.", 1,
            false, 0, 0, 0, -1, EnchantmentRarity.EPIC, List.of(EnchantmentsRecipients.PICKAXE)),

    LASSO("Lasso", "§8Permet d’augmenter la puissance de grab de la rod (pvp).", -1,
            false, 0, 0, 0, -1, EnchantmentRarity.RARE, List.of(EnchantmentsRecipients.ROD)),

    GRAPPIN("Grappin", "§8Permet de propulser le joueur dans la direction de la rod (50 sec de cooldown).",
            -1, false, 0, 0, 50, -1, EnchantmentRarity.EPIC, List.of(EnchantmentsRecipients.ROD));

    private final String displayName;
    private final String description;
    private final int maxLevel;
    private final boolean isPercentage;
    private final double baseValue;
    private final double multiplier;
    private final int baseCooldown;
    private final int cooldownReductionPerLevel;
    private final EnchantmentRarity rarity;
    private final List<EnchantmentsRecipients> recipients;

    Enchantments(String displayName, String description, int maxLevel, boolean isPercentage, double baseValue, double multiplier,
                 int cooldown, int cooldownReductionPerLevel, EnchantmentRarity rarity, List<EnchantmentsRecipients> recipients) {
        this.displayName = displayName;
        this.description = description;
        this.maxLevel = maxLevel;
        this.isPercentage = isPercentage;
        this.baseValue = baseValue;
        this.multiplier = multiplier;
        this.baseCooldown = cooldown;
        this.cooldownReductionPerLevel = cooldownReductionPerLevel;
        this.rarity = rarity;
        this.recipients = recipients;
    }

    public static String getDescription(Enchantments enchantments, int level) {
        return enchantments.description.replace("{value}", NumberFormat.getInstance().format(
                        enchantments.getValueForLevel(level) * (enchantments.isPercentage() ? 100 : 1)))
                .replace("{value_cooldown}", NumberFormat.getInstance().format(enchantments.getCooldown(level)))
                .replace("{value_effect}", NumberFormat.getInstance().format(enchantments.getValueEffectForLevel(level)));
    }

    public static ArrayList<Enchantments> getEnchantmentsByRarity(EnchantmentRarity rarity) {
        ArrayList<Enchantments> enchantments = new ArrayList<>();
        for (Enchantments enchantment : values()) {
            if (enchantment.getRarity() == rarity) {
                enchantments.add(enchantment);
            }
        }
        return enchantments;
    }

    public static LinkedHashMap<EnchantmentsRecipients, ArrayList<Enchantments>> getEnchantmentsByMaterial() {
        LinkedHashMap<EnchantmentsRecipients, ArrayList<Enchantments>> map = new LinkedHashMap<>();

        for (EnchantmentsRecipients recipient : EnchantmentsRecipients.values()) {
            ArrayList<Enchantments> enchantments = new ArrayList<>();
            for (Enchantments enchantment : values()) {
                if (enchantment.isAllowed(recipient)) {
                    enchantments.add(enchantment);
                }
            }
            map.put(recipient, enchantments);
        }
        return map;
    }

    public String canBeAppliedOn() {
        return "§7Applicable sur: " + recipients.stream().map(EnchantmentsRecipients::getName).reduce((s1, s2) -> s1 + ", " + s2).orElse("");
    }

    public String getDisplayName() {
        return rarity.getColor() + displayName;
    }

    public boolean hasMaxLevel() {
        return maxLevel != -1;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public List<EnchantmentsRecipients> getRecipients() {
        return recipients;
    }

    public boolean isPercentage() {
        return isPercentage;
    }

    public boolean hasCooldown() {
        return baseCooldown != -1;
    }

    public int getCooldown(int level) {
        return baseCooldown - (level - 1) * cooldownReductionPerLevel;
    }

    public double getValueForLevel(int level) {
        return baseValue + (level - 1) * multiplier * baseValue;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public int getValueEffectForLevel(int level) {
        return (int) (cooldownReductionPerLevel + (level - 1) * multiplier * cooldownReductionPerLevel);
    }

    public boolean canBeAppliedOn(Material material) {
        return recipients.stream().anyMatch(recipient -> material.name().contains(recipient.name()));
    }

    public boolean isAllowed(EnchantmentsRecipients recipient) {
        if (recipient == null) return false;
        return recipients.contains(recipient);
    }

    public EnchantmentRarity getRarity() {
        return rarity;
    }

    public List<Component> getDescriptionFormatted(int level) {
        int maxLengthPerLine = 34;
        ArrayList<Component> components = new ArrayList<>();
        int current = 0;
        StringBuilder builder = new StringBuilder();
        for (char c : Enchantments.getDescription(this, level).toCharArray()) {
            if (current >= maxLengthPerLine && c == ' ') {
                components.add(Component.text(builder.toString()));
                builder = new StringBuilder();
                builder.append("§8");
                current = 0;
                continue;
            }
            builder.append(c);
            current++;
        }
        components.add(Component.text(builder.toString()));
        components.add(Component.text(""));
        current = 0;
        builder = new StringBuilder();
        for (char c : canBeAppliedOn().toCharArray()) {
            if (current >= maxLengthPerLine && c == ' ') {
                components.add(Component.text(builder.toString()));
                builder = new StringBuilder();
                builder.append("§7");
                current = 0;
                continue;
            }
            builder.append(c);
            current++;
        }
        components.add(Component.text(builder.toString()));

        if (hasMaxLevel()) {
            components.add(Component.text("§7Niveau maximum: " + RomanNumberUtils.toRoman(maxLevel)));
        } else {
            components.add(Component.text("§7Niveau maximum: " + RomanNumberUtils.toRoman(1)));
        }

        return components;
    }
}
