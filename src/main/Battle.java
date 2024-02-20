package main;

import java.lang.reflect.Array;
import java.util.concurrent.ThreadLocalRandom;

public class Battle {

    public static int calcDmg(int soldier, int tier){
        int dmg = soldier * tier;
        return dmg;
    }

    public static int[] calcBattle(int psoldier, int ptier, int esoldier, int etier){

        int battlRes = 1;
        int pdmg = calcDmg(psoldier, ptier);
        int edmg = calcDmg(esoldier, etier);

        esoldier = esoldier -pdmg;
        psoldier = psoldier -edmg;

        if (esoldier <= 0){
            if (esoldier > psoldier){
                battlRes = 0;
                int i = -psoldier;
                esoldier = esoldier + i;
            }else {
                battlRes = 2;
                int i = -esoldier;
                psoldier = psoldier + i;
            }
        }else if (psoldier <= 0){
            battlRes = 0;
            int i = psoldier * -1;
            esoldier = esoldier + i;
        }

        int[] res = new int[3];
        res[0] = battlRes;
        res[1] = psoldier;
        res[2] = esoldier;
        return res;
    }

    public static void field(){

    }

    public static int[] neoCalcBattle(int psoldier, int ptier, int esoldier, int etier) {

        int pdmg = calcDmg(psoldier, ptier);
        int edmg = calcDmg(esoldier, etier);

        int dice = ThreadLocalRandom.current().nextInt(1, 20 + 1);
        dice = dice + ptier;

        if (dice > 18){

        }else if (dice < 6){

        }else{

        }
        

        return null;
    }

}
