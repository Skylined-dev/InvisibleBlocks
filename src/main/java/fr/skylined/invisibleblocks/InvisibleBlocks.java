package fr.skylined.invisibleblocks;

import com.mojang.logging.LogUtils;
import fr.skylined.invisibleblocks.init.ModBlock;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;


public class InvisibleBlocks implements ModInitializer, ClientModInitializer {

    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "invisibleblocks";

    public static final ItemGroup INVBLOCKS_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MODID, "invisible_blocks_group"),
            () -> new ItemStack(Blocks.GRASS_BLOCK));

    BlockColorProvider grassBlockProvider = new BlockColorProvider() {
        @Override
        public int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {
            return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0);
        }
    };

    ItemColorProvider grassBlockItemProvider = new ItemColorProvider() {
        @Override
        public int getColor(ItemStack stack, int tintIndex) {
            return GrassColors.getColor(0.5, 1.0);
        }
    };

    @Override
    public void onInitialize() {
        LOGGER.info("on!");
        LOGGER.info("making block translucent...");
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlock.INV_GRASS_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlock.INV_GRASS_BLOCK, RenderLayer.getCutout());
        LOGGER.info("Registering Blocks...");
        ModBlock.blockInit();
        LOGGER.info("Applying blocks colors...");
        ColorProviderRegistry.BLOCK.register(grassBlockProvider, ModBlock.INV_GRASS_BLOCK);
        ColorProviderRegistry.ITEM.register(grassBlockItemProvider, ModBlock.INV_GRASS_BLOCK);

        LOGGER.info("Done!");

    }

    @Override
    public void onInitializeClient(){


        //MinecraftClient.getInstance().getBlockColors().registerColorProvider(provider, ModBlock.INV_GRASS_BLOCK);


    }


}
