package fr.skylined.invisibleblocks.init;

import fr.skylined.invisibleblocks.InvisibleBlocks;
import fr.skylined.invisibleblocks.block.InvisibleBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class ModBlock {


    private static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }
    private static boolean never(BlockState blockState, BlockView blockView, BlockPos blockPos) { return false; }

    public static final Block INV_GRASS_BLOCK = new InvisibleBlock(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK).noCollision().nonOpaque().allowsSpawning(ModBlock::never).suffocates(ModBlock::never).blockVision(ModBlock::never).ticksRandomly(), InvisibleBlock.color(0, 0, 0));
    public static final Block INV_DIRT = new InvisibleBlock(AbstractBlock.Settings.copy(Blocks.DIRT).noCollision().nonOpaque().allowsSpawning(ModBlock::never).suffocates(ModBlock::never).blockVision(ModBlock::never).ticksRandomly(), InvisibleBlock.color(0, 0, 0));
    public static final Block INV_STONE = new InvisibleBlock(AbstractBlock.Settings.copy(Blocks.STONE).noCollision().nonOpaque().allowsSpawning(ModBlock::never).suffocates(ModBlock::never).blockVision(ModBlock::never).ticksRandomly(), InvisibleBlock.color(0, 0, 0));
    public static final Block INV_DEEPSLATE = new InvisibleBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE).noCollision().nonOpaque().allowsSpawning(ModBlock::never).suffocates(ModBlock::never).blockVision(ModBlock::never).ticksRandomly(), InvisibleBlock.color(0, 0, 0));
    public static final Block INV_OBSIDIAN = new InvisibleBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN).noCollision().nonOpaque().allowsSpawning(ModBlock::never).suffocates(ModBlock::never).blockVision(ModBlock::never).ticksRandomly(), InvisibleBlock.color(0, 0, 0));
    public static final Block INV_CRYING_OBSIDIAN = new InvisibleBlock(AbstractBlock.Settings.copy(Blocks.CRYING_OBSIDIAN).noCollision().nonOpaque().allowsSpawning(ModBlock::never).suffocates(ModBlock::never).blockVision(ModBlock::never).ticksRandomly(), InvisibleBlock.color(0, 0, 0));

    public static void blockInit(){
        registerBlockItem("invisible_grass_block", INV_GRASS_BLOCK, InvisibleBlocks.INVBLOCKS_GROUP);
        registerBlockItem("invisible_dirt", INV_DIRT, InvisibleBlocks.INVBLOCKS_GROUP);
        registerBlockItem("invisible_stone", INV_STONE, InvisibleBlocks.INVBLOCKS_GROUP);
        registerBlockItem("invisible_deepslate", INV_DEEPSLATE, InvisibleBlocks.INVBLOCKS_GROUP);
        registerBlockItem("invisible_obsidian", INV_OBSIDIAN, InvisibleBlocks.INVBLOCKS_GROUP);
        registerBlockItem("invisible_crying_obsidian", INV_CRYING_OBSIDIAN, InvisibleBlocks.INVBLOCKS_GROUP);

    }

    public static void registerBlockItem(String name, Block block, ItemGroup group){
        Registry.register(Registry.BLOCK, new Identifier(InvisibleBlocks.MODID, name), block);
        Registry.register(Registry.ITEM, new Identifier(InvisibleBlocks.MODID, name), new BlockItem(block, new FabricItemSettings().group(group)));
    }
    
}
