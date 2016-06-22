package engine.util.timesteps;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public abstract class AbstractTimestep {

    //TODO: VSync timestep
    //TODO: Uncapped timestep
    //TODO: Variable timestep?

    protected double lastLoopTime;
    protected float timeCount;
    protected int iterationsPerSecond;
    protected int ipsCount;

    public abstract boolean checkStopLoop();

    public abstract void iteration(double timeProgress);

    public abstract void run();

    public float getDelta() {
        double time = glfwGetTime();
        float delta = (float) (time - lastLoopTime);
        lastLoopTime = time;
        timeCount += delta;
        return delta;
    }

    public void updateIPS() {
        ipsCount++;
    }

    public int getIPS() {
        return iterationsPerSecond > 0 ? iterationsPerSecond : ipsCount;
    }

    public double getLastLoopTime() {
        return lastLoopTime;
    }
}
