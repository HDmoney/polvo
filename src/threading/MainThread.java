package threading;

public class MainThread {
    public static void main(String[] args) {
        //NOTE: The rendering thread MUST be in the main thread, otherwise OSX will get angry.
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
}
