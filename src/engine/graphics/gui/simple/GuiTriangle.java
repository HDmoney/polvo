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

public class GuiTriangle extends GuiObject {
    @Getter
    private int x1, y1, x2, y2, x3, y3;
    @Getter
    private GuiColor color;

    public GuiTriangle(int x1, int y1, int x2, int y2, int x3, int y3, GuiColor color) {
        init(x1, y1, x2, y2, x3, y3, color);
    }

    public GuiTriangle(Vector2i xy1, Vector2i xy2, Vector2i xy3, GuiColor color) {
        init(xy1.x, xy1.y, xy2.x, xy2.y, xy3.x, xy3.y, color);
    }

    private void init(int x1, int y1, int x2, int y2, int x3, int y3, GuiColor color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;

        this.color = color;

        float xf1 = xToFloat(x1);
        float yf1 = yToFloat(y1);
        float xf2 = xToFloat(x2);
        float yf2 = yToFloat(y2);
        float xf3 = xToFloat(x3);
        float yf3 = yToFloat(y3);

        mesh = new Mesh(new float[]{
                xf1, yf1, 0.0f,
                xf2, yf2, 0.0f,
                xf3, yf3, 0.0f,
        }, color.rgbFloatArray(), new int[]{
                0, 1, 2
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
