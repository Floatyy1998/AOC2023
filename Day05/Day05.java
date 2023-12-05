package Day05;

import java.util.ArrayList;
import java.util.List;

public class Day05 {

    public static void main(String[] args) throws Exception {
        Day05 day05 = new Day05();
        day05.solve(true, Helper.ReadTxTToArray.readLinesString("Day05/input.txt"));
        day05.solve(false, Helper.ReadTxTToArray.readLinesString("Day05/input.txt"));
    }

    public void solve(boolean part1, List<String> input) {
        long answer = 10000000000L;
        List<RangeMap> rangeMaps = new ArrayList<>();
        List<Range> tmp = new ArrayList<>();
        String[] stringSeeds = input.get(0).split(" ");
        long[] seeds = new long[stringSeeds.length - 1];
        for (int i = 1; i < stringSeeds.length; i++) {
            seeds[i - 1] = Long.parseLong(stringSeeds[i]);
        }
        for (int i = 1; i < input.size(); i++) {
            String line = input.get(i);
            if (line.equals("")) {
                continue;
            }
            if (line.contains("map")) {
                if (tmp.size() > 0) {
                    rangeMaps.add(new RangeMap(tmp));
                }
                tmp = new ArrayList<>();
            } else {
                tmp.add(new Range(line));
            }
        }
        rangeMaps.add(new RangeMap(tmp));
        if (part1) {
            for (Long seed : seeds) {
                long val = seed;
                for (RangeMap rangeMap : rangeMaps) {
                    val = rangeMap.convert(val);
                }
                if (val < answer) {
                    answer = val;
                }
            }
        } else {
            for (int i = 0; i < seeds.length; i += 2) {
                for (long j = seeds[i]; j < seeds[i] + seeds[i + 1]; j++) {
                    long[] ret = returnValAndBound(j, rangeMaps);
                    if (ret[0] < answer) {
                        answer = ret[0];
                    }
                    j += ret[1];
                }
            }
        }
        System.out.println(answer + "");
    }

    private long[] returnValAndBound(long val, List<RangeMap> rangeMaps) {
        long bound = 10000000000L;
        for (RangeMap rangeMap : rangeMaps) {
            bound = Math.min(bound, rangeMap.convert2(val)[1]);
            val = rangeMap.convert2(val)[0];
        }
        return new long[] { val, bound };
    }
}

class Range {
    long destination;
    long source;
    long range;

    public Range(long des, long src, long r) {
        destination = des;
        source = src;
        range = r;
    }

    public Range(String line) {
        String[] pieces = line.split(" ");
        destination = Long.parseLong(pieces[0]);
        source = Long.parseLong(pieces[1]);
        range = Long.parseLong(pieces[2]);
    }
}

class RangeMap {
    List<Long> starts;
    List<Long> ends;
    List<Long> betweens;

    public RangeMap(List<Range> ranges) {
        starts = new ArrayList<>();
        ends = new ArrayList<>();
        betweens = new ArrayList<>();
        for (Range range : ranges) {
            starts.add(range.source);
            ends.add(range.destination);
            betweens.add(range.range);
        }
    }

    public long convert(long val) {
        for (int i = 0; i < starts.size(); i++) {
            if (starts.get(i) <= val && starts.get(i) + betweens.get(i) > val) {
                return ends.get(i) + (val - starts.get(i));
            }
        }
        return val;
    }

    public long[] convert2(long val) {
        long nextStart = 10000000000L;
        for (int i = 0; i < starts.size(); i++) {
            if (starts.get(i) > val) {
                nextStart = Math.min(nextStart, starts.get(i) - val - 1);
            }
            if (starts.get(i) <= val && starts.get(i) + betweens.get(i) > val) {
                return new long[] { ends.get(i) + (val - starts.get(i)), betweens.get(i) - (val - starts.get(i)) - 1 };
            }
        }
        return new long[] { val, nextStart == 10000000000L ? 0 : nextStart };
    }
}