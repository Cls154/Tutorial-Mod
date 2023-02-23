package net.cieran.tutorialmod.item.custom;

import net.cieran.tutorialmod.item.ModItems;
import net.cieran.tutorialmod.particle.ModParticles;
import net.cieran.tutorialmod.sound.ModSounds;
import net.cieran.tutorialmod.util.InventoryUtil;
import net.cieran.tutorialmod.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DowsingRodItem extends Item {

    public DowsingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()) {
            Level level = pContext.getLevel();
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i<=positionClicked.getY() + 64; i++) {
                BlockPos positionBelow = positionClicked.below(i);
                Block blockBelow = level.getBlockState(positionBelow).getBlock();

                if (isValuableBlock(positionBelow, level)) {
                    outputValuableCoords(positionBelow, player, blockBelow);
                    foundBlock = true;

                    if (InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET.get())) {
                        addNbtToDataTablet(player, positionBelow, blockBelow);
                    }

                    spawnFoundParticles(pContext, positionClicked);

                    pContext.getLevel().playSound(player, positionClicked, ModSounds.DOWSING_ROD_FOUND_ORE.get(), SoundSource.BLOCKS, 1.0f, 1.0f);

                    break;
                }
            }

            if (!foundBlock) {
                player.sendMessage(new TranslatableComponent("item.tutorialmod.dowsing_rod.no_valuables"), player.getUUID());
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    private void spawnFoundParticles(UseOnContext pContext, BlockPos positionClicked) {
        for (int i=0; i<360; i++) {
            if (i%20 == 0) {
                pContext.getLevel().addParticle(ModParticles.CITRINE_PARTICLES.get(),
                        positionClicked.getX() + 0.5D, positionClicked.getY() + 1, positionClicked.getZ() + 0.5D,
                        Math.cos(i) * 0.15D, 0.15D, Math.sin(i) * 0.15D);
            }
        }
    }


    private void addNbtToDataTablet(Player player, BlockPos pos, Block blockBelow) {
        ItemStack dataTablet = player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET.get()));
        CompoundTag nbtData = new CompoundTag();
        nbtData.putString("tutorialmod.last_ore", "Found " + blockBelow.asItem().getRegistryName().toString() + " at " +
                "(" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")");
        dataTablet.setTag(nbtData);
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tutorialmod.dowsing_rod.tooltip.shift"));
        } else {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tutorialmod.dowsing_rod.tooltip"));

        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void outputValuableCoords(BlockPos blockPos, Player player, Block blockBelow) {
        player.sendMessage(new TextComponent("Found " + blockBelow.asItem().getRegistryName().toString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), player.getUUID());
    }

    private boolean isValuableBlock(BlockPos blockPos, Level level) {
        return level.getBlockState(blockPos).is(ModTags.Blocks.DOWSING_ROD_VALUABLES);
    }
}
