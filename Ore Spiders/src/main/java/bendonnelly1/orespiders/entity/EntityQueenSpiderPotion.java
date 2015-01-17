package bendonnelly1.orespiders.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityQueenSpiderPotion extends EntityThrowable{

	public EntityQueenSpiderPotion(World world){
		super(world);
	}

	public EntityQueenSpiderPotion(World world, EntityLivingBase entityLivingBase){
		super(world, entityLivingBase);
	}

	public EntityQueenSpiderPotion(World world, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_){
		super(world, p_i1775_2_, p_i1775_4_, p_i1775_6_);
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	@Override
	protected void onImpact(MovingObjectPosition movingObjectPosition)
	{
		EntityQueenSpider queenSpider = new EntityQueenSpider(this.worldObj);
		EntityLightningBolt entityBolt = new EntityLightningBolt(this.worldObj, this.posX, this.posY, this.posZ);

		this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
		this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);

		if(!this.worldObj.isRemote)
		{
			queenSpider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
			this.worldObj.spawnEntityInWorld(queenSpider);
		}
		playParticleEffects(queenSpider);
		entityBolt.setPosition(this.posX, this.posY, this.posZ);
		this.worldObj.spawnEntityInWorld(entityBolt);

		if(!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
	
	private void playParticleEffects(EntityQueenSpider queenSpider)
	{
		for(int i = 0; i < 10; ++i)
		{
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.worldObj.spawnParticle("flame", this.posX + (double) (this.rand.nextFloat() * queenSpider.width * 2.0F) - (double) queenSpider.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * queenSpider.height), this.posZ + (double) (this.rand.nextFloat() * queenSpider.width * 2.0F) - (double) queenSpider.width, d0, d1, d2);
			this.worldObj.spawnParticle("smoke", this.posX + (double) (this.rand.nextFloat() * queenSpider.width * 2.0F) - (double) queenSpider.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * queenSpider.height), this.posZ + (double) (this.rand.nextFloat() * queenSpider.width * 2.0F) - (double) queenSpider.width, d0, d1, d2);
		}
	}
}
