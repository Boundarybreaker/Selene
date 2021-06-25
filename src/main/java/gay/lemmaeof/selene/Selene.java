package gay.lemmaeof.selene;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

public class Selene implements ModInitializer {
	public static final String MODID = "selene";

	public static Item SELENIC_AMETHYST;
	public static SelenicTrinketItem SELENIC_CIRCLET;
	public static SelenicTrinketItem SELENIC_PENDANT;
	public static SelenicTrinketItem SELENIC_BRACES;

	public static final ItemGroup SELENE_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, MODID), () -> new ItemStack(SELENIC_AMETHYST));

	@Override
	public void onInitialize() {
		SELENIC_AMETHYST = register("selenic_amethyst", new Item(new Item.Settings().group(SELENE_GROUP)));
		SELENIC_CIRCLET = register("selenic_circlet", new SelenicTrinketItem(new Item.Settings().group(SELENE_GROUP)));
		SELENIC_PENDANT = register("selenic_pendant", new SelenicTrinketItem(new Item.Settings().group(SELENE_GROUP)));
		SELENIC_BRACES = register("selenic_braces", new SelenicTrinketItem(new Item.Settings().group(SELENE_GROUP)));
	}

	private static <T extends Item> T register(String name, T item) {
		return Registry.register(Registry.ITEM, new Identifier(MODID, name), item);
	}
}
