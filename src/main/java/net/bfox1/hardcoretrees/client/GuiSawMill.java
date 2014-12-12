package net.bfox1.hardcoretrees.client;

import net.bfox1.hardcoretrees.common.inventory.ContainerSawMill;
import net.bfox1.hardcoretrees.common.reference.Reference;
import net.bfox1.hardcoretrees.common.tileentity.TileEntitySawMill;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by bfox1 on 12/12/2014.
 */
public class GuiSawMill extends GuiContainer {

    private static final ResourceLocation guiSawMill = new ResourceLocation(Reference.MODID + ":gui/sawmill.png");
    private TileEntitySawMill sm;

    public GuiSawMill(InventoryPlayer player, TileEntitySawMill sawMill) {
        super(new ContainerSawMill(player, sawMill));
        this.sm = sawMill;

        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.sm.hasCustomName() ? this.sm.getName() : I18n.format(this.sm.getName());
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752); // Draws the name roughly in the same spot as in the furnace - Skymmer
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

        //draw your Gui here, only thing you need to change is the path
        //this.mc.renderEngine.getTexture(GuiForgeStation);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(guiSawMill);

        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
