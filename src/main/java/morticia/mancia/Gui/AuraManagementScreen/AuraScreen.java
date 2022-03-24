package morticia.mancia.Gui.AuraManagementScreen;

import com.mojang.blaze3d.systems.RenderSystem;
import morticia.mancia.Keybindings.Keybindings;
import morticia.mancia.Mancia;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

import static net.minecraft.client.gui.screen.ingame.InventoryScreen.drawEntity;

// These classes are going to be filled out more later, this is just setting up the infrastructure
// This class is heavily similar to AdvancementsScreen

@Environment(EnvType.CLIENT)
public class AuraScreen extends Screen {
    private static final Identifier BORDER_TEXTURE = new Identifier(Mancia.MOD_ID, "textures/gui/auramanagement/border.png");

    private final ClientAuraScreenManager auraHandler;

    // Rendering Settings
    public static final int WINDOW_WIDTH = 300;
    public static final int WINDOW_HEIGHT = 200;
    public static final int BORDER_WIDTH = 304;
    public static final int BORDER_HEIGHT = 204;
    public static final int OFFSET_X = 0;
    public static final int OFFSET_Y = 0;

    // Character entity rendering settings
    public static final int PLAYER_OFFSET_X = 31; // 51
    public static final int PLAYER_OFFSET_Y = 78;
    public static final int PLAYER_SIZE = 35;

    // Keep track of mouse position
    private int mouseX = 0;
    private int mouseY = 0;

    public AuraScreen(Text title, ClientAuraScreenManager auraHandler) {
        super(title);
        this.auraHandler = auraHandler;
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        //auraHandler.client.player.sendMessage(new LiteralText("Added"), false);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (Keybindings.openAuraGui.matchesKey(keyCode, scanCode)) {
            this.client.setScreen(null);
            this.client.mouse.lockCursor();
            return true;
        } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        int i = (this.width - WINDOW_WIDTH) / 2; // window width
        int j = (this.height - WINDOW_HEIGHT) / 2; // window height
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.renderBackground(matrices);
        this.drawWindow(matrices, i, j);
        this.drawBorder(matrices, i, j);
    }

    public void drawWindow(MatrixStack matrices, int x, int y) {
        // Draws solid background color, color will change later depeending on player choices
        fill(matrices, x + OFFSET_X, y + OFFSET_Y, x + OFFSET_X + WINDOW_WIDTH,
                y + OFFSET_Y + WINDOW_HEIGHT, ColorHelper.Argb.getArgb(255, 20, 0, 5)); //-16777216
        // Draws player entity, same as in inventory  31
        drawEntity(x + PLAYER_OFFSET_X, y + PLAYER_OFFSET_Y, PLAYER_SIZE, (float)(x + PLAYER_OFFSET_X) - this.mouseX,
                (float)(y + PLAYER_OFFSET_Y - 50) - this.mouseY, client.player);
    }

    public void drawBorder(MatrixStack matrices, int x, int y) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderTexture(0, BORDER_TEXTURE);
        //this.drawTexture(matrices, x, y, 0, 0, BORDER_WIDTH, BORDER_HEIGHT);
        drawTexture(matrices, x - 2, y - 2, 0, 0, 0, BORDER_WIDTH, BORDER_HEIGHT, BORDER_WIDTH, BORDER_HEIGHT);
    }

    // Writes the stats this gui is meant to display (alignment, level, effects, etc.)
    public void writeStats(MatrixStack matrices, int x, int y) {

    }
}
