package engine.graphics.gui.simple;

import engine.graphics.SimpleMesh;
import lombok.Getter;

public abstract class GuiObject {

    @Getter
    protected SimpleMesh mesh;

    public abstract void render(double delta);
}
