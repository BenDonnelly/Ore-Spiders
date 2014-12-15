package bendonnelly1.orespiders;

import bendonnelly1.orespiders.entity.*;
import bendonnelly1.orespiders.client.renderer.*;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerRenderThings()
	{

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
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenSpider.class, new RenderQueenSpider());
	}

}
