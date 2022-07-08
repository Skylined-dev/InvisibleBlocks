package fr.skylined.invisibleblocks;

import com.mojang.logging.LogUtils;
import fr.skylined.invisibleblocks.init.ModBlock;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.block.BlockColors;
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

    @Override
    public void onInitialize() {
        LOGGER.info("on!");
        LOGGER.info("Registering Blocks...");
        ModBlock.blockInit();
        LOGGER.info("Done!");


        //BlockColors blockColors = new BlockColors();
        //blockColors.registerColorProvider((state, world, pos, tintIndex) -> {
        //    return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0);
        //}, ModBlock.INV_GRASS_BLOCK);


    }

    @Override
    public void onInitializeClient(){

        BlockColorProvider provider = new BlockColorProvider() {
            @Override
            public int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {
                return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0);
            }
        };
        MinecraftClient.getInstance().getBlockColors().registerColorProvider(provider, ModBlock.INV_GRASS_BLOCK);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlock.INV_GRASS_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlock.INV_GRASS_BLOCK, RenderLayer.getCutout());
    }


}
