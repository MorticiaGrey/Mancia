package morticia.mancia;

import morticia.mancia.Registry.ModItems;
import net.fabricmc.api.ModInitializer;

public class Mancia implements ModInitializer {
    public static String MOD_ID = "mancia";

    @Override
    public void onInitialize() {
        ModItems.initItems();
    }
}
