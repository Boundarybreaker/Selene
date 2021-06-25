package gay.lemmaeof.selene.client;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import gay.lemmaeof.selene.Selene;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/*
  Color notes:
  - default amethyst texture
  - #BAF7F5 color overlay, 50%
  - #5BE9C5 saturation overlay, 50%
 */
@Environment(EnvType.CLIENT)
public class SeleneClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		TrinketRendererRegistry.registerRenderer(Selene.SELENIC_CIRCLET, SelenicCircletModel.create());
		TrinketRendererRegistry.registerRenderer(Selene.SELENIC_PENDANT, SelenicPendantModel.create());
		TrinketRendererRegistry.registerRenderer(Selene.SELENIC_BRACES, SelenicBracesModel.create());
	}
}
