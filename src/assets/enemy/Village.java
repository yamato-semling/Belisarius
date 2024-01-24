package assets.enemy;

import java.util.concurrent.ThreadLocalRandom;

public class Village extends SuperEnemy {

    public Village(){

        name = "Village";
        soldier = ThreadLocalRandom.current().nextInt(3, 7 + 1);;
        tier = 1;
        loot = soldier * tier;
        ppl = soldier * 4;
    }

}
