package me.capit.TerraDirt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.block.Biome;
import org.bukkit.plugin.java.JavaPlugin;

public class TerraDirt extends JavaPlugin {
	
	EventListener events = null;
	
	public static final HashMap<String, List<Biome>> bialias = new HashMap<String, List<Biome>>();
	
	@Override
	public void onEnable(){
		events = new EventListener(this);
		this.getServer().getPluginManager().registerEvents(events, this);
		
		bialias.put("DESERT", Arrays.asList(Biome.DESERT, Biome.DESERT_HILLS, Biome.DESERT_MOUNTAINS));
		bialias.put("PLAINS", Arrays.asList(Biome.PLAINS, Biome.SAVANNA, Biome.SAVANNA_MOUNTAINS, Biome.SAVANNA_PLATEAU, Biome.SAVANNA_PLATEAU_MOUNTAINS,
				Biome.SUNFLOWER_PLAINS, Biome.SMALL_MOUNTAINS));
		bialias.put("OCEAN", Arrays.asList(Biome.OCEAN, Biome.DEEP_OCEAN));
		bialias.put("FOREST", Arrays.asList(Biome.FLOWER_FOREST, Biome.FOREST, Biome.FOREST_HILLS, Biome.BIRCH_FOREST, Biome.BIRCH_FOREST_HILLS,
				Biome.BIRCH_FOREST_HILLS_MOUNTAINS, Biome.BIRCH_FOREST_MOUNTAINS, Biome.ROOFED_FOREST, Biome.ROOFED_FOREST_MOUNTAINS));
		bialias.put("COLD", Arrays.asList(Biome.TAIGA, Biome.TAIGA_HILLS, Biome.TAIGA_MOUNTAINS, Biome.MEGA_SPRUCE_TAIGA, Biome.MEGA_SPRUCE_TAIGA_HILLS,
				Biome.COLD_TAIGA, Biome.COLD_TAIGA_HILLS, Biome.COLD_BEACH, Biome.FROZEN_OCEAN, Biome.FROZEN_RIVER,
				Biome.ICE_PLAINS, Biome.ICE_PLAINS_SPIKES));
		bialias.put("RIVER", Arrays.asList(Biome.RIVER, Biome.BEACH));
		bialias.put("WET", Arrays.asList(Biome.SWAMPLAND, Biome.SWAMPLAND_MOUNTAINS));
		bialias.put("JUNGLE", Arrays.asList(Biome.JUNGLE, Biome.JUNGLE_EDGE, Biome.JUNGLE_EDGE_MOUNTAINS, Biome.JUNGLE_HILLS, Biome.JUNGLE_MOUNTAINS));
		bialias.put("MUSHROOM", Arrays.asList(Biome.MUSHROOM_ISLAND, Biome.MUSHROOM_SHORE));
		bialias.put("HIGH", Arrays.asList(Biome.MESA, Biome.MESA_BRYCE, Biome.MESA_PLATEAU, Biome.MESA_PLATEAU_FOREST, Biome.MESA_PLATEAU_FOREST_MOUNTAINS,
					Biome.MESA_PLATEAU_MOUNTAINS, Biome.COLD_TAIGA_MOUNTAINS, Biome.ICE_MOUNTAINS));
		
		bialias.put("HELL", Arrays.asList(Biome.HELL));
		bialias.put("SKY", Arrays.asList(Biome.SKY));
		
	}
	
	public boolean isOfBiomeType(String type, Biome biome){
		if (bialias.get(type).contains(biome)){
			return true;
		} else {
			return false;
		}
	}
	
}
