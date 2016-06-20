package threading;

import gui.Window;
import util.settings.Graphics;

public class RenderingContext {

    Window window;

    public void run() {
        // Unnecessary abstraction, but it does make things look nicer.
        Window win = new Window(Graphics.getWidth(), Graphics.getHeight(),
                "Polvo", !Graphics.isBorderless(), Graphics.isVSyncEnabled()) {
            @Override
            public void update(double delta) {
                render(delta);
            }
        };
        win.setClearColor(0.15f, 0.15f, 0.15f, 1f);
        window = win;

        window.run();
    }

    private void render(double delta) {
        //Finally, we get to the meat.

    }
}
