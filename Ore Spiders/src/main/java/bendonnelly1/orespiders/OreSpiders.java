package bendonnelly1.orespiders;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = OreSpiders.MOD_ID, version = OreSpiders.VERSION, name = OreSpiders.NAME)
public class OreSpiders{

	public static final String MOD_ID = "orespiders";
	public static final String VERSION = "1.2";
	public static final String NAME = "Ore Spiders";
	
	@SidedProxy(clientSide = "bendonnelly1.orespiders.ClientProxy", serverSide = "bendonnelly1.orespiders.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance(OreSpiders.MOD_ID)
	public static OreSpiders oreSpiderInstance;
	
	public static Item queenSpiderPotion; 
	public static Item queenSpiderEye;
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		
		proxy.registerRenderThings();
		SpiderRegister.registerEntities();
		MinecraftForge.EVENT_BUS.register(new OreSpidersEvents());
		
		queenSpiderPotion = new ItemQueenSpiderPotion();
		queenSpiderEye = new Item().setUnlocalizedName(MOD_ID + "." + "queenSpiderEye").setTextureName(MOD_ID + ":" + "eye_queen_spider").setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(queenSpiderEye, "queenSpiderEye");
		
		GameRegistry.addRecipe(new ItemStack(queenSpiderEye), new Object[] {"DRI", "EGL", "SCS", Character.valueOf('D'), Items.diamond, Character.valueOf('R'), Items.redstone, Character.valueOf('I'), Items.iron_ingot, Character.valueOf('E'), Items.emerald, Character.valueOf('G'), Items.gold_ingot, Character.valueOf('L'), new ItemStack(Items.dye, 4), Character.valueOf('S'), Items.spider_eye, Character.valueOf('C'), Items.coal});
		GameRegistry.addRecipe(new ItemStack(queenSpiderPotion), new Object[] {"SGS", "GBG", "SGS", Character.valueOf('S'), queenSpiderEye, Character.valueOf('G'), Items.gunpowder, Character.valueOf('B'), Items.glass_bottle});
	}

}
