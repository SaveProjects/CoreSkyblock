package fr.farmeurimmo.mineblock.purpur.listeners;

import fr.farmeurimmo.mineblock.purpur.events.ChatReactionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatReactionListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (ChatReactionManager.INSTANCE.isRunning) {
            ChatReactionManager.INSTANCE.end(e.getPlayer(), e.getMessage());
        }
    }
}
