package nl.itz_kiwisap.skyfarers.islandtop;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import nl.itz_kiwisap.skyfarers.islandtop.listeners.CommandListener;
import nl.itz_kiwisap.skyfarers.islandtop.listeners.InventoryListener;

public class Main extends JavaPlugin {
	
	private static Main instance;

	public void onEnable() {
		instance = this;
		
		Bukkit.getPluginManager().registerEvents(new CommandListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
		
		Bukkit.getConsoleSender().sendMessage("§a§m+--------------------------------+");
		Bukkit.getConsoleSender().sendMessage("§5§lSkyfarers > §7Island top loaded!");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§5SkyfarersIslandTop v1.0.0 §7by Itz_KiwiSap");
		Bukkit.getConsoleSender().sendMessage("§a§m+--------------------------------+");
	}
	
	public void onDisable() {
		instance = null;
	}
	
	public static Main getInstance() { return instance; }
}
