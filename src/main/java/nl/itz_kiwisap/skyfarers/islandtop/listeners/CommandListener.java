package nl.itz_kiwisap.skyfarers.islandtop.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

import nl.itz_kiwisap.skyfarers.islandtop.menu.IslandTopGUI;

public class CommandListener implements Listener {
	
	private IslandTopGUI gui = new IslandTopGUI();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent e) throws IllegalAccessException {
		Player p = e.getPlayer();
		String message = e.getMessage();
		String[] args = message.split(" ");
		
		if(args[0].equalsIgnoreCase("/is") || args[0].equalsIgnoreCase("/island")) {
			if(args.length == 2) {
				if(args[1].equalsIgnoreCase("top")) {
					e.setCancelled(true);
					
					for(UUID owner : ASkyBlockAPI.getInstance().getOwnedIslands().keySet()) {
						ASkyBlockAPI.getInstance().calculateIslandLevel(owner);
					}
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "asadmin topten");
					
					p.sendMessage("§5§lSkyfarers > §7Opening island top...");
					gui.islandtopgui(p);
				}
			}
		}
	}
}
