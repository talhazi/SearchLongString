import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * this class allowing division of large txt file into parts
 */
 class Divider {

    Divider(){
    }

    /**
     * @param filePath  path of txt file to divide into parts
     * @param partSize  size of each part
     * @return  list of parts (as a list of string lines) of filePath
     */
     List<List<String>> divideIntoParts(String filePath, int partSize) {
        try {
            List<String> allFileByLines = Files.readAllLines(Paths.get(filePath));
            int numOfParts = (allFileByLines.size() + partSize - 1) / partSize; // round-up
            return IntStream.range(0, numOfParts)
                    .mapToObj(i -> allFileByLines.subList(partSize * i, Math.min(partSize * i + partSize, allFileByLines.size())))
                    .collect(Collectors.toList());
        }
        catch (IOException e){
            System.out.println(e.getMessage() + " not found or isn't txt file!");
            System.exit(0);
        }
        return null;
    }

}
