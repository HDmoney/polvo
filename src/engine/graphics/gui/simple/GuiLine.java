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

public class GuiLine extends GuiObject {

    @Getter
    private int x, y;
    @Getter
    private int nx, ny;
    @Getter
    private GuiColor color;

    public GuiLine(int x, int y, int nx, int ny, GuiColor color) {
        init(x, y, nx, ny, color);
    }

    public GuiLine(Vector2i xy1, Vector2i xy2, GuiColor color) {
        init(xy1.x, xy1.y, xy2.x, xy2.y, color);
    }

    private void init(int x, int y, int nx, int ny, GuiColor color) {
        this.x = x;
        this.y = y;
        this.nx = nx;
        this.ny = ny;
        this.color = color;

        float xf0 = xToFloat(x);
        float xf1 = xToFloat(x + 1);
        float yf0 = yToFloat(y);
        float yf1 = yToFloat(y + 1);

        float nxf0 = xToFloat(nx);
        float nxf1 = xToFloat(nx + 1);
        float nyf0 = yToFloat(ny);
        float nyf1 = yToFloat(ny + 1);

        mesh = new SimpleMesh(new float[]{
                xf0, yf0, 0f,
                xf1, yf0, 0f,
                xf0, yf1, 0f,

                nxf1, nyf1, 0f,
                nxf1, nyf0, 0f,
                nxf0, nyf1, 0f,
        }, color.rgbFloatArray(6), new int[]{
                1, 0, 2,
                1, 2, 5,
                1, 5, 4,
                5, 4, 3
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
