// This file is to help you test your code locally and 
// will not be run on ed

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class IslandMapTestTemplate {
    private IslandMap islandMap;

    private boolean findsBestPath(int start, int end, double prob) {
        return Math.abs(islandMap.bestChanceOfSurvival(start, end) - prob) < 1e-5;
    }

    // bridgeString should have the form "[a,b], [c,d], ..., [e,f]"
    // probs should have the form "p1, p2, ..., pn"
     void populateBridges(String bridgeString, String probs) {
        String[] bridgesArray = bridgeString.split("],");
        String[] probsArray = probs.split(",");

        assert bridgesArray.length == probsArray.length;

        for (int i = 0; i < bridgesArray.length; i++) {
            // Extract islands from string
            String[] currentBridgeIslands = bridgesArray[i]
                    .strip()
                    .replace("[", "")
                    .replace("]", "")
                    .split(",");

            // Extract probs from string
            double currentProb = Double.parseDouble(probsArray[i]);

            islandMap.bridges.add(
                    new Bridge(
                            currentProb,
                            Integer.parseInt(currentBridgeIslands[0]),
                            Integer.parseInt(currentBridgeIslands[1])
                    )
            );
        }
    }

    @BeforeEach
    void setUp() {
       islandMap = new IslandMap();
    }

    @Test
    void test1() {
        populateBridges("[0,1],[1,2],[0,2]", "0.5,0.5,0.2");
        assertTrue(findsBestPath(0, 2, 0.25));
    }

    @Test
    void test2() {
        populateBridges("[0,1],[1,2],[0,2]", "0.5,0.5,0.3");
        assertTrue(findsBestPath(0, 2, 0.3));
    }
}