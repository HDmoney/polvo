package game.threading;

import engine.graphics.Camera;
import engine.graphics.ShaderProgram;
import engine.graphics.Transformation;
import engine.graphics.gui.Window;
import engine.util.io.IOUtils;
import engine.util.settings.Graphics;
import game.GameItem;
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
    private Matrix4f viewMatrix;
    private ShaderProgram shaderProgram;
    private Transformation transformation = new Transformation();
    private Camera cam = new Camera();

    public void run() {
        // Unnecessary abstraction, but it does make things look nicer.
        Window win = new Window(Graphics.getWidth(), Graphics.getHeight(),
                "Polvo", !Graphics.isBorderless(), Graphics.isVSyncEnabled()) {
            @Override
            public void update(double delta) {
                if (cam == null)
                    cam = new Camera();
                if (t == null)
                    t = new Temp();
                render(delta, cam, new GameItem[]{t});
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

    private void render(double delta, Camera camera, GameItem[] gameItems) {
        rot += 60 / (1f / delta);
        if (rot > 360) {
            rot = 0;
        }
        t.setRotation(rot, rot, rot);

        //Finally, we get to the meat.
        shaderProgram.bind();

        projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        // Update view Matrix
        viewMatrix = transformation.getViewMatrix(camera);

        shaderProgram.setUniform("texture_sampler", 0);
        // Render each gameItem
        for (GameItem gameItem : gameItems) {
            // Set model view matrix for this item
            Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameItem, viewMatrix);
            shaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
            // Render the mes for this game item
            gameItem.getMesh().render();
        }
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
        shaderProgram.createUniform("modelViewMatrix");
        shaderProgram.createUniform("texture_sampler");
    }
}
