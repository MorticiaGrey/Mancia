package morticia.mancia.Registry;

import morticia.mancia.Items.DebugWand;
import morticia.mancia.Items.RitualKnife;
import morticia.mancia.Mancia;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    // Debug item to make checking things in the world easier
    // TODO: 3/25/22 Remove before releasing
    public static final DebugWand DEBUG_WAND = new DebugWand(new FabricItemSettings().group(ModGroups.MANCIA_ITEM_GROUP));

    public static final RitualKnife RITUAL_KNIFE = new RitualKnife(new FabricItemSettings().group(ModGroups.MANCIA_ITEM_GROUP));

    public static final Item NECROTIC_INGOT = new Item(new FabricItemSettings().group(ModGroups.MANCIA_ITEM_GROUP));
    public static final Item LIVING_INGOT = new Item(new FabricItemSettings().group(ModGroups.MANCIA_ITEM_GROUP));

    public static final Block NECROTIC_STONE = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F));
    public static final Block NECROTIC_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F));
    public static final Block LIVING_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F));
    public static final Block LIVING_STONE = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F));

    public static void initItems() {
        Registry.register(Registry.ITEM, new Identifier(Mancia.MOD_ID, "debug_wand"), DEBUG_WAND);

        Registry.register(Registry.ITEM, new Identifier(Mancia.MOD_ID, "ritual_knife"), RITUAL_KNIFE);

        Registry.register(Registry.ITEM, new Identifier(Mancia.MOD_ID, "necrotic_ingot"), NECROTIC_INGOT);
        Registry.register(Registry.ITEM, new Identifier(Mancia.MOD_ID, "living_ingot"), LIVING_INGOT);

        Registry.register(Registry.BLOCK, new Identifier(Mancia.MOD_ID, "necrotic_stone"), NECROTIC_STONE);
        Registry.register(Registry.ITEM, new Identifier(Mancia.MOD_ID, "necrotic_stone"), new BlockItem(NECROTIC_STONE, new FabricItemSettings().group(ModGroups.MANCIA_ITEM_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(Mancia.MOD_ID, "necrotic_ore"), NECROTIC_ORE);
        Registry.register(Registry.ITEM, new Identifier(Mancia.MOD_ID, "necrotic_ore"), new BlockItem(NECROTIC_ORE, new FabricItemSettings().group(ModGroups.MANCIA_ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(Mancia.MOD_ID, "living_stone"), LIVING_STONE);
        Registry.register(Registry.ITEM, new Identifier(Mancia.MOD_ID, "living_stone"), new BlockItem(LIVING_STONE, new FabricItemSettings().group(ModGroups.MANCIA_ITEM_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(Mancia.MOD_ID, "living_ore"), LIVING_ORE);
        Registry.register(Registry.ITEM, new Identifier(Mancia.MOD_ID, "living_ore"), new BlockItem(LIVING_ORE, new FabricItemSettings().group(ModGroups.MANCIA_ITEM_GROUP)));
    }
}
