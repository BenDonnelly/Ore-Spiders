package bendonnelly1.orespiders.client.renderer;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class RenderOreSpider extends RenderLiving {

    private static final ResourceLocation spiderBodyTexture = new ResourceLocation("orespiders",
            "textures/entity/orespider/body.png");

    public RenderOreSpider() {
        super(new ModelSpider(), 1.0F);
        this.setRenderPassModel(new ModelSpider());
    }

    protected abstract ResourceLocation getSpiderEyesTexture();

    @Override
    protected float getDeathMaxRotation(EntityLivingBase living) {
        return 180.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return spiderBodyTexture;
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase living, int pass, float f) {
        if (pass != 0)
            return -1;

        this.bindTexture(this.getSpiderEyesTexture());
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        GL11.glDepthMask(!living.isInvisible());
        GL11.glDepthMask(true);
        char c0 = 61680;
        int j = c0 % 65536;
        int k = c0 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j / 1.0F, (float) k / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        return 1;
    }

}
