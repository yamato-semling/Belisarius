package assets.laws;

public class SuperLaw {

    public int id;
    public boolean active;
    public String name;
    public String description;
    public String requirement;
    public int gold;
    public int ppl;
    public int soldier;
    public int tier;
    public int loyalty;
    public int power;

    public String getName()
    {
        return this.name;
    }

    public String display(){
        return "Name: " + this.name + "\nDescription: " + this.description;
    }
}
