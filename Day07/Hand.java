package Day07;

import java.util.Arrays;

public class Hand implements Comparable<Hand> {
  int bid;
    int strength;
    int[] freqs = new int[13];
    int[] cards = new int[5];
    String[] ranks= new String[]{"A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2"};

    public Hand(String line, int bid, boolean part1) {
        if (!part1) {
            ranks = new String[]{"A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2", "J"};
        }
        this.bid = bid;
        int numJokers = 0;
        String[] s = line.split("");
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                if (s[i].equals(ranks[j])) {
                    if (!s[i].equals("J") || part1) {
                        freqs[j]++;
                    }
                    if (s[i].equals("J") && !part1) {
                        numJokers++;
                    }
                    cards[i] = j;
                }
            }
        }
        Arrays.sort(freqs);
        freqs[freqs.length - 1] += numJokers;
        strength = 2 * freqs[freqs.length - 1];
        if (freqs[freqs.length - 2] == 2) {
            strength += 1; //for full house and two pair
        }
    }

    @Override
    public int compareTo(Hand o) {
        Hand other = (Hand) o;
        if (strength != other.strength) {
            return strength - other.strength;
        } else {
            for (int i = 0; i < cards.length; i++) {
                if (cards[i] != other.cards[i]) {
                    return other.cards[i] - cards[i];
                }
            }
            return 0;
        }
    }

  
}