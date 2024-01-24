package main;

public class ViewManager {

    UI ui;

    public ViewManager(UI userInterface){

        ui = userInterface;

    }

    public void showTitleScreen(){

        // Show title screen
        ui.titleNamePanel.setVisible(true);
        ui.startButtonPanel.setVisible(true);

        // Hide game screen
        ui.mainImgPanel.setVisible(false);
        ui.mainTextPanel.setVisible(false);
        ui.castleButtonPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);

    }
    public void prologue(){

        // Hide title screen
        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        // Show game screen
        ui.mainImgPanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.castleButtonPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(true);
    }
    public void gameStart(){

        // Hide title screen
        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        // Show game screen
        ui.mainTextPanel.setVisible(true);
        ui.castleButtonPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(true);
    }
}
