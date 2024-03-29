package main;


import assets.laws.CityHall;
import assets.laws.SuperLaw;

import java.io.Serializable;

public class Player implements Serializable {

    public String img;
    public int day;
    public int gold;
    public int food;
    public int wood;
    public int ppl;
    public boolean recruitment;
    public int soldier;
    public int tier;
    public int loyalty;
    public int power;
    public int tax;

    public int goldProduction;
    public int foodProduction;
    public int woodProduction;
    public int pplProduction;
    public int soldierProduction;
    public int loyaltyProduction;
    public int powerProduction;

    public int goldConsumption;
    public int foodConsumption;
    public int woodConsumption;
    public int pplConsumption;

    public boolean[] law = {false, false, false};
}
