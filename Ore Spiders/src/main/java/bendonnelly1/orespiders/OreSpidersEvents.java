package bendonnelly1.orespiders;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import bendonnelly1.orespiders.entity.EntityDiamondSpider;
import bendonnelly1.orespiders.entity.EntityEmeraldSpider;
import bendonnelly1.orespiders.entity.EntityIronSpider;
import bendonnelly1.orespiders.entity.EntityQueenSpider;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class OreSpidersEvents{

	private Random rand = new Random();
	private World world;
	private EntityQueenSpider queenSpider;
	private int queenSpiderHealth;
	private int randNum;

	@SubscribeEvent
	public void queenSpiderHealthHandling(LivingHurtEvent event)
	{
		world = event.entity.worldObj;
		queenSpider = new EntityQueenSpider(world);

		double x, y, z;
		x = queenSpider.posX;
		y = queenSpider.posY;
		z = queenSpider.posZ;

		if(event.entityLiving instanceof EntityQueenSpider)
		{

			randNum = rand.nextInt(2);
			queenSpiderHealth = (int) (Math.round(queenSpider.getHealth()));
			EntityLivingBase spiderArr[] = {new EntityIronSpider(world), new EntityDiamondSpider(world), new EntityEmeraldSpider(world)};
			EntityLivingBase randomSpider = spiderArr[rand.nextInt(spiderArr.length)];

			if(queenSpider.getPhase() == 1 && randNum == 1)
			{

				queenSpider.printPhaseAndHP();

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
