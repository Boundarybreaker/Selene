package gay.lemmaeof.selene.mixin;

import gay.lemmaeof.selene.Selene;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

@Mixin(ItemEntity.class)
public abstract class MixinItemEntity extends Entity {
	public MixinItemEntity(EntityType<?> type, World world) {
		super(type, world);
	}

	@Shadow public abstract ItemStack getStack();

	@Shadow public abstract void setStack(ItemStack stack);

	@Inject(method = "tick", at = @At("HEAD"))
	private void injectSelenicTicking(CallbackInfo info) {
		if (this.getStack().getItem() == Items.AMETHYST_SHARD && this.world != null && !this.world.isClient) {
			if (this.isTouchingWater() && !this.world.isDay() && this.world.getMoonPhase() == 0) {
				this.setStack(new ItemStack(Selene.SELENIC_AMETHYST, getStack().getCount()));
			}
		}
	}
}
