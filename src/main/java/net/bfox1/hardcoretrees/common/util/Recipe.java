package net.bfox1.hardcoretrees.common.util;

import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by bfox1 on 12/11/2014.
 */
public class Recipe {


    public static void registerRecipes()
    {
        ItemStack plank;
        for(BlockPlanks.EnumType type : BlockPlanks.EnumType.values())
        {
            plank = new ItemStack(Blocks.planks, 1, type.getMetadata());
            GameRegistry.addRecipe(new ItemStack(Items.stick, 2),"x","x", 'x', plank );
            if(type.getMetadata() < 4)
            {
                GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks, 1, type.getMetadata()), new ItemStack(Blocks.log, 1, type.getMetadata()));
            }else{
                GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks, 1, type.getMetadata()), new ItemStack(Blocks.log2, 1, type.getMetadata()));
            }
        }
    }

}
