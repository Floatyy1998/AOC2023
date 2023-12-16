package Day13;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day13 {
    public static void main(String[] args) throws IOException {
        System.out.println("Part 1: " + new Day13().solve(true, Helper.ReadTxTToArray.readLinesStringwithBlanks("Day13/input.txt")));
        System.out.println("Part 2: " + new Day13().solve(false, Helper.ReadTxTToArray.readLinesStringwithBlanks("Day13/input.txt")));
    }

    public String solve(boolean part1, List<String> list) {
     
        long answer = 0;
        List<String> tmp = new ArrayList<>();
        List<Pattern> patterns = new ArrayList<>();
        for (String line : list) {
            
            if (line.equals("")) {
                patterns.add(new Pattern(tmp));
                tmp = new ArrayList<>();
            } else {
                tmp.add(line);
            }
        }
        patterns.add(new Pattern(tmp));
        for (Pattern pattern : patterns) {
            List<Long> vals = pattern.reflections(part1 ? 0 : 1);
            for (Long val : vals) {
                if(val < 0){
                    answer+= -100 * val;
                }
                else{
                    answer += val;
                }
            }
        }
        return answer + "";
    }

}

class Pattern {
    int[][] pattern;

    public Pattern(List<String> tmp) {
        pattern = new int[tmp.get(0).length()][tmp.size()];
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length; j++) {
                pattern[i][j] = tmp.get(j).charAt(i) == '.' ? 0 : 1;
            }
        }
    }

    public List<Long> reflections(int closeness) {
        List<Long> ret = new ArrayList<>();
        ret.addAll(vert(closeness));
        for(Long h: horiz(closeness)){
            ret.add(-1 * h);
        }
        return ret;
    }

    private List<Long> vert(int closeness) {
        List<Long> ret = new ArrayList<>();
        for (int i = 1; i < pattern.length; i++) {
            if(vertHelper(i, closeness)){
                ret.add((long) i);
            }
        }
        return ret;
    }

    private boolean vertHelper(int i, int closeness){
        boolean oneOff = false;
        for (int j = 0; j < pattern[0].length; j++) {
            if (i <= pattern.length / 2) {
                for (int k = 1; k <= i; k++) {
                    if (pattern[k - 1][j] != pattern[(2 * i + 1) - k - 1][j]) {
                        if(closeness == 0 || oneOff){
                            return false;
                        }
                        oneOff = true;
                    }
                }
            } else {
                for (int k = pattern.length; k > i; k--) {
                    if (pattern[k - 1][j] != pattern[(2 * i + 1) - k - 1][j]) {
                        if(closeness == 0 || oneOff){
                            return false;
                        }
                        oneOff = true;
                    }
                }
            }

        }
        return closeness == 0 || oneOff;
    }

    private List<Long> horiz(int closeness) {
        List<Long> ret = new ArrayList<>();
        for (int i = 1; i < pattern[0].length; i++) {
            if(horizHelper(i, closeness)){
                ret.add((long) i);
            }
        }
        return ret;
    }

    private boolean horizHelper(int i, int closeness){
        boolean oneOff = false;
        for (int j = 0; j < pattern.length; j++) {
            if (i <= pattern[0].length / 2) {
                for (int k = 1; k <= i; k++) {
                    if (pattern[j][k - 1] != pattern[j][(2 * i + 1) - k - 1]) {
                        if(closeness == 0 || oneOff){
                            return false;
                        }
                        oneOff = true;
                    }
                }
            } else {
                for (int k = pattern[0].length; k > i; k--) {
                    if (pattern[j][k - 1] != pattern[j][(2 * i + 1) - k - 1]) {
                        if(closeness == 0 || oneOff){
                            return false;
                        }
                        oneOff = true;
                    }
                }
            }
        }
        return closeness == 0 || oneOff;
    }
}
