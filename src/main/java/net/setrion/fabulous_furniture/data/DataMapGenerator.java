package net.setrion.fabulous_furniture.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Oxidizable;
import net.neoforged.neoforge.registries.datamaps.builtin.Waxable;
import net.setrion.fabulous_furniture.FabulousFurniture;
import net.setrion.fabulous_furniture.registry.SFFBlocks;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static net.setrion.fabulous_furniture.registry.SFFBlocks.*;

public class DataMapGenerator extends DataMapProvider {

    public DataMapGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        builder(NeoForgeDataMaps.OXIDIZABLES)
                .add(SFFBlocks.COPPER_FRIDGE.getKey(), new Oxidizable(SFFBlocks.EXPOSED_COPPER_FRIDGE.get()), false)
                .add(SFFBlocks.EXPOSED_COPPER_FRIDGE.getKey(), new Oxidizable(SFFBlocks.WEATHERED_COPPER_FRIDGE.get()), false)
                .add(SFFBlocks.WEATHERED_COPPER_FRIDGE.getKey(), new Oxidizable(SFFBlocks.OXIDIZED_COPPER_FRIDGE.get()), false);

        builder(NeoForgeDataMaps.WAXABLES)
                .add(SFFBlocks.COPPER_FRIDGE.getKey(), new Waxable(SFFBlocks.WAXED_COPPER_FRIDGE.get()), false)
                .add(SFFBlocks.EXPOSED_COPPER_FRIDGE.getKey(), new Waxable(SFFBlocks.WAXED_EXPOSED_COPPER_FRIDGE.get()), false)
                .add(SFFBlocks.WEATHERED_COPPER_FRIDGE.getKey(), new Waxable(SFFBlocks.WAXED_WEATHERED_COPPER_FRIDGE.get()), false)
                .add(SFFBlocks.OXIDIZED_COPPER_FRIDGE.getKey(), new Waxable(SFFBlocks.WAXED_OXIDIZED_COPPER_FRIDGE.get()), false);

        METALS.forEach((metal, name) -> {
            if (metal == Blocks.COPPER_BLOCK) {
                builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(name+"_toaster")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix("exposed_" + name + "_toaster"))), false);
                builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("exposed_"+name+"_toaster")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix("weathered_" + name + "_toaster"))), false);
                builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("weathered_"+name+"_toaster")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix("oxidized_" + name + "_toaster"))), false);

                builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(name+"_toaster")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix("waxed_" + name + "_toaster"))), false);
                builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("exposed_"+name+"_toaster")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix("waxed_exposed_" + name + "_toaster"))), false);
                builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("weathered_"+name+"_toaster")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix("waxed_weathered_" + name + "_toaster"))), false);
                builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("oxidized_"+name+"_toaster")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix("waxed_oxidized_" + name + "_toaster"))), false);
            }
        });

        WOOL_COLORS.forEach((wool, color) -> {
            METALS.forEach((metal, name) -> {
                if (metal == Blocks.COPPER_BLOCK) {
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(color+"_"+name+"_curtains")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(color+"_exposed_" + name + "_curtains"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(color+"_exposed_"+name+"_curtains")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(color+"_weathered_" + name + "_curtains"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(color+"_weathered_"+name+"_curtains")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(color+"_oxidized_" + name + "_curtains"))), false);

                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(color+"_"+name+"_curtains")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(color+"_waxed_" + name + "_curtains"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(color+"_exposed_"+name+"_curtains")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(color+"_waxed_exposed_" + name + "_curtains"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(color+"_weathered_"+name+"_curtains")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(color+"_waxed_weathered_" + name + "_curtains"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(color+"_oxidized_"+name+"_curtains")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(color+"_waxed_oxidized_" + name + "_curtains"))), false);
                }
            });
        });

        for (WoodType type : WOOD_TYPES) {
            String log_suffix;
            if (type == WoodType.CRIMSON || type == WoodType.WARPED) {
                log_suffix = "_stem";
            } else if (type == WoodType.BAMBOO) {
                log_suffix = "_block";
            } else {
                log_suffix = "_log";
            }

            METALS.forEach((metal, name) -> {
                if (metal == Blocks.COPPER_BLOCK) {
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_" + name + "_kitchen_shelf")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_exposed_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_exposed_" + name + "_kitchen_shelf")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_weathered_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_weathered_" + name + "_kitchen_shelf")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_oxidized_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_" + name + "_kitchen_shelf")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_exposed_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_exposed_" + name + "_kitchen_shelf")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_weathered_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_weathered_" + name + "_kitchen_shelf")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_oxidized_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_" + name + "_kitchen_shelf")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_exposed_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_exposed_" + name + "_kitchen_shelf")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_weathered_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_weathered_" + name + "_kitchen_shelf")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_oxidized_" + name + "_kitchen_shelf"))), false);

                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_" + name + "_kitchen_shelf")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_waxed_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_exposed_" + name + "_kitchen_shelf")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_waxed_exposed_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_weathered_" + name + "_kitchen_shelf")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_waxed_weathered_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_oxidized_" + name + "_kitchen_shelf")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_waxed_oxidized_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_" + name + "_kitchen_shelf")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_waxed_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_exposed_" + name + "_kitchen_shelf")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_waxed_exposed_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_weathered_" + name + "_kitchen_shelf")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_waxed_weathered_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_oxidized_" + name + "_kitchen_shelf")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_waxed_oxidized_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_" + name + "_kitchen_shelf")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_waxed_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_exposed_" + name + "_kitchen_shelf")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_waxed_exposed_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_weathered_" + name + "_kitchen_shelf")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_waxed_weathered_" + name + "_kitchen_shelf"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_oxidized_" + name + "_kitchen_shelf")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_waxed_oxidized_" + name + "_kitchen_shelf"))), false);

                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_" + name + "_bench")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_exposed_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_exposed_" + name + "_bench")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_weathered_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_weathered_" + name + "_bench")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_oxidized_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_" + name + "_bench")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_exposed_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_exposed_" + name + "_bench")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_weathered_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_weathered_" + name + "_bench")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_oxidized_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_" + name + "_bench")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_exposed_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_exposed_" + name + "_bench")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_weathered_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.OXIDIZABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_weathered_" + name + "_bench")).get(), new Oxidizable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_oxidized_" + name + "_bench"))), false);

                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_" + name + "_bench")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_waxed_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_exposed_" + name + "_bench")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_waxed_exposed_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_weathered_" + name + "_bench")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_waxed_weathered_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + "_oxidized_" + name + "_bench")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + "_waxed_oxidized_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_" + name + "_bench")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_waxed_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_exposed_" + name + "_bench")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_waxed_exposed_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_weathered_" + name + "_bench")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_waxed_weathered_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_oxidized_" + name + "_bench")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix(type.name() + log_suffix + "_waxed_oxidized_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_" + name + "_bench")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_waxed_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_exposed_" + name + "_bench")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_waxed_exposed_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_weathered_" + name + "_bench")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_waxed_weathered_" + name + "_bench"))), false);
                    builder(NeoForgeDataMaps.WAXABLES).add(getKeyFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_oxidized_" + name + "_bench")).get(), new Waxable(getBlockFromResourceLocation(FabulousFurniture.prefix("stripped_" + type.name() + log_suffix + "_waxed_oxidized_" + name + "_bench"))), false);
                }
            });
        }
    }

    private Block getBlockFromResourceLocation(Identifier location) {
        return BuiltInRegistries.BLOCK.getValue(location);
    }

    private Optional<ResourceKey<Block>> getKeyFromResourceLocation(Identifier location) {
        return BuiltInRegistries.BLOCK.getResourceKey(getBlockFromResourceLocation(location));
    }
}