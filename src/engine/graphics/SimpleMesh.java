// TODO: replace Mesh
// Graciously borrowed from:
// https://github.com/lwjglgamedev/lwjglbook/

package engine.graphics;

import lombok.Getter;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class SimpleMesh {
    //TODO: Add "SimpleMesh" combining for batch rendering.

    @Getter
    public final float[] positions;
    @Getter
    public final float[] colors;
    @Getter
    public final int[] indices;
    private final int vaoId;
    private final int posVboId;
    private final int colourVboId;
    private final int idxVboId;
    private final int vertexCount;

    public SimpleMesh(float[] positions, float[] colors, int[] indices) {
        this.positions = positions;
        this.colors = colors;
        this.indices = indices;

        vertexCount = indices.length;

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        // Position VBO
        posVboId = glGenBuffers();
        FloatBuffer posBuffer = BufferUtils.createFloatBuffer(positions.length);
        posBuffer.put(positions).flip();
        glBindBuffer(GL_ARRAY_BUFFER, posVboId);
        glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        // Colour VBO
        colourVboId = glGenBuffers();
        FloatBuffer colourBuffer = BufferUtils.createFloatBuffer(colors.length);
        colourBuffer.put(colors).flip();
        glBindBuffer(GL_ARRAY_BUFFER, colourVboId);
        glBufferData(GL_ARRAY_BUFFER, colourBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);

        // Index VBO
        idxVboId = glGenBuffers();
        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
        indicesBuffer.put(indices).flip();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public static SimpleMesh combine(SimpleMesh... meshes) {
        float[] positions = combinePositions(meshes);
        float[] colors = combineColors(meshes);
        int[] indices = combineIndices(meshes);
        return new SimpleMesh(positions, colors, indices);
    }

    public static float[] combinePositions(SimpleMesh[] meshes) {
        int totalLength = 0;
        for (int i = 0; i < meshes.length; i++) {
            totalLength += meshes[i].getPositions().length;
        }
        float[] result = new float[totalLength];
        int positionTracker = 0;
        for (int i = 0; i < meshes.length; i++) {
            for (int j = 0; j < meshes[i].getPositions().length; j++) {
                result[positionTracker] = meshes[i].getPositions()[j];
                positionTracker++;
            }
        }

        return result;
    }

    public static float[] combineColors(SimpleMesh[] meshes) {
        int totalLength = 0;
        for (int i = 0; i < meshes.length; i++) {
            totalLength += meshes[i].getColors().length;
        }
        float[] result = new float[totalLength];
        int positionTracker = 0;
        for (int i = 0; i < meshes.length; i++) {
            for (int j = 0; j < meshes[i].getColors().length; j++) {
                result[positionTracker] = meshes[i].getColors()[j];
                positionTracker++;
            }
        }

        return result;
    }

    public static int[] combineIndices(SimpleMesh[] meshes) {
        int totalLength = 0;
        for (int i = 0; i < meshes.length; i++) {
            totalLength += meshes[i].getIndices().length;
        }
        int[] result = new int[totalLength];

        int positionTracker = 0;
        int indexOffset = 0;

        for (int i = 0; i < meshes.length; i++) {
            for (int j = 0; j < meshes[i].getIndices().length; j++) {
                result[positionTracker] = meshes[i].getIndices()[j] + indexOffset;
                positionTracker++;
            }
            indexOffset += meshes[i].getPositions().length / 3;
        }

        return result;
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void cleanUp() {
        glDisableVertexAttribArray(0);

        // Delete the VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(posVboId);
        glDeleteBuffers(colourVboId);
        glDeleteBuffers(idxVboId);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }
}