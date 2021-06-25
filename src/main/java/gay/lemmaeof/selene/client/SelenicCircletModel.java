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

public class SelenicCircletModel extends SelenicTrinketModel {
	public static final Identifier TEXTURE = new Identifier(Selene.MODID, "textures/entity/trinket/circlet.png");
	public static final Identifier TEXTURE_ILLUMINATED = new Identifier(Selene.MODID, "textures/entity/trinket/circlet_illuminated.png");

	public SelenicCircletModel(ModelPart root) {
		super(root);
		this.setVisible(false);
		this.head.visible = true;
	}

	public static SelenicCircletModel create() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(1F), 0f);
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0)
				.cuboid(-4f, -10f, -4f, 8f, 8f, 8f, new Dilation(0.1F)), ModelTransform.NONE);
		return new SelenicCircletModel(TexturedModelData.of(modelData, 64, 32).createModel());
	}

	@Override
	Identifier getTexture(ItemStack stack) {
//		NbtCompound tag = stack.getOrCreateTag();
//		if (tag.getInt("Luna") >= 2000) return TEXTURE_ILLUMINATED;
		return TEXTURE;
	}
}
