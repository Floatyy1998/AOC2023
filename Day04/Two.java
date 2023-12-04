package Day04;

import java.io.IOException;

import java.util.Arrays;

import java.util.HashSet;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Two {
    public static void main(String[] args) throws IOException {
        partTwo();

    }

    private static Map<Integer, CardSet> getCards() throws IOException {
        return Helper.ReadTxTToArray.readLinesString("Day04/input.txt").stream().map(Card::create)
                .collect(Collectors.toMap(card -> card.number, CardSet::new));
    }

    public static void partTwo() throws IOException {
        Map<Integer, CardSet> cards = getCards();

        for (Map.Entry<Integer, CardSet> entry : cards.entrySet()) {
            CardSet set = entry.getValue();
            Set<Integer> winningNumbers = set.card.getWinningNumbers();

            for (int i = set.card.number + 1; i < set.card.number + 1 + winningNumbers.size(); i++) {
                for (int j = 0; j < set.count; j++) {
                    cards.get(i).incrementCount();
                }
            }
        }

        System.out.println(String.valueOf(cards.values().stream().map(set -> set.count).reduce(0, Integer::sum)));
    }

    private record Card(int number, Set<Integer> winning, Set<Integer> have) {
        static Card create(String line) {
            String[] split = line.split(":");
            String[] items = split[1].trim().split("\\|");
            int number = Integer.parseInt(split[0].replaceAll("\s+", "\s").split(" ")[1]);
            Set<Integer> winning = Arrays.stream(items[0].trim().replaceAll("\s+", "\s").split(" "))
                    .map(Integer::parseInt).collect(Collectors.toSet());
            Set<Integer> have = Arrays.stream(items[1].trim().replaceAll("\s+", "\s").split(" ")).map(Integer::parseInt)
                    .collect(Collectors.toSet());
            return new Card(number, winning, have);
        }

        public Set<Integer> getWinningNumbers() {
            Set<Integer> intersection = new HashSet<>(have);
            intersection.retainAll(winning);
            return intersection;
        }
    }

    private static final class CardSet {
        private final Card card;
        private int count;

        public CardSet(Card card) {
            this.card = card;
            count = 1;
        }

        public void incrementCount() {
            count++;
        }
    }
}
