package morticia.mancia.Gui.AuraManagementScreen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class ClientAuraScreenManager {
    protected final MinecraftClient client; // TODO: 3/22/22 make private 

    public ClientAuraScreenManager(MinecraftClient client) {
        this.client = client;
    }
}
