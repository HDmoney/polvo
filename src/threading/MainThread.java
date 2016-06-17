package threading;


public class MainThread {


    public static void main(String[] args) {

        //Vector2f vvf = new Vector2f(0f, 1f);

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
