package Day11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Helper.Coordinate;

public class Day11 {
    public static void main(String[] args) throws IOException {
        System.out.println(
                "Part 1: " + new Day11().solve(true, Helper.ReadTxTToArray.readLinesString("Day11/input.txt")));
        System.out.println(
                "Part 2: " + new Day11().solve(false, Helper.ReadTxTToArray.readLinesString("Day11/input.txt")));
    }

    public String solve(boolean part1, List<String> list) {
       long answer = 0;
        List<List<String>> space = new ArrayList<>();
        int index = 0;
        List<Coordinate> galaxies = new ArrayList<>();
        List<Integer> emptyV = new ArrayList<>();
        List<Integer> emptyH = new ArrayList<>();
        for (String nextLine : list) {
            List<String> tmp = new ArrayList<>();
            String[] line = nextLine.split("");
            for (int i = 0; i < line.length; i++) {
                if (line[i].equals("#")) {
                    galaxies.add(new Coordinate(i, index));
                }
                tmp.add(line[i]);
            }
            if (!tmp.contains("#")) {
                emptyH.add(index);
            }
            index++;
            space.add(tmp);
        }
        for (int i = 0; i < space.get(0).size(); i++) {
            boolean noGalaxy = true;
            for (int j = 0; j < space.size(); j++) {
                if (space.get(j).get(i).equals("#")) {
                    noGalaxy = false;
                    break;
                }
            }
            if (noGalaxy) {
                emptyV.add(i);
            }
        }
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                Coordinate g1 = galaxies.get(i);
                Coordinate g2 = galaxies.get(j);
                answer += Math.abs(g1.x - g2.x) + Math.abs(g1.y - g2.y);
            }
        }
        for(Integer ind: emptyV){
            int left = 0;
            for(Coordinate g: galaxies){
                if(g.x < ind){
                    left++;
                }
            }
            answer+= left * (galaxies.size() - left) * (part1 ? 1 : 999999L);
        }
        for(Integer ind: emptyH){
            int up = 0;
            for(Coordinate g: galaxies){
                if(g.y < ind){
                    up++;
                }
            }
            answer+= up * (galaxies.size() - up) * (part1 ? 1 : 999999L);
        }
        return answer + "";
    }
}