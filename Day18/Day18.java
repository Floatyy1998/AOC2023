package Day18;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day18 {
    
    public static void main(String[] args) throws IOException {
        System.out.println(solve(true, Helper.ReadTxTToArray.readLinesString("Day18/input.txt")));
        System.out.println(solve(false, Helper.ReadTxTToArray.readLinesString("Day18/input.txt")));
    }

    public static String solve(boolean part1, List<String> data) {
               long answer = 0;
        List<Instruction> instructions = new ArrayList<>();
        int max = 0;
        for (String line : data){
            instructions.add(new Instruction(line, part1));
            max += instructions.get(instructions.size() - 1).distance;
        }
        List<Long> xVals = new ArrayList<>();
        List<Long> yVals = new ArrayList<>();
        long x = 0;
        long y = 0;
        int[] xs = new int[]{1, 0, -1, 0};
        int[] ys = new int[]{0, -1, 0, 1};
        for (Instruction instruction : instructions) {
            x += instruction.distance * xs[instruction.direction];
            y += instruction.distance * ys[instruction.direction];
            xVals.add(x);
            yVals.add(y);
        }
        for (int i = 0; i < xVals.size(); i++) {
            answer -= xVals.get(i) * yVals.get((i + 1) % xVals.size());
            answer += xVals.get((i + 1) % xVals.size()) * yVals.get(i);
        }
        answer /= 2;
        answer += max / 2 + 1;
        return answer + "";
    }
}

class Instruction {
    int distance;
    int direction;
    public Instruction(String line, boolean part1) {
        Map<String, Integer> map = Map.of("R", 0, "D", 1, "L", 2, "U",3);
        if (part1) {
            distance = Integer.parseInt(line.split(" ")[1]);
            direction = map.get(line.split(" ")[0]);
        } else {
            String hex = line.split("[#)]")[1];
            direction = Integer.parseInt(hex.substring(hex.length() - 1));
            distance = Integer.parseInt(hex.substring(0, hex.length() - 1), 16);
        }
    }
}

