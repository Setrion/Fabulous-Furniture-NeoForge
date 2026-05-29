package net.setrion.fabulous_furniture.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.setrion.fabulous_furniture.FabulousFurniture;
import net.setrion.fabulous_furniture.registry.SFFBlocks;
import net.setrion.fabulous_furniture.world.item.crafting.CarpentryTableRecipe;
import net.setrion.fabulous_furniture.world.level.block.state.properties.FurnitureCategory;
import net.setrion.fabulous_furniture.world.level.block.state.properties.MaterialType;

import javax.annotation.Nullable;

import static net.minecraft.world.level.block.Blocks.*;
import static net.setrion.fabulous_furniture.registry.SFFBlocks.*;

public class RecipeGenerator extends RecipeProvider {
    private final HolderLookup.RegistryLookup<Item> items;

    public RecipeGenerator(HolderLookup.Provider provider, RecipeOutput output) {
        super(provider, output);
        this.items = provider.lookupOrThrow(Registries.ITEM);
    }

    @Override
    public void buildRecipes() {
        ShapedRecipeBuilder.shaped(items, RecipeCategory.DECORATIONS, CARPENTRY_TABLE.get()).define('#', ItemTags.PLANKS).define('X', Items.COPPER_INGOT).pattern("XX").pattern("##").pattern("##").unlockedBy("has_copper", this.has(Items.COPPER_INGOT)).save(this.output);
        createFridgeRecipes();
        createHedgeRecipes();
        createCurtainRecipes();
        createKitchenTileRecipes();
        METALS.forEach((metal, name) -> carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(name+"_toaster")), 1, FurnitureCategory.KITCHEN_MISC, getMaterialTypeFromTop(metal), new ItemStackTemplate(metal.asItem())));
        TABLEWARE_MATERIALS.forEach((block, suffix) -> {
            String top_name = block.getDescriptionId().replaceFirst("block.minecraft.", "").replaceFirst("quartz_block", "quartz");
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(top_name+"_tableware")), 4, FurnitureCategory.KITCHEN_MISC, block == QUARTZ_BLOCK ? MaterialType.QUARTZ : MaterialType.TERRACOTTA, new ItemStackTemplate(block.asItem()));
        });
        STONE_MATERIALS.forEach((block, name) -> {
            String top_name = block.getDescriptionId().replaceFirst("block.minecraft.", "").replaceFirst("quartz_block", "quartz");
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(top_name+"_birdbath")), 1, FurnitureCategory.BIRDBATHS, getMaterialTypeFromTop(block), new ItemStackTemplate(block.asItem()), new ItemStackTemplate(Items.WATER_BUCKET));
        });
        createWoodenFurnitureRecipes(WoodType.OAK, MaterialType.OAK);
        createWoodenFurnitureRecipes(WoodType.SPRUCE, MaterialType.SPRUCE);
        createWoodenFurnitureRecipes(WoodType.BIRCH, MaterialType.BIRCH);
        createWoodenFurnitureRecipes(WoodType.JUNGLE, MaterialType.JUNGLE);
        createWoodenFurnitureRecipes(WoodType.ACACIA, MaterialType.ACACIA);
        createWoodenFurnitureRecipes(WoodType.DARK_OAK, MaterialType.DARK_OAK);
        createWoodenFurnitureRecipes(WoodType.PALE_OAK, MaterialType.PALE_OAK);
        createWoodenFurnitureRecipes(WoodType.CHERRY, MaterialType.CHERRY);
        createWoodenFurnitureRecipes(WoodType.MANGROVE, MaterialType.MANGROVE);
        createWoodenFurnitureRecipes(WoodType.BAMBOO, MaterialType.BAMBOO);
        createWoodenFurnitureRecipes(WoodType.CRIMSON, MaterialType.CRIMSON);
        createWoodenFurnitureRecipes(WoodType.WARPED, MaterialType.WARPED);
    }

    private void createFridgeRecipes() {
        carpentryTableCrafting(IRON_FRIDGE.get().asItem(), 1, FurnitureCategory.FRIDGES, MaterialType.IRON, new ItemStackTemplate(Items.IRON_BLOCK, 2), new ItemStackTemplate(Items.IRON_BARS, 3));
        carpentryTableCrafting(SFFBlocks.COPPER_FRIDGE.get(), 1, FurnitureCategory.FRIDGES, MaterialType.COPPER, new ItemStackTemplate(Items.COPPER_BLOCK, 2), new ItemStackTemplate(Items.IRON_BARS, 3));
        carpentryTableCrafting(SFFBlocks.EXPOSED_COPPER_FRIDGE.get(), 1, FurnitureCategory.FRIDGES, MaterialType.COPPER, new ItemStackTemplate(Items.EXPOSED_COPPER, 2), new ItemStackTemplate(Items.IRON_BARS, 3));
        carpentryTableCrafting(SFFBlocks.WEATHERED_COPPER_FRIDGE.get(), 1, FurnitureCategory.FRIDGES, MaterialType.COPPER, new ItemStackTemplate(Items.WEATHERED_COPPER, 2), new ItemStackTemplate(Items.IRON_BARS, 3));
        carpentryTableCrafting(SFFBlocks.OXIDIZED_COPPER_FRIDGE.get(), 1, FurnitureCategory.FRIDGES, MaterialType.COPPER, new ItemStackTemplate(Items.OXIDIZED_COPPER, 2), new ItemStackTemplate(Items.IRON_BARS, 3));
        carpentryTableCrafting(SFFBlocks.WAXED_COPPER_FRIDGE.get(), 1, FurnitureCategory.FRIDGES, MaterialType.COPPER, new ItemStackTemplate(Items.WAXED_COPPER_BLOCK, 2), new ItemStackTemplate(Items.IRON_BARS, 3));
        carpentryTableCrafting(SFFBlocks.WAXED_EXPOSED_COPPER_FRIDGE.get(), 1, FurnitureCategory.FRIDGES, MaterialType.COPPER, new ItemStackTemplate(Items.WAXED_EXPOSED_COPPER, 2), new ItemStackTemplate(Items.IRON_BARS, 3));
        carpentryTableCrafting(SFFBlocks.WAXED_WEATHERED_COPPER_FRIDGE.get(), 1, FurnitureCategory.FRIDGES, MaterialType.COPPER, new ItemStackTemplate(Items.WAXED_WEATHERED_COPPER, 2), new ItemStackTemplate(Items.IRON_BARS, 3));
        carpentryTableCrafting(SFFBlocks.WAXED_OXIDIZED_COPPER_FRIDGE.get(), 1, FurnitureCategory.FRIDGES, MaterialType.COPPER, new ItemStackTemplate(Items.WAXED_OXIDIZED_COPPER, 2), new ItemStackTemplate(Items.IRON_BARS, 3));
        carpentryTableCrafting(SFFBlocks.GOLD_FRIDGE.get(), 1, FurnitureCategory.FRIDGES, MaterialType.GOLD, new ItemStackTemplate(Items.GOLD_BLOCK, 2), new ItemStackTemplate(Items.IRON_BARS, 3));
        carpentryTableCrafting(SFFBlocks.NETHERITE_FRIDGE.get(), 1, FurnitureCategory.FRIDGES, MaterialType.NETHERITE, new ItemStackTemplate(Items.NETHERITE_BLOCK, 2), new ItemStackTemplate(Items.IRON_BARS, 3));
    }

    private void createHedgeRecipes() {
        carpentryTableCrafting(OAK_HEDGE.get(), 4, FurnitureCategory.OUTDOOR_MISC, MaterialType.LEAVES, new ItemStackTemplate(Items.OAK_LEAVES, 6));
        carpentryTableCrafting(SPRUCE_HEDGE.get(), 4, FurnitureCategory.OUTDOOR_MISC, MaterialType.LEAVES, new ItemStackTemplate(Items.SPRUCE_LEAVES, 6));
        carpentryTableCrafting(BIRCH_HEDGE.get(), 4, FurnitureCategory.OUTDOOR_MISC, MaterialType.LEAVES, new ItemStackTemplate(Items.BIRCH_LEAVES, 6));
        carpentryTableCrafting(JUNGLE_HEDGE.get(), 4, FurnitureCategory.OUTDOOR_MISC, MaterialType.LEAVES, new ItemStackTemplate(Items.JUNGLE_LEAVES, 6));
        carpentryTableCrafting(ACACIA_HEDGE.get(), 4, FurnitureCategory.OUTDOOR_MISC, MaterialType.LEAVES, new ItemStackTemplate(Items.ACACIA_LEAVES, 6));
        carpentryTableCrafting(CHERRY_HEDGE.get(), 4, FurnitureCategory.OUTDOOR_MISC, MaterialType.LEAVES, new ItemStackTemplate(Items.CHERRY_LEAVES, 6));
        carpentryTableCrafting(DARK_OAK_HEDGE.get(), 4, FurnitureCategory.OUTDOOR_MISC, MaterialType.LEAVES, new ItemStackTemplate(Items.DARK_OAK_LEAVES, 6));
        carpentryTableCrafting(PALE_OAK_HEDGE.get(), 4, FurnitureCategory.OUTDOOR_MISC, MaterialType.LEAVES, new ItemStackTemplate(Items.PALE_OAK_LEAVES, 6));
        carpentryTableCrafting(MANGROVE_HEDGE.get(), 4, FurnitureCategory.OUTDOOR_MISC, MaterialType.LEAVES, new ItemStackTemplate(Items.MANGROVE_LEAVES, 6));
        carpentryTableCrafting(AZALEA_HEDGE.get(), 4, FurnitureCategory.OUTDOOR_MISC, MaterialType.LEAVES, new ItemStackTemplate(Items.AZALEA_LEAVES, 6));
        carpentryTableCrafting(FLOWERING_AZALEA_HEDGE.get(), 4, FurnitureCategory.OUTDOOR_MISC, MaterialType.LEAVES, new ItemStackTemplate(Items.FLOWERING_AZALEA_LEAVES, 6));
    }

    private void createCurtainRecipes() {
        WOOL_COLORS.forEach((wool, color) -> METALS.forEach((rod, name) -> carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(color+"_"+name+"_curtains")), 6, FurnitureCategory.CURTAINS, MaterialType.WOOL, getMaterialTypeFromTop(rod), new ItemStackTemplate(wool.asItem(), 1), new ItemStackTemplate(rod.asItem(), 1))));
    }

    private void createKitchenTileRecipes() {
        carpentryTableCrafting(SFFBlocks.WHITE_LIGHT_GRAY_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.LIGHT_GRAY_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_GRAY_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.GRAY_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_BLACK_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.BLACK_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_BROWN_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.BROWN_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_RED_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.RED_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_ORANGE_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.ORANGE_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_YELLOW_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.YELLOW_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_LIME_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.LIME_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_GREEN_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.GREEN_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_CYAN_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.CYAN_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_LIGHT_BLUE_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.LIGHT_BLUE_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_BLUE_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.BLUE_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_PURPLE_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.PURPLE_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_MAGENTA_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.MAGENTA_CONCRETE, 4));
        carpentryTableCrafting(SFFBlocks.WHITE_PINK_KITCHEN_TILES.get(), 8, FurnitureCategory.FLOOR, MaterialType.CONCRETE, new ItemStackTemplate(Items.WHITE_CONCRETE, 4), new ItemStackTemplate(Items.PINK_CONCRETE, 4));
    }

    private void createWoodenFurnitureRecipes(WoodType type, MaterialType materialType) {
        Block planks = getBlockFromIdentifier(Identifier.parse(type.name()+"_planks"));
        Block log;
        Block strippedLog;
        String log_suffix;
        if (type == WoodType.CRIMSON || type == WoodType.WARPED) {
            log = getBlockFromIdentifier(Identifier.parse(type.name()+"_stem"));
            strippedLog = getBlockFromIdentifier(Identifier.parse("stripped_"+type.name()+"_stem"));
            log_suffix = "_stem";
        } else if (type == WoodType.BAMBOO) {
            log = getBlockFromIdentifier(Identifier.parse(type.name()+"_block"));
            strippedLog = getBlockFromIdentifier(Identifier.parse("stripped_"+type.name()+"_block"));
            log_suffix = "_block";
        } else {
            log = getBlockFromIdentifier(Identifier.parse(type.name()+"_log"));
            strippedLog = getBlockFromIdentifier(Identifier.parse("stripped_"+type.name()+"_log"));
            log_suffix = "_log";
        }
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_crate")), 2, FurnitureCategory.CRATES, materialType, new ItemStackTemplate(planks.asItem(), 2), new ItemStackTemplate(log.asItem(), 2));

        STONE_MATERIALS.forEach(((block, s) -> {
            String top_name = block.getDescriptionId().replaceFirst("block.minecraft.", "").replaceFirst("quartz_block", "quartz");
            MaterialType additional = getMaterialTypeFromTop(block);
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+top_name+"_kitchen_counter")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(planks.asItem(), 4), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+top_name+"_kitchen_counter_shelf")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(planks.asItem(), 4), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+top_name+"_kitchen_counter_door")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(planks.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+top_name+"_kitchen_counter_small_drawer")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(planks.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+top_name+"_kitchen_counter_big_drawer")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(planks.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+top_name+"_kitchen_counter_sink")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(planks.asItem(), 4), new ItemStackTemplate(Items.WATER_BUCKET, 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+top_name+"_kitchen_counter_smoker")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(planks.asItem(), 1), new ItemStackTemplate(Items.SMOKER, 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+top_name+"_kitchen_cabinet_door")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, additional, new ItemStackTemplate(planks.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+top_name+"_kitchen_cabinet_glass_door")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, additional, new ItemStackTemplate(planks.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(Items.GLASS_PANE, 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+top_name+"_kitchen_cabinet_sideways_door")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, additional, new ItemStackTemplate(planks.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+top_name+"_kitchen_cabinet_sideways_glass_door")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, additional, new ItemStackTemplate(planks.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(Items.GLASS_PANE, 1), new ItemStackTemplate(block.asItem(), 1));

            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+top_name+"_kitchen_counter")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(log.asItem(), 4), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+top_name+"_kitchen_counter_shelf")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(log.asItem(), 4), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+top_name+"_kitchen_counter_door")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(log.asItem(), 4), new ItemStackTemplate(strippedLog.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+top_name+"_kitchen_counter_small_drawer")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(log.asItem(), 4), new ItemStackTemplate(strippedLog.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+top_name+"_kitchen_counter_big_drawer")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(log.asItem(), 4), new ItemStackTemplate(strippedLog.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+top_name+"_kitchen_counter_sink")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(log.asItem(), 4), new ItemStackTemplate(Items.WATER_BUCKET, 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+top_name+"_kitchen_counter_smoker")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(Items.SMOKER, 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_door")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, additional, new ItemStackTemplate(log.asItem(), 4), new ItemStackTemplate(strippedLog.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_glass_door")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, additional, new ItemStackTemplate(log.asItem(), 4), new ItemStackTemplate(strippedLog.asItem(), 1), new ItemStackTemplate(Items.GLASS_PANE, 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_sideways_door")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, additional, new ItemStackTemplate(log.asItem(), 4), new ItemStackTemplate(strippedLog.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_sideways_glass_door")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, additional, new ItemStackTemplate(log.asItem(), 4), new ItemStackTemplate(strippedLog.asItem(), 1), new ItemStackTemplate(Items.GLASS_PANE, 1), new ItemStackTemplate(block.asItem(), 1));

            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(strippedLog.asItem(), 4), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter_shelf")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(strippedLog.asItem(), 4), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter_door")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(strippedLog.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter_small_drawer")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(strippedLog.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter_big_drawer")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(strippedLog.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter_sink")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(strippedLog.asItem(), 4), new ItemStackTemplate(Items.WATER_BUCKET, 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter_smoker")), 4, FurnitureCategory.KITCHEN_COUNTERS, materialType, additional, new ItemStackTemplate(strippedLog.asItem(), 1), new ItemStackTemplate(Items.SMOKER, 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_door")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, additional, new ItemStackTemplate(strippedLog.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_glass_door")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, additional, new ItemStackTemplate(strippedLog.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(Items.GLASS_PANE, 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_sideways_door")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, additional, new ItemStackTemplate(strippedLog.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_sideways_glass_door")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, additional, new ItemStackTemplate(strippedLog.asItem(), 4), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(Items.GLASS_PANE, 1), new ItemStackTemplate(block.asItem(), 1));

            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+top_name+"_knife_block")), 2, FurnitureCategory.KITCHEN_MISC, materialType, additional, new ItemStackTemplate(planks.asItem(), 1), new ItemStackTemplate(block.asItem(), 1), new ItemStackTemplate(Items.IRON_NUGGET, 10));
        }));

        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_kitchen_cabinet")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, new ItemStackTemplate(planks.asItem(), 4));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_kitchen_cabinet")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, new ItemStackTemplate(log.asItem(), 4));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_kitchen_cabinet")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, new ItemStackTemplate(strippedLog.asItem(), 4));

        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_kitchen_cabinet_shelf")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, new ItemStackTemplate(planks.asItem(), 4));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_kitchen_cabinet_shelf")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, new ItemStackTemplate(log.asItem(), 4));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_kitchen_cabinet_shelf")), 4, FurnitureCategory.KITCHEN_CABINETS, materialType, new ItemStackTemplate(strippedLog.asItem(), 4));

        METALS.forEach((metal, name) -> {
            if (metal != Blocks.GOLD_BLOCK && metal != Blocks.NETHERITE_BLOCK) {
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + "_" + name + "_kitchen_shelf")), 4, FurnitureCategory.KITCHEN_SHELFS, materialType, getMaterialTypeFromTop(metal), new ItemStackTemplate(planks.asItem(), 1), new ItemStackTemplate(getBlockFromIdentifier(Identifier.parse(name+"_chain")).asItem(), 1));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + log_suffix + "_" + name + "_kitchen_shelf")), 4, FurnitureCategory.KITCHEN_SHELFS, materialType, getMaterialTypeFromTop(metal), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(getBlockFromIdentifier(Identifier.parse(name+"_chain")).asItem(), 1));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_" + name + "_kitchen_shelf")), 4, FurnitureCategory.KITCHEN_SHELFS, materialType, getMaterialTypeFromTop(metal), new ItemStackTemplate(strippedLog.asItem(), 1), new ItemStackTemplate(getBlockFromIdentifier(Identifier.parse(name+"_chain")).asItem(), 1));
            }
        });
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_table")), 2, FurnitureCategory.TABLES, materialType, new ItemStackTemplate(planks.asItem(), 1));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_table")), 2, FurnitureCategory.TABLES, materialType, new ItemStackTemplate(log.asItem(), 1));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_table")), 2, FurnitureCategory.TABLES, materialType, new ItemStackTemplate(strippedLog.asItem(), 1));

        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_couch_table")), 4, FurnitureCategory.TABLES, materialType, new ItemStackTemplate(planks.asItem(), 1));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_couch_table")), 4, FurnitureCategory.TABLES, materialType, new ItemStackTemplate(log.asItem(), 1));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_couch_table")), 4, FurnitureCategory.TABLES, materialType, new ItemStackTemplate(strippedLog.asItem(), 1));

        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_bedside_table")), 2, FurnitureCategory.BEDSIDE_TABLES, materialType, new ItemStackTemplate(planks.asItem(), 2), new ItemStackTemplate(log.asItem(), 1));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_bedside_table")), 2, FurnitureCategory.BEDSIDE_TABLES, materialType, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(strippedLog.asItem(), 1));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_bedside_table")), 2, FurnitureCategory.BEDSIDE_TABLES, materialType, new ItemStackTemplate(strippedLog.asItem(), 2), new ItemStackTemplate(log.asItem(), 1));

        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_closet")), 1, FurnitureCategory.CLOSETS, materialType, new ItemStackTemplate(planks.asItem(), 2), new ItemStackTemplate(log.asItem(), 1));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_closet")), 1, FurnitureCategory.CLOSETS, materialType, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(strippedLog.asItem(), 1));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_closet")), 1, FurnitureCategory.CLOSETS, materialType, new ItemStackTemplate(strippedLog.asItem(), 2), new ItemStackTemplate(log.asItem(), 1));

        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_flower_box")), 4, FurnitureCategory.FLOWER_BOXES, materialType, new ItemStackTemplate(planks.asItem()), new ItemStackTemplate(Items.DIRT));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_flower_box")), 4, FurnitureCategory.FLOWER_BOXES, materialType, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(Items.DIRT));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_flower_box")), 4, FurnitureCategory.FLOWER_BOXES, materialType, new ItemStackTemplate(strippedLog.asItem()), new ItemStackTemplate(Items.DIRT));

        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_flower_box_inner_corner")), 8, FurnitureCategory.FLOWER_BOXES, materialType, new ItemStackTemplate(planks.asItem()), new ItemStackTemplate(Items.DIRT));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_flower_box_inner_corner")), 8, FurnitureCategory.FLOWER_BOXES, materialType, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(Items.DIRT));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_flower_box_inner_corner")), 8, FurnitureCategory.FLOWER_BOXES, materialType, new ItemStackTemplate(strippedLog.asItem()), new ItemStackTemplate(Items.DIRT));

        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_flower_box_outer_corner")), 3, FurnitureCategory.FLOWER_BOXES, materialType, new ItemStackTemplate(planks.asItem()), new ItemStackTemplate(Items.DIRT));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_flower_box_outer_corner")), 3, FurnitureCategory.FLOWER_BOXES, materialType, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(Items.DIRT));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_flower_box_outer_corner")), 3, FurnitureCategory.FLOWER_BOXES, materialType, new ItemStackTemplate(strippedLog.asItem()), new ItemStackTemplate(Items.DIRT));

        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_flower_box_big")), 2, FurnitureCategory.FLOWER_BOXES, materialType, new ItemStackTemplate(planks.asItem()), new ItemStackTemplate(Items.DIRT));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_flower_box_big")), 2, FurnitureCategory.FLOWER_BOXES, materialType, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(Items.DIRT));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_flower_box_big")), 2, FurnitureCategory.FLOWER_BOXES, materialType, new ItemStackTemplate(strippedLog.asItem()), new ItemStackTemplate(Items.DIRT));

        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_trash_bin")), 1, FurnitureCategory.OUTDOOR_MISC, materialType, new ItemStackTemplate(planks.asItem()), new ItemStackTemplate(Items.IRON_INGOT, 4));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_trash_bin")), 1, FurnitureCategory.OUTDOOR_MISC, materialType, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(Items.IRON_INGOT, 4));
        carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_trash_bin")), 1, FurnitureCategory.OUTDOOR_MISC, materialType, new ItemStackTemplate(strippedLog.asItem()), new ItemStackTemplate(Items.IRON_INGOT, 4));

        for (WoodType type2 : WOOD_TYPES) {

            Block planks2 = getBlockFromIdentifier(Identifier.parse(type2.name()+"_planks"));
            Block log2;
            Block strippedLog2;
            String log_suffix2;
            if (type2 == WoodType.CRIMSON || type2 == WoodType.WARPED) {
                log2 = getBlockFromIdentifier(Identifier.parse(type2.name()+"_stem"));
                strippedLog2 = getBlockFromIdentifier(Identifier.parse("stripped_"+type2.name()+"_stem"));
                log_suffix2 = "_stem";
            } else if (type2 == WoodType.BAMBOO) {
                log2 = getBlockFromIdentifier(Identifier.parse(type2.name()+"_block"));
                strippedLog2 = getBlockFromIdentifier(Identifier.parse("stripped_"+type2.name()+"_block"));
                log_suffix2 = "_block";
            } else {
                log2 = getBlockFromIdentifier(Identifier.parse(type2.name()+"_log"));
                strippedLog2 = getBlockFromIdentifier(Identifier.parse("stripped_"+type2.name()+"_log"));
                log_suffix2 = "_log";
            }
            MaterialType additional = getMaterialTypeFromTop(getBlockFromIdentifier(Identifier.parse(type2.name()+"_planks")));

            if (planks == planks2) {
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+type2.name()+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(planks.asItem(), 2));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_"+type.name()+"_"+type2.name()+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(planks.asItem(), 2));
            } else {
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+type2.name()+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(planks.asItem()), new ItemStackTemplate(planks2.asItem()));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_"+type.name()+"_"+type2.name()+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(planks.asItem()), new ItemStackTemplate(planks2.asItem()));
            }

            if (log == log2) {
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+type2.name()+log_suffix2+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(planks.asItem()));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+type2.name()+log_suffix2+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem(), 3));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+type2.name()+log_suffix2+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(strippedLog.asItem()));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_"+type.name()+"_"+type2.name()+log_suffix2+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(planks.asItem()));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_"+type.name()+log_suffix+"_"+type2.name()+log_suffix2+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem(), 3));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_stripped_"+type.name()+log_suffix+"_"+type2.name()+log_suffix2+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(strippedLog.asItem()));
            } else {
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_"+type2.name()+log_suffix2+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(planks.asItem()), new ItemStackTemplate(log2.asItem()));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+type2.name()+log_suffix2+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(log2.asItem()));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+type2.name()+log_suffix2+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(strippedLog.asItem()), new ItemStackTemplate(log2.asItem()));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_"+type.name()+"_"+type2.name()+log_suffix2+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(planks.asItem()), new ItemStackTemplate(log2.asItem()));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_"+type.name()+log_suffix+"_"+type2.name()+log_suffix2+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(log2.asItem()));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_stripped_"+type.name()+log_suffix+"_"+type2.name()+log_suffix2+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(strippedLog.asItem()), new ItemStackTemplate(log2.asItem()));
            }

            if (strippedLog == strippedLog2) {
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_stripped_"+type2.name()+log_suffix2+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(strippedLog.asItem(), 2));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_stripped_"+type.name()+log_suffix+"_stripped_"+type2.name()+log_suffix2+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(strippedLog.asItem(), 2));
            } else {
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_stripped_"+type2.name()+log_suffix2+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(strippedLog.asItem()), new ItemStackTemplate(strippedLog2.asItem()));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_stripped_"+type.name()+log_suffix+"_stripped_"+type2.name()+log_suffix2+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(strippedLog.asItem()), new ItemStackTemplate(strippedLog2.asItem()));
            }


            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_"+type2.name()+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(planks2.asItem()));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_"+type.name()+log_suffix+"_"+type2.name()+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(strippedLog.asItem()), new ItemStackTemplate(planks2.asItem()));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+"_stripped_"+type2.name()+log_suffix2+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(planks.asItem()), new ItemStackTemplate(strippedLog2.asItem()));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name()+log_suffix+"_stripped_"+type2.name()+log_suffix2+"_birdhouse")), 2, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(strippedLog2.asItem()));

            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_"+type.name()+log_suffix+"_"+type2.name()+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(planks2.asItem()));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_stripped_"+type.name()+log_suffix+"_"+type2.name()+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(strippedLog.asItem()), new ItemStackTemplate(planks2.asItem()));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_"+type.name()+"_stripped_"+type2.name()+log_suffix2+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem()), new ItemStackTemplate(planks.asItem()), new ItemStackTemplate(strippedLog2.asItem()));
            carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("hanging_"+type.name()+log_suffix+"_stripped_"+type2.name()+log_suffix2+"_birdhouse")), 3, FurnitureCategory.OUTDOOR_MISC, materialType, additional, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(strippedLog2.asItem()));
        }

        METALS.forEach((metal, name) -> {
            if (!name.contains("copper")) {
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + "_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, getMaterialTypeFromTop(metal), new ItemStackTemplate(planks.asItem(), 2), new ItemStackTemplate(metal.asItem()));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + log_suffix + "_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, getMaterialTypeFromTop(metal), new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(metal.asItem()));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, getMaterialTypeFromTop(metal), new ItemStackTemplate(strippedLog.asItem(), 2), new ItemStackTemplate(metal.asItem()));
            } else if (metal == Blocks.COPPER_BLOCK){
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + "_waxed_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(planks.asItem(), 2), new ItemStackTemplate(Items.WAXED_COPPER_BLOCK));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + log_suffix + "_waxed_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(Items.WAXED_COPPER_BLOCK));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_waxed_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(strippedLog.asItem(), 2), new ItemStackTemplate(Items.WAXED_COPPER_BLOCK));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + "_waxed_exposed_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(planks.asItem(), 2), new ItemStackTemplate(Items.WAXED_EXPOSED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + log_suffix + "_waxed_exposed_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(Items.WAXED_EXPOSED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_waxed_exposed_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(strippedLog.asItem(), 2), new ItemStackTemplate(Items.WAXED_EXPOSED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + "_waxed_weathered_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(planks.asItem(), 2), new ItemStackTemplate(Items.WAXED_WEATHERED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + log_suffix + "_waxed_weathered_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(Items.WAXED_WEATHERED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_waxed_weathered_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(strippedLog.asItem(), 2), new ItemStackTemplate(Items.WAXED_WEATHERED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + "_waxed_oxidized_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(planks.asItem(), 2), new ItemStackTemplate(Items.WAXED_OXIDIZED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + log_suffix + "_waxed_oxidized_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(Items.WAXED_OXIDIZED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_waxed_oxidized_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(strippedLog.asItem(), 2), new ItemStackTemplate(Items.WAXED_OXIDIZED_COPPER));

                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + "_" +  name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(planks.asItem(), 2), new ItemStackTemplate(Items.WAXED_COPPER_BLOCK));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + log_suffix + "_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(Items.WAXED_COPPER_BLOCK));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(strippedLog.asItem(), 2), new ItemStackTemplate(Items.WAXED_COPPER_BLOCK));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + "_exposed_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(planks.asItem(), 2), new ItemStackTemplate(Items.WAXED_EXPOSED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + log_suffix + "_exposed_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(Items.WAXED_EXPOSED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_exposed_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(strippedLog.asItem(), 2), new ItemStackTemplate(Items.WAXED_EXPOSED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + "_weathered_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(planks.asItem(), 2), new ItemStackTemplate(Items.WAXED_WEATHERED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + log_suffix + "_weathered_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(Items.WAXED_WEATHERED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_weathered_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(strippedLog.asItem(), 2), new ItemStackTemplate(Items.WAXED_WEATHERED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + "_oxidized_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(planks.asItem(), 2), new ItemStackTemplate(Items.WAXED_OXIDIZED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(type.name() + log_suffix + "_oxidized_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(log.asItem(), 2), new ItemStackTemplate(Items.WAXED_OXIDIZED_COPPER));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_oxidized_" + name + "_bench")), 4, FurnitureCategory.PARK_BENCHES, materialType, MaterialType.COPPER, new ItemStackTemplate(strippedLog.asItem(), 2), new ItemStackTemplate(Items.WAXED_OXIDIZED_COPPER));
            }
        });

        WOOL_COLORS.forEach((block, color) -> BlockFamilies.getAllFamilies().toList().forEach(blockFamily -> {
            if (blockFamily.getBaseBlock() == planks) {
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(color+"_"+type.name()+"_chair")), 2, FurnitureCategory.CHAIRS, materialType, new ItemStackTemplate(planks.asItem(), 1), new ItemStackTemplate(blockFamily.get(BlockFamily.Variant.TRAPDOOR).asItem(), 2), new ItemStackTemplate(block.asItem(), 1));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(color+"_"+type.name()+log_suffix+"_chair")), 2, FurnitureCategory.CHAIRS, materialType, new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(blockFamily.get(BlockFamily.Variant.TRAPDOOR).asItem(), 2), new ItemStackTemplate(block.asItem(), 1));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(color+"_stripped_"+type.name()+log_suffix+"_chair")), 2, FurnitureCategory.CHAIRS, materialType, new ItemStackTemplate(strippedLog.asItem(), 1), new ItemStackTemplate(blockFamily.get(BlockFamily.Variant.TRAPDOOR).asItem(), 2), new ItemStackTemplate(block.asItem(), 1));

                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(color+"_"+type.name()+"_lamp")), 4, FurnitureCategory.LAMPS, materialType, new ItemStackTemplate(planks.asItem(), 1), new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(strippedLog.asItem(), 1), new ItemStackTemplate(Items.REDSTONE_LAMP, 1), new ItemStackTemplate(block.asItem(), 1));

                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(color+"_"+type.name()+"_bed")), 1, FurnitureCategory.BEDS, materialType, new ItemStackTemplate(planks.asItem(), 1), new ItemStackTemplate(blockFamily.get(BlockFamily.Variant.TRAPDOOR).asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(color+"_"+type.name()+log_suffix+"_bed")), 1, FurnitureCategory.BEDS, materialType, new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(blockFamily.get(BlockFamily.Variant.TRAPDOOR).asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(color+"_stripped_"+type.name()+log_suffix+"_bed")), 1, FurnitureCategory.BEDS, materialType, new ItemStackTemplate(strippedLog.asItem(), 1), new ItemStackTemplate(blockFamily.get(BlockFamily.Variant.TRAPDOOR).asItem(), 1), new ItemStackTemplate(block.asItem(), 1));

                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(color.getName()+"_"+type.name()+"_couch")), 1, FurnitureCategory.CHAIRS, materialType, new ItemStackTemplate(planks.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(color.getName()+"_"+type.name()+log_suffix+"_couch")), 1, FurnitureCategory.CHAIRS, materialType, new ItemStackTemplate(log.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
                carpentryTableCrafting(getBlockFromIdentifier(FabulousFurniture.prefix(color.getName()+"_stripped_"+type.name()+log_suffix+"_couch")), 1, FurnitureCategory.CHAIRS, materialType, new ItemStackTemplate(strippedLog.asItem(), 1), new ItemStackTemplate(block.asItem(), 1));
            }
        }));
    }

    private MaterialType getMaterialTypeFromTop(Block block) {
        MaterialType type = MaterialType.OAK;
        for (MaterialType t : MaterialType.values().toList()) {
            if (t.item() == block.asItem()) {
                type = t;
            }
        }
        return type;
    }

    private void carpentryTableCrafting(ItemLike result, int count, FurnitureCategory category, MaterialType main, ItemStackTemplate... ingredients) {
        String resultName = BuiltInRegistries.ITEM.getKey(result.asItem()).getPath();
        this.carpentryTableCrafting(resultName, result, count, category, main, null, ingredients);
    }

    private void carpentryTableCrafting(ItemLike result, int count, FurnitureCategory category, MaterialType main, @Nullable MaterialType additional, ItemStackTemplate... ingredients) {
        String resultName = BuiltInRegistries.ITEM.getKey(result.asItem()).getPath();
        this.carpentryTableCrafting(resultName, result, count, category, main, additional, ingredients);
    }

    private void carpentryTableCrafting(String name, ItemLike result, int count, FurnitureCategory category, MaterialType main, @Nullable MaterialType additional, ItemStackTemplate ... ingredients) {
        CarpentryTableRecipe.Builder builder = CarpentryTableRecipe.builder(result, count, category);
        for(ItemStackTemplate stack : ingredients) {
            builder.requiresMaterial(stack);
            builder.unlockedBy("has_"+BuiltInRegistries.ITEM.getKey(stack.item().value()).getPath(), has(net.minecraft.advancements.criterion.MinMaxBounds.Ints.atLeast(stack.count()), stack.item().value()));
        }
        builder.containsMaterial(main);
        if (additional != null) {
            builder.containsMaterial(additional);
        }
        ResourceKey<Recipe<?>> key = ResourceKey.create(Registries.RECIPE, FabulousFurniture.prefix("carpentry/" + name));
        builder.save(this.output, key);
    }

    private Block getBlockFromIdentifier(Identifier location) {
        return BuiltInRegistries.BLOCK.getValue(location);
    }
}