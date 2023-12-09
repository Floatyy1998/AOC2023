package Day06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class One {
    public static void main(String[] args) throws IOException {
        List<Integer> winningStrats = new ArrayList<>();
        List<String> lines = Helper.ReadTxTToArray.readLinesString("Day06/input.txt");
        List<Integer> times = new ArrayList<>();
        List<Integer> ranges = new ArrayList<>();
        Arrays.asList(lines.get(0).split("\\s+")).forEach(s -> {
            if (!s.contains(":")) {
                times.add(Integer.parseInt(s));
            }
        });
        Arrays.asList(lines.get(1).split("\\s+")).forEach(s -> {
            if (!s.contains(":")) {
                ranges.add(Integer.parseInt(s));
            }
        });

        System.out.println(times);
        System.out.println(ranges);

        for (int i = 0; i < times.size(); i++) {

            winningStrats.add(getWinningStrats(times.get(i), ranges.get(i)));

        }
        System.out.println(winningStrats.stream().reduce(1, (a, b) -> a * b));

    }

    private static int getWinningStrats(int time, int range) {
        int counter = 0;
        for (int i = 1; i < time; i++) {
            int speed = i;
            int distance = speed * (time - i);
            if (distance > range) {
                counter++;
            }

        }
        return counter;

    }

}
