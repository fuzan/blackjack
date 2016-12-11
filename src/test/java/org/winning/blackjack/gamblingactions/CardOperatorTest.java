package org.winning.blackjack.gamblingactions;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CardOperatorTest {

    public static final int CARD_NUMBER = 54;

    @Test
    public void generateRandomCard() throws Exception {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 10000000; i++) {
            int randomNumber = CardOperator.generateRandomCard(CARD_NUMBER);
            if (map.containsKey(randomNumber)) {
                map.replace(randomNumber, map.get(randomNumber) + 1);
            } else {
                map.put(CardOperator.generateRandomCard(CARD_NUMBER), 1);
            }
        }

        int max = 0;
        int min = Integer.MAX_VALUE;
        for (Integer key : map.keySet()) {
            max = map.get(key) > max ? map.get(key) : max;
            min = map.get(key) < min ? map.get(key) : min;
        }
        Assert.assertTrue(max - min < 3000);
    }
}