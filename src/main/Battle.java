package main;

import assets.laws.ProfessionalArmy;
import story.Warroom;

import java.lang.reflect.Array;
import java.util.concurrent.ThreadLocalRandom;

import static main.Dice.dice;
import static main.Story.player;

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

    public static int neoCalcDmg(int soldier, int tier){
        return (soldier * tier) / 8 ;
    }

    public static int[] neoCalcBattle(int psoldier, int ptier, int esoldier, int etier) {

        int pdmg = neoCalcDmg(psoldier, ptier);
        int edmg = neoCalcDmg(esoldier, etier);

        int pres = 0;
        int eres = 0;
        int cres = 1;

        int buff = 0;
        if (player.law[1]){buff = 2;}

        int dice = dice(20) + ptier + buff;

        // crit
        if (dice > 15 + etier){
            pres = psoldier - edmg / 2;
            eres = esoldier - (pdmg + (pdmg / 2));
            cres = 2;
        // crit negative
        }else if (dice < 5 + etier) {
            pres = psoldier - (edmg + (edmg / 2));
            eres = esoldier - pdmg / 2;
            cres = 0;
        // normal
        }else{
            pres = psoldier - edmg;
            eres = esoldier - pdmg;
        }

        int[] res = new int[3];
        res[0] = pres;
        res[1] = eres;
        res[2] = cres;
        return res;
    }
}
