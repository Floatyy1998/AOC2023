package Day09;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day09 {
    public static void main(String[] args) throws IOException {
        System.out.println(
                "Part 1: " + new Day09().solve(true, Helper.ReadTxTToArray.readLinesString("Day09/input.txt")));
        System.out.println(
                "Part 2: " + new Day09().solve(false, Helper.ReadTxTToArray.readLinesString("Day09/input.txt")));
    }

    public String solve(boolean part1, List<String> list) {
        long answer = 0;
        for (String line : list) {
            List<Integer> sequence = new ArrayList<>();
            for (String item : line.split(" ")) {
                sequence.add(Integer.parseInt(item));
            }
            if (!part1) {
                Collections.reverse(sequence);
            }
            answer += helper(sequence);
        }
        return answer + "";
    }

    private int helper(List<Integer> sequence) {
        if (checker(sequence)) {
            return sequence.get(0);
        } else {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < sequence.size() - 1; i++) {
                tmp.add(sequence.get(i + 1) - sequence.get(i));
            }
            return sequence.get(sequence.size() - 1) + helper(tmp);
        }
    }

    private boolean checker(List<Integer> sequence) {
        int first = sequence.get(0);
        for (Integer i : sequence) {
            if (i != first) {
                return false;
            }
        }
        return true;
    }
}
