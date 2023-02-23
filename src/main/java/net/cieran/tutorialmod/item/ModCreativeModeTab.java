package net.cieran.tutorialmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab TUTORIAL_TAB = new CreativeModeTab("tutorial_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.CITRINE.get());
        }
    };

    public static final CreativeModeTab EQUIPMENT_TAB = new CreativeModeTab("equipment_tab") {
        @Override
        public ItemStack makeIcon() { return new ItemStack(ModItems.CITRINE_SWORD.get()); }
    };
}
