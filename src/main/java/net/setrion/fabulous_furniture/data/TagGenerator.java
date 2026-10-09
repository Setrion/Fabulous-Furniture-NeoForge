package net.setrion.fabulous_furniture.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import net.setrion.fabulous_furniture.FabulousFurniture;
import net.setrion.fabulous_furniture.registry.SFFBlocks;
import net.setrion.fabulous_furniture.registry.SFFTags;
import net.setrion.fabulous_furniture.world.level.block.BlockTagSupplier;

import java.util.concurrent.CompletableFuture;

public class TagGenerator {

    public static class Items extends ItemTagsProvider {

        public Items(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
            super(output, future, FabulousFurniture.MOD_ID);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {

        }
    }

    public static class Blocks extends BlockTagsProvider {

        public Blocks(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
            super(output, future, FabulousFurniture.MOD_ID);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            SFFBlocks.BLOCKS.getEntries().stream().filter(entry -> entry.getId().getNamespace().equals(FabulousFurniture.MOD_ID)).forEach(entry -> {
                Block block = entry.get();
                if(block instanceof BlockTagSupplier supplier) {
                    supplier.getTags().forEach(key -> this.tag(key).add(entry.key()));
                } else {
                    throw new IllegalArgumentException("Block doesn't implement BlockTagSupplier: " + entry.getId());
                }
            });

            tag(SFFTags.Blocks.COPPER_FRIDGES).add(
                    SFFBlocks.COPPER_FRIDGE.key(),
                    SFFBlocks.EXPOSED_COPPER_FRIDGE.key(),
                    SFFBlocks.WEATHERED_COPPER_FRIDGE.key(),
                    SFFBlocks.OXIDIZED_COPPER_FRIDGE.key(),
                    SFFBlocks.WAXED_COPPER_FRIDGE.key(),
                    SFFBlocks.WAXED_EXPOSED_COPPER_FRIDGE.key(),
                    SFFBlocks.WAXED_WEATHERED_COPPER_FRIDGE.key(),
                    SFFBlocks.WAXED_OXIDIZED_COPPER_FRIDGE.key()
            );

            tag(SFFTags.Blocks.FRIDGES).add(
                    SFFBlocks.IRON_FRIDGE.key(),
                    SFFBlocks.GOLD_FRIDGE.key(),
                    SFFBlocks.NETHERITE_FRIDGE.key()
            ).addTag(SFFTags.Blocks.COPPER_FRIDGES);

            tag(SFFTags.Blocks.FLOWER_BOX_PLACABLES).add(
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.OAK_SAPLING).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.SPRUCE_SAPLING).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.BIRCH_SAPLING).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.JUNGLE_SAPLING).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.ACACIA_SAPLING).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.DARK_OAK_SAPLING).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.MANGROVE_PROPAGULE).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.CHERRY_SAPLING).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.PALE_OAK_SAPLING).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.BROWN_MUSHROOM).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.RED_MUSHROOM).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.CRIMSON_FUNGUS).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.WARPED_FUNGUS).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.SHORT_GRASS).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.FERN).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.SHORT_DRY_GRASS).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.BUSH).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.DEAD_BUSH).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.DANDELION).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.POPPY).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.BLUE_ORCHID).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.ALLIUM).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.AZURE_BLUET).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.RED_TULIP).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.ORANGE_TULIP).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.WHITE_TULIP).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.PINK_TULIP).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.OXEYE_DAISY).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.CORNFLOWER).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.LILY_OF_THE_VALLEY).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.TORCHFLOWER).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.CACTUS_FLOWER).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.CLOSED_EYEBLOSSOM).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.OPEN_EYEBLOSSOM).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.WITHER_ROSE).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.BAMBOO).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.CRIMSON_ROOTS).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.WARPED_ROOTS).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.NETHER_SPROUTS).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.TALL_DRY_GRASS).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.FIREFLY_BUSH).get(),
                    BuiltInRegistries.BLOCK.getResourceKey(net.minecraft.world.level.block.Blocks.BUSH).get()
                    );
        }
    }
}