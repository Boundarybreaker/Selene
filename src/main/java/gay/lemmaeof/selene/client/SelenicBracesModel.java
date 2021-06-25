package gay.lemmaeof.selene.client;

import gay.lemmaeof.selene.Selene;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class SelenicBracesModel extends SelenicTrinketModel {
	public static final Identifier TEXTURE = new Identifier(Selene.MODID, "textures/entity/trinket/braces.png");
	public static final Identifier TEXTURE_ILLUMINATED = new Identifier(Selene.MODID, "textures/entity/trinket/braces_illuminated.png");

	public SelenicBracesModel(ModelPart root) {
		super(root);
		this.setVisible(false);
		this.leftArm.visible = true;
		this.rightArm.visible = true;
	}

	public static SelenicBracesModel create() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(1F), 0f);
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(0, 0)
				.cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.1F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(0, 0).mirrored()
				.cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.1F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));
		return new SelenicBracesModel(TexturedModelData.of(modelData, 64, 32).createModel());
	}

	@Override
	Identifier getTexture(ItemStack stack) {
//		NbtCompound tag = stack.getOrCreateTag();
//		if (tag.getInt("Luna") >= 2000) return TEXTURE_ILLUMINATED;
		return TEXTURE;
	}
}
