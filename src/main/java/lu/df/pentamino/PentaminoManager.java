package lu.df.pentamino;

import java.util.ArrayList;
import java.util.List;

public class PentaminoManager {

    public List<boolean[][]> Variations;

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
        Variations.add(rotateArray(rotateArray(GetZ())));
        Variations.add(flipArray(GetZ()));
        Variations.add(rotateArray(rotateArray(flipArray(GetZ()))));
    }

    private boolean[][] GetI() {
        boolean[][] shape = { { true, true, true, true, true } };
        return shape;
    }

    private boolean[][] GetL() {
        boolean[][] shape = {
                { true, true, true, true },
                { true, false, false, false }
        };
        return shape;
    }

    private boolean[][] GetY() {
        boolean[][] shape = {
                { true, true, true, true },
                { false, true, false, false }
        };
        return shape;
    }

    private boolean[][] GetN() {
        boolean[][] shape = {
                { false, true, true, true },
                { true, true, false, false }
        };
        return shape;
    }

    private boolean[][] GetF() {
        boolean[][] shape = {
                { false, true, false },
                { true, true, true },
                { false, false, true },
        };
        return shape;
    }

    private boolean[][] GetP() {
        boolean[][] shape = {
                { false, true, true },
                { true, true, true },
        };
        return shape;
    }

    private boolean[][] GetU() {
        boolean[][] shape = {
                { true, false, true },
                { true, true, true },
        };
        return shape;
    }

    private boolean[][] GetT() {
        boolean[][] shape = {
                { false, false, true },
                { true, true, true },
                { false, false, true },
        };
        return shape;
    }

    private boolean[][] GetZ() {
        boolean[][] shape = {
                { false, false, true },
                { true, true, true },
                { true, false, false },
        };
        return shape;
    }

    private boolean[][] GetV() {
        boolean[][] shape = {
                { false, false, true },
                { false, false, true },
                { true, true, true },
        };
        return shape;
    }

    private boolean[][] GetW() {
        boolean[][] shape = {
                { false, false, true },
                { false, true, true },
                { true, true, false },
        };
        return shape;
    }

    private boolean[][] GetX() {
        boolean[][] shape = {
                { false, true, false },
                { true, true, true },
                { false, true, false },
        };
        return shape;
    }

    private void rotateLast3Times() {
        Variations.add(rotateArray(Variations.get(Variations.size() - 1)));
        Variations.add(rotateArray(Variations.get(Variations.size() - 1)));
        Variations.add(rotateArray(Variations.get(Variations.size() - 1)));
    }

    private static boolean[][] rotateArray(boolean[][] source) {
        int rows = source.length;
        int cols = source[0].length;
        boolean[][] result = new boolean[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[j][rows - i - 1] = source[i][j];
        return result;
    }

    private static boolean[][] flipArray(boolean[][] source) {
        int rows = source.length;
        int cols = source[0].length;
        boolean[][] result = new boolean[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][cols - j - 1] = source[i][j];
        return result;
    }
}
