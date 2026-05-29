package net.setrion.fabulous_furniture.events;

import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.setrion.fabulous_furniture.FabulousFurniture;
import net.setrion.fabulous_furniture.client.renderer.blockentity.FlowerBoxRenderer;
import net.setrion.fabulous_furniture.client.renderer.entity.SeatRenderer;
import net.setrion.fabulous_furniture.registry.SFFBlockEntityTypes;
import net.setrion.fabulous_furniture.registry.SFFBlocks;
import net.setrion.fabulous_furniture.registry.SFFEntityTypes;
import net.setrion.fabulous_furniture.world.level.block.BirdbathBlock;
import net.setrion.fabulous_furniture.world.level.block.KitchenCounterSinkBlock;

import java.util.List;

@EventBusSubscriber(modid = FabulousFurniture.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SFFEntityTypes.SEAT.get(), SeatRenderer::new);
        event.registerBlockEntityRenderer(SFFBlockEntityTypes.FLOWER_BOX.get(), FlowerBoxRenderer::new);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
        for (DeferredHolder<Block, ? extends Block> block : SFFBlocks.BLOCKS.getEntries()) {
            if (block.get() instanceof KitchenCounterSinkBlock || block.get() instanceof BirdbathBlock) {
                event.register(List.of(BlockTintSources.water()), block.get());
            }
            event.register(List.of(BlockTintSources.constant(10380959)), SFFBlocks.SPRUCE_HEDGE.get());
            event.register(List.of(BlockTintSources.constant(8345771)), SFFBlocks.BIRCH_HEDGE.get());
            event.register(List.of(BlockTintSources.foliage()), SFFBlocks.OAK_HEDGE.get(), SFFBlocks.JUNGLE_HEDGE.get(), SFFBlocks.ACACIA_HEDGE.get(), SFFBlocks.DARK_OAK_HEDGE.get(), SFFBlocks.MANGROVE_HEDGE.get());
        }
    }
}