package gay.lemmaeof.selene.client;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public abstract class SelenicTrinketModel extends BipedEntityModel<LivingEntity> implements TrinketRenderer {
	public SelenicTrinketModel(ModelPart root) {
		super(root);
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		this.setAngles(entity, limbAngle, limbDistance, animationProgress, animationProgress, headPitch);
		this.animateModel(entity, limbAngle, limbDistance, tickDelta);
		TrinketRenderer.followBodyRotations(entity, this);
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.getLayer(getTexture(stack)));
		this.render(matrices, vertexConsumer, getLight(stack, entity, light), OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
	}

	abstract Identifier getTexture(ItemStack stack);

	private static int getLight(ItemStack stack, LivingEntity entity, int baseLight) {
		World world = entity.world;
		if (world.isSkyVisible(entity.getBlockPos())) {
			if (world.isDay()) return baseLight;
			if (world.getMoonPhase() == 0) return LightmapTextureManager.field_32767;
		}
		int currentLuna = Math.min(15, (stack.getOrCreateNbt().getInt("Luna") / 1000) + 1);
		return LightmapTextureManager.pack(Math.max(currentLuna, LightmapTextureManager.getBlockLightCoordinates(baseLight)),
				Math.max(currentLuna, LightmapTextureManager.getSkyLightCoordinates(baseLight)));
	}
}
