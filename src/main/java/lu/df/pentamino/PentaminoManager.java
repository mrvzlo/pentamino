package lu.df.pentamino;

import java.util.ArrayList;
import java.util.List;

public class PentaminoManager {

    public List<int[][]> Variations;

    public PentaminoManager() {
        Variations = new ArrayList<>();
        Variations.add(GetX());
        Variations.add(GetI());
        Variations.add(rotateArray(GetI()));
        Variations.add(GetF());
        rotateLast3Times();
        Variations.add(flipArray(GetF()));
        rotateLast3Times();
        Variations.add(GetL());
        rotateLast3Times();
        Variations.add(flipArray(GetL()));
        rotateLast3Times();
        Variations.add((GetN()));
        rotateLast3Times();
        Variations.add(flipArray(GetN()));
        rotateLast3Times();
        Variations.add((GetP()));
        rotateLast3Times();
        Variations.add(flipArray(GetP()));
        rotateLast3Times();
        Variations.add((GetT()));
        rotateLast3Times();
        Variations.add((GetU()));
        rotateLast3Times();
        Variations.add((GetV()));
        rotateLast3Times();
        Variations.add((GetW()));
        rotateLast3Times();
        Variations.add((GetY()));
        rotateLast3Times();
        Variations.add(flipArray(GetY()));
        rotateLast3Times();
        Variations.add((GetZ()));
        rotateLast3Times();
        Variations.add(flipArray(GetZ()));
        rotateLast3Times();
    }

    private int[][] GetI() {
        int[][] shape = { { 1, 1, 1, 1, 1 } };
        return shape;
    }

    private int[][] GetL() {
        int[][] shape = {
                { 1, 1, 1, 1 },
                { 1, 0, 0, 0 }
        };
        return shape;
    }

    private int[][] GetY() {
        int[][] shape = {
                { 1, 1, 1, 1 },
                { 0, 1, 0, 0 }
        };
        return shape;
    }

    private int[][] GetN() {
        int[][] shape = {
                { 0, 1, 1, 1 },
                { 1, 1, 0, 0 }
        };
        return shape;
    }

    private int[][] GetF() {
        int[][] shape = {
                { 0, 1, 0 },
                { 1, 1, 1 },
                { 0, 0, 1 },
        };
        return shape;
    }

    private int[][] GetP() {
        int[][] shape = {
                { 0, 1, 1 },
                { 1, 1, 1 },
        };
        return shape;
    }

    private int[][] GetU() {
        int[][] shape = {
                { 1, 0, 1 },
                { 1, 1, 1 },
        };
        return shape;
    }

    private int[][] GetT() {
        int[][] shape = {
                { 0, 0, 1 },
                { 1, 1, 1 },
                { 0, 0, 1 },
        };
        return shape;
    }

    private int[][] GetZ() {
        int[][] shape = {
                { 0, 0, 1 },
                { 1, 1, 1 },
                { 1, 0, 0 },
        };
        return shape;
    }

    private int[][] GetV() {
        int[][] shape = {
                { 0, 0, 1 },
                { 0, 0, 1 },
                { 1, 1, 1 },
        };
        return shape;
    }

    private int[][] GetW() {
        int[][] shape = {
                { 0, 0, 1 },
                { 0, 1, 1 },
                { 1, 1, 0 },
        };
        return shape;
    }

    private int[][] GetX() {
        int[][] shape = {
                { 0, 1, 0 },
                { 1, 1, 1 },
                { 0, 1, 0 },
        };
        return shape;
    }

    private void rotateLast3Times() {
        Variations.add(rotateArray(Variations.get(Variations.size() - 1)));
        Variations.add(rotateArray(Variations.get(Variations.size() - 1)));
        Variations.add(rotateArray(Variations.get(Variations.size() - 1)));
    }

    private static int[][] rotateArray(int[][] source) {
        int rows = source.length;
        int cols = source[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[j][rows - i - 1] = source[i][j];
        return result;
    }

    private static int[][] flipArray(int[][] source) {
        int rows = source.length;
        int cols = source[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][cols - j - 1] = source[i][j];
        return result;
    }
}
