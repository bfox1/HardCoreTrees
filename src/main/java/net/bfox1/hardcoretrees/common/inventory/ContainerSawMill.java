package net.bfox1.hardcoretrees.common.inventory;

import net.bfox1.hardcoretrees.common.tileentity.TileEntitySawMill;
import net.bfox1.hardcoretrees.common.util.SawMillRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by bfox1 on 12/11/2014.
 */
public class ContainerSawMill extends Container {
    private final IInventory tileSawMill;
    private int woodCutTime;
    private int woodTotalTime;
    private int sawMillCutTime;
    private int cutTime;
    public ContainerSawMill(InventoryPlayer p_i45794_1_, IInventory sawMillInventory)
    {
        this.tileSawMill = sawMillInventory;
        this.addSlotToContainer(new SlotSawMillBlade(sawMillInventory, 0, 79, 7)); //sawBlade
        this.addSlotToContainer(new Slot(sawMillInventory, 1, 79, 55)); //Input
        this.addSlotToContainer(new SlotFurnaceFuel(sawMillInventory, 2, 8, 56)); //FUEL
        this.addSlotToContainer(new SlotFurnaceOutput(p_i45794_1_.player,sawMillInventory,  3, 135, 42));//output
        this.addSlotToContainer(new SlotFurnaceOutput(p_i45794_1_.player,sawMillInventory,  4, 135, 59));//output
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(p_i45794_1_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(p_i45794_1_, i, 8 + i * 18, 142));
        }
    }
    @Override
    public void addCraftingToCrafters(ICrafting listener)
    {
        super.addCraftingToCrafters(listener);
        listener.func_175173_a(this, this.tileSawMill);
    }
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.woodCutTime != this.tileSawMill.getField(2))
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileSawMill.getField(2));
            }

            if (this.sawMillCutTime != this.tileSawMill.getField(0))
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileSawMill.getField(0));
            }

            if (this.cutTime != this.tileSawMill.getField(1))
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileSawMill.getField(1));
            }

            if (this.woodTotalTime != this.tileSawMill.getField(3))
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileSawMill.getField(3));
            }
        }

        this.woodCutTime = this.tileSawMill.getField(2);
        this.sawMillCutTime = this.tileSawMill.getField(0);
        this.cutTime = this.tileSawMill.getField(1);
        this.woodTotalTime = this.tileSawMill.getField(3);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data)
    {
        this.tileSawMill.setField(id, data);
    }
    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileSawMill.isUseableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 3 || index == 4)
            {
                if (!this.mergeItemStack(itemstack1, 6, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0 && index != 2)
            {
                if (SawMillRecipes.instance().getCutResults(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntitySawMill.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemstack1);
        }

        return itemstack;
    }
}
