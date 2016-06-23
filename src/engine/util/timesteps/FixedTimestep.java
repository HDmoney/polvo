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

        float accumulator = 0f;
        float interval = 1f / frequency;
        float alpha;

        while (!checkStopLoop()) {
            delta = (float) (glfwGetTime() - lastLoopTime);
            lastLoopTime = glfwGetTime();

            iteration(delta);

            sync(interval);
        }
    }

    public void sync(float interval) {
        double now = glfwGetTime();
        double timeSinceStart = now - lastLoopTime;

        while (timeSinceStart < interval) {
            //NOTE: This may need to be optimized for Windows.
            if (timeSinceStart < 9f / 10f * interval) {
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
