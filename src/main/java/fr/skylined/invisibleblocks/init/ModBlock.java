package fr.skylined.invisibleblocks.init;

import fr.skylined.invisibleblocks.InvisibleBlocks;
import fr.skylined.invisibleblocks.block.GrassBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
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

    private static final Material SOLID_ORGANIC_LIGHT = new Material(MapColor.PALE_GREEN, false, false, true, true, false, true, PistonBehavior.NORMAL);

    private static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }
    private static boolean never(BlockState blockState, BlockView blockView, BlockPos blockPos) { return false; }

    //public static final Block INV_GRASS_BLOCK = new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC_LIGHT).strength(Blocks.GRASS_BLOCK.getHardness(), Blocks.GRASS_BLOCK.getBlastResistance()).requiresTool().collidable(false).nonOpaque().allowsSpawning(ModBlock::never).solidBlock(ModBlock::never).suffocates(ModBlock::never).blockVision(ModBlock::never).lightLevel(15));
    public static final Block INV_GRASS_BLOCK = new GrassBlock(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK).noCollision().nonOpaque().allowsSpawning(ModBlock::never).suffocates(ModBlock::never).blockVision(ModBlock::never));

    public static void blockInit(){
        registerBlockItem("invisible_grass_block", INV_GRASS_BLOCK, InvisibleBlocks.INVBLOCKS_GROUP);
    }

    public static void registerBlockItem(String name, Block block, ItemGroup group){
        Registry.register(Registry.BLOCK, new Identifier(InvisibleBlocks.MODID, name), block);
        Registry.register(Registry.ITEM, new Identifier(InvisibleBlocks.MODID, name), new BlockItem(block, new FabricItemSettings().group(group)));
    }
    
}
