package os.main;

import os.entities.*;
import os.renders.*;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{

	@Override
	public void registerRenderThings()
    {
		RenderingRegistry.registerEntityRenderingHandler(EntityCoalSpider.class, new RenderCoalSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityDiamondSpider.class, new RenderDiamondSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityObsidianSpider.class, new RenderObsidianSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityEmeraldSpider.class, new RenderEmeraldSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityIronSpider.class, new RenderIronSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityEnderSpider.class, new RenderEnderSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityGoldSpider.class, new RenderGoldSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityNetherQuartzSpider.class, new RenderNetherQuartzSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityLapisSpider.class, new RenderLapisSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityRedstoneSpider.class, new RenderRedstoneSpider());
    }
	
}
