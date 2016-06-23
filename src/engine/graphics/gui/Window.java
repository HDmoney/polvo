package engine.graphics.gui;

import engine.graphics.gui.utils.GuiUtils;
import engine.util.timesteps.FixedTimestep;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    public final int WIDTH, HEIGHT;
    public final String TITLE;
    public final boolean DECORATED;
    public final boolean VSYNC;
    // The window handle
    private long window;
    private float r, g, b, a;

    public Window(int width, int height, String title, boolean decorated, boolean vsync) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.TITLE = title;
        this.DECORATED = decorated;
        this.VSYNC = vsync;
        GuiUtils.setWidth(width);
        GuiUtils.setHeight(height);
        init();
    }

    public void run() {
        try {
            loop();

            // Free the window callbacks and destroy the window
            glfwFreeCallbacks(window);
            glfwDestroyWindow(window);
        } finally {
            // Terminate GLFW and free the error callback
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }

    private void init() {
        //Configure the window
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will be resizable
        glfwWindowHint(GLFW_DECORATED, DECORATED ? GLFW_TRUE : GLFW_FALSE);

        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, TITLE, NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true); // We will detect this in our rendering loop
            }
        });

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
                window,
                (vidmode.width() - WIDTH) / 2,
                (vidmode.height() - HEIGHT) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        if (VSYNC) {
            glfwSwapInterval(1);
        }
        // Make the window visible
        glfwShowWindow(window);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);
    }

    private void loop() {

        // Set the clear color
        glClearColor(r, g, b, a);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.

        //TODO: vsync and other rendering options.
        FixedTimestep rendering = new FixedTimestep(60) {
            @Override
            public boolean checkStopLoop() {
                return glfwWindowShouldClose(window);
            }

            @Override
            public void iteration(double delta) {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

                update(delta);

                glfwSwapBuffers(window); // swap the color buffers

                // Poll for window events. The key callback above will only be
                // invoked during this call.
                glfwPollEvents();
            }
        };
        rendering.run();
    }

    public void setClearColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public void update(double delta) {

    }

    public long getWindowID() {
        return window;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
