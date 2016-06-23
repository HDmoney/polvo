package game;

import engine.graphics.Mesh;
import lombok.Getter;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Temp {

    @Getter
    public final Vector3f position = new Vector3f(0f, 0f, -2f);
    @Getter
    public final Vector3f rotation = new Vector3f(0f, 0f, 0f);
    public Mesh mesh;
    @Getter
    public float scale = 1;

    public Temp() {
        //TODO: Remove temp code
        float[] positions = new float[]{
                -0.5f, +0.5f, +0.5f,
                -0.5f, -0.5f, +0.5f,
                +0.5f, -0.5f, +0.5f,
                +0.5f, +0.5f, +0.5f,
                -0.5f, +0.5f, -0.5f,
                +0.5f, +0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                +0.5f, -0.5f, -0.5f,
        };
        float[] colours = new float[]{
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };
        int[] indices = new int[]{
                // Front face
                0, 1, 3, 3, 1, 2,
                // Top Face
                4, 0, 3, 5, 4, 3,
                // Right face
                3, 2, 7, 5, 3, 7,
                // Left face
                0, 1, 6, 4, 0, 6,
                // Bottom face
                6, 1, 2, 7, 6, 2,
                // Back face
                4, 6, 7, 5, 4, 7,
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

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public void setScale(float s) {
        this.scale = s;
    }
}
