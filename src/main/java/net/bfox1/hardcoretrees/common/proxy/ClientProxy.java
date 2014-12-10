package net.bfox1.hardcoretrees.common.proxy;

import net.bfox1.hardcoretrees.common.helper.ModelHelper;
import net.bfox1.hardcoretrees.common.init.initItems;
import net.bfox1.hardcoretrees.common.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by bfox1 on 12/9/2014.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderInfo()
    {

    }

    @Override
    public void registerItem(Item item, String name)
    {
        ModelHelper.registerItem(item, name);
    }
}
