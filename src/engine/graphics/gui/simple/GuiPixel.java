package engine.graphics.gui.simple;

import engine.graphics.SimpleMesh;
import engine.graphics.gui.utils.GuiColor;
import lombok.Getter;
import org.joml.Vector2i;

import static engine.graphics.gui.utils.GuiUtils.xToFloat;
import static engine.graphics.gui.utils.GuiUtils.yToFloat;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class GuiPixel extends GuiObject {
    @Getter
    private int x, y;
    @Getter
    private GuiColor color;

    public GuiPixel(int x, int y, GuiColor color) {
        init(x, y, color);
    }

    public GuiPixel(Vector2i xy, GuiColor color) {
        init(xy.x, xy.y, color);
    }

    private void init(int x, int y, GuiColor color) {
        this.x = x;
        this.y = y;

        this.color = color;

        float xf = xToFloat(x);
        float yf = yToFloat(y);
        float xf1 = xToFloat(x + 1);
        float yf1 = yToFloat(y + 1);

        mesh = new SimpleMesh(new float[]{
                xf, yf1, 0.0f,
                xf, yf, 0.0f,
                xf1, yf, 0.0f,
                xf1, yf1, 0.0f
        }, color.rgbFloatArray(), new int[]{
                0, 1, 3,
                3, 1, 2,
        });
    }

    public void render(double delta) {

        // Draw the mesh
        glBindVertexArray(mesh.getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        // Restore state
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
    }
}
