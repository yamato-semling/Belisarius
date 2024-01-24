package story;

import main.*;

public class Warroom {

    static Game game;
    static UI ui;
    ViewManager vm;

    Player player;

    public Warroom(Game g, UI userInterface, ViewManager vManager, Player p){
        game = g;
        ui = userInterface;
        vm = vManager;
        player = p;
    }

    public static void village(){
        ui.mainTextArea.setText("You gathered your army.\nYour army is camping near the village.");

        ui.choice1.setText("Conquer Village");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("Back");

        game.nextPosition1 = "vbattle";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "warroom";
    }
    public static void town(){

    }


}
