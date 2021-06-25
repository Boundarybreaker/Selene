package gay.lemmaeof.selene;

import java.util.List;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class SelenicTrinketItem extends TrinketItem {

	public SelenicTrinketItem(Settings settings) {
		super(settings);
	}

	@Override
	public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
		NbtCompound tag = stack.getOrCreateTag();
		World world = entity.world;
		int luna = tag.getInt("Luna");
		if (world.isSkyVisible(entity.getBlockPos()) && (world.isDay() || world.getMoonPhase() == 0)) {
			if (luna < 15000) luna = Math.min(15000, luna + 5);
		} else {
			if (luna > 0) luna--;
		}
		tag.putInt("Luna", luna);
		TrinketComponent component = TrinketsApi.TRINKET_COMPONENT.get(entity);
		if (component.isEquipped(Selene.SELENIC_CIRCLET)
				&& component.isEquipped(Selene.SELENIC_PENDANT)
				&&component.isEquipped(Selene.SELENIC_BRACES)) {
			entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 300));
		}
	}

	@Override
	public int getItemBarStep(ItemStack stack) {
		return Math.round(13.0F - (float)stack.getOrCreateTag().getInt("Luna") * 13.0F / 15000F);
	}

	@Override
	public boolean isItemBarVisible(ItemStack stack) {
		return stack.getOrCreateTag().getInt("Luna") != 15000;
	}

	@Override
	public int getItemBarColor(ItemStack stack) {
		return 0x90A1E9;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);
		NbtCompound tag = stack.getOrCreateTag();
		tooltip.add(new TranslatableText("tooltip.selene.luna", tag.getInt("Luna")).formatted(Formatting.GRAY));
		tooltip.add(new TranslatableText("tooltip.selene.set.header",
				new TranslatableText("tooltip.selene.set.moonblessing").formatted(Formatting.BLUE))
				.formatted(Formatting.GRAY));
		if (world != null && world.isClient) {
			if (Screen.hasShiftDown()) {
				PlayerEntity player = MinecraftClient.getInstance().player;
				TrinketComponent component = TrinketsApi.TRINKET_COMPONENT.get(player);
				boolean hasCirclet = component.isEquipped(Selene.SELENIC_CIRCLET);
				boolean hasPendant = component.isEquipped(Selene.SELENIC_PENDANT);
				boolean hasBraces = component.isEquipped(Selene.SELENIC_BRACES);
				tooltip.add(new LiteralText("  - ").append(new TranslatableText("item.selene.selenic_circlet")).formatted(hasCirclet? Formatting.GREEN : Formatting.GRAY));
				tooltip.add(new LiteralText("  - ").append(new TranslatableText("item.selene.selenic_pendant")).formatted(hasPendant? Formatting.GREEN : Formatting.GRAY));
				tooltip.add(new LiteralText("  - ").append(new TranslatableText("item.selene.selenic_braces")).formatted(hasBraces? Formatting.GREEN : Formatting.GRAY));
			} else {
				tooltip.add(new LiteralText("  ").append(new TranslatableText("tooltip.selene.set.more")).formatted(Formatting.GRAY));
			}
		}
		tooltip.add(new TranslatableText("tooltip.selene.set.effect", new TranslatableText("effect.minecraft.night_vision").formatted(Formatting.BLUE)));
	}

}
