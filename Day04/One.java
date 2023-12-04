package Day04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class One {
    public static void main(String[] args) throws IOException {
        List<String> inputData = Helper.ReadTxTToArray.readLinesString("Day04/input.txt");
        List<Integer> sumValues = new ArrayList<>();

        for (String string : inputData) {
            String myNumbersString = string.split(":")[1].trim().split("\\|")[0].trim();
            String[] myNumbers = myNumbersString.split(" ");
            String winningNumbersString = string.split(":")[1].trim().split("\\|")[1].trim();
            String[] winningNumbers = winningNumbersString.split(" ");
            List<String> myNumbersList = new ArrayList<>();
            List<String> winningNumbersList = new ArrayList<>();

            for (String s : myNumbers) {
                int counter = 0;
                if (!s.isEmpty()) {
                    myNumbersList.add(s);
                }

            }
            for (String s : winningNumbers) {
                if (!s.isEmpty()) {
                    winningNumbersList.add(s);
                }
            }

            myNumbersList.retainAll(winningNumbersList);
            sumValues.add((int) Math.pow(2, myNumbersList.size() - 1));

        }
        System.out.println(sumValues.stream().reduce(0, Integer::sum));
    }
}
