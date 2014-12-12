package net.bfox1.hardcoretrees.common.util;

import net.bfox1.hardcoretrees.common.helper.ModelHelper;
import net.bfox1.hardcoretrees.common.items.HandSaw;
import net.bfox1.hardcoretrees.common.items.ItemSawBlade;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by bfox1 on 12/11/2014.
 */
public enum HandSawType {

    WOODSAW("wood", SawBladeMaterial.woodBlade),
    STONESAW("stone", SawBladeMaterial.stoneBlade),
    IRONSAW("iron", SawBladeMaterial.ironBlade),
    DIAMONDSAW("diamond", SawBladeMaterial.diamondBlade);

    private  String name;
    private  SawBladeMaterial material;
    private  int durability;
    private HandSaw item;
    private HandSawType(String name, SawBladeMaterial material)
    {
        this.name = name;
        this.material = material;
        this.durability = material.getDurability();
    }

    public String getName()
    {
        return this.name;
    }

    public SawBladeMaterial getMaterial()
    {
        return this.material;
    }

    public int getDurability()
    {
        return this.durability;
    }

    private HandSaw generateItem()
    {
        item = new HandSaw(this);
        GameRegistry.registerItem(item, name + "_saw");
        ModelHelper.registerItem(item, "hct:" + name + "_saw");
        return item;
    }

    public static void createItem()
    {
        for(HandSawType type : values())
        {
            type.generateItem();
        }
    }

}
