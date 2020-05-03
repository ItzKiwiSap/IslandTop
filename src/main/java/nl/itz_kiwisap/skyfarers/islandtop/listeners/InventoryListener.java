package nl.itz_kiwisap.skyfarers.islandtop.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.wasteofplastic.askyblock.ASkyBlock;
import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.Island;

import nl.itz_kiwisap.skyfarers.islandtop.Main;
import nl.itz_kiwisap.skyfarers.islandtop.menu.IslandTopGUI;
import nl.itz_kiwisap.skyfarers.islandtop.menu.ItemsGUI;
import nl.itz_kiwisap.skyfarers.islandtop.objects.BlockAmount;
import nl.itz_kiwisap.skyfarers.islandtop.objects.SpawnerAmount;

public class InventoryListener implements Listener {

	private ItemsGUI gui = new ItemsGUI();
	private IslandTopGUI isgui = new IslandTopGUI();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		if(e.getInventory().getTitle().equals("§5§lSkyfarers > §7Island Top")) {
			e.setCancelled(true);
			
			if(e.getCurrentItem() == null) return;
			if(e.getCurrentItem().getItemMeta() == null) return;
			if(e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
			
			if(e.isLeftClick() && e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§5§l(!) Island:")) {
				String[] words = e.getCurrentItem().getItemMeta().getDisplayName().split(" ");
				String playername = words[2].substring(4, words[2].length() - 2);
				
				OfflinePlayer player = Bukkit.getOfflinePlayer(playername);
				Island island = ASkyBlock.getPlugin().getGrid().getIsland(player.getUniqueId());
				
				if(ASkyBlockAPI.getInstance().listWarps().contains(player.getUniqueId())) {
					p.closeInventory();
					p.performCommand("island warp " + player.getName());
					p.sendMessage("§5§lSkyfarers > §7Teleporting to §b" + player.getName() + "'s §7warp...");
					return;
				}
				
				if(island.getMembers() == null) {
					p.closeInventory();
					p.sendMessage("§5§lSkyfarers > §cNo member of that island has a warp!");
					return;
				}
				
				for(UUID uuid : island.getMembers()) {
					OfflinePlayer member = Bukkit.getOfflinePlayer(uuid);
					
					if(ASkyBlockAPI.getInstance().listWarps().contains(member.getUniqueId())) {
						p.closeInventory();
						p.performCommand("island warp " + member.getName());
						p.sendMessage("§5§lSkyfarers > §7Teleporting to §b" + member.getName() + "'s §7warp...");
						return;
					}
					continue;
				}
				p.closeInventory();
				p.sendMessage("§5§lSkyfarers > §cNo member of that island has a warp!");
				return;
			} else if(e.isRightClick() && e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§5§l(!) Island:")) {
				String[] words = e.getCurrentItem().getItemMeta().getDisplayName().split(" ");
				String playername = words[2].substring(4, words[2].length() - 2);
				
				OfflinePlayer player = Bukkit.getOfflinePlayer(playername);
				Island island = ASkyBlock.getPlugin().getGrid().getIsland(player.getUniqueId());
				
				gui.itemsgui(p, island, new BlockAmount(island));
			}
		} else if(e.getInventory().getTitle().equals("§5§lSkyfarers > §7Items of island")) {
			e.setCancelled(true);
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§5§lSpawners §7(x" + gui.getBlockAmount().getSpawnerAmount() + ")")) {
				gui.spawnersgui(p, gui.getIsland(), new SpawnerAmount(gui.getIsland()), gui.getBlockAmount());
			}
		} else if(e.getInventory().getTitle().equals("§5§lSkyfarers > §7Spawners")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		
		if(e.getInventory().getTitle().equals("§5§lSkyfarers > §7Items of island")) {
			new BukkitRunnable() {
                @Override
                public void run () {
                	if(e.getPlayer().getOpenInventory().getTopInventory().getTitle().equals("§5§lSkyfarers > §7Spawners")) this.cancel();
                	else isgui.islandtopgui(p);
                }
            }.runTaskLater((Plugin) Main.getInstance(), 1);    
		}
		
		if(e.getInventory().getTitle().equals("§5§lSkyfarers > §7Spawners")) {
			new BukkitRunnable() {
                @Override
                public void run () {
                	gui.itemsgui(p, gui.getIsland(), gui.getBlockAmount());
                }
            }.runTaskLater((Plugin) Main.getInstance(), 1);   
		}
	}
}
