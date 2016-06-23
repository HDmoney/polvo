package game.threading;

import engine.util.timesteps.FixedTimestep;

public class LogicThread implements Runnable {

    public void run() {
        Thread.currentThread().setName("logic");
        init();
        loop();
    }

    private void loop() {
        FixedTimestep logic = new FixedTimestep(60) {
            @Override
            public boolean checkStopLoop() {
                return false;
            }

            @Override
            public void iteration(double delta) {
                update(delta);
            }
        };
        logic.run();
    }

    private void update(double delta) {
        //System.out.println(glfwGetTime());
    }


    private void init() {
        // TODO: Load initial resources into a shared pool

        // TODO:
    }


}
