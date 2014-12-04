package bendonnelly1.orespiders.client.renderer;

import net.minecraft.util.ResourceLocation;

public class RenderLapisSpider extends RenderOreSpider{

	private static final ResourceLocation spiderEyesTexture = new ResourceLocation("orespiders", "textures/entity/orespider/eyes_lapis.png");

	@Override
	protected ResourceLocation getSpiderEyesTexture()
	{
		return spiderEyesTexture;
	}

}
