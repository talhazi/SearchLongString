import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * this class allowing search of {@link #strToken} in txt file,
 * and allowing the search in different parts of the file in parallel
 */
class Matcher implements Callable<List<tokenLocation>> {
    private final List<String> part;  // single part of the main file
    private final String strToken;
    private int lineOffset;

    /**
     * @param part          part of file (as a list of string lines) to search in
     * @param strToken      string token to look for in part
     * @param lineOffset    the relative lineOffset to the entire file
     */
    public Matcher(List<String> part, String strToken, int lineOffset){
        this.part = part;
        this.strToken = strToken;
        this.lineOffset = lineOffset;
    }

    /**
     * @param part          part of file (as a list of string lines) to search in
     * @param strToken      string token to look for in part
     */
    public Matcher(List<String> part, String strToken){
        this.part = part;
        this.strToken = strToken;
        this.lineOffset = 0;
    }

    /**
     * @return  list of all {@link tokenLocation} of strToken in part
     */
    @Override
    public List<tokenLocation> call() {
        List<tokenLocation> tokenLocations = new ArrayList<>();
        List<Integer> charOffsets;
        for (String line : part) {
            lineOffset++;
            charOffsets = findStrTokenOffsets(line, strToken);
            for(Integer charOffset : charOffsets) {
                tokenLocations.add(new tokenLocation(lineOffset, charOffset));
            }
        }
        return tokenLocations;
    }

    /**
     * @param strLine   string (as a line) to search in
     * @param strToken  string token to look for in strLine
     * @return  list of the appearances (as charOffset) of strToken in strLine
     */
    private List<Integer> findStrTokenOffsets(String strLine, String strToken) {
        List<Integer> offsets = new ArrayList<>();
        String lowerCaseStrLine = strLine.toLowerCase();
        String lowerCaseStrToken = strToken.toLowerCase();
        int lastOffset = 0;
        while(lastOffset != -1) {
            lastOffset = lowerCaseStrLine.indexOf(lowerCaseStrToken,lastOffset);
            if(lastOffset != -1){
                offsets.add(lastOffset + 1);
                lastOffset += strToken.length();
            }
        }
        return offsets;
    }

}