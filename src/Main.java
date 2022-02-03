/**
 * @author Tal Hazi
 * @version 1.0
 * @since Feb 3, 2022
 */
public class Main {
    private static final String txtFilePath = "./LongText.txt";
    private static final String[] strTokens = {
                        "James","John","Robert","Michael","William","David","Richard","Charles","Joseph","Thomas",
                        "Christopher","Daniel","Paul","Mark","Donald","George","Kenneth","Steven","Edward","Brian",
                        "Ronald","Anthony","Kevin","Jason","Matthew","Gary","Timothy","Jose","Larry","Jeffrey",
                        "Frank","Scott","Eric","Stephen","Andrew","Raymond","Gregory","Joshua","Jerry","Dennis",
                        "Walter","Patrick","Peter","Harold","Douglas","Henry","Carl","Arthur","Ryan","Roger"
                        };

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Searcher searcher = new Searcher(txtFilePath, strTokens, startTime);
        searcher.run();
        Aggregator.getInstance().display();
        long totalSearchTime = System.currentTimeMillis() - startTime;
        System.out.println("\n==> Total Time of Search: " + totalSearchTime + " ms <==");
    }

}

