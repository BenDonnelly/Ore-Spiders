package bendonnelly1.orespiders;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import bendonnelly1.orespiders.entity.EntityQueenSpiderPotion;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemQueenSpiderPotion extends Item{

	private String name = "queenSpiderPotion";

	public ItemQueenSpiderPotion(){
		this.setUnlocalizedName(OreSpiders.MOD_ID + "." + name);
		this.setTextureName(OreSpiders.MOD_ID + ":" + "potion_queen_spider");
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setMaxStackSize(1);
		GameRegistry.registerItem(this, name);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if(!entityPlayer.capabilities.isCreativeMode)
		{
			--itemStack.stackSize;
		}

		world.playSoundAtEntity(entityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if(!world.isRemote)
		{
			world.spawnEntityInWorld(new EntityQueenSpiderPotion(world, entityPlayer));
		}

		return itemStack;
	}
	
	@Override
    public boolean hasEffect(ItemStack itemStack)
    {
        return true;
    }


}
