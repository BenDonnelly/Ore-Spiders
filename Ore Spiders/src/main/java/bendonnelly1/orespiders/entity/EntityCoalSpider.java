package bendonnelly1.orespiders.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityCoalSpider extends EntityOreSpider{

	public EntityCoalSpider(World world){
		super(world);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(22.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.800000011920929D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.5D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
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
						this.dropItem(Items.coal, amountToDrop);
					}
					this.dropItem(Items.spider_eye, 1);
				}
				else
				{
					this.dropItem(Items.coal, amountToDropWithLootEnchant);
					this.dropItem(Items.spider_eye, amountToDropWithLootEnchant);
				}
			}
		}
	}

	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden
	 * by each mob to define their attack.
	 */
	@Override
	public boolean attackEntityAsMob(Entity ent)
	{
		if(super.attackEntityAsMob(ent))
		{
			if(ent instanceof EntityLivingBase)
			{
				((EntityLivingBase) ent).addPotionEffect(new PotionEffect(Potion.hunger.id, 100, 0));
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public boolean getCanSpawnHere()
	{
		return true;
	}

}
