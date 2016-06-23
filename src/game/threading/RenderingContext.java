package game.threading;

import engine.graphics.ShaderProgram;
import engine.graphics.Transformation;
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
    //TODO: remove
    Temp t;
    //TODO: remove
    float rot;
    private Matrix4f projectionMatrix;
    private Matrix4f worldMatrix;
    private ShaderProgram shaderProgram;
    private Transformation transformation = new Transformation();

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
        rot += 60 / (1f / delta);
        ;
        if (rot > 360) {
            rot = 0;
        }
        t.setRotation(rot, rot, rot);

        //Finally, we get to the meat.
        shaderProgram.bind();

        projectionMatrix = transformation.getProjectionMatrix(
                FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        worldMatrix =
                transformation.getWorldMatrix(
                        t.getPosition(),
                        t.getRotation(),
                        t.getScale());

        shaderProgram.setUniform("worldMatrix", worldMatrix);
        t.render(delta);

        shaderProgram.unbind();
    }

    private void init() throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(IOUtils.loadResource("/shaders/vertex/default.vs"));
        shaderProgram.createFragmentShader(IOUtils.loadResource("/shaders/fragment/default.fs"));
        shaderProgram.link();

        // Create projection matrix
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("worldMatrix");

        t = new Temp();
    }
}
