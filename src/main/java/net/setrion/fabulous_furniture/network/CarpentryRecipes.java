package net.setrion.fabulous_furniture.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.setrion.fabulous_furniture.FabulousFurniture;
import net.setrion.fabulous_furniture.client.gui.screens.inventory.CarpentryTableScreen;
import net.setrion.fabulous_furniture.world.item.crafting.CarpentryTableRecipe;

import java.util.List;

public record CarpentryRecipes(List<RecipeHolder<CarpentryTableRecipe>> recipes) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<CarpentryRecipes> TYPE = new CustomPacketPayload.Type<>(FabulousFurniture.prefix("carpentry_recipes"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CarpentryRecipes> STREAM_CODEC = StreamCodec.of(
            (buf, message) -> {
                if (message.recipes != null) {
                    StreamCodec<RegistryFriendlyByteBuf, List<RecipeHolder<?>>> listCodec =
                            RecipeHolder.STREAM_CODEC.apply(ByteBufCodecs.collection(java.util.ArrayList::new));

                    listCodec.encode(buf, (List<RecipeHolder<?>>) (Object) message.recipes);
                } else {
                    buf.writeVarInt(0);
                }
            },
    buf -> {
            StreamCodec<RegistryFriendlyByteBuf, List<RecipeHolder<?>>> listCodec =
                RecipeHolder.STREAM_CODEC.apply(ByteBufCodecs.collection(java.util.ArrayList::new));

            List<RecipeHolder<?>> decodedList = listCodec.decode(buf);

            return new CarpentryRecipes((List<RecipeHolder<CarpentryTableRecipe>>) (Object) decodedList);
        }
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class ClientPayloadHandler {

        public static void handleDataOnMain(final CarpentryRecipes data, final IPayloadContext context) {
            CarpentryTableScreen.setRecipes(data.recipes);
        }
    }
}