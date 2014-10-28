package bendonnelly1.orespiders.client.renderer;

import net.minecraft.util.ResourceLocation;

public class RenderRedstoneSpider extends RenderOreSpider {

    private static final ResourceLocation spiderEyesTexture = new ResourceLocation("orespiders",
            "textures/entity/orespider/eyes_redstone.png");
    
    @Override
    protected ResourceLocation getSpiderEyesTexture() {
        return spiderEyesTexture;
    }

}
