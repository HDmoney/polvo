package game.threading;

import engine.graphics.ShaderProgram;
import engine.graphics.gui.Window;
import engine.graphics.gui.simple.GuiLine;
import engine.graphics.gui.simple.GuiPixel;
import engine.graphics.gui.simple.GuiRectangle;
import engine.graphics.gui.simple.GuiTriangle;
import engine.graphics.gui.utils.GuiBatch;
import engine.graphics.gui.utils.GuiColor;
import engine.util.io.IOUtils;

public class PatcherContext {

    Window window;
    ShaderProgram shaderProgram;
    GuiBatch guiThing;
    GuiPixel[] pxls;

    public void run() {
        // Unnecessary abstraction, but it does make things look nicer.
        Window win = new Window(480, 360, "Patcher: Polvo", true, false) {
            @Override
            public void update(double delta) {
                updateAndRender(delta);
            }
        };
        win.setClearColor(0.93f, 0.93f, 0.93f, 1f);
        window = win;
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.run();
    }

    private void updateAndRender(double delta) {
        shaderProgram.bind();

        guiThing.render(delta);

        shaderProgram.unbind();
    }

    private void init() throws Exception {
        //TODO: Remove GUI element testing
        guiThing = new GuiBatch(
                new GuiPixel(2, 2, new GuiColor("#0000FF")),
                new GuiLine(40, 20, 280, 20, new GuiColor("#FF00FF")),
                new GuiPixel(1, 1, new GuiColor("#FF0000")),
                new GuiRectangle(40, 21, 200, 32, new GuiColor("#666666")),
                new GuiTriangle(20, 20, 40, 20, 30, 30, new GuiColor("#AAAAAA"))
        );

        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(IOUtils.loadResource("/shaders/vertex/flat.vs"));
        shaderProgram.createFragmentShader(IOUtils.loadResource("/shaders/fragment/default.fs"));
        shaderProgram.link();
    }
}
