package bendonnelly1.orespiders;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import bendonnelly1.orespiders.entity.EntityOrbWeaver;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemOrbWeaver extends Item{

	private String name = "orbWeaver";

	public ItemOrbWeaver(){
		this.setUnlocalizedName(OreSpiders.MOD_ID + "." + name);
		this.setTextureName(OreSpiders.MOD_ID + ":" + "orb_weaver");
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setMaxStackSize(1);
		this.setMaxDamage(75);
		GameRegistry.registerItem(this, name);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if(!entityPlayer.capabilities.isCreativeMode)
		{
			itemStack.damageItem(1, entityPlayer);
		}

		world.playSoundAtEntity(entityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if(!world.isRemote)
		{
			world.spawnEntityInWorld(new EntityOrbWeaver(world, entityPlayer));
		}

		return itemStack;
	}
	
	@Override
    public boolean hasEffect(ItemStack itemStack)
    {
        return true;
    }


}
