package net.bfox1.hardcoretrees.common.util;

import net.bfox1.hardcoretrees.common.helper.ModelHelper;
import net.bfox1.hardcoretrees.common.items.ItemSawBlade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by bfox1 on 12/11/2014.
 */
public enum BladeType
{
    WOODBLADE(0, "wood", SawBladeMaterial.woodBlade),
    STONEBLADE(1, "stone", SawBladeMaterial.stoneBlade),
    IRONBLADE(2, "iron", SawBladeMaterial.ironBlade),
    DIAMONDBLADE(3, "diamond", SawBladeMaterial.diamondBlade);

    private String unlocalizedName;
    private int metaData;
    private SawBladeMaterial material;
    private ItemSawBlade item;
    private BladeType type;
    private int cutSpeed;
    private int itemAmount;




    private BladeType(int metaData, String name, SawBladeMaterial material)
    {
        this.metaData = metaData;
        this.unlocalizedName = name;
        this.material = material;
        this.itemAmount = material.getItemAmount();
        this.cutSpeed = material.getCutSpeed();
    }

    public int getMetadata()
    {
        return this.metaData;
    }

    public int getCutSpeed()
    {
        return this.cutSpeed;
    }
    public Item getSawItem()
    {
        return item;
    }
    public int getItemAmount()
    {
        return itemAmount;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public SawBladeMaterial getMaterial()
    {
        return this.material;
    }

    private ItemSawBlade generateItem()
    {
        item = new ItemSawBlade(this);
        GameRegistry.registerItem(item, unlocalizedName + "_blade");
        ModelHelper.registerItem(item, "hct:" + unlocalizedName + "_blade");
        return item;
    }

    public static void createItem()
    {
        for(BladeType type : values())
        {
            type.generateItem();
        }
    }

    public static boolean isValid(ItemStack stack)
    {
        if(stack != null) {
            Item item = stack.getItem();
            // for(BladeType type : BladeType.values()) {
            // ItemStack stack1 = new ItemStack(type.getSawItem());
            if (item instanceof ItemSawBlade) {
                return true;
            }
        }
       // }
        return false;
    }
}
