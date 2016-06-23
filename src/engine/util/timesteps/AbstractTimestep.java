package engine.util.timesteps;

public abstract class AbstractTimestep {

    //TODO: VSync timestep
    //TODO: Uncapped timestep
    //TODO: Variable timestep?

    protected double lastLoopTime;
    protected int iterationsPerSecond;
    protected int ipsCount;
    protected float delta;

    public abstract boolean checkStopLoop();

    public abstract void iteration(double timeProgress);

    public abstract void run();

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
