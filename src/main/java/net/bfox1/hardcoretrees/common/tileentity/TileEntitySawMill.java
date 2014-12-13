package net.bfox1.hardcoretrees.common.tileentity;

import net.bfox1.hardcoretrees.common.blocks.SawMill;
import net.bfox1.hardcoretrees.common.inventory.ContainerSawMill;
import net.bfox1.hardcoretrees.common.items.ItemSawBlade;
import net.bfox1.hardcoretrees.common.util.BladeType;
import net.bfox1.hardcoretrees.common.util.SawMillRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by bfox1 on 12/11/2014.
 */
public class TileEntitySawMill extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{

    private ItemSawBlade type;
    private static final int[] topSlot = new int[] {0};
    private static final int[] sideSlots = new int[] {1, 2, 3};
    private static final int[] bottomSlots = new int[] {4, 5};
    private ItemStack[] sawMillItemStack = new ItemStack[6];
    private int sawMillCutTime;
    private int cutTime;
    private int currentCutTime;
    private int woodCutTime;
    private String sawMillName;
    private static ItemStack cutSlot;


    public TileEntitySawMill()
    {
        super();
    }

    @Override
    public int getSizeInventory()
    {
        return this.sawMillItemStack.length;
    }
    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.sawMillItemStack[index];
    }
    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.sawMillItemStack[index] != null)
        {
            ItemStack itemstack;

            if (this.sawMillItemStack[index].stackSize <= count)
            {
                itemstack = this.sawMillItemStack[index];
                this.sawMillItemStack[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.sawMillItemStack[index].splitStack(count);

                if (this.sawMillItemStack[index].stackSize == 0)
                {
                    this.sawMillItemStack[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }
    @Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.sawMillItemStack[index] != null)
        {
            ItemStack itemstack = this.sawMillItemStack[index];
            this.sawMillItemStack[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }
    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        boolean flag = stack != null && stack.isItemEqual(this.sawMillItemStack[index]) && ItemStack.areItemStackTagsEqual(stack, this.sawMillItemStack[index]);
        this.sawMillItemStack[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index == 1 && !flag)
        {
            this.woodCutTime = this.func_174904_a(stack);
            this.currentCutTime = 0;
            this.markDirty();
        }
    }
    @Override
    public String getName()
    {
        return this.hasCustomName() ? this.sawMillName : "hct:conatiner.sawmill";
    }
    @Override
    public boolean hasCustomName()
    {
        return this.sawMillName != null && this.sawMillName.length() > 0;
    }

    public void setCustomInventoryName(String p_145951_1_)
    {
        this.sawMillName = p_145951_1_;
    }
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.sawMillItemStack = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.sawMillItemStack.length)
            {
                this.sawMillItemStack[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.sawMillCutTime = compound.getShort("BurnTime");
        this.currentCutTime = compound.getShort("CookTime");
        this.woodCutTime = compound.getShort("CookTimeTotal");
        this.cutTime = getItemBurnTime(this.sawMillItemStack[1]);

        if (compound.hasKey("CustomName", 8))
        {
            this.sawMillName = compound.getString("CustomName");
        }
    }
    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("BurnTime", (short)this.sawMillCutTime);
        compound.setShort("CookTime", (short)this.currentCutTime);
        compound.setShort("CookTimeTotal", (short)this.woodCutTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.sawMillItemStack.length; ++i)
        {
            if (this.sawMillItemStack[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.sawMillItemStack[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        compound.setTag("Items", nbttaglist);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.sawMillName);
        }
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isBurning()
    {
        return this.sawMillCutTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory p_174903_0_)
    {
        return p_174903_0_.getField(0) > 0;
    }
    @Override
    public void update()
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.sawMillCutTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (!this.isBurning() && (this.sawMillItemStack[2] == null || this.sawMillItemStack[1] == null))
            {
                if (!this.isBurning() && this.currentCutTime > 0)
                {
                    this.currentCutTime = MathHelper.clamp_int(this.currentCutTime - 2, 0, this.woodCutTime);
                }
            }
            else
            {
                if (!this.isBurning() && this.canCut()  && isItemSawBlade(this.getStackInSlot(0)))
                {
                    System.out.println(isBurning() + " " + canCut());

                    this.cutTime = this.sawMillCutTime = getItemBurnTime(this.sawMillItemStack[2]);

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (this.sawMillItemStack[2] != null)
                        {
                            --this.sawMillItemStack[2].stackSize;

                            if (this.sawMillItemStack[2].stackSize == 0)
                            {
                                this.sawMillItemStack[2] = sawMillItemStack[2].getItem().getContainerItem(sawMillItemStack[2]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canCut() && isItemSawBlade(this.getStackInSlot(0)))
                {
                    ++this.currentCutTime;

                    if (this.currentCutTime == this.woodCutTime)
                    {
                        this.currentCutTime = 0;
                        //this.woodCutTime = this.func_174904_a(this.sawMillItemStack[1]);
                        this.woodCutTime = getCutTime(this.sawMillItemStack[0]);
                        this.cutItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.currentCutTime = 0;
                }
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                SawMill.setState(this.isBurning(), this.worldObj, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    public int func_174904_a(ItemStack p_174904_1_)
    {
        return 200;
    }

    private boolean canCut()
    {
        if (this.sawMillItemStack[1] == null)
        {

            return false;
        }
        else
        {


            ItemStack itemstack = SawMillRecipes.instance().getCutResults(this.sawMillItemStack[1]);
            if (itemstack == null) return false;
            if (this.sawMillItemStack[4] == null) return true;
            if (!this.sawMillItemStack[4].isItemEqual(itemstack)) return false;
            int result = sawMillItemStack[4].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.sawMillItemStack[4].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        }
    }


    public int calcAmount(ItemStack stack, ItemStack slotStack)
    {
        Item item = slotStack.getItem();
        for(BladeType type: BladeType.values())
        {
            if (item instanceof ItemSawBlade)
            {

                if(item == type.getSawItem())
                {
                   return stack.stackSize = ((ItemSawBlade) item).getType().getItemAmount();
                }
            }

        }
        return stack.stackSize;
    }

    public void cutItem()
    {
        if (this.canCut())
        {
            ItemStack itemstack = SawMillRecipes.instance().getCutResults(this.sawMillItemStack[1]);
            ItemStack itemstack1 = SawMillRecipes.instance().getDustResults(itemstack);
            itemstack.stackSize = calcAmount(itemstack, this.sawMillItemStack[0]);
            if (this.sawMillItemStack[4] == null)
            {
                this.sawMillItemStack[4] = itemstack.copy();
                this.sawMillItemStack[5] = itemstack1.copy();
            }
            else if (this.sawMillItemStack[4].getItem() == itemstack.getItem())
            {
                this.sawMillItemStack[4].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
                this.sawMillItemStack[5].stackSize += itemstack1.stackSize;
            }


            --this.sawMillItemStack[1].stackSize;

            if (this.sawMillItemStack[1].stackSize <= 0)
            {
                this.sawMillItemStack[1] = null;
            }
        }
    }

    public static int getItemBurnTime(ItemStack fuelItem)
    {
        if (fuelItem == null)
        {
            return 0;
        }
        else
        {
            Item item = fuelItem.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab)
                {
                    return 150;
                }

                if (block.getMaterial() == Material.wood)
                {
                    return 300;
                }

                if (block == Blocks.coal_block)
                {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe)item).getMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(fuelItem);
        }
    }

    public static int getCutTime(ItemStack stack)
    {
        Item item = stack.getItem();
        int speed = ((ItemSawBlade)item).getType().getCutSpeed();

        return speed;
    }

    public static boolean isItemFuel(ItemStack p_145954_0_)
    {
        return getItemBurnTime(p_145954_0_) > 0;
    }

    public static boolean isItemSawBlade(ItemStack stack)
    {
        return BladeType.isValid(stack);
    }

    public static Item getWoodItem(ItemStack stack)
    {
        if(stack == null) return null;

        Item item = stack.getItem();

        if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
        {
            Block block = Block.getBlockFromItem(item);
            if(block.equals(Blocks.planks))
            {
                cutSlot = new ItemStack(block);
                return item;
            }
            if(block.equals(Blocks.log))
            {
                cutSlot = new ItemStack(block);
                return item;
            }
        }
        return null;

    }

    public static boolean isItemWood(ItemStack stack)
    {
       return getWoodItem(stack) != null;
    }
    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }
    @Override
    public void openInventory(EntityPlayer player) {}
    @Override
    public void closeInventory(EntityPlayer player) {}
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        System.out.println(index + " " + stack);
        if(index == 2) {
            if (isItemFuel(stack)) {
                return true;
            }
        }
        if(index == 0)
        {
            return isItemSawBlade(stack);
        }
        if(index == 1)
        {
            return isItemWood(stack);
        }
        return false;
    //    return index == 2 ? false : (index == 1 ? isItemFuel(stack) : true);
    }
    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? bottomSlots : (side == EnumFacing.UP ? topSlot : sideSlots);
    }
    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }
    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.water_bucket && item != Items.bucket)
            {
                return false;
            }
        }

        return true;
    }
    @Override
    public String getGuiID()
    {
        return "hct:SawMill";
    }
    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        System.out.println("CREATING CONTAINER");
        return new ContainerSawMill(playerInventory, this);
    }
    @Override
    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.sawMillCutTime;
            case 1:
                return this.cutTime;
            case 2:
                return this.currentCutTime;
            case 3:
                return this.woodCutTime;
            default:
                return 0;
        }
    }
    @Override
    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.sawMillCutTime = value;
                break;
            case 1:
                this.cutTime = value;
                break;
            case 2:
                this.currentCutTime = value;
                break;
            case 3:
                this.woodCutTime = value;
        }
    }
    @Override
    public int getFieldCount()
    {
        return 4;
    }
    @Override
    public void clear()
    {
        for (int i = 0; i < this.sawMillItemStack.length; ++i)
        {
            this.sawMillItemStack[i] = null;
        }
    }
}
