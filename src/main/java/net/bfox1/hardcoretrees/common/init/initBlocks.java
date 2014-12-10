package net.bfox1.hardcoretrees.common.init;

import net.bfox1.hardcoretrees.common.blocks.BlockHCT;
import net.bfox1.hardcoretrees.common.blocks.SawMill;
import net.bfox1.hardcoretrees.common.blocks.SteelBlock;
import net.bfox1.hardcoretrees.common.reference.Reference;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by bfox1 on 12/8/2014.
 */
public class initBlocks {

    public static BlockHCT steelBlock = new SteelBlock(Material.iron);
   // public static BlockHCT sawMill = new SawMill();


    public static void registerInit()
    {
       // GameRegistry.registerBlock(steelBlock, Reference.MODID + steelBlock.getUnlocalizedName().substring(5));
    }
}


