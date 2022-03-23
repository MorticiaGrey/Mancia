package morticia.mancia.Keybindings;

import morticia.mancia.Gui.AuraManagementScreen.AuraScreen;
import morticia.mancia.Gui.AuraManagementScreen.ClientAuraScreenManager;
import morticia.mancia.Mancia;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

public class Keybindings {
    public static String keyBindingCategory = "category." + Mancia.MOD_ID + ".keybindings";

    private static KeyBinding openAuraGui;

    public static void initKeyBindings() {
        openAuraGui = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.mancia.open_aura_gui_keybinding",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R, // TODO: 3/22/22 Change this keybind to not step on any toes
                keyBindingCategory
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openAuraGui.wasPressed()) {
                // Actions to execute when keybind pressed
                if (client.player != null) {
                    //client.player.sendMessage(new LiteralText("Pressed"), false);
                    // TODO: 3/22/22 Replace with aura management screen 
                    client.setScreen(new AuraScreen(new LiteralText("Test"), new ClientAuraScreenManager(client))); // Use translatable text
                }
            }
        });
    }
}
