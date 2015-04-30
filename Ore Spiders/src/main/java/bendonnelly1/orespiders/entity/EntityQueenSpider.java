package bendonnelly1.orespiders.entity;

import bendonnelly1.orespiders.OreSpiders;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityQueenSpider extends EntityOreSpider implements IBossDisplayData{

	private int queenSpiderHealth;
	public int phase;
	private boolean attackedByPlayer;
	public int oldPhase = 0;

	public EntityQueenSpider(World world){
		super(world);
		// this.setSize(2.8F, 1.5F);
		this.isImmuneToFire = true;
		this.experienceValue = 500;
		phase = 0;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.6000011920929D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(9.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);

	}

	@Override
	public Entity findPlayerToAttack()
	{
		if(attackedByPlayer)
		{
			double d0 = 40.0D;
			this.worldObj.getClosestVulnerablePlayerToEntity(this, d0);
		}
		return null;
	}

	@Override
	public boolean attackEntityAsMob(Entity ent)
	{
		if(super.attackEntityAsMob(ent))
		{
			if(ent instanceof EntityLivingBase && this.getPhase() == 3)
			{
				((EntityLivingBase) ent).addPotionEffect(new PotionEffect(Potion.poison.id, 40, 2));
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	private void generateParticles(String particle)
	{
		for(int i = 0; i < 4; ++i)
		{
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.worldObj.spawnParticle(particle, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setBoolean("attackedByPlayer", this.attackedByPlayer);
		tag.setInteger("phase", phase);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag)
	{
		super.readEntityFromNBT(tag);
		this.attackedByPlayer = tag.getBoolean("attackedByPlayer");
		this.phase = tag.getInteger("phase");
	}

	@Override
	public void onLivingUpdate()
	{
		queenSpiderHealth = (int) (Math.round(this.getHealth()));
		int x = MathHelper.floor_double(this.posX);
		int y = MathHelper.floor_double(this.posY);
		int z = MathHelper.floor_double(this.posZ);

		if(getPhase() == 2)
		{
			if(this.ticksExisted % 60 == 0)
			{
				this.heal(5.0F);
				generateParticles("heart");
			}
		}
		// ULTRA HARD MODE!!!
		/*
		 * if(getPhase() == 3) { for(int l = 0; l < 1; ++l) { x = MathHelper.floor_double(this.posX + (double) ((float) (l % 2 * 2 - 1) * 0.25F)); y = MathHelper.floor_double(this.posY); z = MathHelper.floor_double(this.posZ + (double) ((float) (l /
		 * 2 % 2 * 2 - 1) * 0.25F));
		 * 
		 * if(this.worldObj.getBlock(x, y, z).getMaterial() == Material.air) { this.worldObj.setBlock(x, y, z, Blocks.web); } } }
		 */
		phaseTransition();
		super.onLivingUpdate();
	}

	public int getPhase()
	{
		if(queenSpiderHealth == 200)//100 hearts
		{
			phase = 0;
		}
		else if(queenSpiderHealth <= 199 && queenSpiderHealth > 100)//99.5 && 50 hearts
		{
			phase = 1;
		}
		else if(queenSpiderHealth <= 100 && queenSpiderHealth > 25)//50 && 25
		{
			phase = 2;
		}
		else if(queenSpiderHealth <= 25)
		{
			phase = 3;
		}
		return phase;
	}

	public boolean hasPhaseChanged()
	{
		if(oldPhase != this.getPhase())
		{
			oldPhase = this.getPhase();
			return true;
		}
		return false;
	}

	public void phaseTransition()
	{
		if(hasPhaseChanged())
		{
			if(this.getPhase() == 1)
			{
				generateParticles("angryVillager");
				this.worldObj.playSoundAtEntity(this, OreSpiders.MOD_ID + ":" + "spider_scream", 1.0F, 1.0F);
			}
			else if(this.getPhase() == 2)
			{
				generateParticles("angryVillager");
				this.worldObj.playSoundAtEntity(this, OreSpiders.MOD_ID + ":" + "spider_scream", 1.0F, 1.0F);
			}
			else if(this.getPhase() == 3)
			{
				this.setKnockbackHeight(1.8000000059604645D);
				this.setMovementSpeed(2.500000011920929D);
				generateParticles("angryVillager");
				this.worldObj.playSoundAtEntity(this, OreSpiders.MOD_ID + ":" + "spider_scream", 1.0F, 1.0F);
			}
		}
	}

	@Override
	protected void dropFewItems(boolean hitByPlayer, int lootEnchantLevel)
	{
		this.dropItem(OreSpiders.orbWeaver, 1);
	}

/*	@Override
	protected void onDeathUpdate()
	{

		if(!this.worldObj.isRemote)
		{
			this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
		}
		generateParticles("largeexplode");
		this.setDead();
	}*/

	public void expandBoundingBox(float growthFactor)
	{
		this.setSize(1.4F * growthFactor, 0.9F * growthFactor);
	}

	public void printPhaseAndHP()
	{
		System.out.println("Phase: " + phase + "   HP:" + this.getHealth());
	}

}
