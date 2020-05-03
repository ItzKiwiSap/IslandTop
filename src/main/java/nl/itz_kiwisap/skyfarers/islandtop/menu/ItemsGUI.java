package nl.itz_kiwisap.skyfarers.islandtop.menu;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.wasteofplastic.askyblock.ASkyBlock;
import com.wasteofplastic.askyblock.Island;

import nl.itz_kiwisap.skyfarers.islandtop.objects.BlockAmount;
import nl.itz_kiwisap.skyfarers.islandtop.objects.SpawnerAmount;

public class ItemsGUI {

	private static ItemsGUI instance;
	public ItemsGUI() { instance = this; }
	
	private Island island;
	private BlockAmount bamount;
	private SpawnerAmount samount;

	public void itemsgui(Player p, Island island, BlockAmount amount) {
		Inventory inv = Bukkit.createInventory(null, 9, "§5§lSkyfarers > §7Items of island");
		OfflinePlayer owner = Bukkit.getOfflinePlayer(island.getOwner());
		this.island = island;
		this.bamount = amount;

		amount.reset();
		getBlockAmounts(ASkyBlock.getPlugin().getGrid().getIsland(owner.getUniqueId()), amount);

		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta glassm = glass.getItemMeta();
		glassm.setDisplayName("§7");
		glass.setItemMeta(glassm);

		ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, (amount.getSpawnerAmount() <= 64) ? amount.getSpawnerAmount() : 64);
		ItemMeta spawnerm = spawner.getItemMeta();
		spawnerm.setDisplayName("§5§lSpawners §7(x" + amount.getSpawnerAmount() + ")");
		spawnerm.setLore(Arrays.asList("", "§7Click §8to see amount of each spawner"));
		spawner.setItemMeta(spawnerm);

		ItemStack beacon = new ItemStack(Material.BEACON, (amount.getBeaconAmount() <= 64) ? amount.getBeaconAmount() : 64);
		ItemMeta beaconm = beacon.getItemMeta();
		beaconm.setDisplayName("§5§lBeacons §7(x" + amount.getBeaconAmount() + ")");
		beacon.setItemMeta(beaconm);

		ItemStack dragon_egg = new ItemStack(Material.DRAGON_EGG, (amount.getDragoneggAmount() <= 64) ? amount.getDragoneggAmount() : 64);
		ItemMeta dragon_eggm = dragon_egg.getItemMeta();
		dragon_eggm.setDisplayName("§5§lDragon Eggs §7(x" + amount.getDragoneggAmount() + ")");
		dragon_egg.setItemMeta(dragon_eggm);

		ItemStack diamond = new ItemStack(Material.DIAMOND_BLOCK, (amount.getDiamondAmount() <= 64) ? amount.getDiamondAmount() : 64);
		ItemMeta diamondm = diamond.getItemMeta();
		diamondm.setDisplayName("§5§lDiamond Blocks §7(x" + amount.getDiamondAmount() + ")");
		diamond.setItemMeta(diamondm);

		ItemStack emerald = new ItemStack(Material.EMERALD_BLOCK, (amount.getEmeraldAmount() <= 64) ? amount.getEmeraldAmount() : 64);
		ItemMeta emeraldm = emerald.getItemMeta();
		emeraldm.setDisplayName("§5§lEmerald Blocks §7(x" + amount.getEmeraldAmount() + ")");
		emerald.setItemMeta(emeraldm);

		ItemStack gold = new ItemStack(Material.GOLD_BLOCK, (amount.getGoldAmount() <= 64) ? amount.getGoldAmount() : 64);
		ItemMeta goldm = gold.getItemMeta();
		goldm.setDisplayName("§5§lGold Blocks §7(x" + amount.getGoldAmount() + ")");
		gold.setItemMeta(goldm);

		ItemStack iron = new ItemStack(Material.IRON_BLOCK, (amount.getIronAmount() <= 64) ? amount.getIronAmount() : 64);
		ItemMeta ironm = iron.getItemMeta();
		ironm.setDisplayName("§5§lIron Blocks §7(x" + amount.getIronAmount() + ")");
		iron.setItemMeta(ironm);

		inv.setItem(0, glass);
		inv.setItem(1, spawner);
		inv.setItem(2, beacon);
		inv.setItem(3, dragon_egg);
		inv.setItem(4, diamond);
		inv.setItem(5, emerald);
		inv.setItem(6, gold);
		inv.setItem(7, iron);
		inv.setItem(8, glass);

		p.openInventory(inv);
	}
	
	public void spawnersgui(Player p, Island island, SpawnerAmount amount, BlockAmount bamount) {
		Inventory inv = Bukkit.createInventory(null, 54, "§5§lSkyfarers > §7Spawners");
		OfflinePlayer owner = Bukkit.getOfflinePlayer(island.getOwner());
		this.island = island;
		this.bamount = bamount;
		this.samount = amount;
		
		amount.reset();
		getSpawnerAmounts(island, amount);
		
		ItemStack item = new ItemStack(Material.MOB_SPAWNER, (bamount.getSpawnerAmount() <= 64) ? bamount.getSpawnerAmount() : 64);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName("§5§l(!) Spawners > §7§n" + owner.getName() + "'s§r §7island");
		itemm.setLore(Arrays.asList("§5Total Amount: §7" + bamount.getSpawnerAmount()));
		item.setItemMeta(itemm);
		
		ItemStack tier1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta tier1m = (SkullMeta) tier1.getItemMeta();
		tier1m.setOwner("MFH_Spawner");
		tier1m.setDisplayName("§5§lTier 1");
		tier1m.setLore(Arrays.asList("§7Spawner in the category tier 1.", "", "§5Total Tier Amount: §7" + amount.getTierOne()));
		tier1.setItemMeta(tier1m);
		
		ItemStack tier2 = new ItemStack(Material.SKULL_ITEM, 2, (short) 3);
		SkullMeta tier2m = (SkullMeta) tier2.getItemMeta();
		tier2m.setOwner("MFH_Spawner");
		tier2m.setDisplayName("§5§lTier 2");
		tier2m.setLore(Arrays.asList("§7Spawner in the category tier 2.", "", "§5Total Tier Amount: §7" + amount.getTierTwo()));
		tier2.setItemMeta(tier2m);
		
		ItemStack tier3 = new ItemStack(Material.SKULL_ITEM, 3, (short) 3);
		SkullMeta tier3m = (SkullMeta) tier3.getItemMeta();
		tier3m.setOwner("MFH_Spawner");
		tier3m.setDisplayName("§5§lTier 3");
		tier3m.setLore(Arrays.asList("§7Spawner in the category tier 3.", "", "§5Total Tier Amount: §7" + amount.getTierThree()));
		tier3.setItemMeta(tier3m);
		
		ItemStack tier4 = new ItemStack(Material.SKULL_ITEM, 4, (short) 3);
		SkullMeta tier4m = (SkullMeta) tier4.getItemMeta();
		tier4m.setOwner("MFH_Spawner");
		tier4m.setDisplayName("§5§lTier 4");
		tier4m.setLore(Arrays.asList("§7Spawner in the category tier 4.", "", "§5Total Tier Amount: §7" + amount.getTierFour()));
		tier4.setItemMeta(tier4m);
		
		ItemStack cow = new ItemStack(Material.SKULL_ITEM, (amount.getCows() <= 64) ? amount.getCows() : 64, (short) 3);
		SkullMeta cowm = (SkullMeta) cow.getItemMeta();
		cowm.setOwner("MHF_Cow");
		cowm.setDisplayName("§5§lCows §7(x" + amount.getCows() + ")");
		cow.setItemMeta(cowm);
		
		ItemStack sheep = new ItemStack(Material.SKULL_ITEM, (amount.getSheeps() <= 64) ? amount.getSheeps() : 64, (short) 3);
		SkullMeta sheepm = (SkullMeta) sheep.getItemMeta();
		sheepm.setOwner("MHF_Sheep");
		sheepm.setDisplayName("§5§lSheeps §7(x" + amount.getSheeps() + ")");
		sheep.setItemMeta(sheepm);
		
		ItemStack chicken = new ItemStack(Material.SKULL_ITEM, (amount.getChickens() <= 64) ? amount.getChickens() : 64, (short) 3);
		SkullMeta chickenm = (SkullMeta) chicken.getItemMeta();
		chickenm.setOwner("MHF_Chicken");
		chickenm.setDisplayName("§5§lChickens §7(x" + amount.getChickens() + ")");
		chicken.setItemMeta(chickenm);
		
		ItemStack pig = new ItemStack(Material.SKULL_ITEM, (amount.getPigs() <= 64) ? amount.getPigs() : 64, (short) 3);
		SkullMeta pigm = (SkullMeta) pig.getItemMeta();
		pigm.setOwner("MHF_Pig");
		pigm.setDisplayName("§5§lPigs §7(x" + amount.getPigs() + ")");
		pig.setItemMeta(pigm);
		
		ItemStack creeper = new ItemStack(Material.SKULL_ITEM, (amount.getCreepers() <= 64) ? amount.getCreepers() : 64, (short) 3);
		SkullMeta creeperm = (SkullMeta) creeper.getItemMeta();
		creeperm.setOwner("MHF_Creeper");
		creeperm.setDisplayName("§5§lCreepers §7(x" + amount.getCreepers() + ")");
		creeper.setItemMeta(creeperm);
		
		ItemStack spider = new ItemStack(Material.SKULL_ITEM, (amount.getSpiders() <= 64) ? amount.getSpiders() : 64, (short) 3);
		SkullMeta spiderm = (SkullMeta) spider.getItemMeta();
		spiderm.setOwner("MHF_Spider");
		spiderm.setDisplayName("§5§lSpiders §7(x" + amount.getSpiders() + ")");
		spider.setItemMeta(spiderm);
		
		ItemStack zombie = new ItemStack(Material.SKULL_ITEM, (amount.getZombies() <= 64) ? amount.getZombies() : 64, (short) 3);
		SkullMeta zombiem = (SkullMeta) zombie.getItemMeta();
		zombiem.setOwner("MHF_Zombie");
		zombiem.setDisplayName("§5§lZombies §7(x" + amount.getZombies() + ")");
		zombie.setItemMeta(zombiem);
		
		ItemStack skeleton = new ItemStack(Material.SKULL_ITEM, (amount.getSkeletons() <= 64) ? amount.getSkeletons() : 64, (short) 3);
		SkullMeta skeletonm = (SkullMeta) skeleton.getItemMeta();
		skeletonm.setOwner("MHF_Skeleton");
		skeletonm.setDisplayName("§5§lSkeletons §7(x" + amount.getSkeletons() + ")");
		skeleton.setItemMeta(skeletonm);
		
		ItemStack blaze = new ItemStack(Material.SKULL_ITEM, (amount.getBlazes() <= 64) ? amount.getBlazes() : 64, (short) 3);
		SkullMeta blazem = (SkullMeta) blaze.getItemMeta();
		blazem.setOwner("MHF_Blaze");
		blazem.setDisplayName("§5§lBlazes §7(x" + amount.getBlazes() + ")");
		blaze.setItemMeta(blazem);
		
		ItemStack enderman = new ItemStack(Material.SKULL_ITEM, (amount.getEndermans() <= 64) ? amount.getEndermans() : 64, (short) 3);
		SkullMeta endermanm = (SkullMeta) enderman.getItemMeta();
		endermanm.setOwner("MHF_Enderman");
		endermanm.setDisplayName("§5§lEndermans §7(x" + amount.getEndermans() + ")");
		enderman.setItemMeta(endermanm);
		
		ItemStack slime = new ItemStack(Material.SKULL_ITEM, (amount.getSlimes() <= 64) ? amount.getSlimes() : 64, (short) 3);
		SkullMeta slimem = (SkullMeta) slime.getItemMeta();
		slimem.setOwner("MHF_Slime");
		slimem.setDisplayName("§5§lSlimes §7(x" + amount.getSlimes() + ")");
		slime.setItemMeta(slimem);
		
		ItemStack villager = new ItemStack(Material.SKULL_ITEM, (amount.getVillagers() <= 64) ? amount.getVillagers() : 64, (short) 3);
		SkullMeta villagerm = (SkullMeta) villager.getItemMeta();
		villagerm.setOwner("MHF_Villager");
		villagerm.setDisplayName("§5§lVillagers §7(x" + amount.getVillagers() + ")");
		villager.setItemMeta(villagerm);
		
		ItemStack magmacube = new ItemStack(Material.SKULL_ITEM, (amount.getMagmaCubes() <= 64) ? amount.getMagmaCubes() : 64, (short) 3);
		SkullMeta magmacubem = (SkullMeta) magmacube.getItemMeta();
		magmacubem.setOwner("MagmaCube");
		magmacubem.setDisplayName("§5§lMagma Cubes §7(x" + amount.getMagmaCubes() + ")");
		magmacube.setItemMeta(magmacubem);
		
		ItemStack golem = new ItemStack(Material.SKULL_ITEM, (amount.getIronGolems() <= 64) ? amount.getIronGolems() : 64, (short) 3);
		SkullMeta golemm = (SkullMeta) golem.getItemMeta();
		golemm.setOwner("MHF_Golem");
		golemm.setDisplayName("§5§lIron Golems §7(x" + amount.getIronGolems() + ")");
		golem.setItemMeta(golemm);
		
		ItemStack pigzombie = new ItemStack(Material.SKULL_ITEM, (amount.getZombiePigMans() <= 64) ? amount.getZombiePigMans() : 64, (short) 3);
		SkullMeta pigzombiem = (SkullMeta) pigzombie.getItemMeta();
		pigzombiem.setOwner("MHF_PigZombie");
		pigzombiem.setDisplayName("§5§lZombie Pigmans §7(x" + amount.getZombiePigMans() + ")");
		pigzombie.setItemMeta(pigzombiem);
		
		inv.setItem(2, item);
		inv.setItem(3, tier1);
		inv.setItem(4, tier2);
		inv.setItem(5, tier3);
		inv.setItem(6, tier4);
		inv.setItem(21, cow);
		inv.setItem(22, creeper);
		inv.setItem(23, blaze);
		inv.setItem(24, villager);
		inv.setItem(30, sheep);
		inv.setItem(31, spider);
		inv.setItem(32, enderman);
		inv.setItem(33, magmacube);
		inv.setItem(39, chicken);
		inv.setItem(40, zombie);
		inv.setItem(41, slime);
		inv.setItem(42, golem);
		inv.setItem(48, pig);
		inv.setItem(49, skeleton);
		inv.setItem(51, pigzombie);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 10);
		ItemMeta glassm = glass.getItemMeta();
		glassm.setDisplayName("§7");
		glass.setItemMeta(glassm);
		
		ItemStack glass2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta glass2m = glass2.getItemMeta();
		glass2m.setDisplayName("§7");
		glass2.setItemMeta(glass2m);
		
		for(int i = 9; i < 48; i++) {
			if(i >= 9 && i <= 17) inv.setItem(i, glass);
			else if(i == 20 || i == 29 || i == 38 || i == 47) inv.setItem(i, glass);
			else continue;
		}
		
		for(int i = 18; i < 54; i++) {
			if(i == 50) continue;
			if(inv.getItem(i) == null) inv.setItem(i, glass2);
			else continue;
		}
		
		p.openInventory(inv);
	}

	public static ItemsGUI getInstance() { return instance; }

	private void getBlockAmounts(Island island, BlockAmount amount) {
		for(int x = island.getMinProtectedX(); x <= (island.getMinProtectedX() + island.getProtectionSize() - 1); x++) {
			for(int z = island.getMinProtectedZ(); z <= (island.getMinProtectedZ() + island.getProtectionSize() - 1); z++) {
				for(int y = 0; y < 255; y++) {
					Block b = island.getCenter().getWorld().getBlockAt(x, y, z);
					if(b.getType() == Material.AIR) continue;

					if(b.getType() == Material.MOB_SPAWNER) {
						amount.setSpawnerAmount(amount.getSpawnerAmount() + 1);						
						continue;
					} else if(b.getState() instanceof Beacon) {
						amount.setBeaconAmount(amount.getBeaconAmount() + 1);
						continue;
					} else if(b.getType() == Material.DRAGON_EGG) {
						amount.setDragoneggsAmount(amount.getDragoneggAmount() + 1);
						continue;
					} else if(b.getType() == Material.DIAMOND_BLOCK) {
						amount.setDiamondAmount(amount.getDiamondAmount() + 1);
						continue;
					} else if(b.getType() == Material.EMERALD_BLOCK) {
						amount.setEmeraldAmount(amount.getEmeraldAmount() + 1);
						continue;
					} else if(b.getType() == Material.GOLD_BLOCK) {
						amount.setGoldAmount(amount.getGoldAmount() + 1);
						continue;
					} else if(b.getType() == Material.IRON_BLOCK) {
						amount.setIronAmount(amount.getIronAmount() + 1);
						continue;
					} else {
						continue;
					}
				}
			}
		}
	}
	
	private void getSpawnerAmounts(Island island, SpawnerAmount amount) {
		for(int x = island.getMinProtectedX(); x <= (island.getMinProtectedX() + island.getProtectionSize() - 1); x++) {
			for(int z = island.getMinProtectedZ(); z <= (island.getMinProtectedZ() + island.getProtectionSize() - 1); z++) {
				for(int y = 0; y < 255; y++) {
					Block b = island.getCenter().getWorld().getBlockAt(x, y, z);
					if(b.getType() == Material.AIR) continue;
					
					if(b.getType() == Material.MOB_SPAWNER) {
						CreatureSpawner cs = (CreatureSpawner) b.getState();
						if(cs.getSpawnedType().equals(EntityType.COW)) {
							amount.setCows(amount.getCows() + 1);
							amount.setTierOne(amount.getTierOne() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.SHEEP)) {
							amount.setSheeps(amount.getSheeps() + 1);
							amount.setTierOne(amount.getTierOne() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.CHICKEN)) {
							amount.setChickens(amount.getChickens() + 1);
							amount.setTierOne(amount.getTierOne() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.PIG)) {
							amount.setPigs(amount.getPigs() + 1);
							amount.setTierOne(amount.getTierOne() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.CREEPER)) {
							amount.setCreepers(amount.getCreepers() + 1);
							amount.setTierTwo(amount.getTierTwo() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.SPIDER)) {
							amount.setSpiders(amount.getSpiders() + 1);
							amount.setTierTwo(amount.getTierTwo() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.ZOMBIE)) {
							amount.setZombies(amount.getZombies() + 1);
							amount.setTierTwo(amount.getTierTwo() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.SKELETON)) {
							amount.setSkeletons(amount.getSkeletons() + 1);
							amount.setTierTwo(amount.getTierTwo() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.BLAZE)) {
							amount.setBlazes(amount.getBlazes() + 1);
							amount.setTierThree(amount.getTierThree() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.ENDERMAN)) {
							amount.setEndermans(amount.getEndermans() + 1);
							amount.setTierThree(amount.getTierThree() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.SLIME)) {
							amount.setSlimes(amount.getSlimes() + 1);
							amount.setTierThree(amount.getTierThree() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.VILLAGER)) {
							amount.setVillagers(amount.getVillagers() + 1);
							amount.setTierFour(amount.getTierFour() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.MAGMA_CUBE)) {
							amount.setMagmaCubes(amount.getMagmaCubes() + 1);
							amount.setTierFour(amount.getTierFour() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.IRON_GOLEM)) {
							amount.setIronGolems(amount.getIronGolems() + 1);
							amount.setTierFour(amount.getTierFour() + 1);
							continue;
						}
						else if(cs.getSpawnedType().equals(EntityType.PIG_ZOMBIE)) {
							amount.setZombiePigMans(amount.getZombiePigMans() + 1);
							amount.setTierFour(amount.getTierFour() + 1);
							continue;
						} else continue;
					} else continue;
				}
			}
		}
	}
	
	public Island getIsland() { return this.island; }
	public BlockAmount getBlockAmount() { return this.bamount; }
	public SpawnerAmount getSpawnerAmount() { return this.samount; }
}
