package Day01;

import Helper.ReadTxTToArray;

public class Two {
    static String nums[] = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

    private static String wordsToNum(String str) {
        String num = "";
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                num += str.charAt(i);
                continue;
            }
            for (int j = 0; j < nums.length; j++) {
                if (str.substring(i).startsWith(nums[j])) {
                    num += j + 1;
                    i += nums[j].length() - 2;
                    break;
                }
            }
        }
        return num;
    }

    public static void main(String[] args) throws Exception {
        int sum = 0;
        for (String line : ReadTxTToArray.readLinesString("Day01/input.txt")) {
            String num = wordsToNum(line);
            sum += Integer.parseInt(num.charAt(0) + "" + num.charAt(num.length() - 1));
        }
        System.out.println(sum);

    }

}
