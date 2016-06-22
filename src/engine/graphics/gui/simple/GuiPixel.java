package engine.graphics.gui.simple;

import engine.graphics.Mesh;
import lombok.Getter;

import static engine.graphics.gui.simple.GuiUtils.xToFloat;
import static engine.graphics.gui.simple.GuiUtils.yToFloat;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class GuiPixel {
    @Getter
    private int x, y;
    @Getter
    private GuiColor color;
    @Getter
    private Mesh mesh;

    public GuiPixel(int x, int y, GuiColor color) {
        this.x = x;
        this.y = y;

        this.color = color;

        float xf = xToFloat(x);
        float yf = yToFloat(y);
        float xf1 = xToFloat(x + 1);
        float yf1 = yToFloat(y + 1);

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
