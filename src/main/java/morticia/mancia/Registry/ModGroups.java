package morticia.mancia.Registry;

import morticia.mancia.Mancia;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModGroups {
    public static final ItemGroup MANCIA_ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(Mancia.MOD_ID, "item_group"),
            () -> new ItemStack(Items.ENDER_PEARL)
    );
}
