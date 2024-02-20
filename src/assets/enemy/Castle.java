package assets.enemy;

import java.util.concurrent.ThreadLocalRandom;

public class Castle extends SuperEnemy {

    public Castle(){

        name = "Castle";
        soldier = ThreadLocalRandom.current().nextInt(20, 35 + 1);
        tier = 3;
        loot = soldier * tier;
        ppl = soldier * 5;
    }

}
