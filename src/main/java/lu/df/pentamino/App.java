package lu.df.pentamino;

import java.time.Duration;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Iterator;

import javax.imageio.ImageIO;

import ai.timefold.solver.core.api.solver.Solver;
import ai.timefold.solver.core.api.solver.SolverFactory;
import ai.timefold.solver.core.config.solver.SolverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(Field.class);

    public static void main(String[] args) throws Exception {
        Field field = new Field(ReadConfig("config.json"));
        int secs = field.Pentaminoes.length * 2 + 10;

        SolverFactory<Field> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(Field.class)
                .withEntityClasses(Pentamino.class)
                .withConstraintProviderClass(FieldConstraintProvider.class)
                .withTerminationSpentLimit(Duration.ofSeconds(secs)));
        Solver<Field> solver = solverFactory.buildSolver();

        Field solution = solver.solve(field);
        ImageIO.write(solution.ToImage(), "png", new File("output.png"));
    }

    private static int[][] ReadConfig(String name) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File(name));
        int x = rootNode.get("x").asInt();
        int y = rootNode.get("y").asInt();
        Iterator<JsonNode> blocks = rootNode.get("blocks").iterator();
        int[][] array2D = new int[y][x];

        while (blocks.hasNext()) {
            JsonNode block = blocks.next();
            int blockX = block.get("x").asInt();
            int blockY = block.get("y").asInt();
            array2D[blockY][blockX] = -1;
        }
        return array2D;
    }
}
