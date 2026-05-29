package net.setrion.fabulous_furniture.world.item.crafting;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.Utf8String;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.setrion.fabulous_furniture.registry.SFFRecipeTypes;
import net.setrion.fabulous_furniture.world.level.block.state.properties.FurnitureCategory;
import net.setrion.fabulous_furniture.world.level.block.state.properties.MaterialType;

import javax.annotation.Nullable;
import java.util.*;

public class CarpentryTableRecipe implements Recipe<SingleRecipeInput> {

    static StreamCodec<FriendlyByteBuf, FurnitureCategory> FURNITURE_CATEGORY_STREAM_CODEC = StreamCodec.of((buf, type) -> Utf8String.write(buf, type.name(), 128), buf -> {
        String name = Utf8String.read(buf, 128);
        FurnitureCategory w = FurnitureCategory.CRATES;
        for (FurnitureCategory wType : FurnitureCategory.values().toList()) {
            if (wType.name().equals(name)) {
                w = wType;
            }
        }
        return w;
    });

    static StreamCodec<FriendlyByteBuf, MaterialType> MATERIAL_TYPE_STREAM_CODEC = StreamCodec.of((buf, type) -> Utf8String.write(buf, type.name(), 128), buf -> {
        String name = Utf8String.read(buf, 128);
        MaterialType w = MaterialType.OAK;
        for (MaterialType wType : MaterialType.values().toList()) {
            if (wType.name().equals(name)) {
                w = wType;
            }
        }
        return w;
    });

    public static final MapCodec<CarpentryTableRecipe> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(StackedIngredient.CODEC.listOf().fieldOf("ingredients").flatXmap(materials -> {
        NonNullList<StackedIngredient> inputs = NonNullList.create();
        inputs.addAll(materials);
        return DataResult.success(inputs);
    }, DataResult::success).forGetter(o -> o.ingredients), ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result), FurnitureCategory.CODEC.fieldOf("category").forGetter(recipe -> recipe.category), MaterialType.CODEC.listOf().fieldOf("materials").flatXmap(materials -> {
        NonNullList<MaterialType> inputs = NonNullList.create();
        inputs.addAll(materials);
        return DataResult.success(inputs);
    }, DataResult::success).forGetter(o -> o.materialTypes)).apply(builder, CarpentryTableRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CarpentryTableRecipe> STREAM_CODEC = StreamCodec.composite(
            StackedIngredient.STREAM_CODEC.apply(ByteBufCodecs.collection(NonNullList::createWithCapacity)),
            CarpentryTableRecipe::getMaterials,
            ItemStackTemplate.STREAM_CODEC,
            CarpentryTableRecipe::result,
            CarpentryTableRecipe.FURNITURE_CATEGORY_STREAM_CODEC,
            CarpentryTableRecipe::getCategory,
            CarpentryTableRecipe.MATERIAL_TYPE_STREAM_CODEC.apply(ByteBufCodecs.collection(NonNullList::createWithCapacity)),
            CarpentryTableRecipe::getMaterialTypes,
            CarpentryTableRecipe::new
    );

    public static final RecipeSerializer<CarpentryTableRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);

    private final NonNullList<StackedIngredient> ingredients;
    private final ItemStackTemplate result;
    private final FurnitureCategory category;
    private final NonNullList<MaterialType> materialTypes;

    public CarpentryTableRecipe(NonNullList<StackedIngredient> ingredients, ItemStackTemplate result, FurnitureCategory category, NonNullList<MaterialType> materials) {
        this.ingredients = ingredients;
        this.result = result;
        this.category = category;
        this.materialTypes = materials;
    }

    @Override
    public boolean matches(SingleRecipeInput singleRecipeInput, Level level) {
        return true;
    }

    @Override
    public ItemStack assemble(SingleRecipeInput singleRecipeInput) {
        return null;
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public RecipeSerializer<CarpentryTableRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return SFFRecipeTypes.CARPENTRY_TABLE_RECIPE.get();
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(
                new ShapelessCraftingRecipeDisplay(
                        this.ingredients.stream().map(stackedIngredient -> stackedIngredient.ingredient().display()).toList(),
                        new SlotDisplay.ItemStackSlotDisplay(this.result),
                        new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)
                )
        );
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.SMITHING;
    }

    public FurnitureCategory getCategory() {
        return category;
    }

    public NonNullList<MaterialType> getMaterialTypes() {
        return materialTypes;
    }

    public NonNullList<StackedIngredient> getMaterials() {
        return ingredients;
    }

    public int getResultId() {
        return Item.getId(result.item().value());
    }

    protected ItemStackTemplate result() {
        return this.result;
    }

    public ItemStack getResult() {
        return this.result.create();
    }

    public static Builder builder(ItemLike result, int count, FurnitureCategory category) {
        return new Builder(new ItemStackTemplate(result.asItem(), count), category);
    }

    public static class Builder implements RecipeBuilder {
        private final ItemStackTemplate result;
        private final int count;
        private final FurnitureCategory furnitureCategory;
        private final NonNullList<MaterialType> materials = NonNullList.create();
        private final NonNullList<StackedIngredient> ingredients = NonNullList.create();
        private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
        private RecipeCategory category = RecipeCategory.MISC;

        private Builder(ItemStackTemplate result, FurnitureCategory furnitureCategory) {
            this.result = result;
            this.count = result.count();
            this.furnitureCategory = furnitureCategory;
        }

        @Override
        public Builder unlockedBy(String name, Criterion<?> trigger) {
            criteria.put(name, trigger);
            return this;
        }

        @Override
        public Builder group(@Nullable String group) {
            return this;
        }

        @Override
        public ResourceKey<Recipe<?>> defaultId() {
            return null;
        }

        public void requiresMaterial(ItemStackTemplate stack) {
            ingredients.add(new StackedIngredient(Ingredient.of(stack.item().value()), stack.count()));
        }

        public void containsMaterial(MaterialType type) {
            materials.add(type);
        }

        public Builder category(RecipeCategory category) {
            this.category = category;
            return this;
        }

        @Override
        public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
            validate(id);
            Advancement.Builder builder = output.advancement()
                    .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                    .rewards(AdvancementRewards.Builder.recipe(id))
                    .requirements(AdvancementRequirements.Strategy.OR);
            criteria.forEach(builder::addCriterion);
            output.accept(id, new CarpentryTableRecipe(ingredients, result, furnitureCategory, materials), builder.build(id.identifier().withPrefix("recipes/" + category.getFolderName() + "/")));
        }

        private void validate(ResourceKey<Recipe<?>> id) {
            if(ingredients.isEmpty()) {
                throw new IllegalArgumentException("There must be at least one material for carpentry table recipe %s".formatted(id.identifier()));
            }
            if(criteria.isEmpty()) {
                throw new IllegalStateException("No way of obtaining recipe " + id.identifier());
            }
        }
    }
}
