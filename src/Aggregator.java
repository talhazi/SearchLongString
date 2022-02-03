import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this class aggregates the all the {@link #tokensLocations},
 * implemented as a singleton to promise one instance
 */
 class Aggregator {
    private static Aggregator instance = null;
    private Map<String,List<tokenLocation>> tokensLocations;

    private Aggregator() {
        tokensLocations = new HashMap<>();
    }

    static Aggregator getInstance(){
        if(instance == null){
            instance = new Aggregator();
        }
        return instance;
    }

    /**
     * @param strToken              string token that was searched
     * @param appearances           all appearances of strToken in the whole file (as a list of {@link tokenLocation})
     * @param tokenSearchRuntime    the runtime of searching strToken in the whole file in ms (should be added if needed)
     * add new token locations of strToken (as a list of {@link tokenLocation}) to {@link #tokensLocations}
     */
     void addTokenLocations(String strToken, List<tokenLocation> appearances, long tokenSearchRuntime){
        tokensLocations.put(strToken, appearances);
    }

    /**
     * print {@link #tokensLocations} to stdout
     */
     void display(){
        tokensLocations.forEach((key, value) -> System.out.println(key + " --> " + value));
    }

     Map<String, List<tokenLocation>> getTokensLocations() {
        return tokensLocations;
    }
}
