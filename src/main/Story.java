package main;

import assets.enemy.SuperEnemy;
import assets.enemy.Village;
import assets.weapons.Knife;
import assets.weapons.LongSword;
import story.Warroom;

import javax.swing.*;

public class Story {

    Game game;
    UI ui;
    ViewManager vm;
    Player player = new Player();
    SuperEnemy village = new Village();
    Battle battle;

    private int esoldier;
    private int etier;
    private int eloot;
    private int eppl;

    public Story(Game g, UI userInterface, ViewManager vManager){

        game = g;
        ui = userInterface;
        vm = vManager;
    }

    public void defaultSetup(){

        player.day = 0;
        player.gold = 100;
        player.tax = 2;
        player.food = 30;
        player.wood = 50;
        player.ppl = 15;
        player.soldier = 5;
        player.tier = 2;
        player.moral = 60;
        player.power = 10;

        player.goldProduction = player.tax * player.ppl;
        player.foodProduction = player.ppl * 3;
        player.woodProduction = player.ppl;
        player.soldierProduction = 0;
        player.moralProduction = 1;
        player.powerProduction = 0;

        player.pplConsumption = player.soldierProduction;
        player.goldConsumption = player.soldier * 1;
        player.foodConsumption = player.soldier * 3;
        player.woodConsumption = player.soldier * 2;

        ui.dayLabel.setText("Days: "+player.day);
        ui.goldLabel.setText("Gold: "+player.gold);
        ui.taxLabel.setText("Tax: "+player.tax);
        ui.foodLabel.setText("Food: "+player.food);
        ui.woodLabel.setText("Wood: "+player.wood);
        ui.pplLabel.setText("People: "+player.ppl);
        ui.soldierLabel.setText("Soldiers: "+player.soldier);

        player.currentWeapon = new Knife();

    }
    public void selectPosition(String nextPosition){

        switch (nextPosition){
            case "prologue1":
                prologue1();
                break;
            case "prologue2":
                prologue2();
                break;
            case "prologue3":
                vm.gameStart();
                office();
                break;
            case "office":
                office();
                break;
            case "warroom":
                warroom();
                break;
            case "barracks":
                barracks();
                break;
            case "base":
                base();
                break;
            case "test":
                test();
                break;
            case "test1":
                test1();
                break;
            case "day":
                day();
                break;
            case "week":
                week();
                break;
            case "village":
                Warroom.village();
                break;
            case "vbattle":
                battle(village);
                break;
            case "attack":
                attack();
                break;
            case "retreat":
                warroom();
                break;
            case "tax":
                tax();
                break;
            case "raise":
                taxRaise();
                break;
            case "raise1":
                raise(1);
                break;
            case "raise5":
                raise(5);
                break;
            case "raise10":
                raise(10);
                break;
            case "decrease":
                taxDecrease();
                break;
            case "decrease1":
                decrease(1);
                break;
            case "decrease5":
                decrease(5);
                break;
            case "decrease10":
                decrease(10);
                break;
            default:
                break;
        }
    }

    public void castleDefault(){
        ui.castleButtonPanel.setVisible(true);
        ui.castle1.setText("Office");
        ui.castle2.setText("Warroom");
        ui.castle3.setText("Barracks");
        ui.castle4.setText("Base");
    }

    public void sunday(){
        int goldDif = player.goldProduction - player.goldConsumption;
        int foodDif = player.foodProduction - player.foodConsumption;
        int woodDif = player.woodProduction - player.woodConsumption;
        int pplDif = player.pplProduction - player.pplConsumption;

        player.gold = player.gold + goldDif;
        player.food = player.food + foodDif;
        player.wood = player.wood + woodDif;
        player.ppl = player.ppl + pplDif;
        player.soldier = player.soldier + player.soldierProduction;
        player.moral = player.moral + player.moralProduction;
        player.power = player.power + player.powerProduction;

        ui.dayLabel.setText("Days: "+player.day);
        ui.goldLabel.setText("Gold: "+player.gold);
        ui.taxLabel.setText("Tax: "+player.tax);
        ui.foodLabel.setText("Food: "+player.food);
        ui.woodLabel.setText("Wood: "+player.wood);
        ui.pplLabel.setText("People: "+player.ppl);
        ui.soldierLabel.setText("Soldiers: "+player.soldier);

        ui.mainTextArea.setText("This week you made \n" + goldDif + " gold,\n" + foodDif +" food,\n" + woodDif + " wood,\n" + pplDif + " citizens,\n" + player.soldierProduction + " soldiers,\n" + player.moralProduction + " moral,\n" + player.powerProduction + " power.");

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "office";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void prologue1(){
        int[] battleRes = battle.calcBattle(5, 2, 5, 1);

        ui.mainTextArea.setText("1" + battleRes[0] + " " + battleRes[1]);

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "prologue2";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void day(){
        player.day = player.day + 1;

        ui.dayLabel.setText("Days:"+ player.day);
        ui.mainTextArea.setText("You slept.");

        if (player.day % 7 == 0){
            sunday();
        }
    }
    public void week(){
        player.day = player.day + 7;
        sunday();
    }

    public void battle(SuperEnemy enemy){
        esoldier = enemy.soldier;
        etier = enemy.tier;
        eloot = enemy.loot;
        eppl = enemy.ppl;

        ui.mainTextArea.setText("You are in the siege camp. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + village.name + " has " + esoldier + " tier " + village.tier + " soldiers.");

        ui.castleButtonPanel.setVisible(false);

        ui.choice1.setText("Attack");
        ui.choice2.setText("Retreat");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "attack";
        game.nextPosition2 = "retreat";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    public void attack(){
        ui.mainTextArea.setText("You are in the siege camp. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + village.name + " has " + esoldier + " tier " + village.tier + " soldiers.");

        ui.castleButtonPanel.setVisible(false);

        ui.choice1.setText("Attack");
        ui.choice2.setText("Retreat");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "attack";
        game.nextPosition2 = "retreat";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        System.out.println(player.soldier + " " + player.tier + " " + esoldier + " " + etier);
        int[] battleRes =  battle.calcBattle(player.soldier, player.tier, esoldier, etier);

        player.soldier = battleRes[1];
        esoldier = battleRes[2];

        switch (battleRes[0]){
            case 2:
                win();
                break;
            case 0:
                lose();
                break;
            default:
                attack();
                break;
        }
    }
    public void win(){
        player.gold = player.gold + eloot;
        player.ppl = player.ppl + eppl;

        ui.mainTextArea.setText("You defeated your enemy and subjugated " + eppl + " villagers,\nnow you have " + player.ppl + " subjects.\nThey paid you " + eloot + " gold. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + village.name + " has " + esoldier + " tier " + village.tier + " soldiers.");

        ui.castleButtonPanel.setVisible(false);

        ui.choice1.setText("Go back to office.");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "office";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        ui.goldLabel.setText("Gold: "+player.gold);
        ui.pplLabel.setText("People: "+player.ppl);
    }
    public void lose(){
        ui.mainTextArea.setText("You lost against your enemy. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + village.name + " has " + esoldier + " tier " + village.tier + " soldiers.");

        ui.castleButtonPanel.setVisible(false);

        ui.choice1.setText("Go back to office.");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "office";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void prologue2(){
        ui.mainTextArea.setText("2");

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "prologue3";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }public void office(){
        ui.mainTextArea.setText("You are in your Office.\nFrom here you can make new laws.");

        castleDefault();

        ui.choice1.setText("Wait a day");
        ui.choice2.setText("Wait a week");
        ui.choice3.setText("Adjust Tax");
        ui.choice4.setText("Law");

        game.nextPosition1 = "day";
        game.nextPosition2 = "week";
        game.nextPosition3 = "tax";
        game.nextPosition4 = "law";
    }
    public void warroom(){
        ui.mainTextArea.setText("You are in your Warroom.\nFrom here you can command your army.");

        castleDefault();

        ui.choice1.setText("Nearby Village");
        ui.choice2.setText("Nearby Town");
        ui.choice3.setText("Nearby Castle");
        ui.choice4.setText("");

        game.nextPosition1 = "village";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    public void barracks(){
        ui.mainTextArea.setText("You are in the Brracks.\nFrom here you can train your soldiers or adjust their budget.");

        castleDefault();

        ui.choice1.setText("Recruit Soldiers");
        ui.choice2.setText("Train Soldiers");
        ui.choice3.setText("Moral Boost");
        ui.choice4.setText("Adjust Budget");

        game.nextPosition1 = "train";
        game.nextPosition2 = "recruit";
        game.nextPosition3 = "moral";
        game.nextPosition4 = "bbudget";
    }
    public void base(){
        ui.mainTextArea.setText("You are with your servants.\nYou can command them to adjust the infrastructure.");

        castleDefault();

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "test";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    public void test(){
        ui.mainTextArea.setText("You are in your Test.\nFrom here you can make new laws or command your army.");

        castleDefault();

        game.office = "office";

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "test1";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    public void test1(){
        ui.mainTextArea.setText("You are in your Test1.\nFrom here you can make new laws or command your army.");

        castleDefault();

        game.office = "office";

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "office";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    public void law(){
        ui.mainTextArea.setText("You are in your Office with your most intelligent vassals discussing on your lands laws.");

        ui.choice1.setText("");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    public void tax(){
        int goldDif = player.goldProduction - player.goldConsumption;
        ui.mainTextArea.setText("The Tax in your land is set on " + player.tax + ",\nwith " + player.ppl  +" subjects in your lands you make " + player.goldProduction + ".\nAfter " + player.goldConsumption + " gold consumption, your true gold balance is at: " + goldDif);

        ui.choice1.setText("Raise Tax");
        ui.choice2.setText("Decrease Tax");
        ui.choice3.setText("");
        ui.choice4.setText("Back");

        game.nextPosition1 = "raise";
        game.nextPosition2 = "decrease";
        game.nextPosition3 = "";
        game.nextPosition4 = "office";
    }
    public void taxRaise(){
        ui.mainTextArea.setText("Tax " + player.tax + "\nRaising Tax can gain you gold but with high tax you lose moral and power.");

        ui.choice1.setText("Raise by 1");
        ui.choice2.setText("Raise by 5");
        ui.choice3.setText("Raise by 10");
        ui.choice4.setText("Back");

        game.nextPosition1 = "raise1";
        game.nextPosition2 = "raise5";
        game.nextPosition3 = "raise10";
        game.nextPosition4 = "office";
    }
    public void taxDecrease(){
        ui.mainTextArea.setText("Tax " + player.tax + "\nDecreasing Tax helps gaining moral and power..");

        ui.choice1.setText("Decrease by 1");
        ui.choice2.setText("Decrease by 5");
        ui.choice3.setText("Decrease by 10");
        ui.choice4.setText("Back");

        game.nextPosition1 = "decrease1";
        game.nextPosition2 = "decrease5";
        game.nextPosition3 = "decrease10";
        game.nextPosition4 = "office";
    }
    public void raise(int rate){

        switch (rate){
            case 1:
                player.tax = player.tax + 1;
                if (player.tax >= 5){
                    player.moralProduction = player.moralProduction - 1;
                }
                ui.mainTextArea.setText("Tax: " + player.tax + " Raising Tax can gain you gold but with high tax you lose moral and power.\nRaised tax by 1.");
                ui.taxLabel.setText("Tax: "+player.tax);
                break;
            case 5:
                player.tax = player.tax + 5;
                player.moralProduction = player.moralProduction - 3;
                ui.mainTextArea.setText("Tax: " + player.tax + " Raising Tax can gain you gold but with high tax you lose moral and power.\nRaised tax by 5.");
                ui.taxLabel.setText("Tax: "+player.tax);
                break;
            case 10:
                player.tax = player.tax + 10;
                player.moralProduction = player.moralProduction - 5;
                ui.mainTextArea.setText("Tax: " + player.tax + " Raising Tax can gain you gold but with high tax you lose moral and power.\nRaised tax by 10.");
                ui.taxLabel.setText("Tax: "+player.tax);
                break;
            default:
                break;
        }
        player.goldProduction = player.ppl + player.tax;

    }

    public void decrease(int rate){

        switch (rate){
            case 1:
                player.tax = player.tax - 1;
                if (player.tax >= 5){
                    player.moralProduction = player.moralProduction + 1;
                }
                ui.mainTextArea.setText("Tax: " + player.tax + " Decreasing Tax helps gaining moral and power.\nDecreased tax by 1.");
                ui.taxLabel.setText("Tax: "+player.tax);
                break;
            case 5:
                player.tax = player.tax - 5;
                player.moralProduction = player.moralProduction + 3;
                ui.mainTextArea.setText("Tax: " + player.tax + " Decreasing Tax helps gaining moral and power.\nDecreased tax by 5.");
                ui.taxLabel.setText("Tax: "+player.tax);
                break;
            case 10:
                player.tax = player.tax - 10;
                player.moralProduction = player.moralProduction + 5;
                ui.mainTextArea.setText("Tax: " + player.tax + " Decreasing Tax helps gaining moral and power.\nDecreased tax by 10.");
                ui.taxLabel.setText("Tax: "+player.tax);
                break;
            default:
                break;
        }
        player.goldProduction = player.ppl + player.tax;

    }
}
