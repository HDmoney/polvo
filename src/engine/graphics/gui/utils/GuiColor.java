package engine.graphics.gui.utils;

import org.joml.Vector4f;

public class GuiColor {
    private float rf, gf, bf, af;
    private int r, g, b, a;
    private Vector4f rgba;

    public GuiColor(String hexRgb) {
        StringBuilder sb = new StringBuilder(hexRgb);
        sb.deleteCharAt(0);
        this.r = Integer.parseInt(sb.substring(0, 2), 16);
        this.g = Integer.parseInt(sb.substring(2, 4), 16);
        this.b = Integer.parseInt(sb.substring(4, 6), 16);
        this.a = 255;

        this.rf = r / 255f;
        this.gf = g / 255f;
        this.bf = b / 255f;
        this.af = a / 255f;

        rgba = new Vector4f(rf, gf, bf, af);
    }

    public GuiColor(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;

        this.rf = r / 255f;
        this.gf = g / 255f;
        this.bf = b / 255f;
        this.af = a / 255f;

        rgba = new Vector4f(rf, gf, bf, af);
    }

    public float getRedF() {
        return rf;
    }

    public float getGreenF() {
        return gf;
    }

    public float getBlueF() {
        return bf;
    }

    public float getAlphaF() {
        return af;
    }

    public int getRed() {
        return r;
    }

    public int getGreen() {
        return g;
    }

    public int getBlue() {
        return b;
    }

    public int getAlpha() {
        return a;
    }

    public Vector4f getRgba() {
        return rgba;
    }

    public float[] toFloatArray() {
        return new float[]{rf, gf, bf, af};
    }

    public float[] rgbFloatArray() {
        return new float[]{
                rf, gf, bf,
                rf, gf, bf,
                rf, gf, bf,
                rf, gf, bf
        };
    }

    public float[] rgbFloatArray(int points) {
        int parts = 3;
        float[] floats = new float[parts * points];

        for (int i = 0; i < points * parts; i++) {
            switch (i % parts) {
                case 0:
                    floats[i] = rf;
                    break;
                case 1:
                    floats[i] = gf;
                    break;
                case 2:
                    floats[i] = bf;
                    break;
            }
        }
        return floats;
    }
}
