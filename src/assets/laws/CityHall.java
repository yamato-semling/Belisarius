package assets.laws;

public class CityHall extends SuperLaw{

    public CityHall(){

        id = 1;
        active = false;
        name = "City Hall";
        description = "Organizes the city affairs, you gain 10% more gold at the end of the week.";
        requirement = "More than 25 subjects.";
        gold = 0;
        ppl = 25;
        soldier = 0;
        tier = 0;
        moral = 0;
        power = 0;

    }
}
