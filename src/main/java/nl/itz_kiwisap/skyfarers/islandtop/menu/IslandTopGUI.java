package nl.itz_kiwisap.skyfarers.islandtop.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.Island;

public class IslandTopGUI {

	private static IslandTopGUI instance;
	public IslandTopGUI() { instance = this; }
	private HashMap<Integer, UUID> top5 = new HashMap<Integer, UUID>();

	@SuppressWarnings("deprecation")
	public void islandtopgui(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "§5§lSkyfarers > §7Island Top");

		ValueComparator bvc =  new ValueComparator(ASkyBlockAPI.getInstance().getTopTen());
		TreeMap<UUID, Integer> sorted_map = new TreeMap<UUID, Integer>(bvc);
		sorted_map.putAll(ASkyBlockAPI.getInstance().getTopTen());
		
		if(!top5.isEmpty()) {
			top5.clear();
		}

		rankloop:
		for(int rank = 1; rank < (sorted_map.size() + 1); rank++) {
			if(rank == 6) break;
			for(Entry<UUID, Integer> map : sorted_map.entrySet()) {
				if(top5.containsValue(map.getKey())) continue;
				else top5.put(rank, map.getKey());
				continue rankloop;
			}
		}

		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta glassm = glass.getItemMeta();
		glassm.setDisplayName("§7");
		glass.setItemMeta(glassm);

		for(int slot = 0; slot < 27; slot++) {
			if(slot >= 11 && slot <= 15) {
				continue;
			}
			else inv.setItem(slot, glass);
		}

		for(int i = 1; i < (top5.size() + 1); i++) {
			Island is = ASkyBlockAPI.getInstance().getIslandOwnedBy(top5.get((i)));
			OfflinePlayer player = Bukkit.getOfflinePlayer(is.getOwner());

			ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta itemm = (SkullMeta) item.getItemMeta();
			itemm.setOwner(player.getName());
			itemm.setDisplayName("§5§l(!) Island: §7§n" + player.getName() + "§r §7(#" + i + ")");
			List<String> iteml = new ArrayList<>();
			iteml.add("§5§lLevel: §7" + ASkyBlockAPI.getInstance().getIslandLevel(is.getOwner()));
			iteml.add("");
			iteml.add("§5§lMembers:");
			if(is.getMembers() != null) {
				for(UUID uid : is.getMembers()) {
					OfflinePlayer member = Bukkit.getOfflinePlayer(uid);
					if(is.getMembers().size() < 2) iteml.add("§7- §fNone");
					if(iteml.contains(member.getName()) || member.getName().equals(player.getName())) continue;
					
					if(member != null) {
						iteml.add("§7- §f" + member.getName());
						continue;
					}
				}
			} else iteml.add("§7- §fNone");
			iteml.add("");
			iteml.add("§7Right-Click §8to see items");
			iteml.add("§7Left-Click §8to tp to warp (if there is a warp)");
			itemm.setLore(iteml);
			item.setItemMeta(itemm);

			inv.addItem(item);
			continue;
		}
		
		for(int i = 11; i < 16; i++) {
			ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta itemm = (SkullMeta)  item.getItemMeta();
			itemm.setOwner("mhf_question");
			itemm.setDisplayName("§5§l(?) Island: §7§nNobody§r §7(#" + (i - 10) + ")");
			itemm.setLore(Arrays.asList("§5§lLevel: §7?", "", "§5§lMembers:", "§7- §fNone"));
			item.setItemMeta(itemm);
			
			if(inv.getItem(i) != null) continue;
			else inv.setItem(i, item);
		}

		p.openInventory(inv);
	}

	public static IslandTopGUI getInstance() { return instance; }
	public Map<Integer, UUID> getTopFive() { return top5; }

	class ValueComparator implements Comparator<UUID> {

		Map<UUID, Integer> base;
		public ValueComparator(Map<UUID, Integer> base) {
			this.base = base;
		}

		public int compare(UUID a, UUID b) {
			if (base.get(a) >= base.get(b)) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
