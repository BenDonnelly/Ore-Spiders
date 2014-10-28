package bendonnelly1.orespiders.entity;

import bendonnelly1.orespiders.OreSpiders;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDiamondSpider extends EntitySpider {

    public EntityDiamondSpider(World world) {
        super(world);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(45.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.900000011920929D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity
     * has recently been hit by a player. @param lootEnchantLevel - Level of
     * Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(boolean hitByPlayer, int lootEnchantLevel) {
        super.dropFewItems(hitByPlayer, lootEnchantLevel);
        int ranNum, amountToDrop, amountToDropWithLootEnchant;
        ranNum = this.rand.nextInt(2);
        amountToDrop = this.rand.nextInt(3 + 1);
        amountToDropWithLootEnchant = (lootEnchantLevel + 1) * amountToDrop;

        if (!(this.isBurning())) {
            if (amountToDrop != 0) {
                if (lootEnchantLevel == 0) {
                    if (ranNum == 1) {
                        this.dropItem(OreSpiders.diamond_nugget, amountToDrop);
                    }
                    this.dropItem(Items.spider_eye, 1);
                } else {
                    this.dropItem(OreSpiders.diamond_nugget, amountToDropWithLootEnchant);
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
    public boolean attackEntityAsMob(Entity ent) {
        if (super.attackEntityAsMob(ent)) {
            if (ent instanceof EntityLivingBase) {
                ((EntityLivingBase) ent).addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 1));
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this
     * entity.
     */
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double(this.posY);

        if (i <= 15 && super.getCanSpawnHere()) {
            return true;
        } else {
            return false;
        }
    }

}
