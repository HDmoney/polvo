package threading;

import util.timesteps.FixedTimestep;

public class LogicThread implements Runnable {

    public void run() {
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
            public void iteration(double delta) { /* Update logic here. */ }
        };
        logic.run();
    }


    private void init() {
    }


}
