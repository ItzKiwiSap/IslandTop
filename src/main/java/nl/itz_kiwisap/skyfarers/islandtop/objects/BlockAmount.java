package nl.itz_kiwisap.skyfarers.islandtop.objects;

import com.wasteofplastic.askyblock.Island;

public class BlockAmount {

	private Island island;
	private int spawners;
	private int beacons;
	private int dragoneggs;
	private int diamonds;
	private int emeralds;
	private int gold;
	private int iron;
	
	public BlockAmount(Island island) {
		this.island = island;
	}
	
	public void reset() {
		this.spawners = 0;
		this.beacons = 0;
		this.dragoneggs = 0;
		this.diamonds = 0;
		this.emeralds = 0;
		this.gold = 0;
		this.iron = 0;
	}
	
	public void setSpawnerAmount(int spawner) { this.spawners = spawner; }
	public void setBeaconAmount(int beacon) { this.beacons = beacon; }
	public void setDragoneggsAmount(int dragonegg) { this.dragoneggs = dragonegg; }
	public void setDiamondAmount(int diamond) { this.diamonds = diamond; }
	public void setEmeraldAmount(int emerald) { this.emeralds = emerald; }
	public void setGoldAmount(int gold) { this.gold = gold; }
	public void setIronAmount(int iron) { this.iron = iron; }
	
	public Island getIsland() { return this.island; }
	public int getSpawnerAmount() { return this.spawners; }
	public int getBeaconAmount() { return this.beacons; }
	public int getDragoneggAmount() { return this.dragoneggs; }
	public int getDiamondAmount() { return this.diamonds; }
	public int getEmeraldAmount() { return this.emeralds; }
	public int getGoldAmount() { return this.gold; }
	public int getIronAmount() { return this.iron; }
}
