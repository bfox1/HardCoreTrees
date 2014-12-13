package net.bfox1.hardcoretrees.common.proxy;

import net.bfox1.hardcoretrees.client.GuiSawMill;
import net.bfox1.hardcoretrees.common.blocks.SawMill;
import net.bfox1.hardcoretrees.common.helper.ModelHelper;
import net.bfox1.hardcoretrees.common.init.initBlocks;
import net.bfox1.hardcoretrees.common.init.initItems;
import net.bfox1.hardcoretrees.common.inventory.ContainerSawMill;
import net.bfox1.hardcoretrees.common.reference.Reference;
import net.bfox1.hardcoretrees.common.tileentity.TileEntitySawMill;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.Sys;

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

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        x = SawMill.getBlockPosX();
        y = SawMill.getBlockPosY();
        z = SawMill.getBlockPosZ();
        System.out.println(x + " " + y + " " + z);
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        System.out.println("First - CLIENTGUI" + ID + te);
        switch (ID) {
            case 1:
                if (te != null && te instanceof TileEntitySawMill) {
                    System.out.println("Second - CLIENTGUI");
                    //return GUIChest.GUI.buildGUI(IronChestType.values()[ID], player.inventory, (TileEntityIronChest) te);

                    System.out.println("Third - CLIENTGUI");
                    return new GuiSawMill(player.inventory, (TileEntitySawMill) te);
                } else {
                    return null;
                }
            default:
                return null;
        }

    }
}
