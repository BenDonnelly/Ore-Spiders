package os.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import os.main.Debug;
import os.main.Main;

public class EntityDiamondSpider extends EntitySpider
{
	
	public EntityDiamondSpider(World world) 
	{
		super(world);
	}

	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(45.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.900000011920929D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(35.0D);
	}
	
	/**
	 * Returns the item ID for the item the mob drops on death.
	 */	
	@Override
	protected int getDropItemId()
	{
		return this.isBurning() ? 0 :  Item.silk.itemID;
	}
		
	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
	 * lootEnchantLevel - Level of Looting used to kill this mob.
	 */
	@Override
	protected void dropFewItems(boolean hitByPlayer, int lootEnchantLevel)
	{
		super.dropFewItems(hitByPlayer, lootEnchantLevel);
		int ranNum, amountToDrop, amountToDropWithLootEnchant;
		ranNum = this.rand.nextInt(2);
		amountToDrop = this.rand.nextInt(3 + 1);
		amountToDropWithLootEnchant = (lootEnchantLevel + 1) * amountToDrop;
		
		if(! (this.isBurning()))
		{
			if(amountToDrop != 0)
			{
				if(lootEnchantLevel == 0)
				{
					if(ranNum == 1)
					{
						this.dropItem(Main.diamondNugget.itemID, amountToDrop);
					}
					this.dropItem(Item.spiderEye.itemID, 1);
				}
				else
				{
					this.dropItem(Main.diamondNugget.itemID, amountToDropWithLootEnchant);
					this.dropItem(Item.spiderEye.itemID, amountToDropWithLootEnchant);
				}
			}
		}
	}
	
	/**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
	@Override
	public boolean attackEntityAsMob(Entity ent)
    {
		if (super.attackEntityAsMob(ent))
        {
			if(ent instanceof EntityLivingBase)
			{
				((EntityLivingBase) ent).addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 1));
			}
			return true;
        }
		else
		{
			return false;
		}
    }	

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
	public boolean getCanSpawnHere()
	 {
		 int i = MathHelper.floor_double(this.posY);

		 if (i <= 15 && super.getCanSpawnHere())
		 {
			 Debug.out("Spawning Diamond Spider:" + "X:" + this.posX + " Y:" + this.posY + " Z:" + this.posZ);
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	 }
	
}
