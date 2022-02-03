import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * this class allowing search of {@link #strTokens} in {@link #txtFilePath},
 * optimize the search process by dividing the file into parts and searching
 * for one token in all the parts in parallel
 */
public class Searcher {
    private final String txtFilePath;
    private final HashSet<String> strTokens; // tokens as a set to avoid duplicates
    private final long startTime;
    private final int partSize;

    /**
     * @param txtFilePath   path of txt file to search in
     * @param strTokens     string tokens to looking for in txtFilePath
     * @param startTime     the starting of searching in ms
     */
    public Searcher(String txtFilePath, String[] strTokens, long startTime) {
        this.txtFilePath = txtFilePath;
        this.strTokens = Stream.of(strTokens).collect(Collectors.toCollection(HashSet::new));
        this.partSize = 5000;
        this.startTime = startTime;
    }

    /**
     * run the whole process: divide {@link #txtFilePath} into parts >
     *                        create instance of {@link Aggregator} >
     *                        create threadPool the size of the number of parts >
     *                        send each strToken to {@link Matcher} >
     *                        update the {@link Aggregator} with the results
     */
    void run() {
        List<List<String>> allFileByParts = new Divider().divideIntoParts(txtFilePath, partSize);
        int numOfParts = allFileByParts.size();
        Aggregator aggregator = Aggregator.getInstance();
        for (String strToken : strTokens) {
            ExecutorService executor = Executors.newFixedThreadPool(numOfParts);
            List<Future> futures = new ArrayList<>();
            int lineCounter = 0;
            for (List<String> part : allFileByParts) {
                Future<List<tokenLocation>> future = executor.submit(new Matcher(part, strToken, lineCounter)); //send process to thread
                lineCounter += partSize;
                futures.add(future);
            }
            List<tokenLocation> tokenLocations = getLocations(futures);
            long tokenSearchRuntime = System.currentTimeMillis() - startTime;
            aggregator.addTokenLocations(strToken, tokenLocations, tokenSearchRuntime);
            executor.shutdownNow();
        }
    }

    /**
     * @param futures   list of Future, that each of them contains the ret from the asynchronous {@link Matcher} task
     * @return list of {@link tokenLocation}, that indicates the locations of single strToken in the file
     */
    public List<tokenLocation> getLocations(List<Future> futures) {
        try {
            List<tokenLocation> appearances = new ArrayList<>();
            for (Future<List<tokenLocation>> future : futures) {
                List<tokenLocation> tokenLocation = future.get();
                if (tokenLocation.size() > 0) {
                    appearances.addAll(tokenLocation);
                }
            }
            return appearances;
        }
        catch (InterruptedException | ExecutionException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return null;
    }
}
