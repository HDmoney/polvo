package util.settings;

public class Graphics {
    public static boolean fullscreen;
    public static boolean vSync;
    public static boolean borderless = true;
    public static boolean shadows;
    public static boolean hdr;
    public static boolean bloom;
    public static boolean ambientocclusion;
    public static int[] resolution = new int[]{640, 480};

    public static int getWidth() {
        return resolution[0];
    }

    public static int getHeight() {
        return resolution[1];
    }

    public static boolean isVSyncEnabled() {
        return vSync;
    }

    public static boolean isBorderless() {
        return borderless;
    }
    public static enum AntiAliasing {
        NONE, MSAA;
        private byte samples;
    }
}
