package net.azdo0m.betterbalance.mixin.logic;

import net.azdo0m.betterbalance.item.ModItems;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.BeaconScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.BeaconMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeaconScreen.class)
public abstract class BeaconScreenMixin extends AbstractContainerScreen<BeaconMenu> {

    public BeaconScreenMixin(BeaconMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Shadow @Final private static Identifier BEACON_LOCATION;

    @Inject(method = "renderBg", at = @At("HEAD"), cancellable = true)
    private void betterbalance$renderCustomBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY, CallbackInfo ci) {

        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BEACON_LOCATION, k, l, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);

        guiGraphics.renderItem(new ItemStack(ModItems.OCEANITE_INGOT), k + 17, l + 103);
        guiGraphics.renderItem(new ItemStack(Items.NETHERITE_INGOT), k + 17, l + 115);
        guiGraphics.renderItem(new ItemStack(Items.EMERALD), k + 41, l + 109);
        guiGraphics.renderItem(new ItemStack(Items.DIAMOND), k + 41 + 22, l + 109);
        guiGraphics.renderItem(new ItemStack(Items.GOLD_INGOT), k + 42 + 44, l + 109);
        guiGraphics.renderItem(new ItemStack(Items.IRON_INGOT), k + 42 + 66, l + 109);

        ci.cancel();
    }
}