package engine.graphics.gui.simple;

import engine.graphics.Mesh;
import engine.graphics.gui.utils.GuiColor;
import lombok.Getter;
import org.joml.Vector2i;

import static engine.graphics.gui.utils.GuiUtils.xToFloat;
import static engine.graphics.gui.utils.GuiUtils.yToFloat;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class GuiRectangle extends GuiObject {
    @Getter
    private int x, y;
    @Getter
    private int width, height;
    @Getter
    private GuiColor color;

    public GuiRectangle(int x, int y, int width, int height, GuiColor color) {
        init(x, y, width, height, color);
    }

    public GuiRectangle(Vector2i xy, int width, int height, GuiColor color) {
        init(xy.x, xy.y, width, height, color);
    }

    private void init(int x, int y, int width, int height, GuiColor color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.color = color;

        float xf = xToFloat(x);
        float yf = yToFloat(y);
        float xf1 = xToFloat(x + width);
        float yf1 = yToFloat(y + height);

        mesh = new Mesh(new float[]{
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
