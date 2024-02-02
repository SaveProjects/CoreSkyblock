package fr.farmeurimmo.skylyblock.purpur.scoreboard;

import fr.farmeurimmo.skylyblock.common.SkyblockUser;
import fr.farmeurimmo.skylyblock.common.SkyblockUsersManager;
import fr.farmeurimmo.skylyblock.common.islands.Island;
import fr.farmeurimmo.skylyblock.purpur.IslandsManager;
import fr.farmeurimmo.skylyblock.purpur.SkylyBlock;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardManager {

    public static ScoreboardManager INSTANCE;
    private final Map<UUID, FastBoard> boards = new HashMap<>();
    private final Map<UUID, Integer> boardNumber = new HashMap<>();

    public ScoreboardManager() {
        INSTANCE = this;

        Bukkit.getScheduler().runTaskTimerAsynchronously(SkylyBlock.INSTANCE, this::updateClock, 0, 20);
    }

    public void addPlayer(Player p) {
        FastBoard board = new FastBoard(p);
        board.updateTitle("§6§lSkylyBlock");
        boards.put(p.getUniqueId(), board);
        boardNumber.put(p.getUniqueId(), 0);
    }

    public void updateBoard(UUID uuid) {
        FastBoard board = boards.get(uuid);
        int number = 0;
        if (boardNumber.containsKey(uuid)) number = boardNumber.get(uuid);
        Player p = Bukkit.getPlayer(uuid);
        if (p == null) return;
        SkyblockUser user = SkyblockUsersManager.INSTANCE.getCachedUsers().get(p.getUniqueId());
        Island island = IslandsManager.INSTANCE.getIslandOf(p.getUniqueId());
        if (user == null) {
            board.updateTitle("§a§lChargement...");
            return;
        }
        ArrayList<String> islandLines = new ArrayList<>();
        if (island != null) {
            islandLines.add("§6§lVotre île §8[§7#" + "EN DEV" + "§8]");
            islandLines.add("§8┃ §7Rang: §4" + island.getMembers().get(p.getUniqueId()).getName());
            islandLines.add("§8┃ §7Membres: §e" + island.getMembers().size());
            islandLines.add("§8┃ §7Cristaux: §d" + NumberFormat.getInstance().format(island.getBankCrystals()));
            islandLines.add("§8┃ §7Niveau: §b" + NumberFormat.getInstance().format(island.getLevel()));
            islandLines.add("§8┃ §8[§b||||||||||||||||||||§7]");
        } else {
            islandLines.add("§6§lVous n'avez pas d'île");
            islandLines.add("§8┃ §7/is create pour en créer une");
            islandLines.add("§8┃ §7/is join <joueur> pour rejoindre une");
            islandLines.add("§8┃ §7île avec une invitation");
        }
        if (number == 0) {
            board.updateTitle("§4»§c» §c§lSKYBLOCK §c«§4«");
            board.updateLines(
                    "",
                    "§6§lProfil",
                    "§8┃ §7Pseudo: §f" + p.getName(),
                    "§8┃ §7Argent: §e" + NumberFormat.getInstance().format(user.getMoney()),
                    "§8┃ §7Grade: §c????",
                    "",
                    islandLines.get(0),
                    islandLines.get(1),
                    islandLines.get(2),
                    islandLines.get(3),
                    islandLines.stream().skip(4).findFirst().orElse(""),
                    islandLines.stream().skip(5).findFirst().orElse(""),
                    islandLines.stream().skip(6).findFirst().orElse(""),
                    "",
                    "§f» §c§lplay.skyly.fr"
            );
        } else if (number == 1) {
            board.updateTitle("§6§lSkylyBlock");
        }
    }

    public void updateClock() {
        ArrayList<UUID> toRemove = new ArrayList<>();
        for (UUID uuid : boards.keySet()) {
            if (Bukkit.getPlayer(uuid) == null) {
                toRemove.add(uuid);
                continue;
            }
            updateBoard(uuid);
        }
        for (UUID uuid : toRemove) {
            boards.remove(uuid);
            boardNumber.remove(uuid);
        }
    }
}
