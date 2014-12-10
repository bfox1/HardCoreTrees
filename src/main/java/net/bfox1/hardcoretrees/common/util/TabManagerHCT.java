package net.bfox1.hardcoretrees.common.util;

import net.bfox1.hardcoretrees.common.init.initItems;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by bfox1 on 12/8/2014.
 */
public class TabManagerHCT extends CreativeTabs {

    public TabManagerHCT (int id, String name)
    {
        super(id, name);
    }

    public static final TabManagerHCT BLOCKS = new TabManagerHCT(CreativeTabs.getNextID(), "HardCore Trees Block")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return initItems.steelIngot;
        }
    };

    public static final TabManagerHCT ITEMS = new TabManagerHCT(CreativeTabs.getNextID(), "HardCore Trees Items")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return initItems.steelIngot;
        }
    };

    @Override
    public Item getTabIconItem() {
        return null;
    }
}
