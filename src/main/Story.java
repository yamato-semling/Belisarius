package main;

import assets.enemy.Castle;
import assets.enemy.SuperEnemy;
import assets.enemy.Town;
import assets.enemy.Village;
import assets.laws.CityHall;
import assets.laws.ProfessionalArmy;
import assets.laws.SuperLaw;
import story.Warroom;

public class Story {

    Game game;
    UI ui;
    ViewManager vm;
    Player player = new Player();
    SuperLaw cityHall = new CityHall();
    SuperLaw professionalArmy = new ProfessionalArmy();
    Battle battle;

    private String ename;
    private int esoldier;
    private int etier;
    private int eloot;
    private int eppl;

    private int lawCountMax;
    private int lawCount;

    private SuperLaw[] laws = {
            cityHall, professionalArmy
    };

    public Story(Game g, UI userInterface, ViewManager vManager){

        game = g;
        ui = userInterface;
        vm = vManager;
    }

    public void updateStats(){
        ui.dayLabel.setText("Days: "+player.day);
        ui.goldLabel.setText("Gold: "+player.gold);
        ui.taxLabel.setText("Tax: "+player.tax);
        ui.foodLabel.setText("Food: "+player.food);
        ui.woodLabel.setText("Wood: "+player.wood);
        ui.pplLabel.setText("People: "+player.ppl);
        ui.soldierLabel.setText("Soldiers: "+player.soldier);
        ui.tierLabel.setText("Tier: "+player.tier);
        ui.loyaltyLabel.setText("Loyalty: "+player.loyalty);
        ui.powerLabel.setText("Power: "+player.power);
    }

    public void defaultSetup(){

        player.day = 0;
        player.gold = 100;
        player.tax = 2;
        player.food = 30;
        player.wood = 50;
        player.ppl = 15;
        player.recruitment = false;
        player.soldier = 5;
        player.tier = 2;
        player.loyalty = 60;
        player.power = 10;

        player.goldProduction = player.tax * player.ppl;
        player.pplProduction =
        player.foodProduction = player.ppl * 3;
        player.woodProduction = player.ppl;
        player.soldierProduction = 0;
        player.loyaltyProduction = 1;
        player.powerProduction = 0;

        player.pplConsumption = player.soldierProduction;
        player.goldConsumption = player.soldier;
        player.foodConsumption = player.soldier * 3;
        player.woodConsumption = player.soldier * 2;

        updateStats();

        player.img = "title.png";

        lawCountMax = laws.length - 1;
        lawCount = 0;

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
            case "sundaycheckout":
                sundayCheckout();
                break;
            case "gameover":
                gameOver();
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
            case "basement":
                basement();
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
                battle(new Village());
                break;
            case "town":
                Warroom.town();
                break;
            case "tbattle":
                battle(new Town());
                break;
            case "castle":
                Warroom.castle();
                break;
            case "cbattle":
                battle(new Castle());
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
            case "recruit":
                recruit();
                break;
            case "startrecruit":
                startRecruit();
                break;
            case "stoprecruit":
                stopRecruit();
                break;
            case "law":
                law();
                break;
            case "nextavaiblelaw":
                if (lawCount == lawCountMax){
                    lawCount = 0;
                }else {
                    lawCount = lawCount + 1;
                }
                law();
                break;
            case "lastavaiblelaw":
                if (lawCount == 0){
                    lawCount = lawCountMax;
                }else {
                    lawCount = lawCount - 1;
                }
                law();
                break;
            case "switchlaw":
                switchLaw(lawCount);
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
        ui.castle4.setText("Basement");
    }

    public void sunday(){
        int recruits = 0;

        if (player.recruitment){
            recruits = player.ppl / 4;
        }
        player.soldierProduction = recruits;

        int profArmy = 0;
        if(professionalArmy.active){profArmy = player.soldier / 100 * 20;}

        int goldDif = player.goldProduction - (player.goldConsumption + profArmy);
        int foodDif = player.foodProduction - player.foodConsumption;
        int woodDif = player.woodProduction - player.woodConsumption;
        int pplDif = player.pplProduction - (player.pplConsumption + recruits);
        int loyaltyDif = player.loyalty + (player.tax * -1);

        player.gold = player.gold + goldDif;
        player.food = player.food + foodDif;
        player.wood = player.wood + woodDif;
        player.ppl = player.ppl + pplDif;
        player.soldier = player.soldier + player.soldierProduction;
        player.loyalty = player.loyalty + loyaltyDif;
        player.power = player.power + player.powerProduction;

        if(cityHall.active){player.gold = player.gold + (player.goldProduction / 10);}

        updateStats();

        ui.mainTextArea.setText("Administrative Officer:\nMy Lord, this week you made:\n" + goldDif + " gold,\n" + foodDif +" food,\n" + woodDif + " wood,\n" + pplDif + " citizens,\n" + player.soldierProduction + " soldiers,\n" + loyaltyDif + " loyalty,\n" + player.powerProduction + " power.");

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "sundaycheckout";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    public void sundayCheckout(){
        if (player.loyalty <= 0){
            gameOverRes("Messenger:\nMy Lord, your subjects aren't loyal to you anymore and started an uprising against your oppressive tyranny!\n\nYOU WERE OVERTHROWN!");
        }else{
            office();
        }
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

    public void gameOver(){
        defaultSetup();
        vm.showTitleScreen();
        ui.titleNameLabel.setText("Game Over");
        ui.startButton.setText("New Game");
    }public void gameOverRes(String res){
        ui.castleButtonPanel.setVisible(false);

        ui.mainTextArea.setText(res);

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "gameover";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void prologue1(){
        ui.mainTextArea.setText("Prologue 1");

        ui.setImage(Img.title);

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "prologue2";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    public void prologue2(){
        ui.mainTextArea.setText("Prologue 2");

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "prologue3";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void battle(SuperEnemy enemy){
        ename = enemy.name;
        esoldier = enemy.soldier;
        etier = enemy.tier;
        eloot = enemy.loot;
        eppl = enemy.ppl;

        ui.mainTextArea.setText("You are in the siege camp. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + ename + " has " + esoldier + " tier " + etier + " soldiers.");

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
        ui.mainTextArea.setText("You are in the siege camp. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + ename + " has " + esoldier + " tier " + etier + " soldiers.");

        ui.castleButtonPanel.setVisible(false);

        ui.choice1.setText("Attack");
        ui.choice2.setText("Retreat");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "attack";
        game.nextPosition2 = "retreat";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        int[] battleRes =  battle.neoCalcBattle(player.soldier, player.tier, esoldier, etier);

        player.soldier = battleRes[0];
        esoldier = battleRes[1];
        int cres = battleRes[2];

        if (cres == 2){
            ui.mainTextArea.setText("You are in the siege camp. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + ename + " has " + esoldier + " tier " + etier + " soldiers.\nYou hit them with a critical!");
        }else if (cres == 0){
            ui.mainTextArea.setText("You are in the siege camp. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + ename + " has " + esoldier + " tier " + etier + " soldiers.\nThey hit you with a critical!");
        }else {
            ui.mainTextArea.setText("You are in the siege camp. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + ename + " has " + esoldier + " tier " + etier + " soldiers.");
        }

        if (esoldier <= 0){
            win();
        }else if (player.soldier <= 0){
            lose();
        }else {
            nextBattle();
        }
    }
    public void nextBattle(){
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
    public void win(){
        player.gold = player.gold + eloot;
        player.loyalty = player.loyalty + eloot / 5;
        player.power = player.power + eloot / 2;
        player.ppl = player.ppl + eppl;

        ui.mainTextArea.setText("You defeated your enemy and subjugated " + eppl + " villagers,\nnow you have " + player.ppl + " subjects.\nThey paid you " + eloot + " gold. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + ename + " has " + esoldier + " tier " + etier + " soldiers.");

        ui.castleButtonPanel.setVisible(false);

        ui.choice1.setText("Go back to office.");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "office";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        updateStats();
    }
    public void lose(){
        ui.mainTextArea.setText("You lost against your enemy. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + ename + " has " + esoldier + " tier " + etier + " soldiers.");

        ui.castleButtonPanel.setVisible(false);

        ui.choice1.setText("Go back to office.");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "office";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        updateStats();
    }
    public void office(){
        ui.mainTextArea.setText("You are in your Office.\nFrom here you can make new laws.");
        ui.setImage(Img.title);

        castleDefault();

        ui.choice1.setText("Wait a day");
        ui.choice2.setText("Wait a week");
        ui.choice3.setText("Adjust Tax");
        ui.choice4.setText("Laws");

        game.nextPosition1 = "day";
        game.nextPosition2 = "week";
        game.nextPosition3 = "tax";
        game.nextPosition4 = "law";
    }
    public void warroom(){
        ui.mainTextArea.setText("You are in your Warroom.\nFrom here you can command your army.");
        ui.setImage(Img.warroom);

        castleDefault();

        ui.choice1.setText("Nearby Village");
        ui.choice2.setText("Nearby Town");
        ui.choice3.setText("Nearby Castle");
        ui.choice4.setText("");

        game.nextPosition1 = "village";
        game.nextPosition2 = "town";
        game.nextPosition3 = "castle";
        game.nextPosition4 = "";
    }
    public void barracks(){
        ui.mainTextArea.setText("You are in the Brracks.\nFrom here you can train your soldiers or adjust their budget.");
        ui.setImage(Img.title);

        castleDefault();

        ui.choice1.setText("Recruit Soldiers");
        ui.choice2.setText("Train Soldiers");
        ui.choice3.setText("Loyalty Boost");
        ui.choice4.setText("Adjust Budget");

        game.nextPosition1 = "recruit";
        game.nextPosition2 = "train";
        game.nextPosition3 = "loyalty";
        game.nextPosition4 = "bbudget";
    }
    public void recruit(){
        ui.mainTextArea.setText("You are in the Barracks.\nYou are discussing on the recruitment policy with your Chief Commander.\nThe Recruitment is influenced by the loyalty and the number of your subjects, it will cost gold too.\nRecruits per week: " + player.soldierProduction+ "\nRecruitment Status: " + player.recruitment);

        ui.choice1.setText("Start Recruitment");
        ui.choice2.setText("Stop Recruitment");
        ui.choice3.setText("");
        ui.choice4.setText("Back");

        game.nextPosition1 = "startrecruit";
        game.nextPosition2 = "stoprecruit";
        game.nextPosition3 = "";
        game.nextPosition4 = "barracks";
    }
    public void startRecruit(){

        if (!player.recruitment){
            player.recruitment = true;
            int recruits = player.ppl / 4;

            ui.mainTextArea.setText("You started recruiting soldiers.\nYou have " + player.ppl + " subjects.\nYou will recruit " + recruits + " recruits per week.");
        }else {
            ui.mainTextArea.setText("You are already recruiting soldiers.");
        }

        ui.choice1.setText("Start Recruitment");
        ui.choice2.setText("Stop Recruitment");
        ui.choice3.setText("");
        ui.choice4.setText("Back");

        game.nextPosition1 = "startrecruit";
        game.nextPosition2 = "train";
        game.nextPosition3 = "";
        game.nextPosition4 = "barracks";

    }
    public void stopRecruit(){

        if (player.recruitment){
            player.recruitment = false;

            ui.mainTextArea.setText("You aren't recruiting anymore.");
        }else {
            ui.mainTextArea.setText("You aren't recruiting right now..");
        }

        ui.choice1.setText("Start Recruitment");
        ui.choice2.setText("Stop Recruitment");
        ui.choice3.setText("");
        ui.choice4.setText("Back");

        game.nextPosition1 = "startrecruit";
        game.nextPosition2 = "stoprecruit";
        game.nextPosition3 = "";
        game.nextPosition4 = "barracks";

    }
    public void basement(){
        ui.mainTextArea.setText("You are alone in the basement of your castle.");

        castleDefault();

        ui.choice1.setText("Prison");
        ui.choice2.setText("Hidden Room");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    public void hiddenRoom(){
        ui.mainTextArea.setText("You are in your hidden room.\nHere you can do hidden rituals...");

        ui.choice1.setText("Blood Magic");
        ui.choice2.setText("Blood Rituals");
        ui.choice3.setText("Blood Summoning");
        ui.choice4.setText("Back");

        game.nextPosition1 = "bmagic";
        game.nextPosition2 = "brituals";
        game.nextPosition3 = "bsummoning";
        game.nextPosition4 = "basement";
    }
    public void defaultLawText(SuperLaw localLaw){
        ui.mainTextArea.setText("Law: " + localLaw.name + "\nState: " + localLaw.active + "\nDescription: " + localLaw.description + "\nRequirements: " + localLaw.requirement);
    }public void law(){
        SuperLaw localLaw = laws[lawCount];

        defaultLawText(localLaw);

        ui.choice1.setText("->");
        ui.choice2.setText("<-");
        ui.choice3.setText("Activate Law");
        ui.choice4.setText("Back");

        game.nextPosition1 = "nextavaiblelaw";
        game.nextPosition2 = "lastavaiblelaw";
        game.nextPosition3 = "switchlaw";
        game.nextPosition4 = "office";
    }public void switchLaw(int count) {

        SuperLaw localLaw = laws[lawCount];

        if (!localLaw.active) {
            if (player.gold > laws[count].gold && player.ppl > laws[count].ppl && player.soldier > laws[count].soldier && player.tier > laws[count].tier && player.loyalty > laws[count].loyalty && player.power > laws[count].power) {

                localLaw.active = true;
                defaultLawText(localLaw);
            }else {
                ui.mainTextArea.setText("Law: " + localLaw.name + "\nState: " + localLaw.active + "\nDescription: " + localLaw.description + "\nRequirements: " + localLaw.requirement + "\n\nRequirements not met!");
            }
        }else {
            localLaw.active = false;
            defaultLawText(localLaw);
        }
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
        ui.mainTextArea.setText("Tax " + player.tax + "\nRaising Tax can gain you gold but with high tax you lose loyalty and power.");

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
        ui.mainTextArea.setText("Tax " + player.tax + "\nDecreasing Tax helps gaining loyalty and power.");

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
                    player.loyaltyProduction = player.loyaltyProduction - 1;
                }
                ui.mainTextArea.setText("Tax: " + player.tax + " Raising Tax can gain you gold but with high tax you lose loyalty and power.\nRaised tax by 1.");
                break;
            case 5:
                player.tax = player.tax + 5;
                player.loyaltyProduction = player.loyaltyProduction - 3;
                ui.mainTextArea.setText("Tax: " + player.tax + " Raising Tax can gain you gold but with high tax you lose loyalty and power.\nRaised tax by 5.");
                break;
            case 10:
                player.tax = player.tax + 10;
                player.loyaltyProduction = player.loyaltyProduction - 5;
                ui.mainTextArea.setText("Tax: " + player.tax + " Raising Tax can gain you gold but with high tax you lose loyalty and power.\nRaised tax by 10.");
                break;
            default:
                break;
        }
        updateStats();
        player.goldProduction = player.ppl + player.tax;

    }

    public void decrease(int rate){

        switch (rate){
            case 1:
                player.tax = player.tax - 1;
                if (player.tax >= 5){
                    player.loyaltyProduction = player.loyaltyProduction + 1;
                }
                ui.mainTextArea.setText("Tax: " + player.tax + " Decreasing Tax helps gaining loyalty and power.\nDecreased tax by 1.");
                break;
            case 5:
                player.tax = player.tax - 5;
                player.loyaltyProduction = player.loyaltyProduction + 3;
                ui.mainTextArea.setText("Tax: " + player.tax + " Decreasing Tax helps gaining loyalty and power.\nDecreased tax by 5.");
                break;
            case 10:
                player.tax = player.tax - 10;
                player.loyaltyProduction = player.loyaltyProduction + 5;
                ui.mainTextArea.setText("Tax: " + player.tax + " Decreasing Tax helps gaining loyalty and power.\nDecreased tax by 10.");
                break;
            default:
                break;
        }
        updateStats();
        player.goldProduction = player.ppl + player.tax;

    }
}
