package util.timesteps;

public abstract class AbstractTimestep {

    //TODO: VSync timestep
    //TODO: Uncapped timestep
    //TODO: Variable timestep?


    protected long startTime;

    protected double lastLoopTime;
    protected float timeCount;
    protected int iterationsPerSecond;
    protected int ipsCount;

    public abstract boolean checkStopLoop();

    public abstract void iteration(double timeProgress);

    public abstract void run();

    public void initTimer() {
        startTime = System.nanoTime();
        lastLoopTime = 0d;
    }

    public double getTime() {
        return (System.nanoTime() - startTime) / 1000000000d;
    }

    public float getDelta() {
        double time = getTime();
        float delta = (float) (time - lastLoopTime);
        lastLoopTime = time;
        timeCount += delta;
        return delta;
    }

    public void updateIPS() {
        ipsCount++;
    }

    public void updateTimer() {
        if (timeCount > 1f) {
            iterationsPerSecond = ipsCount;
            ipsCount = 0;
            timeCount -= 1f;
        }
    }

    public int getIPS() {
        return iterationsPerSecond > 0 ? iterationsPerSecond : ipsCount;
    }

    public double getLastLoopTime() {
        return lastLoopTime;
    }
}
