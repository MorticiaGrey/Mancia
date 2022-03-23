package morticia.mancia.client;

import morticia.mancia.Keybindings.Keybindings;
import net.fabricmc.api.ClientModInitializer;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class ManciaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Keybindings.initKeyBindings();
    }
}
