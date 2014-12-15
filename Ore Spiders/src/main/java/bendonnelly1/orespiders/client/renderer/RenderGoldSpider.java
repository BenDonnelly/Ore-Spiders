package bendonnelly1.orespiders.client.renderer;

import bendonnelly1.orespiders.OreSpiders;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderGoldSpider extends RenderOreSpider{

	private static final ResourceLocation spiderEyesTexture = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/gold/eyes_gold.png");
	private static final ResourceLocation spiderBodyTexture = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/gold/body_gold.png");
	
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
