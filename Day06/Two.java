package Day06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Two {
    public static void main(String[] args) throws IOException {

        List<String> lines = Helper.ReadTxTToArray.readLinesString("Day06/input.txt");
        List<String> times = new ArrayList<>();
        List<String> ranges = new ArrayList<>();
        Arrays.asList(lines.get(0).split("\\s+")).forEach(s -> {
            if (!s.contains(":")) {
                times.add(s);
            }
        });
        Arrays.asList(lines.get(1).split("\\s+")).forEach(s -> {
            if (!s.contains(":")) {
                ranges.add(s);
            }
        });

        String time = String.join("", times);
        String range = String.join("", ranges);

        System.out.println(getWinningStrats(Long.parseLong(time), Long.parseLong(range)));

    }

    private static long getWinningStrats(long time, long range) {
        int counter = 0;
        for (long i = 1; i < time; i++) {
            long speed = i;
            long distance = speed * (time - i);
            if (distance > range) {
                counter++;
            }

        }
        return counter;

    }

}
