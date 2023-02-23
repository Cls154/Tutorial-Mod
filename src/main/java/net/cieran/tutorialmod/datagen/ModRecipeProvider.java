package net.cieran.tutorialmod.datagen;

import net.cieran.tutorialmod.block.ModBlocks;
import net.cieran.tutorialmod.datagen.custom.GemCuttingRecipeBuilder;
import net.cieran.tutorialmod.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }



    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.EBONY_DOOR.get())
                .define('E', ModBlocks.EBONY_PLANKS.get())
                .pattern("EE")
                .pattern("EE")
                .pattern("EE")
                .unlockedBy("has_ebony_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.EBONY_PLANKS.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(ModItems.CITRINE.get(), 9)
                .requires(ModBlocks.CITRINE_BLOCK.get())
                .unlockedBy("has_citrine_block", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.CITRINE_BLOCK.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.CITRINE_BLOCK.get())
                .define('E', ModItems.CITRINE.get())
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .unlockedBy("has_citrine", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RAW_CITRINE.get()).build()))
                .save(pFinishedRecipeConsumer);

        new GemCuttingRecipeBuilder(ModItems.RAW_CITRINE.get(), ModItems.CITRINE.get(), 1)
                .unlockedBy("has_citrine_ore", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RAW_CITRINE.get()).build()))
                .save(pFinishedRecipeConsumer);

        new GemCuttingRecipeBuilder(ModBlocks.CITRINE_ORE.get(), ModItems.RAW_CITRINE.get(), 1)
                .unlockedBy("has_citrine_ore", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.CITRINE_ORE.get()).build()))
                .save(pFinishedRecipeConsumer);
    }
}
