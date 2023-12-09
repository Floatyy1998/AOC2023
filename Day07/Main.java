package Day07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        long answer = 0;
        List<Hand> hands = new ArrayList<>();
        List<String> lines = Helper.ReadTxTToArray.readLinesString("Day07/input.txt");
       for (String line : lines) {
            hands.add(new Hand(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]), false));
        }

        Collections.sort(hands);
        for (int i = 0; i < hands.size(); i++) {
            answer += hands.get(i).bid * (i + 1);
        }
        System.out.println(answer);

    }
}
