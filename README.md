# Strings searching

This program find specific strings in a large text, by several concurrent matchers.

The flow of the code is as follows:

1. The Searcher accepts txtFilePath, strTokens and startTime as arguments.
2. It uses the Divider to divide the large txt file into parts of 5000 lines each (optimal division).
3. Create Aggregator instance that will aggregates the results.
4. Iterate through the strTokens set and create threadPool the size of the number of parts.
5. Send the current strToken to the Matcher that return its location(s).
6. Add the results to the Aggregator and print it when done.


### *Text file* and *Strings tokens* inputs example: <br/>
#### [Large Text File](http://norvig.com/big.txt) <br/>
#### The 50 most common English first names (= strTokens):
James,John,Robert,Michael,William,David,Richard,Charles,Joseph,Thomas,
Christopher,Daniel,Paul,Mark,Donald,George,Kenneth,Steven,Edward,Brian,
Ronald,Anthony,Kevin,Jason,Matthew,Gary,Timothy,Jose,Larry,Jeffrey,
Frank,Scott,Eric,Stephen,Andrew,Raymond,Gregory,Joshua,Jerry,Dennis,
Walter,Patrick,Peter,Harold,Douglas,Henry,Carl,Arthur,Ryan,Roger

### *output* example: <br/>
![Output Example][output-example]


## Assumptions
* There is no sensitivity in the search between lowercase and uppercase.
* The string tokens input is a valid String array.

## Tests
Few junits added in the SearcherTest class.


## Running The Project
1. Download the SearchLongString zip folder.
2. Extract the Files from the downloaded file.
3. Open the folder [as IntelliJ IDEA project](https://www.jetbrains.com/help/idea/import-project-or-module-wizard.html).
4. (Usage) Update in the Main class *txtFilePath* to a valid (large) txt to search in
   and *strTokens* to looking for, for example: <br/>
   ```
    private static final String txtFilePath = "./LongText.txt";
    private static final String[] strTokens = {
                        "James","John","Robert","Michael","William","David","Richard","Charles","Joseph","Thomas",
                        "Christopher","Daniel","Paul","Mark","Donald","George","Kenneth","Steven","Edward","Brian",
                        "Ronald","Anthony","Kevin","Jason","Matthew","Gary","Timothy","Jose","Larry","Jeffrey",
                        "Frank","Scott","Eric","Stephen","Andrew","Raymond","Gregory","Joshua","Jerry","Dennis",
                        "Walter","Patrick","Peter","Harold","Douglas","Henry","Carl","Arthur","Ryan","Roger"
                        };
   ```
5. RUN.


## Contact

Tal Hazi <> [talhazi114@gmail.com](mailto:talhazi114@gmail.com)




[output-example]: ./output-example.png
