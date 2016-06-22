package game;

import engine.graphics.Mesh;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Temp {

    public Mesh mesh;

    public Temp() {
        //TODO: Remove temp code
        float[] positions = new float[]{
                -0.5f, +0.5f, -1.1f,
                -0.5f, -0.5f, -1.0f,
                +0.5f, -0.5f, -0.9f,
                +0.5f, +0.5f, -1.0f,
        };
        float[] colours = new float[]{
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.5f, 0.5f, 0.0f,
        };
        int[] indices = new int[]{
                0, 1, 3, 3, 1, 2,
        };
        mesh = new Mesh(positions, colours, indices);
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
