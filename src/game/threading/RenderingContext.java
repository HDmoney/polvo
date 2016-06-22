package game.threading;

import engine.graphics.ShaderProgram;
import engine.graphics.gui.Window;
import engine.util.io.IOUtils;
import engine.util.settings.Graphics;
import game.Temp;
import org.joml.Matrix4f;

public class RenderingContext {

    private static final float FOV = (float) Math.toRadians(60.0f);
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.f;

    public Window window;
    private Matrix4f projectionMatrix;
    private ShaderProgram shaderProgram;

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
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.run();
    }

    private void render(double delta) {
        //TODO: remove
        Temp t = new Temp();

        //Finally, we get to the meat.
        shaderProgram.bind();
        //TODO: Move this?
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        t.render(delta);

        shaderProgram.unbind();
    }

    private void init() throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(IOUtils.loadResource("/shaders/vertex/default.vs"));
        shaderProgram.createFragmentShader(IOUtils.loadResource("/shaders/fragment/default.fs"));
        shaderProgram.link();

        // Create projection matrix
        float aspectRatio = (float) window.getWidth() / window.getHeight();
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
        shaderProgram.createUniform("projectionMatrix");
    }
}
