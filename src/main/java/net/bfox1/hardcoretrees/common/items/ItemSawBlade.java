package net.bfox1.hardcoretrees.common.items;

import com.google.common.collect.Maps;
import net.bfox1.hardcoretrees.common.helper.ModelHelper;
import net.bfox1.hardcoretrees.common.util.BladeType;
import net.bfox1.hardcoretrees.common.util.SawBladeMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Map;

/**
 * Created by bfox1 on 12/11/2014.
 */
public class ItemSawBlade extends Item {

    private static SawBladeMaterial material;

    private static int durability;

    private BladeType type;

    private static String materialName;


    public ItemSawBlade(BladeType type)
    {
        this.type = type;
        this.setCreativeTab(CreativeTabs.tabMaterials);
         this.setUnlocalizedName("hct:" + type.getUnlocalizedName() + "_blade");
    }

    public int getDurability()
    {
        return durability;
    }

    public BladeType getType()
    {
        return type;
    }

    public void setDurability(int amount)
    {
        this.durability = amount;
    }

    public String getMaterial()
    {
        return materialName;
    }
}
