package net.bfox1.hardcoretrees.common.init;

import net.bfox1.hardcoretrees.common.blocks.BlockHCT;
import net.bfox1.hardcoretrees.common.blocks.SawMill;
import net.bfox1.hardcoretrees.common.blocks.SteelBlock;
import net.bfox1.hardcoretrees.common.helper.ModelHelper;
import net.bfox1.hardcoretrees.common.reference.Reference;
import net.bfox1.hardcoretrees.common.tileentity.TileEntitySawMill;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by bfox1 on 12/8/2014.
 */
public class initBlocks {

    public static final int sawMillID = 1;

    public static BlockHCT steelBlock = new SteelBlock(Material.iron);
   // public static BlockHCT sawMill = new SawMill();
    public static Block sawMillActive = new SawMill(Material.wood, true);
    public static Block sawMill = new SawMill(Material.wood, false);



    public static void registerInit()
    {
       // GameRegistry.registerBlock(steelBlock, Reference.MODID + steelBlock.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(sawMill, sawMill.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(sawMillActive, "SawMill_Active");
        GameRegistry.registerTileEntity(TileEntitySawMill.class, "SawMill");
        ModelHelper.registerBlock(sawMill, "hct:SawMill");
        ModelHelper.registerBlock(sawMillActive, "hct:SawMill_Active");
    }
}


