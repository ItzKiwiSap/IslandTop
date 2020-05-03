package nl.itz_kiwisap.skyfarers.islandtop.objects;

import com.wasteofplastic.askyblock.Island;

public class SpawnerAmount {

	private Island island;

	// Tier 1
	private int tier1;
	private int cows;
	private int sheeps;
	private int chickens;
	private int pigs;

	// Tier 2
	private int tier2;
	private int creepers;
	private int spiders;
	private int zombies;
	private int skeletons;

	// Tier 3
	private int tier3;
	private int blazes;
	private int endermans;
	private int slimes;

	// Tier 4
	private int tier4;
	private int villagers;
	private int magmacubes;
	private int irongolems;
	private int zombiepigmans;

	public SpawnerAmount(Island island) {
		this.island = island;
	}
	
	public void reset() {
		this.cows = 0;
		this.sheeps = 0;
		this.chickens = 0;
		this.pigs = 0;
		this.creepers = 0;
		this.spiders = 0;
		this.zombies = 0;
		this.skeletons = 0;
		this.blazes = 0;
		this.slimes = 0;
		this.villagers = 0;
		this.magmacubes = 0;
		this.irongolems = 0;
		this.zombiepigmans = 0;
		this.tier1 = 0;
		this.tier2 = 0;
		this.tier3 = 0;
		this.tier4 = 0;
	}
	
	public void setCows(int cow) { this.cows = cow; }
	public void setSheeps(int sheep) { this.sheeps = sheep; }
	public void setChickens(int chicken) { this.chickens = chicken; }
	public void setPigs(int pig) { this.pigs = pig; }
	public void setCreepers(int creeper) { this.creepers = creeper; }
	public void setSpiders(int spider) { this.spiders = spider; }
	public void setZombies(int zombie) { this.zombies = zombie; }
	public void setSkeletons(int skeleton) { this.skeletons = skeleton; }
	public void setBlazes(int blaze) { this.blazes = blaze; }
	public void setEndermans(int enderman) { this.endermans = enderman; }
	public void setSlimes(int slime) { this.slimes = slime; }
	public void setVillagers(int villager) { this.villagers = villager; }
	public void setMagmaCubes(int magmacube) { this.magmacubes = magmacube; }
	public void setIronGolems(int irongolem) { this.irongolems = irongolem; }
	public void setZombiePigMans(int zombiepigman) { this.zombiepigmans = zombiepigman; }
	public void setTierOne(int tier) { this.tier1 = tier; }
	public void setTierTwo(int tier) { this.tier2 = tier; }
	public void setTierThree(int tier) { this.tier3 = tier; }
	public void setTierFour(int tier) { this.tier4 = tier; }
	
	public Island getIsland() { return this.island; }
	public int getCows() { return this.cows; }
	public int getSheeps() { return this.sheeps; }
	public int getChickens() { return this.chickens; }
	public int getPigs() { return this.pigs; }
	public int getCreepers() { return this.creepers; }
	public int getSpiders() { return this.spiders; }
	public int getZombies() { return this.zombies; }
	public int getSkeletons() { return this.skeletons; }
	public int getBlazes() { return this.blazes; }
	public int getEndermans() { return this.endermans; }
	public int getSlimes() { return this.slimes; }
	public int getVillagers() { return this.villagers; }
	public int getMagmaCubes() { return this.magmacubes; }
	public int getIronGolems() { return this.irongolems; }
	public int getZombiePigMans() { return zombiepigmans; }
	public int getTierOne() { return this.tier1; }
	public int getTierTwo() { return this.tier2; }
	public int getTierThree() { return this.tier3; }
	public int getTierFour() { return this.tier4; }
}