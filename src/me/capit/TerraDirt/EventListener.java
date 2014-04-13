package me.capit.TerraDirt;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EventListener implements Listener {

	TerraDirt plugin;
	public EventListener(TerraDirt plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onChunkGen(ChunkLoadEvent e){
		if (e.isNewChunk()){
			Chunk c = e.getChunk();
			fixChunk(c);
		}
	}
	
	public void fixChunk(Chunk chunk){
		final ChunkSnapshot c = chunk.getChunkSnapshot();
		new BukkitRunnable(){
			
			@Override
			public void run(){
				World w = plugin.getServer().getWorld(c.getWorldName());
				int x1 = c.getX()*16;
				int z1 = c.getZ()*16;
				int bedrockY = 6;
				for (int bi = bedrockY; bi>0; bi--){
					for (int xi = x1; xi<(x1+16); xi++){
						for (int zi = z1; zi<(z1+16); zi++){
							Location loc = new Location(w, xi, bi, zi);
							if (loc.getBlock().getType()==Material.BEDROCK){
								loc.getBlock().setType(Material.STONE);
							}
						}
					}
				}
			}
		}.runTaskLater(plugin, 1L);
		new BukkitRunnable(){
			
			@Override
			public void run(){
				World w = plugin.getServer().getWorld(c.getWorldName());
				int x1 = c.getX()*16;
				int z1 = c.getZ()*16;
				int y = w.getMaxHeight();
				for (int yi = 0; yi<y; yi++){
					for (int xi = x1; xi<(x1+16); xi++){
						for (int zi = z1; zi<(z1+16); zi++){
							Location loc = new Location(w, xi, yi, zi);
							if (isOre(loc)){
								if (!shouldOreGenerate(loc.getBlock())){
									loc.getBlock().setType(Material.STONE);
								}
							}
						}
					}
				}
				// TODO Biome-based ore population.
			}
		}.runTaskLater(plugin, 5L);
	}
	
	public boolean isOre(Location loc){
		Block b = loc.getBlock();
		switch(b.getType()){
		case IRON_ORE:
			return true;
		case COAL_ORE:
			return true;
		case REDSTONE_ORE:
			return true;
		case LAPIS_ORE:
			return true;
		case DIAMOND_ORE:
			return true;
		case EMERALD_ORE:
			return true;
		case GOLD_ORE:
			return true;
		default:
			return false;
		}
	}
	
	public boolean shouldOreGenerate(Block b){
		double c1 = 0.8;
		double c2 = 0;
		switch(b.getType()){
		case COAL_ORE:
			if (plugin.isOfBiomeType("OCEAN", b.getBiome())) c2 = 0.25;
			if (plugin.isOfBiomeType("PLAINS", b.getBiome())) c2 = 0.25;
			if (plugin.isOfBiomeType("HIGH", b.getBiome())) c2 = 0.75;
			if (plugin.isOfBiomeType("RIVER", b.getBiome())) c2 = 0.5;
			if (plugin.isOfBiomeType("JUNGLE", b.getBiome())) c2 = 0.5;
			if (plugin.isOfBiomeType("COLD", b.getBiome())) c2 = 0.75;
			if (plugin.isOfBiomeType("FOREST", b.getBiome())) c2 = 0.5;
		case IRON_ORE:
			if (plugin.isOfBiomeType("DESERT", b.getBiome())) c2 = 0.25;
			if (plugin.isOfBiomeType("PLAINS", b.getBiome())) c2 = 0.25;
			if (plugin.isOfBiomeType("HIGH", b.getBiome())) c2 = 0.5;
			if (plugin.isOfBiomeType("WET", b.getBiome())) c2 = 0.25;
			if (plugin.isOfBiomeType("JUNGLE", b.getBiome())) c2 = 0.25;
			if (plugin.isOfBiomeType("COLD", b.getBiome())) c2 = 0.75;
			if (plugin.isOfBiomeType("FOREST", b.getBiome())) c2 = 0.25;
			break;
		case GOLD_ORE:
			if (plugin.isOfBiomeType("DESERT", b.getBiome())) c2 = 0.75;
			if (plugin.isOfBiomeType("HIGH", b.getBiome())) c2 = 0.25;
			if (plugin.isOfBiomeType("WET", b.getBiome())) c2 = 0.15;
			if (plugin.isOfBiomeType("JUNGLE", b.getBiome())) c2 = 0.15;
			if (plugin.isOfBiomeType("FOREST", b.getBiome())) c2 = 0.25;
			if (plugin.isOfBiomeType("HELL", b.getBiome())) c2 = 0.25;
			break;
		case REDSTONE_ORE:
			if (plugin.isOfBiomeType("PLAINS", b.getBiome())) c2 = 0.5;
			if (plugin.isOfBiomeType("HIGH", b.getBiome())) c2 = 0.5;
			if (plugin.isOfBiomeType("RIVER", b.getBiome())) c2 = 0.25;
			if (plugin.isOfBiomeType("JUNGLE", b.getBiome())) c2 = 0.25;
			if (plugin.isOfBiomeType("MUSHROOM", b.getBiome())) c2 = 0.75;
			if (plugin.isOfBiomeType("HELL", b.getBiome())) c2 = 0.25;
			break;
		case LAPIS_ORE:
			if (plugin.isOfBiomeType("OCEAN", b.getBiome())) c2 = 0.5;
			if (plugin.isOfBiomeType("WET", b.getBiome())) c2 = 0.5;
			if (plugin.isOfBiomeType("RIVER", b.getBiome())) c2 = 0.75;
			if (plugin.isOfBiomeType("COLD", b.getBiome())) c2 = 0.25;
			if (plugin.isOfBiomeType("MUSHROOM", b.getBiome())) c2 = 0.5;
			break;
		case DIAMOND_ORE:
			c1=0.5;
			if (plugin.isOfBiomeType("OCEAN", b.getBiome())) c2 = 0.5;
			if (plugin.isOfBiomeType("HIGH", b.getBiome())) c2 = 0.15;
			if (plugin.isOfBiomeType("RIVER", b.getBiome())) c2 = 0.15;
			if (plugin.isOfBiomeType("WET", b.getBiome())) c2 = 0.25;
			if (plugin.isOfBiomeType("MUSHROOM", b.getBiome())) c2 = 0.15;
			if (plugin.isOfBiomeType("JUNGLE", b.getBiome())) c2 = 0.25;
			break;
		case EMERALD_ORE:
			if (plugin.isOfBiomeType("JUNGLE", b.getBiome())) c2 = 0.5;
			if (plugin.isOfBiomeType("HIGH", b.getBiome())) c2 = 0.5;
			if (plugin.isOfBiomeType("RIVER", b.getBiome())) c2 = 0.15;
			if (plugin.isOfBiomeType("SKY", b.getBiome())) c2 = 0.75;
			break;
		case GLOWSTONE:
			if (plugin.isOfBiomeType("MUSHROOM", b.getBiome())) c2 = 0.15;
			if (plugin.isOfBiomeType("HELL", b.getBiome())) c2 = 0.75;
			if (plugin.isOfBiomeType("SKY", b.getBiome())) c2 = 0.25;
			break;
		case QUARTZ_ORE:
			if (plugin.isOfBiomeType("JUNGLE", b.getBiome())) c2 = 0.15;
			if (plugin.isOfBiomeType("MUSHROOM", b.getBiome())) c2 = 0.5;
			if (plugin.isOfBiomeType("HELL", b.getBiome())) c2 = 0.5;
			if (plugin.isOfBiomeType("SKY", b.getBiome())) c2 = 0.5;
			break;
		case CLAY:
			if (plugin.isOfBiomeType("OCEAN", b.getBiome())) c2 = 0.75;
			if (plugin.isOfBiomeType("RIVER", b.getBiome())) c2 = 0.5;
			break;
		case ENDER_STONE:
			if (plugin.isOfBiomeType("HELL", b.getBiome())) c2 = 0.15;
			if (plugin.isOfBiomeType("SKY", b.getBiome())) c2 = 0.75;
			break;
		default:
			break;
		}
		double rand = Math.random();
		if (rand<c1){
			if (rand<c2){
				return true;
			}
		}
		return false;
	}
}
