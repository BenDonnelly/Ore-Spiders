package bendonnelly1.orespiders;

import bendonnelly1.orespiders.entity.EntityRegisterer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = OreSpiders.MOD_ID, version = OreSpiders.VERSION, name = OreSpiders.NAME)
public class OreSpiders{

	public static final String MOD_ID = "orespiders";
	public static final String VERSION = "1.3";
	public static final String NAME = "Ore Spiders";

	@SidedProxy(clientSide = "bendonnelly1.orespiders.ClientProxy", serverSide = "bendonnelly1.orespiders.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance(OreSpiders.MOD_ID)
	public static OreSpiders oreSpiderInstance;

	public static Item queenSpiderPotion;
	public static Item queenSpiderEye;
	public static Item orbWeaver;
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{

		EntityRegisterer.registerEntities();
		MinecraftForge.EVENT_BUS.register(new OreSpidersEvents());

		queenSpiderPotion = new ItemQueenSpiderPotion();
		queenSpiderEye = new Item().setUnlocalizedName(MOD_ID + "." + "queenSpiderEye").setTextureName(MOD_ID + ":" + "eye_queen_spider").setCreativeTab(CreativeTabs.tabMisc);
		orbWeaver =new ItemOrbWeaver();
		GameRegistry.registerItem(queenSpiderEye, "queenSpiderEye");

		GameRegistry.addRecipe(new ItemStack(queenSpiderEye), new Object[] {"DRI", "EGL", "SCS", Character.valueOf('D'), Items.diamond, Character.valueOf('R'), Items.redstone, Character.valueOf('I'), Items.iron_ingot, Character.valueOf('E'), Items.emerald, Character.valueOf('G'), Items.gold_ingot, Character.valueOf('L'),
				new ItemStack(Items.dye, 1, 4), Character.valueOf('S'), Items.spider_eye, Character.valueOf('C'), Items.coal});
		GameRegistry.addRecipe(new ItemStack(queenSpiderPotion), new Object[] {"SGS", "GBG", "SGS", Character.valueOf('S'), queenSpiderEye, Character.valueOf('G'), Items.gunpowder, Character.valueOf('B'), Items.glass_bottle});

		proxy.registerRenderThings();
	}

	public static void out(Object text)
	{
		System.out.println("[" + (FMLCommonHandler.instance().getSide() == Side.CLIENT ? "CLIENT" : "SERVER") + "] " + text);
	}

}
