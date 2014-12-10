package net.bfox1.hardcoretrees.common.init;

import net.bfox1.hardcoretrees.common.HardCoreTrees;
import net.bfox1.hardcoretrees.common.items.SteelIngot;
import net.bfox1.hardcoretrees.common.reference.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by bfox1 on 12/8/2014.
 */
public class initItems {

    public static Item steelIngot;

    public static void init()
    {
         steelIngot = new SteelIngot();
        registerItem(steelIngot, "SteelIngot");
    }

    public static void registerItem(Item item, String name)
    {
        GameRegistry.registerItem(item, name);
        HardCoreTrees.proxy.registerItem(item, "hct:" + name); //<<Temporary placement to see if works.
    }
}
