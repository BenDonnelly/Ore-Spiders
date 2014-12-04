package bendonnelly1.orespiders.entity;

import java.util.Random;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityEnderSpider extends EntityMob{

	private static final UUID attackingSpeedBoostModifierUUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
	private static final AttributeModifier attackingSpeedBoostModifier = (new AttributeModifier(attackingSpeedBoostModifierUUID, "Attacking speed boost", 6.199999809265137D,
			0)).setSaved(false);

	/**
	 * Counter to delay the teleportation of an enderman towards the currently
	 * attacked target
	 */
	private int teleportDelay;

	/**
	 * A player must stare at an enderman for 5 ticks before it becomes
	 * aggressive. This field counts those ticks.
	 */
	private int stareTimer;
	private Entity lastEntityToAttack;
	private boolean isAggressive;

	public EntityEnderSpider(World world){
		super(world);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte) 0));
		this.dataWatcher.addObject(17, new Byte((byte) 0));
		this.dataWatcher.addObject(18, new Byte((byte) 0));
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity
	 * has recently been hit by a player. @param par2 - Level of Looting used to
	 * kill this mob.
	 */
	@Override
	protected void dropFewItems(boolean hitByPlayer, int lootEnchantLevel)
	{
		super.dropFewItems(hitByPlayer, lootEnchantLevel);
		int ranNum, amountToDrop, amountToDropWithLootEnchant;
		ranNum = this.rand.nextInt(2);
		amountToDrop = this.rand.nextInt(3 + 1);
		amountToDropWithLootEnchant = (lootEnchantLevel + 1) * amountToDrop;

		if(!(this.isBurning()))
		{
			if(amountToDrop != 0)
			{
				if(lootEnchantLevel == 0)
				{
					if(ranNum == 1)
					{
						this.dropItem(Items.ender_pearl, amountToDrop);
					}
					this.dropItem(Items.spider_eye, 1);
				}
				else
				{
					this.dropItem(Items.ender_pearl, amountToDropWithLootEnchant);
					this.dropItem(Items.spider_eye, amountToDropWithLootEnchant);
				}
			}
		}
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound()
	{
		return this.isScreaming() ? "mob.endermen.scream" : "mob.endermen.idle";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound()
	{
		return "mob.endermen.hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound()
	{
		return "mob.spider.death";
	}

	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	@Override
	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
	{
		this.playSound("mob.spider.step", 0.15F, 1.0F);
	}

	/**
	 * Finds the closest player within 16 blocks to attack, or null if this
	 * Entity isn't interested in attacking (Animals, Spiders at day, peaceful
	 * PigZombies).
	 */
	@Override
	protected Entity findPlayerToAttack()
	{
		EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);

		if(entityplayer != null)
		{
			if(this.shouldAttackPlayer(entityplayer))
			{
				this.isAggressive = true;

				if(this.stareTimer == 0)
				{
					this.worldObj.playSoundAtEntity(entityplayer, "mob.endermen.stare", 1.0F, 1.0F);
				}

				if(this.stareTimer++ == 5)
				{
					this.stareTimer = 0;
					this.setScreaming(true);
					return entityplayer;
				}
			}
			else
			{
				this.stareTimer = 0;
			}
		}

		return null;
	}

	/**
	 * Checks to see if this ender spider should be attacking this player
	 */
	private boolean shouldAttackPlayer(EntityPlayer p_70821_1_)
	{
		ItemStack itemstack = p_70821_1_.inventory.armorInventory[3];

		if(itemstack != null && itemstack.getItem() == Item.getItemFromBlock(Blocks.pumpkin))
		{
			return false;
		}
		else
		{
			Vec3 vec3 = p_70821_1_.getLook(1.0F).normalize();
			Vec3 vec31 = Vec3.createVectorHelper(this.posX - p_70821_1_.posX,
					this.boundingBox.minY + (double) (this.height / 2.0F) - (p_70821_1_.posY + (double) p_70821_1_.getEyeHeight()), this.posZ - p_70821_1_.posZ);
			double d0 = vec31.lengthVector();
			vec31 = vec31.normalize();
			double d1 = vec3.dotProduct(vec31);
			return d1 > 1.0D - 0.025D / d0 && p_70821_1_.canEntityBeSeen(this);
		}
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate()
	{
		if(this.isWet())
		{
			this.attackEntityFrom(DamageSource.drown, 1.0F);
		}

		if(this.lastEntityToAttack != this.entityToAttack)
		{
			IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
			iattributeinstance.removeModifier(attackingSpeedBoostModifier);

			if(this.entityToAttack != null)
			{
				iattributeinstance.applyModifier(attackingSpeedBoostModifier);
			}
		}

		this.lastEntityToAttack = this.entityToAttack;
		int i;
		int j;
		int k;
		int l;

		for(i = 0; i < 2; ++i)
		{
			this.worldObj.spawnParticle("portal", this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height
					- 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(),
					(this.rand.nextDouble() - 0.5D) * 2.0D);
		}

		if(this.worldObj.isDaytime() && !this.worldObj.isRemote)
		{
			float f = this.getBrightness(1.0F);

			if(f > 0.5F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))
					&& this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F)
			{
				this.entityToAttack = null;
				this.setScreaming(false);
				this.isAggressive = false;
				this.teleportRandomly();
			}
		}

		if(this.isWet() || this.isBurning())
		{
			this.entityToAttack = null;
			this.setScreaming(false);
			this.isAggressive = false;
			this.teleportRandomly();
		}

		if(this.isScreaming() && !this.isAggressive && this.rand.nextInt(100) == 0)
		{
			this.setScreaming(false);
		}

		this.isJumping = false;

		if(this.entityToAttack != null)
		{
			this.faceEntity(this.entityToAttack, 100.0F, 100.0F);
		}

		if(!this.worldObj.isRemote && this.isEntityAlive())
		{
			if(this.entityToAttack != null)
			{
				if(this.entityToAttack instanceof EntityPlayer && this.shouldAttackPlayer((EntityPlayer) this.entityToAttack))
				{
					if(this.entityToAttack.getDistanceSqToEntity(this) < 16.0D)
					{
						this.teleportRandomly();
					}

					this.teleportDelay = 0;
				}
				else if(this.entityToAttack.getDistanceSqToEntity(this) > 256.0D && this.teleportDelay++ >= 30 && this.teleportToEntity(this.entityToAttack))
				{
					this.teleportDelay = 0;
				}
			}
			else
			{
				this.setScreaming(false);
				this.teleportDelay = 0;
			}
		}

		super.onLivingUpdate();
	}

	/**
	 * Teleport the ender spider to a random nearby position
	 */
	protected boolean teleportRandomly()
	{
		double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
		double d1 = this.posY + (double) (this.rand.nextInt(64) - 32);
		double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
		return this.teleportTo(d0, d1, d2);
	}

	/**
	 * Teleport the ender spider to another entity
	 */
	protected boolean teleportToEntity(Entity par1Entity)
	{
		Vec3 vec3 = Vec3.createVectorHelper(this.posX - par1Entity.posX,
				this.boundingBox.minY + (double) (this.height / 2.0F) - par1Entity.posY + (double) par1Entity.getEyeHeight(), this.posZ - par1Entity.posZ);
		vec3 = vec3.normalize();
		double d0 = 16.0D;
		double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
		double d2 = this.posY + (double) (this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
		double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
		return this.teleportTo(d1, d2, d3);
	}

	/**
	 * Teleport the ender spider
	 */
	protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_)
	{
		EnderTeleportEvent event = new EnderTeleportEvent(this, p_70825_1_, p_70825_3_, p_70825_5_, 0);
		if(MinecraftForge.EVENT_BUS.post(event))
		{
			return false;
		}
		double d3 = this.posX;
		double d4 = this.posY;
		double d5 = this.posZ;
		this.posX = event.targetX;
		this.posY = event.targetY;
		this.posZ = event.targetZ;
		boolean flag = false;
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posY);
		int k = MathHelper.floor_double(this.posZ);

		if(this.worldObj.blockExists(i, j, k))
		{
			boolean flag1 = false;

			while(!flag1 && j > 0)
			{
				Block block = this.worldObj.getBlock(i, j - 1, k);

				if(block.getMaterial().blocksMovement())
				{
					flag1 = true;
				}
				else
				{
					--this.posY;
					--j;
				}
			}

			if(flag1)
			{
				this.setPosition(this.posX, this.posY, this.posZ);

				if(this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
				{
					flag = true;
				}
			}
		}

		if(!flag)
		{
			this.setPosition(d3, d4, d5);
			return false;
		}
		else
		{
			short short1 = 128;

			for(int l = 0; l < short1; ++l)
			{
				double d6 = (double) l / ((double) short1 - 1.0D);
				float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * (double) this.width * 2.0D;
				double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double) this.height;
				double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * (double) this.width * 2.0D;
				this.worldObj.spawnParticle("portal", d7, d8, d9, (double) f, (double) f1, (double) f2);
			}

			this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
			this.playSound("mob.endermen.portal", 1.0F, 1.0F);
			return true;
		}
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if(this.isEntityInvulnerable())
		{
			return false;
		}
		else
		{
			this.setScreaming(true);

			if(par1DamageSource instanceof EntityDamageSource && par1DamageSource.getEntity() instanceof EntityPlayer)
			{
				this.isAggressive = true;
			}

			if(par1DamageSource instanceof EntityDamageSourceIndirect)
			{
				this.isAggressive = false;

				for(int i = 0; i < 64; ++i)
				{
					if(this.teleportRandomly())
					{
						return true;
					}
				}

				return super.attackEntityFrom(par1DamageSource, par2);
			}
			else
			{
				return super.attackEntityFrom(par1DamageSource, par2);
			}
		}
	}

	public boolean isScreaming()
	{
		return this.dataWatcher.getWatchableObjectByte(18) > 0;
	}

	public void setScreaming(boolean par1)
	{
		this.dataWatcher.updateObject(18, Byte.valueOf((byte) (par1 ? 1 : 0)));
	}

	public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
	{
		Object p_110161_1_1 = super.onSpawnWithEgg(p_110161_1_);

		if(this.worldObj.rand.nextInt(100) == 0)
		{
			EntitySkeleton entityskeleton = new EntitySkeleton(this.worldObj);
			entityskeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
			entityskeleton.onSpawnWithEgg((IEntityLivingData) null);
			this.worldObj.spawnEntityInWorld(entityskeleton);
			entityskeleton.mountEntity(this);
		}

		if(p_110161_1_1 == null)
		{
			p_110161_1_1 = new EntitySpider.GroupData();

			if(this.worldObj.difficultySetting == EnumDifficulty.HARD && this.worldObj.rand.nextFloat() < 0.1F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ))
			{
				((EntitySpider.GroupData) p_110161_1_1).func_111104_a(this.worldObj.rand);
			}
		}

		if(p_110161_1_1 instanceof EntitySpider.GroupData)
		{
			int i = ((EntitySpider.GroupData) p_110161_1_1).field_111105_a;

			if(i > 0 && Potion.potionTypes[i] != null)
			{
				this.addPotionEffect(new PotionEffect(i, Integer.MAX_VALUE));
			}
		}

		return (IEntityLivingData) p_110161_1_1;
	}

	public static class GroupData implements IEntityLivingData{

		public int field_111105_a;
		private static final String __OBFID = "CL_00001700";

		public void func_111104_a(Random p_111104_1_)
		{
			int i = p_111104_1_.nextInt(5);

			if(i <= 1)
			{
				this.field_111105_a = Potion.moveSpeed.id;
			}
			else if(i <= 2)
			{
				this.field_111105_a = Potion.damageBoost.id;
			}
			else if(i <= 3)
			{
				this.field_111105_a = Potion.regeneration.id;
			}
			else if(i <= 4)
			{
				this.field_111105_a = Potion.invisibility.id;
			}
		}
	}
}
