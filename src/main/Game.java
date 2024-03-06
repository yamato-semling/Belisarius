package main;

import story.Warroom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    public static void saveGame(int num){
        try{
            FileOutputStream fos = new FileOutputStream("Game"+ num +".sav");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Story.player);
            oos.flush();
            oos.close();
            System.out.println("Game Saved");
        }catch (Exception e){
            System.out.println("Serialization Error! Can't save data\n"
                +e.getClass() + ": " + e.getMessage() + "\n");
        }
    }
    public static void loadGame(int num){
        try{
            FileInputStream fis = new FileInputStream("Game"+ num +".sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Story.player = (Player) ois.readObject();
            ois.close();
            System.out.println("Game Loaded");
        }catch (Exception e){
            System.out.println("Serialization Error! Can't save data\n"
                    +e.getClass() + ": " + e.getMessage() + "\n");
        }
    }
    public void loadFirst(){
        ui.mainTextArea.setText("Load Game Files.");

        ui.choice1.setText("Load Game1");
        ui.choice2.setText("Load Game2");
        ui.choice3.setText("Load Game3");
        ui.choice4.setText("");

        nextPosition1 = "load1f";
        nextPosition2 = "load2f";
        nextPosition3 = "load3f";
        nextPosition4 = "";
    }

    public class ChoiceHandler implements ActionListener{

        public void actionPerformed(ActionEvent event){

            String choice = event.getActionCommand();

            switch (choice){
                case "start":
                    vm.prologue();
                    story.prologue1();
                    break;
                case "loadFile":
                    vm.gameLoad();
                    loadFirst();
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
                case "ca4": story.basement(); break;
            }
        }
    }

    public void choice(String c1, String pos1, String c2, String pos2, String c3, String pos3, String c4, String pos4){

        ui.choice1.setText(c1);
        ui.choice2.setText(c2);
        ui.choice3.setText(c3);
        ui.choice4.setText(c4);

        nextPosition1 = pos1;
        nextPosition2 = pos2;
        nextPosition3 = pos3;
        nextPosition4 = pos4;
    }
}