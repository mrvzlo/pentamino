package lu.df.pentamino;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ai.timefold.solver.persistence.common.api.domain.solution.SolutionFileIO;

public class FieldFileManager implements SolutionFileIO<Field> {

    @Override
    public String getInputFileExtension() {
        return "json";
    }

    @Override
    public Field read(File inputSolutionFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(inputSolutionFile);
        } catch (IOException e) {
            return new Field();
        }
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
        return new Field(array2D);
    }

    @Override
    public void write(Field field, File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        fileName = fileName.substring(0, dotIndex) + ".png";

        try {
            ImageIO.write(field.ToImage(), "png", new File(fileName));
        } catch (IOException e) {
        }
    }
}
