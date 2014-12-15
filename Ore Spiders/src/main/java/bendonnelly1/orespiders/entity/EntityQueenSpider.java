package bendonnelly1.orespiders.entity;

import scala.reflect.internal.Trees.ThisSubstituter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityQueenSpider extends EntityOreSpider implements IBossDisplayData{

	private int queenSpiderHealth;
	public int phase;

	public EntityQueenSpider(World world){
		super(world);
		this.setSize(2.8F, 1.5F);
		this.isImmuneToFire = true;
		this.experienceValue = 100;
		phase = 0;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.99900011920929D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(9.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	private void playHealthEffect()
	{
		for(int i = 0; i < 4; ++i)
		{
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.worldObj.spawnParticle("heart", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
		}
	}

	@Override
	public void onLivingUpdate()
	{
		queenSpiderHealth = (int) (Math.round(this.getHealth()));

		if(getPhase() == 2)
		{
			if(this.ticksExisted % 60 == 0)
			{
				this.heal(5.0F);
				playHealthEffect();
			}
		}
		super.onLivingUpdate();
	}

	public int getPhase()
	{
		if(queenSpiderHealth <= 200 && queenSpiderHealth > 175)
		{
			phase = 0;
			printPhaseAndHP();
		}
		else if(queenSpiderHealth <= 175 && queenSpiderHealth > 100)
		{
			phase = 1;
			printPhaseAndHP();
		}
		else if(queenSpiderHealth <= 100 && queenSpiderHealth > 50)
		{
			phase = 2;
			printPhaseAndHP();
		}
	/*	else if(queenSpiderHealth <= 25)
		{
			phase = 3;
			printPhaseAndHP();
		}*/
		return phase;
	}

	@Override
	public void onDeath(DamageSource damageSource)
	{
		EntityRedstoneSpider redstoneSpider = new EntityRedstoneSpider(this.worldObj);
		redstoneSpider.setPosition(this.posX, this.posY, this.posZ);
		this.worldObj.spawnEntityInWorld(redstoneSpider);
	}

	public void printPhaseAndHP()
	{
		System.out.println("Phase: " + phase + "   HP:" + queenSpiderHealth);
	}

}
