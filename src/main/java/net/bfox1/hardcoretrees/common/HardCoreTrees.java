package net.bfox1.hardcoretrees.common;

import net.bfox1.hardcoretrees.common.handler.GuiHandler;
import net.bfox1.hardcoretrees.common.init.initBlocks;
import net.bfox1.hardcoretrees.common.init.initItems;
import net.bfox1.hardcoretrees.common.items.SteelIngot;
import net.bfox1.hardcoretrees.common.proxy.CommonProxy;
import net.bfox1.hardcoretrees.common.proxy.HctProxy;
import net.bfox1.hardcoretrees.common.reference.Reference;
import net.bfox1.hardcoretrees.common.util.Recipe;
import net.bfox1.hardcoretrees.common.util.RecipeRemoval;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by bfox1 on 12/8/2014.
 */
@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class HardCoreTrees {

    @Mod.Instance("hct")
    public static HardCoreTrees instance;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
    public static CommonProxy proxy;

        @EventHandler
        public void preInit(FMLPreInitializationEvent event)
        {
            RecipeRemoval.init();
            Recipe.registerRecipes();
        }

        @Mod.EventHandler
        public void init(FMLInitializationEvent event)
        {
            initItems.init();
            initBlocks.registerInit();

            proxy.registerRenderInfo();
            NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);


        }

        @EventHandler
        public void postInit(FMLPostInitializationEvent event)
        {

        }

}
