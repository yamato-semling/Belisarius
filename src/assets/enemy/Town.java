package assets.enemy;

import java.util.concurrent.ThreadLocalRandom;

public class Town extends SuperEnemy {

    public Town(){

        name = "Town";
        soldier = ThreadLocalRandom.current().nextInt(7, 14 + 1);
        tier = 2;
        loot = soldier * tier;
        ppl = soldier * 5;
    }

}
