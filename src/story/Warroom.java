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

        game.choice("Conquer Village", "vbattle", "", "", "", "", "Back", "warroom");
    }
    public static void town(){
        ui.mainTextArea.setText("You gathered your army.\nYour army is camping near the town.");

        game.choice("Conquer Town", "tbattle", "", "", "", "", "Back", "warroom");
    }

    public static void castle(){
        ui.mainTextArea.setText("You gathered your army.\nYour army is camping near the castle.");

        game.choice("Conquer Castle", "cbattle", "", "", "", "", "Back", "warroom");
    }
}
