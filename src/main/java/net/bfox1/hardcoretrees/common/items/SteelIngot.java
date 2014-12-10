package net.bfox1.hardcoretrees.common.items;

import net.bfox1.hardcoretrees.common.util.TabManagerHCT;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by bfox1 on 12/9/2014.
 */
public class SteelIngot extends Item {

   // public static String name = "SteelIngot";

    public SteelIngot()
    {
        super();
       this.setUnlocalizedName("SteelIngot");
        this.setCreativeTab(TabManagerHCT.ITEMS);
      //  GameRegistry.registerItem(this, name);


    }

   // public String getUnlocalizedName(ItemStack stack)
    {
    //    return name;
    }

}
