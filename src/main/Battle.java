package main;

import java.lang.reflect.Array;

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
            battlRes = 2;
            int i = esoldier * -1;
            psoldier = psoldier + i;
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

}
