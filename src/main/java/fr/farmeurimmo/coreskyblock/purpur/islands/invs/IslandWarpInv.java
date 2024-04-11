package fr.farmeurimmo.coreskyblock.purpur.islands.invs;

import fr.farmeurimmo.coreskyblock.purpur.CoreSkyblock;
import fr.farmeurimmo.coreskyblock.purpur.islands.IslandsManager;
import fr.farmeurimmo.coreskyblock.purpur.islands.IslandsWarpManager;
import fr.farmeurimmo.coreskyblock.storage.islands.Island;
import fr.farmeurimmo.coreskyblock.storage.islands.IslandWarp;
import fr.farmeurimmo.coreskyblock.utils.LocationTranslator;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class IslandWarpInv extends FastInv {

    private static final long COOLDOWN = 5_000;
    private boolean gotUpdate = false;
    private long lastAction = System.currentTimeMillis() - 5_000;
    private boolean closed = false;

    public IslandWarpInv(Island island, IslandWarp warp) {
        super(27, "§8Warp de l'île");

        setItem(26, ItemBuilder.copyOf(new ItemStack(Material.ARROW))
                .name("§6Retour §8| §7(clic gauche)").build(), e -> {
            new IslandInv(island).open((Player) e.getWhoClicked());
            gotUpdate = true;
        });

        update(island, warp);

        setCloseFilter(p -> {
            gotUpdate = true;
            closed = true;
            return false;
        });

        Bukkit.getScheduler().runTaskTimerAsynchronously(CoreSkyblock.INSTANCE, (task) -> {
            if (closed) {
                task.cancel();
                return;
            }
            if (gotUpdate) return;
            gotUpdate = true;
            update(island, warp);
        }, 0, 40L);
    }

    private void update(Island island, IslandWarp warp) {
        gotUpdate = false;

        if (warp != null) {
            setItem(10, ItemBuilder.copyOf(new ItemStack(Material.NAME_TAG))
                    .name("§6Nom §8| §7(clic gauche)")
                    .lore("§7" + warp.getName()).build(), e -> {
                if (!island.isLoaded()) {
                    e.getWhoClicked().sendMessage(Component.text("§cL'île n'est pas chargée ici."));
                    return;
                }

                if (System.currentTimeMillis() - lastAction < COOLDOWN) {
                    e.getWhoClicked().sendMessage(Component.text("§cMerci d'attendre un peu avant de modifier le nom."));
                    return;
                }
                lastAction = System.currentTimeMillis();
                e.getWhoClicked().sendMessage(Component.text("§cEn développement..."));
            });

            setItem(11, ItemBuilder.copyOf(new ItemStack(Material.BOOK))
                    .name("§6Description §8| §7(clic gauche)")
                    .lore("§7" + warp.getDescription()).build(), e -> {
                if (!island.isLoaded()) {
                    e.getWhoClicked().sendMessage(Component.text("§cL'île n'est pas chargée ici."));
                    return;
                }

                if (System.currentTimeMillis() - lastAction < COOLDOWN) {
                    e.getWhoClicked().sendMessage(Component.text("§cMerci d'attendre un peu avant de modifier la description."));
                    return;
                }
                lastAction = System.currentTimeMillis();
                e.getWhoClicked().sendMessage(Component.text("§cEn développement..."));
            });

            setItem(12, ItemBuilder.copyOf(new ItemStack(Material.PAPER))
                    .name("§6Catégories §8| §7(clic gauche)")
                    .lore("§7" + warp.getCategories()).build(), e -> {
                if (!island.isLoaded()) {
                    e.getWhoClicked().sendMessage(Component.text("§cL'île n'est pas chargée ici."));
                    return;
                }

                if (System.currentTimeMillis() - lastAction < COOLDOWN) {
                    e.getWhoClicked().sendMessage(Component.text("§cEn développement..."));
                    return;
                }
                lastAction = System.currentTimeMillis();
                e.getWhoClicked().sendMessage(Component.text("§cEn développement..."));
            });

            setItem(13, ItemBuilder.copyOf(new ItemStack(Material.COMPASS))
                    .name("§6Location §8| §7(clic gauche pour définir sur vous)")
                    .lore("§7" + LocationTranslator.readableLocation(warp.getLocation())).build(), e -> {
                if (e.getWhoClicked().getWorld() != IslandsManager.INSTANCE.getIslandWorld(island.getIslandUUID())) {
                    e.getWhoClicked().sendMessage(Component.text("§cVous devez être sur l'île pour définir la localisation."));
                    return;
                }
                if (!island.isLoaded()) {
                    e.getWhoClicked().sendMessage(Component.text("§cL'île n'est pas chargée ici."));
                    return;
                }

                if (System.currentTimeMillis() - lastAction < COOLDOWN) {
                    e.getWhoClicked().sendMessage(Component.text("§cVeuillez attendre un peu avant de définir la localisation."));
                    return;
                }
                lastAction = System.currentTimeMillis();
                warp.setLocation(e.getWhoClicked().getLocation());
                e.getWhoClicked().sendMessage(Component.text("§aLocation définie sur vous."));
                gotUpdate = true;
                update(island, warp);
            });

            setItem(14, ItemBuilder.copyOf(new ItemStack(Material.REDSTONE_TORCH))
                    .name("§6Activation §8| §7(clic gauche)")
                    .lore("§7" + (warp.isActivated() ? "Activé" : "Désactivé")).build(), e -> {
                if (!island.isLoaded()) {
                    e.getWhoClicked().sendMessage(Component.text("§cL'île n'est pas chargée ici."));
                    return;
                }

                //check if it can be activated
                if (warp.getLocation() == null) {
                    e.getWhoClicked().sendMessage(Component.text("§cVous devez définir une localisation avant d'activer le warp."));
                    return;
                }
                if (warp.getCategories().isEmpty()) {
                    e.getWhoClicked().sendMessage(Component.text("§cVous devez définir au moins une catégorie avant d'activer le warp."));
                    return;
                }
                if (warp.getName() == null || warp.getName().isEmpty()) {
                    e.getWhoClicked().sendMessage(Component.text("§cVous devez définir un nom avant d'activer le warp."));
                    return;
                }

                if (System.currentTimeMillis() - lastAction < COOLDOWN) {
                    e.getWhoClicked().sendMessage(Component.text("§cMerci d'attendre un peu avant d'activer/désactiver le warp."));
                    return;
                }
                lastAction = System.currentTimeMillis();

                warp.setActivated(!warp.isActivated());
                e.getWhoClicked().sendMessage(Component.text("§aWarp " + (warp.isActivated() ? "activé" : "désactivé") + "."));
                gotUpdate = true;
                update(island, warp);
            });

            setItem(16, ItemBuilder.copyOf(new ItemStack(Material.GOLD_INGOT)).name(
                    "§6Mise en avant §8| §7(clic gauche)").lore("§7" + (warp.getForwardedWarp() > System.currentTimeMillis()
                    ? "Oui" : "Non")).build(), e -> {
                if (!island.isLoaded()) {
                    e.getWhoClicked().sendMessage(Component.text("§cL'île n'est pas chargée ici."));
                    return;
                }

                if (System.currentTimeMillis() - lastAction < COOLDOWN) {
                    e.getWhoClicked().sendMessage(Component.text("§cMerci d'attendre un peu avant de mettre en avant le warp."));
                    return;
                }
                lastAction = System.currentTimeMillis();

                e.getWhoClicked().sendMessage(Component.text("§cEn développement..."));
            });
        } else {
            setItem(13, ItemBuilder.copyOf(new ItemStack(Material.COMPASS))
                    .name("§6Créer un warp §8| §7(clic gauche pour définir sur vous)")
                    .lore("§7L'édition du nom, la description, les catégories,", "§7la mise en avant et l'activation du warp",
                            "§7seront disponible après cette étape").build(), e -> {
                if (!island.isLoaded()) {
                    e.getWhoClicked().sendMessage(Component.text("§cL'île n'est pas chargée ici."));
                    return;
                }

                if (e.getWhoClicked().getWorld() != IslandsManager.INSTANCE.getIslandWorld(island.getIslandUUID())) {
                    e.getWhoClicked().sendMessage(Component.text("§cVous devez être sur l'île pour définir la localisation."));
                    return;
                }

                if (System.currentTimeMillis() - lastAction < COOLDOWN) {
                    e.getWhoClicked().sendMessage(Component.text("§cVeuillez attendre un peu avant de créer un nouveau warp."));
                    return;
                }
                lastAction = System.currentTimeMillis();

                IslandWarp newWarp = new IslandWarp(island.getIslandUUID(), e.getWhoClicked().getName(),
                        e.getWhoClicked().getLocation(), true);
                IslandsWarpManager.INSTANCE.updateWarpWithId(newWarp.getUuid(), newWarp);
                e.getWhoClicked().sendMessage(Component.text("§aWarp créé."));
                gotUpdate = true;
                new IslandWarpInv(island, newWarp).open((Player) e.getWhoClicked());
            });
        }
    }
}
