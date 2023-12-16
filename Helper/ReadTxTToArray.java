package Helper;

import java.io.*;
import java.util.*;

public class ReadTxTToArray {
    private ReadTxTToArray() {

    }

    public static List<Integer> readLinesInt(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String line;
        List<String> list = new ArrayList<>();
        List<Integer> listInt = new ArrayList<>();

        while ((line = in.readLine()) != null) {
            if (!line.isEmpty() && !line.equals("")) {
                list.add(line);
            } else {
                list.add("-1");
            }
        }
        in.close();

        String[] lines = (String[]) list.toArray(new String[0]);

        for (String s : lines) {
            listInt.add(Integer.parseInt(s));
        }
        return listInt;
    }

    public static List<String> readLinesString(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String line;
        List<String> list = new ArrayList<>();

        while ((line = in.readLine()) != null) {
            if (!line.isEmpty() && !line.equals("")) {
                list.add(line);
            }
        }
        in.close();

        return list;
    }

    public static List<String> readLinesStringwithBlanks(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String line;
        List<String> list = new ArrayList<>();

        while ((line = in.readLine()) != null) {

            list.add(line);

        }
        in.close();

        return list;
    }
}
