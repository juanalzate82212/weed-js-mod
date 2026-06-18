package jjag.weedjsmod.client;

import jjag.weedjsmod.WeedJSMod;
import jjag.weedjsmod.screen.CannabisCraftingScreenHandler;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CannabisCraftingScreen extends HandledScreen<CannabisCraftingScreenHandler> {

    private static final Identifier TEXTURE = Identifier.of(
            WeedJSMod.MOD_ID, "textures/gui/cannabis_crafting_table.png"
    );

    public CannabisCraftingScreen(CannabisCraftingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
        this.titleY = 6;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(
                RenderPipelines.GUI_TEXTURED,
                TEXTURE,
                this.x, this.y,
                0.0f, 0.0f,
                this.backgroundWidth,
                this.backgroundHeight,
                256, 256
        );
        drawProgressBar(context);
    }

    private void drawProgressBar(DrawContext context) {
        float progress = this.handler.getProgress();
        if (progress <= 0f) return;

        int barWidth = 22; //ajustar según textura;
        int barHeight = 2;
        int filled = (int) (barWidth * progress);

        int screenX = this.x + 92;
        int screenY = this.y + 42;

        //context.fill(x, y, x + filled, y + barHeight, 0xFF00AA00);

        context.drawTexture(
                RenderPipelines.GUI_TEXTURED,
                TEXTURE,
                screenX, screenY,
                176f, 0f,
                filled, barHeight,
                256, 256
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
