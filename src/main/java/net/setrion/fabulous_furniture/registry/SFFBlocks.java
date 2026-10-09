package net.setrion.fabulous_furniture.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.fabulous_furniture.FabulousFurniture;
import net.setrion.fabulous_furniture.world.level.block.*;
import net.setrion.fabulous_furniture.world.level.block.state.properties.LampPart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class SFFBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FabulousFurniture.MOD_ID);

    public static final DeferredBlock<CarpentryTableBlock> CARPENTRY_TABLE;

    public static final List<WoodType> WOOD_TYPES = new ArrayList<>();
    public static final Map<Block, String> STONE_MATERIALS = new HashMap<>();
    public static final Map<Block, DyeColor> CONCRETE_MATERIALS = new HashMap<>();
    public static final Map<Block, DyeColor> WOOL_COLORS = new HashMap<>();
    public static final Map<Block, String> METALS = new HashMap<>();
    public static final Map<Block, String> TABLEWARE_MATERIALS = new HashMap<>();

    static {
        WOOD_TYPES.add(0, WoodType.OAK);
        WOOD_TYPES.add(1, WoodType.SPRUCE);
        WOOD_TYPES.add(2, WoodType.BIRCH);
        WOOD_TYPES.add(3, WoodType.ACACIA);
        WOOD_TYPES.add(4, WoodType.CHERRY);
        WOOD_TYPES.add(5, WoodType.JUNGLE);
        WOOD_TYPES.add(6, WoodType.DARK_OAK);
        WOOD_TYPES.add(7, WoodType.PALE_OAK);
        WOOD_TYPES.add(8, WoodType.CRIMSON);
        WOOD_TYPES.add(9, WoodType.WARPED);
        WOOD_TYPES.add(10, WoodType.MANGROVE);
        WOOD_TYPES.add(11, WoodType.BAMBOO);
        WOOD_TYPES.add(12, WoodType.POPLAR);

        STONE_MATERIALS.put(Blocks.COBBLESTONE, "");
        STONE_MATERIALS.put(Blocks.COBBLED_DEEPSLATE, "");
        STONE_MATERIALS.put(Blocks.ANDESITE, "");
        STONE_MATERIALS.put(Blocks.DIORITE, "");
        STONE_MATERIALS.put(Blocks.GRANITE, "");
        STONE_MATERIALS.put(Blocks.STONE, "");
        STONE_MATERIALS.put(Blocks.POLISHED_GRANITE, "");
        STONE_MATERIALS.put(Blocks.POLISHED_DIORITE, "");
        STONE_MATERIALS.put(Blocks.POLISHED_ANDESITE, "");
        STONE_MATERIALS.put(Blocks.POLISHED_DEEPSLATE, "");
        STONE_MATERIALS.put(Blocks.POLISHED_TUFF, "");
        STONE_MATERIALS.put(Blocks.SANDSTONE, "_top");
        STONE_MATERIALS.put(Blocks.RED_SANDSTONE, "_top");
        STONE_MATERIALS.put(Blocks.POLISHED_BLACKSTONE, "");
        STONE_MATERIALS.put(Blocks.GILDED_BLACKSTONE, "");
        STONE_MATERIALS.put(Blocks.SMOOTH_BASALT, "");
        STONE_MATERIALS.put(Blocks.QUARTZ_BLOCK, "_top");
        STONE_MATERIALS.put(Blocks.CALCITE, "");

        CONCRETE_MATERIALS.put(Blocks.CONCRETE.white(), DyeColor.WHITE);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.lightGray(), DyeColor.LIGHT_GRAY);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.gray(), DyeColor.GRAY);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.black(), DyeColor.BLACK);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.brown(), DyeColor.BROWN);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.red(), DyeColor.RED);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.orange(), DyeColor.ORANGE);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.yellow(), DyeColor.YELLOW);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.lime(), DyeColor.LIME);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.green(), DyeColor.GREEN);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.cyan(), DyeColor.CYAN);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.lightBlue(), DyeColor.LIGHT_BLUE);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.blue(), DyeColor.BLUE);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.purple(), DyeColor.PURPLE);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.magenta(), DyeColor.MAGENTA);
        CONCRETE_MATERIALS.put(Blocks.CONCRETE.pink(), DyeColor.PINK);

        WOOL_COLORS.put(Blocks.WOOL.white(), DyeColor.WHITE);
        WOOL_COLORS.put(Blocks.WOOL.lightGray(), DyeColor.LIGHT_GRAY);
        WOOL_COLORS.put(Blocks.WOOL.gray(), DyeColor.GRAY);
        WOOL_COLORS.put(Blocks.WOOL.black(), DyeColor.BLACK);
        WOOL_COLORS.put(Blocks.WOOL.brown(), DyeColor.BROWN);
        WOOL_COLORS.put(Blocks.WOOL.red(), DyeColor.RED);
        WOOL_COLORS.put(Blocks.WOOL.orange(), DyeColor.ORANGE);
        WOOL_COLORS.put(Blocks.WOOL.yellow(), DyeColor.YELLOW);
        WOOL_COLORS.put(Blocks.WOOL.lime(), DyeColor.LIME);
        WOOL_COLORS.put(Blocks.WOOL.green(), DyeColor.GREEN);
        WOOL_COLORS.put(Blocks.WOOL.cyan(), DyeColor.CYAN);
        WOOL_COLORS.put(Blocks.WOOL.lightBlue(), DyeColor.LIGHT_BLUE);
        WOOL_COLORS.put(Blocks.WOOL.blue(), DyeColor.BLUE);
        WOOL_COLORS.put(Blocks.WOOL.purple(), DyeColor.PURPLE);
        WOOL_COLORS.put(Blocks.WOOL.magenta(), DyeColor.MAGENTA);
        WOOL_COLORS.put(Blocks.WOOL.pink(), DyeColor.PINK);

        METALS.put(Blocks.COPPER_BLOCK.weathering().unaffected(), "copper");
        METALS.put(Blocks.COPPER_BLOCK.weathering().exposed(), "exposed_copper");
        METALS.put(Blocks.COPPER_BLOCK.weathering().weathered(), "weathered_copper");
        METALS.put(Blocks.COPPER_BLOCK.weathering().oxidized(), "oxidized_copper");
        METALS.put(Blocks.COPPER_BLOCK.waxed().unaffected(), "waxed_copper");
        METALS.put(Blocks.COPPER_BLOCK.waxed().exposed(), "waxed_exposed_copper");
        METALS.put(Blocks.COPPER_BLOCK.waxed().weathered(), "waxed_weathered_copper");
        METALS.put(Blocks.COPPER_BLOCK.waxed().oxidized(), "waxed_oxidized_copper");
        METALS.put(Blocks.IRON_BLOCK, "iron");
        METALS.put(Blocks.GOLD_BLOCK, "gold");
        METALS.put(Blocks.NETHERITE_BLOCK, "netherite");

        TABLEWARE_MATERIALS.put(Blocks.QUARTZ_BLOCK, "_top");
        TABLEWARE_MATERIALS.put(Blocks.TERRACOTTA, "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.white(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.lightGray(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.gray(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.black(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.brown(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.red(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.orange(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.yellow(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.lime(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.green(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.cyan(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.lightBlue(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.blue(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.purple(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.magenta(), "");
        TABLEWARE_MATERIALS.put(Blocks.DYED_TERRACOTTA.pink(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.white(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.lightGray(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.gray(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.black(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.brown(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.red(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.orange(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.yellow(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.lime(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.green(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.cyan(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.lightBlue(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.blue(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.purple(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.magenta(), "");
        TABLEWARE_MATERIALS.put(Blocks.GLAZED_TERRACOTTA.pink(), "");

        CARPENTRY_TABLE = registerBlockWithItem("carpentry_table", CarpentryTableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
        for (WoodType type : WOOD_TYPES) {
            String log_suffix;
            if (type == WoodType.CRIMSON || type == WoodType.WARPED) {
                log_suffix = "_stem";
            } else if (type == WoodType.BAMBOO) {
                log_suffix = "_block";
            } else {
                log_suffix = "_log";
            }
            registerBlockWithItem(type.name()+"_crate", CrateBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            STONE_MATERIALS.forEach(((block, suffix) -> {
                String top_name = block.getDescriptionId().replaceFirst("block.minecraft.", "").replaceFirst("quartz_block", "quartz");
                registerBlockWithItem(type.name()+"_"+top_name+"_kitchen_counter", KitchenCounterBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
                registerBlockWithItem(type.name()+"_"+top_name+"_kitchen_counter_shelf", properties -> new KitchenCounterContainerBaseBlock(properties, false, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+"_"+top_name+"_kitchen_counter_door", KitchenCounterOpenableHingeContainerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+"_"+top_name+"_kitchen_counter_small_drawer", properties -> new KitchenCounterOpenableContainerBlock(properties, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+"_"+top_name+"_kitchen_counter_big_drawer", properties -> new KitchenCounterOpenableContainerBlock(properties, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+"_"+top_name+"_kitchen_counter_sink", KitchenCounterSinkBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).randomTicks().pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+"_"+top_name+"_kitchen_counter_smoker", KitchenCounterSmokerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+"_"+top_name+"_kitchen_cabinet_door", properties -> new KitchenCabinetOpenableContainerBlock(properties, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+"_"+top_name+"_kitchen_cabinet_glass_door", properties -> new KitchenCabinetOpenableContainerBlock(properties, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+"_"+top_name+"_kitchen_cabinet_sideways_door", KitchenCabinetOpenableHingeContainerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+"_"+top_name+"_kitchen_cabinet_sideways_glass_door", KitchenCabinetOpenableHingeContainerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));

                registerBlockWithItem(type.name()+log_suffix+"_"+top_name+"_kitchen_counter", KitchenCounterBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
                registerBlockWithItem(type.name()+log_suffix+"_"+top_name+"_kitchen_counter_shelf", properties -> new KitchenCounterContainerBaseBlock(properties, false, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+log_suffix+"_"+top_name+"_kitchen_counter_door", KitchenCounterOpenableHingeContainerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+log_suffix+"_"+top_name+"_kitchen_counter_small_drawer", properties -> new KitchenCounterOpenableContainerBlock(properties, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+log_suffix+"_"+top_name+"_kitchen_counter_big_drawer", properties -> new KitchenCounterOpenableContainerBlock(properties, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+log_suffix+"_"+top_name+"_kitchen_counter_sink", KitchenCounterSinkBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).randomTicks().pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+log_suffix+"_"+top_name+"_kitchen_counter_smoker", KitchenCounterSmokerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_door", properties -> new KitchenCabinetOpenableContainerBlock(properties, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_glass_door", properties -> new KitchenCabinetOpenableContainerBlock(properties, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_sideways_door", KitchenCabinetOpenableHingeContainerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem(type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_sideways_glass_door", KitchenCabinetOpenableHingeContainerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));

                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter", KitchenCounterBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter_shelf", properties -> new KitchenCounterContainerBaseBlock(properties, false, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter_door", KitchenCounterOpenableHingeContainerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter_small_drawer", properties -> new KitchenCounterOpenableContainerBlock(properties, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter_big_drawer", properties -> new KitchenCounterOpenableContainerBlock(properties, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter_sink", KitchenCounterSinkBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).randomTicks().pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_counter_smoker", KitchenCounterSmokerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_door", properties -> new KitchenCabinetOpenableContainerBlock(properties, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_glass_door", properties -> new KitchenCabinetOpenableContainerBlock(properties, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_sideways_door", KitchenCabinetOpenableHingeContainerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_"+top_name+"_kitchen_cabinet_sideways_glass_door", KitchenCabinetOpenableHingeContainerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));

                registerBlockWithItem(type.name()+"_"+top_name+"_knife_block", properties -> new RotatableBlock(properties, Block.box(4, 0, 4, 12, 14, 12)), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            }));

            registerBlockWithItem(type.name()+"_kitchen_cabinet", KitchenCabinetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_kitchen_cabinet", KitchenCabinetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_kitchen_cabinet", KitchenCabinetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_kitchen_cabinet_shelf", properties -> new KitchenCabinetContainerBaseBlock(properties, false, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
            registerBlockWithItem(type.name()+log_suffix+"_kitchen_cabinet_shelf", properties -> new KitchenCabinetContainerBaseBlock(properties, false, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_kitchen_cabinet_shelf", properties -> new KitchenCabinetContainerBaseBlock(properties, false, false), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.IMMOVEABLE));

            METALS.forEach((metal, name) -> {
                if (metal != Blocks.GOLD_BLOCK && metal != Blocks.NETHERITE_BLOCK) {
                    if (!name.contains("waxed") && name.contains("copper")) {
                        registerBlockWithItem(type.name() + "_" + name + "_kitchen_shelf", properties -> new WeatheringKitchenShelfBlock(getWeatherStateFromBlock(metal), properties), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name() + "_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                        registerBlockWithItem(type.name() + log_suffix + "_" + name + "_kitchen_shelf", properties -> new WeatheringKitchenShelfBlock(getWeatherStateFromBlock(metal), properties), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name() + "_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                        registerBlockWithItem("stripped_" + type.name() + log_suffix + "_" + name + "_kitchen_shelf", properties -> new WeatheringKitchenShelfBlock(getWeatherStateFromBlock(metal), properties), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name() + "_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                    } else {
                        registerBlockWithItem(type.name() + "_" + name + "_kitchen_shelf", KitchenShelfBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name() + "_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                        registerBlockWithItem(type.name() + log_suffix + "_" + name + "_kitchen_shelf", KitchenShelfBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name() + "_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                        registerBlockWithItem("stripped_" + type.name() + log_suffix + "_" + name + "_kitchen_shelf", KitchenShelfBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name() + "_planks"))).pushReaction(PushReaction.IMMOVEABLE));
                    }
                }
            });

            registerBlockWithItem(type.name()+"_flower_box_inner_corner", FlowerBoxInnerCornerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_flower_box_inner_corner", FlowerBoxInnerCornerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_flower_box_inner_corner", FlowerBoxInnerCornerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_flower_box", FlowerBoxBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_flower_box", FlowerBoxBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_flower_box", FlowerBoxBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_flower_box_outer_corner", FlowerBoxOuterCornerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_flower_box_outer_corner", FlowerBoxOuterCornerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_flower_box_outer_corner", FlowerBoxOuterCornerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_flower_box_big", FlowerBoxBigBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_flower_box_big", FlowerBoxBigBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_flower_box_big", FlowerBoxBigBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_trash_bin", TrashBinBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_trash_bin", TrashBinBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_trash_bin", TrashBinBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_clock", WoodenClockBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_clock", WoodenClockBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_clock", WoodenClockBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_wall_clock", WoodenWallClockBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_wall_clock", WoodenWallClockBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_wall_clock", WoodenWallClockBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_grandfather_clock", GrandfatherClockBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_grandfather_clock", GrandfatherClockBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_grandfather_clock", GrandfatherClockBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem("modern_"+type.name()+"_grandfather_clock", ModernGrandfatherClockBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("modern_"+type.name()+log_suffix+"_grandfather_clock", ModernGrandfatherClockBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("modern_stripped_"+type.name()+log_suffix+"_grandfather_clock", ModernGrandfatherClockBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_disc_mount", DiscMountBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_disc_mount", DiscMountBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_disc_mount", DiscMountBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_music_disc_shelf", MusicDiscShelfBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_music_disc_shelf", MusicDiscShelfBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_music_disc_shelf", MusicDiscShelfBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_globe", GlobeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            for (WoodType type2 : WOOD_TYPES) {
                String log_suffix2;
                if (type2 == WoodType.CRIMSON || type2 == WoodType.WARPED) {
                    log_suffix2 = "_stem";
                } else if (type2 == WoodType.BAMBOO) {
                    log_suffix2 = "_block";
                } else {
                    log_suffix2 = "_log";
                }

                registerBlockWithItem(type.name()+"_"+type2.name()+"_birdhouse", BirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem(type.name()+log_suffix+"_"+type2.name()+"_birdhouse", BirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_"+type2.name()+"_birdhouse", BirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem(type.name()+"_"+type2.name()+log_suffix2+"_birdhouse", BirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem(type.name()+log_suffix+"_"+type2.name()+log_suffix2+"_birdhouse", BirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_"+type2.name()+log_suffix2+"_birdhouse", BirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem(type.name()+"_stripped_"+type2.name()+log_suffix2+"_birdhouse", BirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem(type.name()+log_suffix+"_stripped_"+type2.name()+log_suffix2+"_birdhouse", BirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem("stripped_"+type.name()+log_suffix+"_stripped_"+type2.name()+log_suffix2+"_birdhouse", BirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));

                registerBlockWithItem("hanging_"+type.name()+"_"+type2.name()+"_birdhouse", HangingBirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem("hanging_"+type.name()+log_suffix+"_"+type2.name()+"_birdhouse", HangingBirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem("hanging_stripped_"+type.name()+log_suffix+"_"+type2.name()+"_birdhouse", HangingBirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem("hanging_"+type.name()+"_"+type2.name()+log_suffix2+"_birdhouse", HangingBirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem("hanging_"+type.name()+log_suffix+"_"+type2.name()+log_suffix2+"_birdhouse", HangingBirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem("hanging_stripped_"+type.name()+log_suffix+"_"+type2.name()+log_suffix2+"_birdhouse", HangingBirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem("hanging_"+type.name()+"_stripped_"+type2.name()+log_suffix2+"_birdhouse", HangingBirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem("hanging_"+type.name()+log_suffix+"_stripped_"+type2.name()+log_suffix2+"_birdhouse", HangingBirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
                registerBlockWithItem("hanging_stripped_"+type.name()+log_suffix+"_stripped_"+type2.name()+log_suffix2+"_birdhouse", HangingBirdhouseBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).pushReaction(PushReaction.POPPED));
            }

            WOOL_COLORS.forEach(((block, color) -> {
                registerBlockWithItem(color.getName()+"_"+type.name()+"_chair", ChairBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
                registerBlockWithItem(color.getName()+"_"+type.name()+log_suffix+"_chair", ChairBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
                registerBlockWithItem(color.getName()+"_stripped_"+type.name()+log_suffix+"_chair", ChairBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
                registerBlockWithItem(color.getName()+"_"+type.name()+"_lamp", LampBlock::new, () -> lampProperties(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
                registerBlockWithItem(color.getName()+"_"+type.name()+"_bed", WoodenBedBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).noOcclusion().bounceRestitution(0.75F));
                registerBlockWithItem(color.getName()+"_"+type.name()+log_suffix+"_bed", WoodenBedBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).noOcclusion());
                registerBlockWithItem(color.getName()+"_stripped_"+type.name()+log_suffix+"_bed", WoodenBedBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))).noOcclusion());

                registerBlockWithItem(color.getName()+"_"+type.name()+"_couch", CouchBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
                registerBlockWithItem(color.getName()+"_"+type.name()+log_suffix+"_couch", CouchBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
                registerBlockWithItem(color.getName()+"_stripped_"+type.name()+log_suffix+"_couch", CouchBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

                registerBlockWithItem(color.getName()+"_"+type.name()+"_footstool", FootstoolBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
                registerBlockWithItem(color.getName()+"_"+type.name()+log_suffix+"_footstool", FootstoolBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
                registerBlockWithItem(color.getName()+"_stripped_"+type.name()+log_suffix+"_footstool", FootstoolBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            }));

            registerBlockWithItem(type.name()+"_table", TableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_table", TableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_table", TableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_couch_table", CouchTableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_couch_table", CouchTableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_couch_table", CouchTableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_bedside_table", BedsideTableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_bedside_table", BedsideTableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_bedside_table", BedsideTableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            registerBlockWithItem(type.name()+"_closet", ClosetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem(type.name()+log_suffix+"_closet", ClosetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));
            registerBlockWithItem("stripped_"+type.name()+log_suffix+"_closet", ClosetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name()+"_planks"))));

            /*TABLEWARE_MATERIALS.forEach((block, suffix) -> {
                String top_name = block.getDescriptionId().replaceFirst("block.minecraft.", "").replaceFirst("quartz_block", "quartz");
                registerBlockWithItem(type.name() + "_" + top_name+"_tiled_coffee_table", TiledCoffeeTableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(block));
                registerBlockWithItem(type.name() + log_suffix + "_" + top_name+"_tiled_coffee_table", TiledCoffeeTableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(block));
                registerBlockWithItem("stripped_" + type.name() + log_suffix + "_" + top_name+"_tiled_coffee_table", TiledCoffeeTableBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(block));
            });*/

            METALS.forEach((metal, name) -> {
                if (!name.contains("waxed") && name.contains("copper")) {
                    registerBlockWithItem(type.name() + "_" + name + "_bench", properties -> new WeatheringBenchBlock(getWeatherStateFromBlock(metal), properties), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name() + "_planks"))).noOcclusion());
                    registerBlockWithItem(type.name() + log_suffix + "_" + name + "_bench", properties -> new WeatheringBenchBlock(WeatheringCopper.WeatherState.UNAFFECTED, properties), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name() + "_planks"))).noOcclusion());
                    registerBlockWithItem("stripped_" + type.name() + log_suffix + "_" + name + "_bench", properties -> new WeatheringBenchBlock(WeatheringCopper.WeatherState.UNAFFECTED, properties), () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name() + "_planks"))).noOcclusion());
                } else {
                    registerBlockWithItem(type.name() + "_" + name + "_bench", BenchBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name() + "_planks"))).noOcclusion());
                    registerBlockWithItem(type.name() + log_suffix + "_" + name + "_bench", BenchBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name() + "_planks"))).noOcclusion());
                    registerBlockWithItem("stripped_" + type.name() + log_suffix + "_" + name + "_bench", BenchBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BuiltInRegistries.BLOCK.getValue(Identifier.parse(type.name() + "_planks"))).noOcclusion());
                }
            });
        }

        STONE_MATERIALS.forEach(((block, suffix) -> {
            String top_name = block.getDescriptionId().replaceFirst("block.minecraft.", "").replaceFirst("quartz_block", "quartz");
            registerBlockWithItem(top_name+"_birdbath", BirdbathBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(block));
        }));

        TABLEWARE_MATERIALS.forEach((block, suffix) -> {
            String top_name = block.getDescriptionId().replaceFirst("block.minecraft.", "").replaceFirst("quartz_block", "quartz");
            registerBlockWithItem(top_name+"_tableware", TablewareBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(block));
        });

        WOOL_COLORS.forEach((block, color) -> METALS.forEach((metal, name) -> {
            if (!name.contains("waxed") && name.contains("copper")) {
                registerBlockWithItem(color+"_"+name+"_curtains", properties -> new WeatheringCurtainBlock(getWeatherStateFromBlock(metal), properties), () -> BlockBehaviour.Properties.ofFullCopy(block).noOcclusion());
            } else {
                registerBlockWithItem(color+"_"+name+"_curtains", CurtainBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(block).noOcclusion());
            }
        }));

        METALS.forEach((metal, name) ->  {
            if (!name.contains("waxed") && name.contains("copper")) {
                registerBlockWithItem(name+"_toaster", properties -> new WeatheringToasterBlock(getWeatherStateFromBlock(metal), properties), () -> BlockBehaviour.Properties.ofFullCopy(metal));
            } else {
                registerBlockWithItem(name+"_toaster", ToasterBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(metal).noOcclusion());
            }
        });

        CONCRETE_MATERIALS.forEach((concrete, color) -> {
            registerBlockWithItem(color.getName()+"_ceiling_lamp", CeilingLampBlock::new, () -> ceilingLampProperties(concrete));
        });
    }

    public static final DeferredBlock<Block> IRON_FRIDGE = registerBlockWithItem("iron_fridge", FridgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).pushReaction(PushReaction.IMMOVEABLE));
    public static final DeferredBlock<Block> GOLD_FRIDGE = registerBlockWithItem("gold_fridge", FridgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).pushReaction(PushReaction.IMMOVEABLE));
    public static final DeferredBlock<Block> NETHERITE_FRIDGE = registerBlockWithItem("netherite_fridge", FridgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).pushReaction(PushReaction.IMMOVEABLE));
    public static final DeferredBlock<Block> COPPER_FRIDGE = registerBlockWithItem("copper_fridge", properties -> new WeatheringKitchenFridgeBlock(WeatheringCopper.WeatherState.UNAFFECTED, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK.weathering().unaffected()).pushReaction(PushReaction.IMMOVEABLE));
    public static final DeferredBlock<Block> EXPOSED_COPPER_FRIDGE = registerBlockWithItem("exposed_copper_fridge", properties -> new WeatheringKitchenFridgeBlock(WeatheringCopper.WeatherState.WEATHERED, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK.weathering().exposed()).pushReaction(PushReaction.IMMOVEABLE));
    public static final DeferredBlock<Block> WEATHERED_COPPER_FRIDGE = registerBlockWithItem("weathered_copper_fridge", properties -> new WeatheringKitchenFridgeBlock(WeatheringCopper.WeatherState.EXPOSED, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK.weathering().weathered()).pushReaction(PushReaction.IMMOVEABLE));
    public static final DeferredBlock<Block> OXIDIZED_COPPER_FRIDGE = registerBlockWithItem("oxidized_copper_fridge", properties -> new WeatheringKitchenFridgeBlock(WeatheringCopper.WeatherState.OXIDIZED, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK.weathering().oxidized()).pushReaction(PushReaction.IMMOVEABLE));
    public static final DeferredBlock<Block> WAXED_COPPER_FRIDGE = registerBlockWithItem("waxed_copper_fridge", FridgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK.waxed().unaffected()).pushReaction(PushReaction.IMMOVEABLE));
    public static final DeferredBlock<Block> WAXED_EXPOSED_COPPER_FRIDGE = registerBlockWithItem("waxed_exposed_copper_fridge", FridgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK.waxed().exposed()).pushReaction(PushReaction.IMMOVEABLE));
    public static final DeferredBlock<Block> WAXED_WEATHERED_COPPER_FRIDGE = registerBlockWithItem("waxed_weathered_copper_fridge", FridgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK.waxed().weathered()).pushReaction(PushReaction.IMMOVEABLE));
    public static final DeferredBlock<Block> WAXED_OXIDIZED_COPPER_FRIDGE = registerBlockWithItem("waxed_oxidized_copper_fridge", FridgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK.waxed().oxidized()).pushReaction(PushReaction.IMMOVEABLE));

    public static final DeferredBlock<Block> OAK_HEDGE = registerBlockWithItem("oak_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> SPRUCE_HEDGE = registerBlockWithItem("spruce_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES));
    public static final DeferredBlock<Block> BIRCH_HEDGE = registerBlockWithItem("birch_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LEAVES));
    public static final DeferredBlock<Block> JUNGLE_HEDGE = registerBlockWithItem("jungle_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LEAVES));
    public static final DeferredBlock<Block> ACACIA_HEDGE = registerBlockWithItem("acacia_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_LEAVES));
    public static final DeferredBlock<Block> CHERRY_HEDGE = registerBlockWithItem("cherry_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES));
    public static final DeferredBlock<Block> DARK_OAK_HEDGE = registerBlockWithItem("dark_oak_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_LEAVES));
    public static final DeferredBlock<Block> PALE_OAK_HEDGE = registerBlockWithItem("pale_oak_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_LEAVES));
    public static final DeferredBlock<Block> RED_POPLAR_HEDGE = registerBlockWithItem("red_poplar_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.RED_POPLAR_LEAVES));
    public static final DeferredBlock<Block> ORANGE_POPLAR_HEDGE = registerBlockWithItem("orange_poplar_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_POPLAR_LEAVES));
    public static final DeferredBlock<Block> YELLOW_POPLAR_HEDGE = registerBlockWithItem("yellow_poplar_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_POPLAR_LEAVES));
    public static final DeferredBlock<Block> MANGROVE_HEDGE = registerBlockWithItem("mangrove_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_LEAVES));
    public static final DeferredBlock<Block> AZALEA_HEDGE = registerBlockWithItem("azalea_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA_LEAVES));
    public static final DeferredBlock<Block> FLOWERING_AZALEA_HEDGE = registerBlockWithItem("flowering_azalea_hedge", HedgeBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWERING_AZALEA_LEAVES));

    public static final DeferredBlock<Block> WHITE_LIGHT_GRAY_KITCHEN_TILES = registerBlockWithItem("white_light_gray_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_GRAY_KITCHEN_TILES = registerBlockWithItem("white_gray_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_BLACK_KITCHEN_TILES = registerBlockWithItem("white_black_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_BROWN_KITCHEN_TILES = registerBlockWithItem("white_brown_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_RED_KITCHEN_TILES = registerBlockWithItem("white_red_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_ORANGE_KITCHEN_TILES = registerBlockWithItem("white_orange_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_YELLOW_KITCHEN_TILES = registerBlockWithItem("white_yellow_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_LIME_KITCHEN_TILES = registerBlockWithItem("white_lime_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_GREEN_KITCHEN_TILES = registerBlockWithItem("white_green_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_CYAN_KITCHEN_TILES = registerBlockWithItem("white_cyan_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_LIGHT_BLUE_KITCHEN_TILES = registerBlockWithItem("white_light_blue_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_BLUE_KITCHEN_TILES = registerBlockWithItem("white_blue_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_PURPLE_KITCHEN_TILES = registerBlockWithItem("white_purple_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_MAGENTA_KITCHEN_TILES = registerBlockWithItem("white_magenta_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));
    public static final DeferredBlock<Block> WHITE_PINK_KITCHEN_TILES = registerBlockWithItem("white_pink_kitchen_tiles", KitchenTileBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CONCRETE.white()));

    private static WeatheringCopper.WeatherState getWeatherStateFromBlock(Block metal) {
        if (metal == Blocks.COPPER_BLOCK.weathering().unaffected()) return WeatheringCopper.WeatherState.UNAFFECTED;
        else if (metal == Blocks.COPPER_BLOCK.weathering().exposed()) return WeatheringCopper.WeatherState.EXPOSED;
        else if (metal == Blocks.COPPER_BLOCK.weathering().weathered()) return WeatheringCopper.WeatherState.WEATHERED;
        else return WeatheringCopper.WeatherState.OXIDIZED;
    }

    private static BlockBehaviour.Properties lampProperties(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block).lightLevel((state) -> state.getValue(LampBlock.ON) && (state.getValue(LampBlock.PART) == LampPart.TOP || state.getValue(LampBlock.PART) == LampPart.SINGLE) ? 15 : 0);
    }

    private static BlockBehaviour.Properties ceilingLampProperties(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block).lightLevel((state) -> state.getValue(CeilingLampBlock.ON) && (state.getValue(CeilingLampBlock.PART) == LampPart.BOTTOM || state.getValue(CeilingLampBlock.PART) == LampPart.SINGLE) ? 15 : 0);
    }
    
    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
        return BLOCKS.register(name, () -> block.apply(properties.get().setId(ResourceKey.create(Registries.BLOCK, FabulousFurniture.prefix(name)))));
    }

    public static <T extends Block> DeferredBlock<T> registerBlockWithItem(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
        DeferredBlock<T> deferredBlock = BLOCKS.register(name, () -> block.apply(properties.get().setId(ResourceKey.create(Registries.BLOCK, FabulousFurniture.prefix(name)))));
        SFFItems.registerItem(name, itemProps -> new BlockItem(deferredBlock.get(), itemProps), new Item.Properties());
        return deferredBlock;
    }
}