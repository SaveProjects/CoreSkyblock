package fr.farmeurimmo.mineblock.purpur.featherfly;

import fr.farmeurimmo.mineblock.common.skyblockusers.SkyblockUser;
import fr.farmeurimmo.mineblock.common.skyblockusers.SkyblockUsersManager;
import fr.farmeurimmo.mineblock.purpur.islands.IslandsManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class FeatherFlyListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() != null && e.getItem().getType().equals(Material.FEATHER)) {
            if (e.getItem().getItemMeta().getItemFlags().contains(ItemFlag.HIDE_UNBREAKABLE)) {
                String sample = ChatColor.stripColor(e.getItem().getDisplayName());
                char[] chars = sample.toCharArray();
                StringBuilder sb = new StringBuilder();
                for (char c : chars) {
                    if (Character.isDigit(c)) {
                        sb.append(c);
                    }
                }
                if (!sb.isEmpty()) {
                    e.setUseItemInHand(org.bukkit.event.Event.Result.DENY);
                    int count = e.getItem().getAmount();
                    String time = sb.toString();
                    try {
                        int timeInt = Integer.parseInt(time);
                        if (timeInt <= 0) return;
                        if (!FeatherFlyManager.INSTANCE.enableFly(p, timeInt)) {
                            p.sendMessage(Component.text("§aErreur lors de l'activation du fly ! Code PLUMEFLY-0"));
                            return;
                        }
                    } catch (NumberFormatException ignored) {
                        p.sendMessage(Component.text("§aErreur lors de l'activation du fly ! Code PLUMEFLY-1"));
                        return;
                    }
                    ItemStack aaa = e.getItem();
                    aaa.setAmount(count - 1);
                    p.getInventory().setItemInMainHand(aaa);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (IslandsManager.INSTANCE.isAnIsland(p.getWorld())) {
            SkyblockUser user = SkyblockUsersManager.INSTANCE.getCachedUsers().get(p.getUniqueId());
            if (user != null) {
                if (user.getFlyTime() > 0) {
                    FeatherFlyManager.INSTANCE.enableFly(p, 0);
                }
            }
        }
    }
}
