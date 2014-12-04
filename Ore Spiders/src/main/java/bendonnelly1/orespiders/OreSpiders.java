package bendonnelly1.orespiders;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import bendonnelly1.orespiders.client.renderer.RenderCoalSpider;
import bendonnelly1.orespiders.client.renderer.RenderDiamondSpider;
import bendonnelly1.orespiders.client.renderer.RenderEmeraldSpider;
import bendonnelly1.orespiders.client.renderer.RenderEnderSpider;
import bendonnelly1.orespiders.client.renderer.RenderGoldSpider;
import bendonnelly1.orespiders.client.renderer.RenderIronSpider;
import bendonnelly1.orespiders.client.renderer.RenderLapisSpider;
import bendonnelly1.orespiders.client.renderer.RenderObsidianSpider;
import bendonnelly1.orespiders.client.renderer.RenderQuartzSpider;
import bendonnelly1.orespiders.client.renderer.RenderRedstoneSpider;
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
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "oreSpiders", version = "1.0")
public class OreSpiders{

	public static Item diamond_nugget = new Item().setUnlocalizedName("diamondNugget").setCreativeTab(CreativeTabs.tabMaterials).setTextureName("orespiders:diamond_nugget");

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		GameRegistry.registerItem(diamond_nugget, "diamondNugget");
		GameRegistry.addRecipe(new ItemStack(Items.diamond), new Object[] {"XXX", "X X", "XXX", Character.valueOf('X'), diamond_nugget});

		SpiderRegister.registerEntities();

		// Check whether the mod is runned on client, if not do return
		if(!event.getSide().isClient()) return;

		RenderingRegistry.registerEntityRenderingHandler(EntityCoalSpider.class, new RenderCoalSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityDiamondSpider.class, new RenderDiamondSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityEmeraldSpider.class, new RenderEmeraldSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityEnderSpider.class, new RenderEnderSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityGoldSpider.class, new RenderGoldSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityIronSpider.class, new RenderIronSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityLapisSpider.class, new RenderLapisSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityQuartzSpider.class, new RenderQuartzSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityObsidianSpider.class, new RenderObsidianSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityRedstoneSpider.class, new RenderRedstoneSpider());
	}

}
