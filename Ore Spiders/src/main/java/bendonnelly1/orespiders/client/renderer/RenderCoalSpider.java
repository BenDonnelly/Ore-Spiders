package bendonnelly1.orespiders.client.renderer;

import net.minecraft.util.ResourceLocation;

public class RenderCoalSpider extends RenderOreSpider {

    private static final ResourceLocation spiderEyesTexture = new ResourceLocation("orespiders",
            "textures/entity/orespider/eyes_coal.png");
    
    @Override
    protected ResourceLocation getSpiderEyesTexture() {
        return spiderEyesTexture;
    }

}
