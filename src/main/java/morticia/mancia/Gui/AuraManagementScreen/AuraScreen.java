package morticia.mancia.Gui.AuraManagementScreen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

// These classes are going to be filled out more later, this is just setting up the infrastructure
// This class is heavily similar to AdvancementsScreen

@Environment(EnvType.CLIENT)
public class AuraScreen extends Screen {
    private static final Identifier BACKGROUND_TEXTURE = new Identifier("");

    private final ClientAuraScreenManager auraHandler;

    // Rendering Settings
    public static final int WINDOW_WIDTH = 300;
    public static final int WINDOW_HEIGHT = 200;
    public static final int OFFSET_X = 0;
    public static final int OFFSET_Y = 0;

    public AuraScreen(Text title, ClientAuraScreenManager auraHandler) {
        super(title);
        this.auraHandler = auraHandler;
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        //auraHandler.client.player.sendMessage(new LiteralText("Added"), false);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        int i = (this.width - WINDOW_WIDTH) / 2; // window width
        int j = (this.height - WINDOW_HEIGHT) / 2; // window height
        this.renderBackground(matrices);
        this.drawWindow(matrices, i, j);
    }

    public void drawWindow(MatrixStack matrices, int x, int y) {
        // Draws window, obviously
        fill(matrices, x + OFFSET_X, y + OFFSET_Y, x + OFFSET_X + WINDOW_WIDTH,
                y + OFFSET_Y + WINDOW_HEIGHT, -16777216);
    }
}
