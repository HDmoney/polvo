package util.settings;

public class Graphics {
    public static boolean fullscreen;
    public static boolean vsync;
    public static boolean borderless;
    public static boolean shadows;
    public static boolean hdr;
    public static boolean bloom;
    public static boolean ambientocclusion;
    public static int[] resolution;

    public static enum AntiAliasing {
        NONE, MSAA;
        private byte samples;
    }
}
