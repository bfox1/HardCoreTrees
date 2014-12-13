package net.bfox1.hardcoretrees.client;

import net.bfox1.hardcoretrees.common.inventory.ContainerSawMill;
import net.bfox1.hardcoretrees.common.reference.Reference;
import net.bfox1.hardcoretrees.common.tileentity.TileEntitySawMill;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by bfox1 on 12/12/2014.
 */
public class GuiSawMill extends GuiContainer {

    private static final ResourceLocation guiSawMill = new ResourceLocation(Reference.MODID, "/textures/gui/container/saw_mill.png");
    private IInventory sm;

    public GuiSawMill(InventoryPlayer player, IInventory sawMill) {
        super(new ContainerSawMill(player, sawMill));
        this.sm = sawMill;

        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        //String s = this.sm.hasCustomName() ? this.sm.getName() : I18n.format(this.sm.getName());
        String s = I18n.format("SawMill");
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752); // Draws the name roughly in the same spot as in the furnace - Skymmer
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(guiSawMill);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

        int i1;

        Item item = TileEntitySawMill.getWoodItem(this.sm.getStackInSlot(1));
        if(Block.getBlockFromItem(item) instanceof BlockLog)
        {
            if (TileEntitySawMill.isBurning(sm)) {

                i1 = getCutProgress(16);
                this.drawTexturedModalRect(k + 80, l + 31, 176, 31, 16, 17);
                this.drawTexturedModalRect(k + 80, l + 40, 192, 39, i1 + 1, 1);


            }else {
                this.drawTexturedModalRect(k + 80, l + 31, 176, 31, 16, 17);
            }
        } else if(Block.getBlockFromItem(item) instanceof BlockPlanks)
        {
            if(TileEntitySawMill.isBurning(sm) )
            {
                i1 = getCutProgress(16);
                this.drawTexturedModalRect(k + 80, l + 31, 176, 48, 16, 17);
                this.drawTexturedModalRect(k + 80, l + 40, 192, 56, i1 + 1, 1);
            }else {
                this.drawTexturedModalRect(k + 80, l + 31, 176, 48, 16, 17);
            }
        }
    }


    private void drawTextureModel(int x, int y, int textureX, int textureY, int width, int height)
    {

    }

    private int getCutProgress(int time)
    {
        return this.sm.getField(2)*time /this.sm.getField(3);
    }
}
