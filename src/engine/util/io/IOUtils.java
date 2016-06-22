// TODO: replace IOUtils
// Graciously borrowed from:
// https://github.com/lwjglgamedev/lwjglbook/blob/master/chapter20/c20-p2/src/main/java/org/lwjglb/engine/Utils.java

package engine.util.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOUtils {
    public static String loadResource(String fileName) throws Exception {
        String result = "";
        try (InputStream in = IOUtils.class.getClass().getResourceAsStream(fileName)) {
            result = new Scanner(in, "UTF-8").useDelimiter("\\A").next();
        }
        return result;
    }

    public static List<String> readAllLines(String fileName) throws Exception {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(IOUtils.class.getClass().getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        }
        return list;
    }

    public static int[] listIntToArray(List<Integer> list) {
        int[] result = list.stream().mapToInt((Integer v) -> v).toArray();
        return result;
    }

    public static float[] listToArray(List<Float> list) {
        int size = list != null ? list.size() : 0;
        float[] floatArr = new float[size];
        for (int i = 0; i < size; i++) {
            floatArr[i] = list.get(i);
        }
        return floatArr;
    }

    public static boolean existsResourceFile(String fileName) {
        boolean result;
        try (InputStream is = IOUtils.class.getResourceAsStream(fileName)) {
            result = is != null;
        } catch (Exception excp) {
            result = false;
        }
        return result;
    }
}
