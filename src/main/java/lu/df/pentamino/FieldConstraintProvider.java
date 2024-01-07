package lu.df.pentamino;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;

public class FieldConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                BoundsConflict(constraintFactory),
                BlackDotsConflict(constraintFactory),
                OverlapConflict(constraintFactory),
        };
    }

    Constraint OverlapConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachUniquePair(Pentamino.class)
                .filter((a, b) -> a.GetOverlaps(b) > 0)
                .penalize(HardSoftScore.ONE_HARD, Pentamino::GetOverlaps)
                .asConstraint("Overlap conflict");
    }

    Constraint BlackDotsConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Pentamino.class)
                .filter((a) -> a.GetWrongBounds() > 0)
                .penalize(HardSoftScore.ONE_SOFT, Pentamino::GetWrongBounds)
                .asConstraint("Black dot conflict");
    }

    Constraint BoundsConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Pentamino.class)
                .filter((a) -> a.GetOutOfBounds() > 0)
                .penalize(HardSoftScore.ONE_HARD, Pentamino::GetOutOfBounds)
                .asConstraint("Bounds conflict");
    }
}
