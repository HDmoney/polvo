package engine.graphics.gui.simple;

import lombok.Getter;
import lombok.Setter;

public class GuiUtils {
    @Getter
    @Setter
    public static int width, height;

    public static float xToFloat(int x) {
        //2f because the screen ranges from -1 to 1, a difference of 2.
        float pxl = 2f / width;
        return (x * pxl) - 1f;
    }

    public static float yToFloat(int y) {
        float pxl = 2f / height;
        return (-y * pxl) + 1f;
    }
}
