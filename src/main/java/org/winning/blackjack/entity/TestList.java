package org.winning.blackjack.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestList {

    public static void main(String[] args) {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        //list.stream().forEach( a -> System.out.print(a));
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) {
            Integer inta = iterator.next();
            if (inta == 2) {
                iterator.remove();
                //list.add(3);
                //list.add(4);
            }
        }

        list.stream().forEach( a -> System.out.println(a));
    }
}
