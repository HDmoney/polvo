package game;

import engine.graphics.Mesh;
import lombok.Getter;
import org.joml.Vector3f;

public class GameItem {
    @Getter
    public final Vector3f position = new Vector3f(0f, 0f, 0f);
    @Getter
    public final Vector3f rotation = new Vector3f(0f, 0f, 0f);
    @Getter
    public Mesh mesh;
    @Getter
    public float scale = 1;
}
