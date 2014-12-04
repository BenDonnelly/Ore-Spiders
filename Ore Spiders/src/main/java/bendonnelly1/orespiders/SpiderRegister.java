package bendonnelly1.orespiders;

import bendonnelly1.orespiders.entity.EntityCoalSpider;
import bendonnelly1.orespiders.entity.EntityDiamondSpider;
import bendonnelly1.orespiders.entity.EntityEmeraldSpider;
import bendonnelly1.orespiders.entity.EntityEnderSpider;
import bendonnelly1.orespiders.entity.EntityGoldSpider;
import bendonnelly1.orespiders.entity.EntityIronSpider;
import bendonnelly1.orespiders.entity.EntityLapisSpider;
import bendonnelly1.orespiders.entity.EntityObsidianSpider;
import bendonnelly1.orespiders.entity.EntityQuartzSpider;
import bendonnelly1.orespiders.entity.EntityRedstoneSpider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenEnd;
import cpw.mods.fml.common.registry.EntityRegistry;

public class SpiderRegister{

	static int zombieBackGround = 0x00AFAF;
	static int zombieSpots = 0x5FA88E;
	static int whiteColour = 0xffffff;
	static int blackColour = 0x000000;
	static int grayColour = 0x424242;
	static int lightGrayColour = 0xEEEEEE;
	static int lightBlueColour = 0xAFF5FF;
	static int blueishIcyColour = 0x3EA6CF;
	static int kindaBlueColour = 0x337BC7;
	static int purpleBlueishColour = 0x6419F0;
	static int redishPinkishColour = 0xEB0E58;
	static int greenishColour = 0x99FF66;
	static int yellowishColour = 0xFFFF33;
	static int brownishColour = 0x63560A;
	static int purpleishColour = 0x6B13AD;
	static int grayishIronishColour = 0x949191;
	static int enderColour = 0xCA75EF;
	static int goldishColour = 0xF7DB5E;
	static int netherQuartzColour = 0xCACDDB;
	static int lapisishColour = 0x536CE0;
	static int redishColour = 0xDE2644;

	public static void registerEntities()
	{
		// COAL SPIDER
		EntityRegistry.registerGlobalEntityID(EntityCoalSpider.class, "CoalSpider", EntityRegistry.findGlobalUniqueEntityId(), blackColour, grayColour);
		EntityRegistry.addSpawn(EntityCoalSpider.class, 6, 3, 4, EnumCreatureType.monster, BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.extremeHills,
				BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.iceMountains,
				BiomeGenBase.icePlains, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.taiga,
				BiomeGenBase.taigaHills);

		// DIAMOND SPIDER
		EntityRegistry.registerGlobalEntityID(EntityDiamondSpider.class, "DiamondSpider", EntityRegistry.findGlobalUniqueEntityId(), blackColour, lightBlueColour);
		EntityRegistry.addSpawn(EntityDiamondSpider.class, 15, 2, 4, EnumCreatureType.monster, BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.extremeHills,
				BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.iceMountains,
				BiomeGenBase.icePlains, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.taiga,
				BiomeGenBase.taigaHills);

		// OBSIDIAN SPIDER
		EntityRegistry.registerGlobalEntityID(EntityObsidianSpider.class, "ObsidianSpider", EntityRegistry.findGlobalUniqueEntityId(), blackColour, purpleishColour);
		EntityRegistry.addSpawn(EntityObsidianSpider.class, 15, 2, 4, EnumCreatureType.monster, BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.extremeHills,
				BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.iceMountains,
				BiomeGenBase.icePlains, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.taiga,
				BiomeGenBase.taigaHills);

		// EMERALD SPIDER
		EntityRegistry.registerGlobalEntityID(EntityEmeraldSpider.class, "EmeraldSpider", EntityRegistry.findGlobalUniqueEntityId(), blackColour, greenishColour);
		EntityRegistry.addSpawn(EntityEmeraldSpider.class, 20, 3, 4, EnumCreatureType.monster, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge);

		// IRON SPIDER
		EntityRegistry.registerGlobalEntityID(EntityIronSpider.class, "IronSpider", EntityRegistry.findGlobalUniqueEntityId(), blackColour, grayishIronishColour);
		EntityRegistry.addSpawn(EntityIronSpider.class, 20, 4, 5, EnumCreatureType.monster, BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.extremeHills,
				BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.iceMountains,
				BiomeGenBase.icePlains, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.taiga,
				BiomeGenBase.taigaHills);

		// ENDER SPIDER
		EntityRegistry.registerGlobalEntityID(EntityEnderSpider.class, "EnderSpider", EntityRegistry.findGlobalUniqueEntityId(), blackColour, enderColour);
		EntityRegistry.addSpawn(EntityEnderSpider.class, 20, 2, 4, EnumCreatureType.monster, BiomeGenEnd.sky);

		// GOLD SPIDER
		EntityRegistry.registerGlobalEntityID(EntityGoldSpider.class, "GoldSpider", EntityRegistry.findGlobalUniqueEntityId(), blackColour, goldishColour);
		EntityRegistry.addSpawn(EntityGoldSpider.class, 20, 5, 6, EnumCreatureType.monster, BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.extremeHills,
				BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.iceMountains,
				BiomeGenBase.icePlains, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.taiga,
				BiomeGenBase.taigaHills);

		// NETHER QUARTZ SPIDER
		EntityRegistry.registerGlobalEntityID(EntityQuartzSpider.class, "NetherQuartzSpider", EntityRegistry.findGlobalUniqueEntityId(), blackColour, netherQuartzColour);
		EntityRegistry.addSpawn(EntityQuartzSpider.class, 20, 4, 4, EnumCreatureType.monster, BiomeGenEnd.hell);

		// LAPIS SPIDER
		EntityRegistry.registerGlobalEntityID(EntityLapisSpider.class, "LapisSpider", EntityRegistry.findGlobalUniqueEntityId(), blackColour, lapisishColour);
		EntityRegistry.addSpawn(EntityLapisSpider.class, 20, 3, 4, EnumCreatureType.monster, BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.extremeHills,
				BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.iceMountains,
				BiomeGenBase.icePlains, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.taiga,
				BiomeGenBase.taigaHills);

		// REDSTONE SPIDER
		EntityRegistry.registerGlobalEntityID(EntityRedstoneSpider.class, "RedstoneSpider", EntityRegistry.findGlobalUniqueEntityId(), blackColour, redishColour);
		EntityRegistry.addSpawn(EntityRedstoneSpider.class, 20, 4, 5, EnumCreatureType.monster, BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.extremeHills,
				BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.iceMountains,
				BiomeGenBase.icePlains, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.taiga,
				BiomeGenBase.taigaHills);
	}

}
