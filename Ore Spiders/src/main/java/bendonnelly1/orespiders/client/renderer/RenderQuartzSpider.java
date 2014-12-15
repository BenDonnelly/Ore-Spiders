package bendonnelly1.orespiders.client.renderer;

import bendonnelly1.orespiders.OreSpiders;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderQuartzSpider extends RenderOreSpider{

	private static final ResourceLocation spiderEyesTexture = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/quartz/eyes_quartz.png");
	private static final ResourceLocation spiderBodyTexture = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/quartz/body_quartz.png");

	@Override
	protected ResourceLocation getSpiderEyesTexture()
	{
		return spiderEyesTexture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return spiderBodyTexture;
	}

}
