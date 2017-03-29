package org.winning.blackjack.util.card;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

public class RandomIntegerUtil {

    public static Integer getNextInteger() {
        return RandomUtils.nextInt(0, Integer.MAX_VALUE);
    }

    public static Integer getNextInteger(int min, int max) {
        return RandomUtils.nextInt(min, max);
    }

    public static Integer getNextInteger(long seed){
        final Random random = new Random(seed);
        return random.nextInt();
    }

    public static void main(String[] args) {
        System.out.println(getNextInteger());
        System.out.println(getNextInteger(10,100));
        System.out.println(getNextInteger(System.currentTimeMillis()));
    }
}
