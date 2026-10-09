package net.setrion.fabulous_furniture;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.setrion.fabulous_furniture.client.gui.screens.inventory.CarpentryTableScreen;
import net.setrion.fabulous_furniture.client.model.object.clock.ClockHandsModel;
import net.setrion.fabulous_furniture.client.model.object.clock.GrandfatherClockModel;
import net.setrion.fabulous_furniture.client.model.object.globe.GlobeModel;
import net.setrion.fabulous_furniture.client.renderer.blockentity.*;
import net.setrion.fabulous_furniture.config.FurnitureConfig;
import net.setrion.fabulous_furniture.events.RegistryEvents;
import net.setrion.fabulous_furniture.registry.SFFBlockEntityTypes;
import net.setrion.fabulous_furniture.registry.SFFBlocks;
import net.setrion.fabulous_furniture.registry.SFFMenuTypes;
import net.setrion.fabulous_furniture.world.level.block.*;
import org.slf4j.Logger;

@Mod(FabulousFurniture.MOD_ID)
public class FabulousFurniture {
    public static final String MOD_ID = "fabulous_furniture";

    public static final Logger LOGGER = LogUtils.getLogger();

    public FabulousFurniture(ModContainer container) {
        IEventBus modEventBus = container.getEventBus();

        RegistryEvents.registerEverything(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientScreenSetup);
        modEventBus.addListener(this::addBlockEntityTypes);
        modEventBus.addListener(this::addBlockEntityRenderers);
        modEventBus.addListener(this::registerLayerDefinitions);

        container.registerConfig(ModConfig.Type.LOCAL, FurnitureConfig.SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    public void clientScreenSetup(RegisterMenuScreensEvent event) {
        event.register(SFFMenuTypes.CARPENTRY_TABLE.get(), CarpentryTableScreen::new);
    }

    public void addBlockEntityTypes(BlockEntityTypeAddBlocksEvent event) {
        for (DeferredHolder<Block, ? extends Block> block : SFFBlocks.BLOCKS.getEntries()) {
            if (block.get() instanceof BlockEntitySupplier supplier) {
                event.modify(supplier.getBlockEntityType(), block.get());
            }
        }
    }

    public void addBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(SFFBlockEntityTypes.CLOCK.get(), ClockRenderer::new);
        event.registerBlockEntityRenderer(SFFBlockEntityTypes.GRANDFATHER_CLOCK.get(), GrandfatherClockRenderer::new);
        event.registerBlockEntityRenderer(SFFBlockEntityTypes.DISC_MOUNT.get(), DiscMountRenderer::new);
        event.registerBlockEntityRenderer(SFFBlockEntityTypes.MUSIC_DISC_SHELF.get(), MusicDiscShelfRenderer::new);
        event.registerBlockEntityRenderer(SFFBlockEntityTypes.GLOBE.get(), GlobeRenderer::new);
    }

    public void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ClockHandsModel.SMALL_LAYER_LOCATION, () -> ClockHandsModel.createLayer(-3.5F, 0F, 2.5F, 1.75F));
        event.registerLayerDefinition(ClockHandsModel.WALL_LAYER_LOCATION, () -> ClockHandsModel.createLayer(6.5F, 4F, 3.5F, 2.75F));
        event.registerLayerDefinition(GrandfatherClockModel.LOCATION, () -> GrandfatherClockModel.createLayer(-0.25F, 3.0F));
        event.registerLayerDefinition(GrandfatherClockModel.MODERN_LOCATION, () -> GrandfatherClockModel.createModernLayer(-4.25F, 6.0F));
        event.registerLayerDefinition(GlobeModel.GLOBE_LOCATION, GlobeModel::createLayer);
    }

    public static Identifier prefix(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }
}
