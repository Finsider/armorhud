package com.chailotl.better_hud.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud
{
	@Final
	@Shadow
	private MinecraftClient client;

	@Inject(
		method = "renderHotbar",
		at = @At(value = "TAIL")
	)
	private void renderArmorDurability(float tickDelta, DrawContext context, CallbackInfo ci) {
		assert client.player != null;

        int x = client.getWindow().getScaledWidth() / 2 - 7;
	int y = client.getWindow().getScaledHeight() - 39;

	for (ItemStack armor : client.player.getArmorItems()) {
		if (armor.isDamageable()) {
			int i = armor.getItemBarStep();
			int j = armor.getItemBarColor();

			context.fill(x, y, x + 13, y + 2, 0xFF000000);
			context.fill(x, y, x + i, y + 1, j | 0xFF000000);
			y -= 3;
			}
		}
	}
}
