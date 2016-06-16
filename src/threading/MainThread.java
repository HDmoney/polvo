package threading;


public class MainThread {


    public static void main(String[] args) {
        //TODO: if (settings.ini runPatcher=1) {
            //Opens patcher on startup.
            new PatcherThread().run();
        //} else {
            //Start the real game
        //}
    }

    public void run() {

    }


}
