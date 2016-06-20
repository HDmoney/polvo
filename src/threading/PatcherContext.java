package threading;

import gui.Window;

public class PatcherContext {

    public void run() {
        // Unnecessary abstraction, but it does make things look nicer.
        Window win = new Window(480, 360, "Patcher: Polvo", false, false) {
            @Override
            public void update(double delta) {
                updateAndRender(delta);
            }
        };
        win.setClearColor(0.93f, 0.93f, 0.93f, 1f);
        win.run();
    }

    private void updateAndRender(double delta) {
    }
}
