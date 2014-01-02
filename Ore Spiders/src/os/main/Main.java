package os.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import os.entities.EntityRegister;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Strings.MOD_ID, name = Strings.MOD_NAME, version = Strings.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class Main
{
	@SidedProxy(clientSide = Strings.CLIENT_PROXY , serverSide = Strings.COMMON_PROXY)	
	public static CommonProxy proxy;

	public static Item diamondNugget;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.registerRenderThings();
		diamondNugget = (new Item(3001)).setUnlocalizedName("diamondNugget").setCreativeTab(CreativeTabs.tabMaterials).setTextureName(Strings.MOD_ID  + ":diamond_nugget");
		EntityRegister.registerEntities();
		GameRegistry.addRecipe(new ItemStack(Items.diamond), new Object[] {"XXX" , "X X" , "XXX" , Character.valueOf('X') , diamondNugget});
		LanguageRegistry.addName(diamondNugget, "Diamond Nugget");
		
	}

	@EventHandler
	public void init(FMLInitializationEvent event){}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event){}
	
}
