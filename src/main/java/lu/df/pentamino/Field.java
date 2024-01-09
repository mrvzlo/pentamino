package lu.df.pentamino;

import java.util.ArrayList;
import java.util.List;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import java.awt.image.BufferedImage;

@PlanningSolution
public class Field {
    @PlanningEntityCollectionProperty
    public Pentamino[] Pentaminoes;
    @PlanningScore
    public HardSoftScore Score;
    @ProblemFactCollectionProperty
    @ValueRangeProvider
    private List<PentaminoVariation> Variations;
    @ProblemFactCollectionProperty
    @ValueRangeProvider
    private List<Cell> Available;

    public int[][] InitialBoard;

    public Field() {
    }

    public Field(int[][] board) {
        PentaminoManager manager = new PentaminoManager();
        Variations = new ArrayList<>();
        for (int i = 0; i < manager.Variations.size(); i++)
            Variations.add(new PentaminoVariation(manager.Variations.get(i)));

        InitialBoard = board;

        int cells = 0;
        Available = new ArrayList<>();
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++) {
                if ((j < board[0].length - 4 || i != board.length - 1)
                        && (i < board.length - 4 || j != board[0].length - 1))
                    Available.add(new Cell(j, i));

                if (board[i][j] != 0)
                    continue;
                cells++;

            }
        int figures = cells / 5;

        Pentaminoes = new Pentamino[figures];
        for (int i = 0; i < figures; i++) {
            Pentaminoes[i] = new Pentamino(i, this);
            Pentaminoes[i].Color = generateRandomColor();
            Pentaminoes[i].Variation = Variations.get(1);
            Pentaminoes[i].Cell = Available.get((i * 5) % Available.size());
        }
    }

    public BufferedImage ToImage() {
        int width = InitialBoard[0].length;
        int height = InitialBoard.length;

        for (int i = 0; i < Pentaminoes.length; i++) {
            if (Pentaminoes[i].Variation == null || Pentaminoes[i].Cell == null)
                continue;
            boolean[][] form = Pentaminoes[i].Variation.Form;
            int cx = Pentaminoes[i].Cell.X;
            int cy = Pentaminoes[i].Cell.Y;
            int h = form.length;
            int w = form[0].length;
            for (int y = 0; y < h; y++)
                for (int x = 0; x < w; x++)
                    if (y + cy < height && x + cx < width && form[y][x])
                        InitialBoard[y + cy][x + cx] = InitialBoard[y + cy][x + cx] > 0 ? -2
                                : InitialBoard[y + cy][x + cx] < 0 ? -3 : (i + 1);
        }

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgbColor = mapValueToRGB(InitialBoard[y][x]);
                image.setRGB(x, y, rgbColor);
            }
        }
        return image;
    }

    private int mapValueToRGB(int value) {
        if (value == 0)
            return 0xffffff;
        if (value == -1)
            return 0;
        if (value == -2)
            return 0xff0000;
        if (value == -3)
            return 0xffff00;
        return Pentaminoes[value - 1].Color;
    }

    private int generateRandomColor() {
        int red = (int) (Math.random() * 128 + 64) * 0xffff;
        int green = (int) (Math.random() * 128 + 64) * 0xff;
        int blue = (int) (Math.random() * 128 + 64);
        return red + green + blue;
    }
}
