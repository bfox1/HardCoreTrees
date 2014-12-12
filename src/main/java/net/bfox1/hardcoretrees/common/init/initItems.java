package net.bfox1.hardcoretrees.common.init;

import net.bfox1.hardcoretrees.common.HardCoreTrees;
import net.bfox1.hardcoretrees.common.helper.ModelHelper;
import net.bfox1.hardcoretrees.common.items.ItemSawBlade;
import net.bfox1.hardcoretrees.common.items.SawDust;
import net.bfox1.hardcoretrees.common.items.SteelIngot;
import net.bfox1.hardcoretrees.common.reference.Reference;
import net.bfox1.hardcoretrees.common.util.BladeType;
import net.bfox1.hardcoretrees.common.util.HandSawType;
import net.bfox1.hardcoretrees.common.util.SawBladeMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by bfox1 on 12/8/2014.
 */
public class initItems {

    public static Item steelIngot;
    public static Item sawDust;


    public static void init()
    {
         steelIngot = new SteelIngot();
        sawDust = new SawDust();

        registerItem(steelIngot, "SteelIngot");

        BladeType.createItem();
        HandSawType.createItem();
        registerItem(sawDust, "SawDust");

    }

    private static void registerItem(Item item, String name)
    {
        GameRegistry.registerItem(item, name);
        HardCoreTrees.proxy.registerItem(item, "hct:" + name); //<<Temporary placement to see if works.
    }
    private static void registerMetaItem(Item item, int metaData, String name)
    {
        GameRegistry.registerItem(item, name + metaData);
        ModelHelper.registerItem(item, metaData, "hct:" + name + metaData);
    }
}
