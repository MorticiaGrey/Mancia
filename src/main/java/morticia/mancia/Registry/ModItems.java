package morticia.mancia.Registry;

import morticia.mancia.Items.DebugWand;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    // Debug item to make checking things in the world easier
    // TODO: 3/25/22 Remove before releasing
    public static final DebugWand DEBUG_WAND = new DebugWand(new FabricItemSettings().group(ModGroups.MANCIA_ITEM_GROUP));
    
    public static void initItems() {
        Registry.register(Registry.ITEM, new Identifier("mancia", "debug_wand"), DEBUG_WAND);
    }
}
