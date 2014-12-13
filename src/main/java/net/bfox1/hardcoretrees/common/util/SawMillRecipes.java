package net.bfox1.hardcoretrees.common.util;

import com.google.common.collect.Maps;
import net.bfox1.hardcoretrees.common.init.initItems;
import net.bfox1.hardcoretrees.common.items.ItemSawBlade;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by bfox1 on 12/11/2014.
 */
public class SawMillRecipes {

    private static final SawMillRecipes cuttingBase = new SawMillRecipes();
    private Map cutList = Maps.newHashMap();
    private Map dustList = Maps.newHashMap();


    public static SawMillRecipes instance()
    {
        return cuttingBase;
    }

    private SawMillRecipes()
    {
        for(BlockPlanks.EnumType type: BlockPlanks.EnumType.values())
        {
            if(type.getMetadata() <4) {
                this.addSawMillBlockRecipe(Blocks.log, new ItemStack(Blocks.planks, 10, type.getMetadata()), new ItemStack(initItems.sawDust, 3));
            } else {
                this.addSawMillBlockRecipe(Blocks.log2, new ItemStack(Blocks.planks, 10, type.getMetadata()), new ItemStack(initItems.sawDust, 3));
            }
        }
        this.addSawMillBlockRecipe(Blocks.planks, new ItemStack(Items.stick, 8), new ItemStack(initItems.sawDust, 2));
    }

    public void addSawMillBlockRecipe(Block input, ItemStack stack, ItemStack dust)
    {
        this.addSawMillRecipe(Item.getItemFromBlock(input), stack, dust);
    }

    public void addSawMillRecipe(Item input, ItemStack stack, ItemStack dust)
    {
        this.addSawMillRecipe(new ItemStack(input, 1, 32767), stack, dust);
    }

    public void addSawMillRecipe(ItemStack input, ItemStack stack, ItemStack dust)
    {
        this.cutList.put(input, stack);
        this.dustList.put(stack, dust);
    }

    public ItemStack getCutResults(ItemStack stack)
    {
        Iterator iterator = this.cutList.entrySet().iterator();
        Map.Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Map.Entry)iterator.next();
        }
        while (!this.compareItemStacks(stack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map getCutList()
    {
        return this.cutList;
    }

    public ItemStack getDustResults(ItemStack stack)
    {
        Iterator iterator = this.dustList.entrySet().iterator();
        Map.Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Map.Entry)iterator.next();
        }
        while (!this.compareItemStacks(stack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

}
