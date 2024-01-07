package lu.df.pentamino;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Pentamino {
    @PlanningId
    public int Id;
    @PlanningVariable
    public PentaminoVariation Variation;
    @PlanningVariable
    public Cell Cell;

    public Field Parent;
    public int Color;

    public Pentamino() {
    }

    public Pentamino(int id, Field field) {
        Parent = field;
        Id = id;
    }

    public int GetOutOfBounds() {
        int rows = Variation.Form.length;
        int cols = Variation.Form[0].length;
        int bottom = Cell.Y + rows;
        int right = Cell.X + cols;

        int[][] board = Parent.InitialBoard;
        int count = 0;

        for (int i = Cell.Y; i < bottom; i++)
            for (int j = Cell.X; j < right; j++)
                if (i >= board.length || j >= board[0].length)
                    count++;

        return count;
    }

    public int GetWrongBounds() {
        int rows = Variation.Form.length;
        int cols = Variation.Form[0].length;
        int bottom = Cell.Y + rows;
        int right = Cell.X + cols;

        boolean[][] form = Variation.Form;
        int[][] board = Parent.InitialBoard;
        int count = 0;

        for (int i = Cell.Y; i < bottom; i++)
            for (int j = Cell.X; j < right; j++) {
                if (i >= board.length || j >= board[0].length)
                    continue;
                if (board[i][j] != 0 && form[i - Cell.Y][j - Cell.X])
                    count++;
            }

        return count;
    }

    public int GetOverlaps(Pentamino other) {
        boolean[][] formA = Variation.Form;
        boolean[][] formB = other.Variation.Form;

        int rowsA = formA.length;
        int colsA = formA[0].length;
        int rowsB = formB.length;
        int colsB = formB[0].length;
        int count = 0;

        for (int ia = 0; ia < rowsA; ia++)
            for (int ja = 0; ja < colsA; ja++)
                for (int ib = 0; ib < rowsB; ib++)
                    for (int jb = 0; jb < colsB; jb++)
                        if (Cell.X + ja == other.Cell.X + jb && Cell.Y + ia == other.Cell.Y + ib
                                && formA[ia][ja] && formB[ib][jb])
                            count++;

        return count;
    }

}
