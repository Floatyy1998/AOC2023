package Day01;

import java.util.ArrayList;
import java.util.List;

import Helper.ReadTxTToArray;

public class One {
    public static void main(String[] args) throws Exception {
        List<String> list = ReadTxTToArray.readLinesString("Day01/input.txt");
        List<Integer> listInt = new ArrayList<>();
        list.forEach(line -> {
            List<String> list2 = new ArrayList<>();

            for (int i = 0; i < line.length() - 1; i++) {
                if (Character.isDigit(line.charAt(i))) {
                    list2.add(String.valueOf(line.charAt(i)));
                    break;
                }

            }
            for (int i = line.length() - 1; i >= 0; i--) {
                if (Character.isDigit(line.charAt(i))) {
                    list2.add(String.valueOf(line.charAt(i)));
                    break;
                }
            }
            if (list2.size() == 2) {
                listInt.add(Integer.parseInt(list2.get(0) + list2.get(1)));
            } else {
                listInt.add(Integer.parseInt(list2.get(0) + list2.get(0)));
            }
        });
        System.out.println(listInt);
        System.out.println(listInt.stream().mapToInt(Integer::intValue).sum());
    }

}
