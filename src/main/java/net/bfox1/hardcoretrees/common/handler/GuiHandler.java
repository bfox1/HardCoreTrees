package net.bfox1.hardcoretrees.common.handler;

import net.bfox1.hardcoretrees.common.inventory.ContainerSawMill;
import net.bfox1.hardcoretrees.common.tileentity.TileEntitySawMill;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by bfox1 on 12/11/2014.
 */
public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
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
}
