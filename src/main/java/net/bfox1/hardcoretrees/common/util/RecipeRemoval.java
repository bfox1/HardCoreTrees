package net.bfox1.hardcoretrees.common.util;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.util.ArrayList;

/**
 * Created by bfox1 on 12/8/2014.
 */
public class RecipeRemoval {



    public static void init()
    {
        RemoveRecipeByOutput(new ItemStack(Items.stick));
        RemoveRecipeByOutput(new ItemStack(Blocks.planks));
    }

    private static void RemoveRecipeByOutput(ItemStack resultItem) {
        ItemStack recipeResult = null;
        ArrayList recipes = (ArrayList) CraftingManager.getInstance().getRecipeList();

        for (int scan = 0; scan < recipes.size(); scan++) {
            IRecipe tmpRecipe = (IRecipe) recipes.get(scan);
            recipeResult = tmpRecipe.getRecipeOutput();
            if (recipeResult != null) {
                if (recipeResult.getItem() == resultItem.getItem() && recipeResult.getItemDamage() == resultItem.getItemDamage() && recipeResult.stackSize == recipeResult.stackSize) {
                    //LogHelper.info("SAO Removed Recipe: " + recipes.get(scan) + " ===> " + recipeResult);
                    recipes.remove(scan);
                    scan--;
                }
            }
        }
    }
}
