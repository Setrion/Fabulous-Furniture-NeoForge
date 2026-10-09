package net.setrion.fabulous_furniture.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.setrion.fabulous_furniture.FabulousFurniture;
import net.setrion.fabulous_furniture.registry.SFFBlockEntityTypes;

import javax.annotation.Nullable;

public class MusicDiscShelfBlockEntity extends BlockEntity implements Clearable, Container {

    private final NonNullList<ItemStack> items;

    public MusicDiscShelfBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(SFFBlockEntityTypes.MUSIC_DISC_SHELF.get(), worldPosition, blockState);
        this.items = NonNullList.withSize(5, ItemStack.EMPTY);
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.items.clear();
        ContainerHelper.loadAllItems(input, this.items);
    }

    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.items, true);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        try (ProblemReporter.ScopedCollector reporter = new ProblemReporter.ScopedCollector(this.problemPath(), FabulousFurniture.LOGGER)) {
            TagValueOutput output = TagValueOutput.createWithContext(reporter, registries);
            ContainerHelper.saveAllItems(output, this.items, true);
            return output.buildResult();
        }
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public boolean placeDisk(ServerLevel level, @Nullable LivingEntity entity, int slot, ItemStack stack) {
        if (items.size() >= slot+1) {
            ItemStack itemstack = items.get(slot);
            if (itemstack.isEmpty()) {
                items.set(slot, stack.consumeAndReturn(1, entity));
                level.gameEvent(GameEvent.BLOCK_CHANGE, getBlockPos(), GameEvent.Context.of(entity, getBlockState()));
                markUpdated();
                return true;
            }
        }
        return false;
    }

    public ItemStack takeDisk(ServerLevel level, int slot, @Nullable LivingEntity entity) {
        if(items.size() >= slot+1) {
            ItemStack itemstack = items.get(slot);
            if (!itemstack.isEmpty()) {
                items.set(slot, ItemStack.EMPTY);
                level.gameEvent(GameEvent.BLOCK_CHANGE, getBlockPos(), GameEvent.Context.of(entity, getBlockState()));
                markUpdated();
                return itemstack;
            }
        }

        return ItemStack.EMPTY;
    }

    public void clearContent() {
        this.items.clear();
    }

    protected void applyImplicitComponents(DataComponentGetter components) {
        super.applyImplicitComponents(components);
        components.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(this.getItems());
    }

    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.getItems()));
    }

    public void removeComponentsFromTag(ValueOutput output) {
        output.discard("Items");
    }

    @Override
    public int getContainerSize() {
        return 5;
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public ItemStack getItem(int i) {
        return items.get(i);
    }

    public ItemStack removeItem(int slot, int count) {
        ItemStack result = ContainerHelper.removeItem(this.getItems(), slot, count);
        if (!result.isEmpty()) {
            this.setChanged();
        }

        return result;
    }

    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.getItems(), slot);
    }

    public void setItem(int slot, ItemStack itemStack) {
        this.setItem(slot, itemStack, false);
    }

    public void setItem(int slot, ItemStack itemStack, boolean insideTransaction) {
        this.getItems().set(slot, itemStack);
        itemStack.limitSize(this.getMaxStackSize(itemStack));
        if (!insideTransaction) {
            this.setChanged();
        }

    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }
}