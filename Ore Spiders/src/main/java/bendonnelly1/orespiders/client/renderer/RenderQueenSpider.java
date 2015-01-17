package bendonnelly1.orespiders.client.renderer;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import bendonnelly1.orespiders.OreSpiders;
import bendonnelly1.orespiders.entity.EntityQueenSpider;

public class RenderQueenSpider extends RenderLiving{

	public RenderQueenSpider(){
		super(new ModelSpider(), 1.0F);
		this.setRenderPassModel(new ModelSpider());
	}

	private static final ResourceLocation spiderEyesPhase0 = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/queen/eyes_queen_phase_0.png");
	private static final ResourceLocation spiderBodyPhase0 = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/queen/body_queen_phase_0.png");

	private static final ResourceLocation spiderEyesPhase1 = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/queen/eyes_queen_phase_1.png");
	private static final ResourceLocation spiderBodyPhase1 = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/queen/body_queen_phase_1.png");

	private static final ResourceLocation spiderEyesPhase2 = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/queen/eyes_queen_phase_2.png");
	private static final ResourceLocation spiderBodyPhase2 = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/queen/body_queen_phase_2.png");

	private static final ResourceLocation spiderEyesPhase3 = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/queen/eyes_queen_phase_3.png");
	private static final ResourceLocation spiderBodyPhase3 = new ResourceLocation(OreSpiders.MOD_ID, "textures/spiders/queen/body_queen_phase_3.png");

	private void scaleQueenSpider(EntityQueenSpider queenSpider, float par2)
	{

		if(queenSpider.hasPhaseChanged())
		{
			if(queenSpider.getPhase() == 0)
			{
				queenSpider.expandBoundingBox(1.0F);
			}
			else if(queenSpider.getPhase() == 1)
			{
				queenSpider.expandBoundingBox(1.7F);
			}
			else if(queenSpider.getPhase() == 2)
			{
				queenSpider.expandBoundingBox(1.4F);
			}
			else if(queenSpider.getPhase() == 3)
			{
				queenSpider.expandBoundingBox(0.9F);
			}
		}

		if(queenSpider.getPhase() == 0)
		{
			GL11.glScalef(1.0F, 1.0F, 1.0F);
		}
		else if(queenSpider.getPhase() == 1)
		{
			GL11.glScalef(1.7F, 1.7F, 1.7F);
		}
		else if(queenSpider.getPhase() == 2)
		{
			GL11.glScalef(1.4F, 1.4F, 1.4F);
		}
		else if(queenSpider.getPhase() == 3)
		{
			GL11.glScalef(0.9F, 0.9F, 0.9F);
		}
	}

	private ResourceLocation getEyesBasedOnPhase(EntityQueenSpider queenSpider)
	{

		if(queenSpider.getPhase() == 0)
		{
			return spiderEyesPhase0;
		}
		else if(queenSpider.getPhase() == 1)
		{
			return spiderEyesPhase1;
		}
		else if(queenSpider.getPhase() == 2)
		{
			return spiderEyesPhase2;
		}
		else
		{
			return spiderEyesPhase3;
		}
	}

	protected ResourceLocation getSpiderEyesTexture(EntityQueenSpider queenSpider)
	{
		return getEyesBasedOnPhase(queenSpider);
	}

	public void renderBossBar(EntityQueenSpider queenSpider, double d1, double d2, double d3, float f1, float f2)
	{
		BossStatus.setBossStatus(queenSpider, false);
		super.doRender((EntityLiving) queenSpider, d1, d2, d3, f1, f2);
	}

	@Override
	public void doRender(EntityLiving entityLiving, double d1, double d2, double d3, float f1, float f2)
	{
		this.renderBossBar((EntityQueenSpider) entityLiving, d1, d2, d3, f1, f2);
	}

	@Override
	public void doRender(EntityLivingBase entityLivingBase, double d1, double d2, double d3, float f1, float f2)
	{
		this.renderBossBar((EntityQueenSpider) entityLivingBase, d1, d2, d3, f1, f2);
	}

	@Override
	public void doRender(Entity ent, double d1, double d2, double d3, float f1, float f2)
	{
		this.renderBossBar((EntityQueenSpider) ent, d1, d2, d3, f1, f2);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase livingBase, float par2)
	{
		this.scaleQueenSpider((EntityQueenSpider) livingBase, par2);
	}

	private ResourceLocation getTextureBasedOfPhase(EntityQueenSpider queenSpider)
	{

		if(queenSpider.getPhase() == 0)
		{
			return spiderBodyPhase0;
		}
		else if(queenSpider.getPhase() == 1)
		{
			return spiderBodyPhase1;
		}
		else if(queenSpider.getPhase() == 2)
		{
			return spiderBodyPhase2;
		}
		else
		{
			return spiderBodyPhase3;
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return getTextureBasedOfPhase((EntityQueenSpider) entity);
	}

	

	@Override
	protected int shouldRenderPass(EntityLivingBase living, int pass, float f)
	{
		if(pass != 0) return -1;

		this.bindTexture(this.getSpiderEyesTexture((EntityQueenSpider) living));
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
