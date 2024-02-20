package main;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    public static int dice(int max){

        return ThreadLocalRandom.current().nextInt(1, max + 1);
    }

}
