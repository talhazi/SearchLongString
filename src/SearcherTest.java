import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class SearcherTest {
    private static final String txtFilePath = "./LongText.txt";
    private static final String[] strTokens = {
            "James","John","Robert","Michael","William","David","Richard","Charles","Joseph","Thomas",
            "Christopher","Daniel","Paul","Mark","Donald","George","Kenneth","Steven","Edward","Brian",
            "Ronald","Anthony","Kevin","Jason","Matthew","Gary","Timothy","Jose","Larry","Jeffrey",
            "Frank","Scott","Eric","Stephen","Andrew","Raymond","Gregory","Joshua","Jerry","Dennis",
            "Walter","Patrick","Peter","Harold","Douglas","Henry","Carl","Arthur","Ryan","Roger"
    };

    @Test
    public void testSize() {
        Searcher searcher = new Searcher(txtFilePath, strTokens, 0);
        searcher.run();
        Map<String, List<tokenLocation>> aggregatorCopy = Aggregator.getInstance().getTokensLocations();
        assertThat(aggregatorCopy.size(), is(50));
    }

    @Test
    public void testContainsKey1() {
        Map<String, List<tokenLocation>> aggregatorCopy = Aggregator.getInstance().getTokensLocations();
        assertTrue(aggregatorCopy.containsKey("Timothy"));
    }

    @Test
    public void testValueOfKey1() {
        Map<String, List<tokenLocation>> aggregatorCopy = Aggregator.getInstance().getTokensLocations();
        List<tokenLocation> tokenLocationList = new ArrayList<>();
        tokenLocation tokenLocation1 = new tokenLocation(13388, 1);
        tokenLocation tokenLocation2 = new tokenLocation(13752, 30);
        tokenLocation tokenLocation3 = new tokenLocation(128405, 352);
        tokenLocationList.add(tokenLocation1);
        tokenLocationList.add(tokenLocation2);
        tokenLocationList.add(tokenLocation3);
        assertEquals(tokenLocationList.toString(), aggregatorCopy.get("Timothy").toString());
    }

    @Test
    public void testContainsKey2() {
        Map<String, List<tokenLocation>> aggregatorCopy = Aggregator.getInstance().getTokensLocations();
        assertTrue(aggregatorCopy.containsKey("Joshua"));
    }

    @Test
    public void testValueOfKey2() {
        Map<String, List<tokenLocation>> aggregatorCopy = Aggregator.getInstance().getTokensLocations();
        List<tokenLocation> tokenLocationList = new ArrayList<>();
        tokenLocation tokenLocation1 = new tokenLocation(121345, 12);
        tokenLocation tokenLocation2 = new tokenLocation(128314, 508);
        tokenLocationList.add(tokenLocation1);
        tokenLocationList.add(tokenLocation2);
        assertEquals(tokenLocationList.toString(), aggregatorCopy.get("Joshua").toString());
    }

    @Test
    public void testNotContainsKey1() {
        Map<String, List<tokenLocation>> aggregatorCopy = Aggregator.getInstance().getTokensLocations();
        assertFalse(aggregatorCopy.containsKey("Tal"));
    }

    @Test
    public void testNotContainsKey2() {
        Map<String, List<tokenLocation>> aggregatorCopy = Aggregator.getInstance().getTokensLocations();
        assertFalse(aggregatorCopy.containsKey("Joni"));
    }

}
