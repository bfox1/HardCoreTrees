package net.bfox1.hardcoretrees.common.proxy;

import net.bfox1.hardcoretrees.client.GuiSawMill;
import net.bfox1.hardcoretrees.common.blocks.SawMill;
import net.bfox1.hardcoretrees.common.inventory.ContainerSawMill;
import net.bfox1.hardcoretrees.common.tileentity.TileEntitySawMill;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by bfox1 on 12/9/2014.
 */
public abstract class CommonProxy implements HctProxy, IGuiHandler {
    @Override
    public void registerRenderInfo() {

    }

    public void registerItem(Item item, String name)
    {}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        x = SawMill.getBlockPosX();
        y = SawMill.getBlockPosY();
        z = SawMill.getBlockPosZ();
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        if(te != null & te instanceof TileEntitySawMill)
        {
            TileEntitySawMill tileEntitySawMill = (TileEntitySawMill)te;
            return new ContainerSawMill(player.inventory, tileEntitySawMill);
        }else
        {
            return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

            return null;

    }

}
