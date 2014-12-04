package bendonnelly1.orespiders.client.renderer;

import net.minecraft.util.ResourceLocation;

public class RenderQuartzSpider extends RenderOreSpider{

	private static final ResourceLocation spiderEyesTexture = new ResourceLocation("orespiders", "textures/entity/orespider/eyes_quartz.png");

	@Override
	protected ResourceLocation getSpiderEyesTexture()
	{
		return spiderEyesTexture;
	}

}
