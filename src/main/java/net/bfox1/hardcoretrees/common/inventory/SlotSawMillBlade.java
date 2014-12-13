package net.bfox1.hardcoretrees.common.inventory;

import net.bfox1.hardcoretrees.common.tileentity.TileEntitySawMill;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * Created by bfox1 on 12/12/2014.
 */
public class SlotSawMillBlade extends Slot {
    public SlotSawMillBlade(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        System.out.println(stack);
        return TileEntitySawMill.isItemSawBlade(stack);
    }


}
