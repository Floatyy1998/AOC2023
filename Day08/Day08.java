package Day08;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day08 {
    public static void main(String[] args) throws IOException {
        System.out.println(
                "Part 1: " + new Day08().solve(true, Helper.ReadTxTToArray.readLinesString("Day08/input.txt")));
        System.out.println(
                "Part 2: " + new Day08().solve(false, Helper.ReadTxTToArray.readLinesString("Day08/input.txt")));
    }

    public String solve(boolean part1, List<String> list) {
        long answer = 0;
        List<String> currSteps = new ArrayList<>();
        Map<String, Step> map = new HashMap<>();
        String[] instructions = list.get(0).split("");

        for (int i = 1; i < list.size(); i++) {
            Step step = new Step(list.get(i));
            map.put(step.name, step);
            if (step.name.substring(2).equals("A")) {
                if (step.name.equals("AAA") || !part1) {
                    currSteps.add(step.name);
                }
            }
        }
        int index = 0;
        int[] loops = new int[currSteps.size()];
        while (!check(currSteps, index, loops, part1)) {
            for (int i = 0; i < currSteps.size(); i++) {
                Step step = map.get(currSteps.get(i));
                if (instructions[index % instructions.length].equals("L")) {
                    currSteps.set(i, step.left);
                } else {
                    currSteps.set(i, step.right);
                }
            }
            index++;
        }
        answer = lcm(loops);
        return answer + "";
    }

    private boolean check(List<String> currSteps, int index, int[] loops, boolean part1) {
        boolean ret = true;
        for (int i = 0; i < currSteps.size(); i++) {
            if (loops[i] != 0) {
                continue;
            }
            if ((currSteps.get(i).substring(2).equals("Z") && !part1) || currSteps.get(i).equals("ZZZ")) {
                loops[i] = index;
            } else {
                ret = false;
            }
        }
        return ret;
    }

    private long lcm(int[] loops) {
        long lcm = 1;
        for (int loop : loops) {
            lcm = lcm * loop / gcd(lcm, loop);
        }
        return lcm;
    }

    private long gcd(long a, long b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}

class Step {
    String left;
    String right;
    String name;

    public Step(String line) {
        left = line.split("\\(|,")[1].trim();
        right = line.split(",|\\)")[1].trim();
        name = line.split("=")[0].trim();
    }
}
