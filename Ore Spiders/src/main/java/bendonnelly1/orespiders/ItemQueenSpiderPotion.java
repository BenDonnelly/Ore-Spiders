package bendonnelly1.orespiders;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class ItemQueenSpiderPotion extends Item{

	private String name = "queenSpiderPotion";
	
	public ItemQueenSpiderPotion()
	{
		this.setUnlocalizedName(OreSpiders.MOD_ID + "." + name);
		this.setTextureName(OreSpiders.MOD_ID + ":" + "potion_queen_spider");
		this.setCreativeTab(CreativeTabs.tabMisc);
		GameRegistry.registerItem(this, name);
	}
	
}
