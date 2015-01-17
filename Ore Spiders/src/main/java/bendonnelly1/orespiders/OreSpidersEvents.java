package bendonnelly1.orespiders;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import bendonnelly1.orespiders.entity.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class OreSpidersEvents{

	private Random rand = new Random();
	private World world;
	private EntityLivingBase queenSpider;
	private int queenSpiderHealth;
	private int randNum;

	@SubscribeEvent
	public void queenSpiderHealthHandling(LivingHurtEvent event)
	{
		world = event.entity.worldObj;
		queenSpider = event.entityLiving;

		double x, y, z;
		x = queenSpider.posX;
		y = queenSpider.posY;
		z = queenSpider.posZ;

		if(event.entityLiving instanceof EntityQueenSpider)
		{

			randNum = rand.nextInt(2);
			queenSpiderHealth = (int) (Math.round(queenSpider.getHealth()));
			EntityLivingBase spiderArr[] = {new EntityCoalSpider(world), new EntityDiamondSpider(world), new EntityEmeraldSpider(world), new EntityEnderSpider(world), new EntityGoldSpider(world), new EntityIronSpider(world), new EntityLapisSpider(world), new EntityObsidianSpider(world), new EntityQuartzSpider(world), new EntityRedstoneSpider(world), new EntitySpider(world), new EntityCaveSpider(world)};
			EntityLivingBase randomSpider = spiderArr[rand.nextInt(spiderArr.length)];
			
			PotionEffect potionArr[] = {new PotionEffect(Potion.moveSpeed.id, 60, randNum), new PotionEffect(Potion.jump.id, 60, randNum), new PotionEffect(Potion.regeneration.id, 60, randNum), new PotionEffect(Potion.resistance.id, 60, randNum), new PotionEffect(Potion.invisibility.id, 60, randNum)};
			PotionEffect randomPotion = potionArr[rand.nextInt(potionArr.length)];
			// OreSpiders.out(queenSpider.getPhase() +"|" + queenSpiderHealth);
			if(queenSpiderHealth <= 175 && queenSpiderHealth > 100 && randNum == 1)
			{
				randNum = rand.nextInt(2);
				if(world.difficultySetting == EnumDifficulty.NORMAL && randNum == 0)
				{
					System.out.println(randomPotion);
					randomSpider.addPotionEffect(randomPotion);
				}
				else if(world.difficultySetting == EnumDifficulty.HARD)
				{
					System.out.println(randomPotion);
					randomSpider.addPotionEffect(randomPotion);
				}
				randomSpider.setPosition(x, y, z);
				randomSpider.worldObj.spawnEntityInWorld(randomSpider);

				for(int i = 0; i < 10; ++i)
				{
					double d0 = this.rand.nextGaussian() * 0.02D;
					double d1 = this.rand.nextGaussian() * 0.02D;
					double d2 = this.rand.nextGaussian() * 0.02D;
					Minecraft.getMinecraft().theWorld.spawnParticle("flame", x + (double) (this.rand.nextFloat() * queenSpider.width * 2.0F) - (double) queenSpider.width, y + 0.5D + (double) (this.rand.nextFloat() * queenSpider.height), z + (double) (this.rand.nextFloat() * queenSpider.width * 2.0F) - (double) queenSpider.width, d0, d1, d2);
					Minecraft.getMinecraft().theWorld.spawnParticle("smoke", x + (double) (this.rand.nextFloat() * queenSpider.width * 2.0F) - (double) queenSpider.width, y + 0.5D + (double) (this.rand.nextFloat() * queenSpider.height), z + (double) (this.rand.nextFloat() * queenSpider.width * 2.0F) - (double) queenSpider.width, d0, d1, d2);
				}

			}
		}

	}


}
