package net.bfox1.hardcoretrees.common.items;

import net.bfox1.hardcoretrees.common.util.HandSawType;
import net.bfox1.hardcoretrees.common.util.SawBladeMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by bfox1 on 12/11/2014.
 */
public class HandSaw extends Item {

    private HandSawType type;
    private int durability;
    private SawBladeMaterial material;
    public HandSaw(HandSawType type)
    {
        this.type = type;
        this.durability = type.getDurability();
        this.material = type.getMaterial();
        this.setUnlocalizedName("hct:" + type.getName() + "_saw");
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    public int getDurability()
    {
        return durability;
    }

    public void setDurability(int amount)
    {
        this.durability = amount;
    }

    public SawBladeMaterial getMaterial()
    {
        return material;
    }
}
