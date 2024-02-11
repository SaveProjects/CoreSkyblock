package fr.farmeurimmo.mineblock.purpur.islands;

import fr.farmeurimmo.mineblock.common.islands.Island;
import fr.farmeurimmo.mineblock.purpur.islands.invs.IslandInv;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class IslandCmd implements CommandExecutor {

    private static final Component USAGE_NO_IS = Component.text("§cUtilisation: /is create OU /is join <joueur> " +
            "tout en possédant une invitation.");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cVous devez être un joueur pour exécuter cette commande.");
            return false;
        }
        Island island = IslandsManager.INSTANCE.getIslandOf(p.getUniqueId());
        if (island == null) {
            if (args.length == 0) {
                p.sendMessage(USAGE_NO_IS);
                return false;
            }
            if (args[0].equalsIgnoreCase("create")) {
                //JedisManager.INSTANCE.publishToRedis("MineBlock", "island:create:" + p.getUniqueId());
                IslandsManager.INSTANCE.createIsland(p.getUniqueId());
            /*if (MineBlock.INSTANCE.getServerType() != ServerType.SKYBLOCK_ISLAND) {
                return false;
            }*/
                return false;
            }
            p.sendMessage(USAGE_NO_IS);
            return false;
        }
        if (args.length == 0) {
            new IslandInv(island).open(p);
            return false;
        }
        if (args[0].equalsIgnoreCase("go")) {
            IslandsManager.INSTANCE.teleportToIsland(island, p);
            return false;
        }
        return false;
    }
}
