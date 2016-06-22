package game.threading;

import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class MainThread {


    public static void main(String[] args) {
        //TODO: Make TODO list

        //NOTE: The GLFW MUST be in the main thread, otherwise OSX will get angry.
        initGlfwLib();

        // TODO: if runPatcher in settings.ini
        if (true) {
            //Opens patcher on startup.
            //We keep this on the main thread as well.
            new PatcherContext().run();
        } else {
            //Start the real game
            new Thread(new LogicThread()).start();
            new RenderingContext().run();
        }
    }

    public static void initGlfwLib() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure glfw
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
    }
}
