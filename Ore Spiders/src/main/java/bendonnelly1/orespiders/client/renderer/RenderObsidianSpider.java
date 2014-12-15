package bendonnelly1.orespiders.client.renderer;

import bendonnelly1.orespiders.OreSpiders;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderObsidianSpider extends RenderOreSpider{

	private static final ResourceLocation spiderEyesTexture = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/obsidian/eyes_obsidian.png");
	private static final ResourceLocation spiderBodyTexture = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/obsidian/body_obsidian.png");
	
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
