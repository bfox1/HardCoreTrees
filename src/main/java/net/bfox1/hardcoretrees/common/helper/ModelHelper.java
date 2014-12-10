package net.bfox1.hardcoretrees.common.helper;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by bfox1 on 12/10/2014.
 */
public class ModelHelper {


    public static void registerItem(Item item , int metadata, String name)
    {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        mesher.register(item, metadata, new ModelResourceLocation(name, "inventory"));
    }

    public static void registerBlock(Block block, int metadata, String name)
    {
        registerItem(Item.getItemFromBlock(block), metadata, name);
    }

    public static void registerBlock(Block block, String name)
    {
        registerBlock(block, 0, name);
    }

    public static void registerItem(Item item, String name)
    {
        registerItem(item, 0, name);
    }

}
