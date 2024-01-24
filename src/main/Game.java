package main;

import story.Warroom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    ChoiceHandler cHandler = new ChoiceHandler();
    CastleHandler caHandler = new CastleHandler();
    UI ui = new UI();
    ViewManager vm = new ViewManager(ui);
    Story story = new Story(this,ui,vm);

    private Player p;
    Warroom warroom = new Warroom(this, ui, vm, p);

    public String nextPosition1;
    public String nextPosition2;
    public String nextPosition3;
    public String nextPosition4;
    String office;
    String barracks;
    String base;

    public static void main(String[] args) {

        new Game();
    }

    public Game(){

        ui.createUI(cHandler, caHandler);
        story.defaultSetup();
        vm.showTitleScreen();
    }

    public class ChoiceHandler implements ActionListener{

        public void actionPerformed(ActionEvent event){

            String choice = event.getActionCommand();

            switch (choice){
                case "start":
                    vm.prologue();
                    story.prologue1();
                    break;
                case "c1": story.selectPosition(nextPosition1); break;
                case "c2": story.selectPosition(nextPosition2); break;
                case "c3": story.selectPosition(nextPosition3); break;
                case "c4": story.selectPosition(nextPosition4); break;
            }
        }
    }

    public class CastleHandler implements ActionListener{

        public void actionPerformed(ActionEvent event){

            String castle = event.getActionCommand();

            switch (castle){
                case "ca1": story.office(); break;
                case "ca2": story.warroom(); break;
                case "ca3": story.barracks(); break;
                case "ca4": story.base(); break;
            }
        }
    }
}