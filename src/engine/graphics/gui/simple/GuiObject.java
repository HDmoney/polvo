package engine.graphics.gui.simple;

import engine.graphics.Mesh;
import lombok.Getter;

public abstract class GuiObject {

    @Getter
    protected Mesh mesh;

    public abstract void render(double delta);
}
