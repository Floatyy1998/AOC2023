package Day02;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class One {
    static Map<String, Integer> maxCubes = Map.of("red", 12, "green", 13, "blue", 14);
    static Map<String, Integer> minCubes = new HashMap<>();

    private static Map<String, Integer> loadNewMap() {
        Map<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put("red", 0);
        scoreMap.put("blue", 0);
        scoreMap.put("green", 0);
        return scoreMap;
    }

    private static Boolean checkMap(Map<String, Integer> scoreMap) {
        if (scoreMap.get("red") > 12) {
            return false;
        }
        if (scoreMap.get("green") > 13) {
            return false;
        }
        if (scoreMap.get("blue") > 14) {
            return false;
        }
        return true;
    }

    public static void play(List<String> inputData) {
        List<Integer> sumValues = new ArrayList<>();
        for (String str : inputData) {
            String[] gameData = str.split(": ");
            var strGameId = gameData[0].split(" ");
            Integer gameID = Integer.valueOf(strGameId[1]);

            String gameSet = gameData[1];
            boolean gameFlag = true;
            for (String game : gameSet.split("; ")) {
                Map<String, Integer> scoreMap = loadNewMap();
                for (String gameInput : game.split(", ")) {
                    String[] rawCube = gameInput.split(" ");
                    scoreMap.put(rawCube[1], scoreMap.get(rawCube[1]) + Integer.parseInt(rawCube[0]));
                }
                if (!checkMap(scoreMap)) {
                    gameFlag = false;
                    break;
                }
            }
            if (gameFlag) {
                sumValues.add(gameID);
            }
        }
        System.out.println(sumValues.stream().reduce(0, Integer::sum));

    }

    public static void main(String[] args) throws IOException {
        List<String> games = Helper.ReadTxTToArray.readLinesString("Day02/input.txt");

        play(games);
    }

}
