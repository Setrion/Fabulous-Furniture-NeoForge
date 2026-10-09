package net.setrion.fabulous_furniture.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.fabulous_furniture.FabulousFurniture;
import net.setrion.fabulous_furniture.world.level.block.entity.*;

public class SFFBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, FabulousFurniture.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SFFBaseContainerBlockEntity>> SFF_CONTAINER = BLOCK_ENTITY_TYPES.register("sff_container", () -> new BlockEntityType<>(SFFBaseContainerBlockEntity::new,
            BuiltInRegistries.BLOCK.get(FabulousFurniture.prefix("oak_polished_tuff_kitchen_counter_shelf")).get().value()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CrateBlockEntity>> CRATE = BLOCK_ENTITY_TYPES.register("crate", () -> new BlockEntityType<>(CrateBlockEntity::new,
            BuiltInRegistries.BLOCK.get(FabulousFurniture.prefix("oak_crate")).get().value()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BedsideTableBlockEntity>> BEDSIDE_TABLE = BLOCK_ENTITY_TYPES.register("bedside_table", () -> new BlockEntityType<>(BedsideTableBlockEntity::new,
            BuiltInRegistries.BLOCK.get(FabulousFurniture.prefix("oak_bedside_table")).get().value()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ClosetBlockEntity>> CLOSET = BLOCK_ENTITY_TYPES.register("closet", () -> new BlockEntityType<>(ClosetBlockEntity::new,
            BuiltInRegistries.BLOCK.get(FabulousFurniture.prefix("oak_closet")).get().value()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<KitchenStorageBaseBlockEntity>> KITCHEN_STORAGE = BLOCK_ENTITY_TYPES.register("kitchen_storage", () -> new BlockEntityType<>(KitchenStorageBaseBlockEntity::new,
            BuiltInRegistries.BLOCK.get(FabulousFurniture.prefix("oak_polished_tuff_kitchen_counter_shelf")).get().value()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<KitchenFridgeBlockEntity>> FRIDGE = BLOCK_ENTITY_TYPES.register("fridge", () -> new BlockEntityType<>(KitchenFridgeBlockEntity::new,
            SFFBlocks.IRON_FRIDGE.get(), SFFBlocks.GOLD_FRIDGE.get(), SFFBlocks.NETHERITE_FRIDGE.get(), SFFBlocks.COPPER_FRIDGE.get(), SFFBlocks.EXPOSED_COPPER_FRIDGE.get(), SFFBlocks.WEATHERED_COPPER_FRIDGE.get(), SFFBlocks.OXIDIZED_COPPER_FRIDGE.get(), SFFBlocks.WAXED_COPPER_FRIDGE.get(), SFFBlocks.WAXED_EXPOSED_COPPER_FRIDGE.get(), SFFBlocks.WAXED_WEATHERED_COPPER_FRIDGE.get(), SFFBlocks.WAXED_OXIDIZED_COPPER_FRIDGE.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FlowerBoxBlockEntity>> FLOWER_BOX = BLOCK_ENTITY_TYPES.register("flower_box", () -> new BlockEntityType<>(FlowerBoxBlockEntity::new,
            BuiltInRegistries.BLOCK.get(FabulousFurniture.prefix("oak_log_flower_box")).get().value()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ClockBlockEntity>> CLOCK = BLOCK_ENTITY_TYPES.register("clock", () -> new BlockEntityType<>(ClockBlockEntity::new,
            BuiltInRegistries.BLOCK.get(FabulousFurniture.prefix("oak_log_clock")).get().value()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GrandfatherClockBlockEntity>> GRANDFATHER_CLOCK = BLOCK_ENTITY_TYPES.register("grandfather_clock", () -> new BlockEntityType<>(GrandfatherClockBlockEntity::new,
            BuiltInRegistries.BLOCK.get(FabulousFurniture.prefix("oak_log_grandfather_clock")).get().value()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DiscMountBlockEntity>> DISC_MOUNT = BLOCK_ENTITY_TYPES.register("disc_mount", () -> new BlockEntityType<>(DiscMountBlockEntity::new,
            BuiltInRegistries.BLOCK.get(FabulousFurniture.prefix("oak_log_disc_mount")).get().value()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MusicDiscShelfBlockEntity>> MUSIC_DISC_SHELF = BLOCK_ENTITY_TYPES.register("music_disc_shelf", () -> new BlockEntityType<>(MusicDiscShelfBlockEntity::new,
            BuiltInRegistries.BLOCK.get(FabulousFurniture.prefix("oak_log_music_disc_shelf")).get().value()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GlobeBlockEntity>> GLOBE = BLOCK_ENTITY_TYPES.register("globe", () -> new BlockEntityType<>(GlobeBlockEntity::new,
            BuiltInRegistries.BLOCK.get(FabulousFurniture.prefix("oak_globe")).get().value()));

}