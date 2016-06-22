package engine.util.timesteps;

import static java.lang.Thread.yield;
import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class FixedTimestep extends AbstractTimestep {

    public int frequency;

    public FixedTimestep(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean checkStopLoop() {
        return false;
    }

    @Override
    public void iteration(double delta) {
    }

    @Override
    public void run() {

        //Heavily inspired by http://gafferongames.com/game-physics/fix-your-timestep/
        //and https://github.com/SilverTiger/lwjgl3-tutorial/wiki/Game-loops

        float delta;
        float accumulator = 0f;
        float interval = 1f / frequency;

        while (!checkStopLoop()) {
            delta = getDelta();
            accumulator += delta;

            while (accumulator >= interval) {
                iteration(delta);
                accumulator -= interval;
            }

            sync();
        }
    }

    public void sync() {
        double now = glfwGetTime();
        double timeSinceStart = now - lastLoopTime;
        float targetTime = 1f / frequency;

        while (timeSinceStart < targetTime) {
            //NOTE: This may need to be optimized for Windows.
            if (timeSinceStart < 3f / 4f * targetTime) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            yield();

            now = glfwGetTime();
            timeSinceStart = now - lastLoopTime;
        }
    }
}
