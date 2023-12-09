package Day02;

import java.io.IOException;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Two {
    static Map<String, Integer> maxCubes = Map.of("red", 12, "green", 13, "blue", 14);
    static Map<String, Integer> minCubes = new HashMap<>();

    private static Map<String, Integer> loadNewMap() {
        Map<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put("red", 0);
        scoreMap.put("blue", 0);
        scoreMap.put("green", 0);
        return scoreMap;
    }

    public static void play(List<String> inputData) {

        List<Integer> powerValues = new ArrayList<>();
        for (String str : inputData) {
            String[] gameData = str.split(": ");

            String gameSet = gameData[1];
            Map<String, Integer> scoreMap = loadNewMap();
            for (String game : gameSet.split("; ")) {

                for (String gameInput : game.split(", ")) {
                    String[] rawCube = gameInput.split(" ");
                    Integer cubeValue = Integer.valueOf(rawCube[0]);
                    if (scoreMap.get(rawCube[1]) < cubeValue) {
                        scoreMap.put(rawCube[1], cubeValue);
                    }
                }
            }

            var product = scoreMap.values().stream().reduce(1, (a, b) -> a * b);
            powerValues.add(product);
        }

        System.out.println(powerValues.stream().reduce(0, Integer::sum).toString());

    }

    public static void main(String[] args) throws IOException {
        List<String> games = Helper.ReadTxTToArray.readLinesString("Day02/input.txt");
        play(games);
    }

}
