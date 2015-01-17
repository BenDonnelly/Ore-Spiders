package bendonnelly1.orespiders.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityOrbWeaver extends EntityThrowable{


	public EntityOrbWeaver(World world){
		super(world);
	}

	public EntityOrbWeaver(World world, EntityLivingBase entityLivingBase){
		super(world, entityLivingBase);
	}

	public EntityOrbWeaver(World world, double d1, double d2, double d3){
		super(world, d1, d2, d3);
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(MovingObjectPosition movingObjectPosition)
	{
		if(this.worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ) == Blocks.air) this.worldObj.setBlock((int) this.posX, (int) this.posY, (int) this.posZ, Blocks.web);
		if(!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
}
