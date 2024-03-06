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
    static Player player = new Player();
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
            case "file":
                file();
                break;
            case "save":
                save();
                break;
            case "save1":
                Game.saveGame(1);
                ui.mainTextArea.setText("Successfully saved to Game1.");
                break;
            case "save2":
                Game.saveGame(2);
                ui.mainTextArea.setText("Successfully saved to Game2.");
                break;
            case "save3":
                Game.saveGame(3);
                ui.mainTextArea.setText("Successfully saved to Game3.");
                break;
            case "load":
                load();
                break;
            case "load1":
                Game.loadGame(1);
                updateStats();
                ui.mainTextArea.setText("Successfully loaded Game1.");
                break;
            case "load2":
                Game.loadGame(2);
                updateStats();
                ui.mainTextArea.setText("Successfully loaded Game2.");
                break;
            case "load3":
                Game.loadGame(3);
                updateStats();
                ui.mainTextArea.setText("Successfully loaded Game3.");
                break;
            case "load1f":
                Game.loadGame(1);
                updateStats();
                ui.mainTextArea.setText("Successfully loaded Game1.");
                vm.gameStart();
                office();
                break;
            case "load2f":
                Game.loadGame(2);
                updateStats();
                ui.mainTextArea.setText("Successfully loaded Game2.");
                vm.gameStart();
                office();
                break;
            case "load3f":
                Game.loadGame(3);
                updateStats();
                ui.mainTextArea.setText("Successfully loaded Game3.");
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
            case "wait":
                sleep();
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
            case "hiddenroom":
                hiddenRoom();
                break;
            default:
                break;
        }
    }

    public int toWeek(int week){
        return week * 7;
    }
    public int toMonth(int month){
        return month * 7 * 4;
    }

    public void castleDefault(){
        ui.castleButtonPanel.setVisible(true);
        ui.castle1.setText("Office");
        ui.castle2.setText("Warroom");
        ui.castle3.setText("Barracks");
        ui.castle4.setText("Basement");
    }

    public void sunday(){
        int profArmy = 0;
        if(player.law[professionalArmy.id]){profArmy = player.soldier / 100 * 20;}

        player.goldProduction = player.tax * player.ppl;
        player.pplProduction = player.ppl / 3;
        player.foodProduction = player.ppl * 3;
        player.woodProduction = player.ppl;

        player.goldConsumption = player.soldier;
        player.foodConsumption = player.soldier * 3;
        player.woodConsumption = player.soldier * 2;
        player.soldierProduction = 0;
        if (player.recruitment){
            player.soldierProduction = player.ppl / 4;
        }
        player.pplConsumption = player.soldierProduction;

        int goldDif = player.goldProduction - (player.goldConsumption + profArmy);
        int foodDif = player.foodProduction - player.foodConsumption;
        int woodDif = player.woodProduction - player.woodConsumption;
        int pplDif = player.pplProduction - player.pplConsumption;
        player.loyaltyProduction = - (player.tax +(player.soldierProduction / 2));

        player.ppl = player.ppl + pplDif;

        player.gold = player.gold + goldDif;
        player.food = player.food + foodDif;
        player.wood = player.wood + woodDif;
        player.soldier = player.soldier + player.soldierProduction;
        player.loyalty = player.loyalty + player.loyaltyProduction;
        player.power = player.power + player.powerProduction;

        if(player.law[cityHall.id]){player.gold = player.gold + (player.goldProduction / 10);}

        updateStats();

        ui.mainTextArea.setText("Administrative Officer:\nM'Lord, this week you made:\n" + goldDif + " gold,\n" + foodDif +" food,\n" + woodDif + " wood,\n" + pplDif + " citizens,\n" + player.soldierProduction + " soldiers,\n" + player.loyaltyProduction + " loyalty,\n" + player.powerProduction + " power.");

        game.choice(">", "sundaycheckout", "", "", "", "", "", "");
    }
    public void sundayCheckout(){
        if (player.loyalty <= 0){
            ui.setImage("message.png");
            gameOverRes("Messenger:\nM'Lord, your subjects aren't loyal to you anymore and started an uprising against your oppressive tyranny!\n\nYOU WERE OVERTHROWN!");
        }else{
            office();
        }

        if (player.day > toMonth(1)){
            if(player.gold > 500){
                player.gold = player.gold - 500;
                office();
            }else{
                ui.setImage("message.png");
                gameOverRes("Messenger:\nM'Lord, You failed to deliver the king the royal tribute!\n\nYOU WERE ABDICATED!");
            }
        }
    }
    public void day(){
        player.day = player.day + 1;

        updateStats();
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

        game.choice(">", "gameover", "", "", "", "", "", "");
    }

    public void prologue1(){
        ui.mainTextArea.setText("Prologue 1");

        ui.setImage(Img.title);

        game.choice(">", "prologue2", "", "", "", "", "", "");
    }
    public void prologue2(){
        ui.mainTextArea.setText("Administrative Officer:\nM'Lord, you only have one month left to pay the royal tribute of 500 G to the king!\nPlease hurry m'Lord!");

        game.choice(">", "prologue3", "", "", "", "", "", "");
    }

    public void battle(SuperEnemy enemy){
        ename = enemy.name;
        esoldier = enemy.soldier;
        etier = enemy.tier;
        eloot = enemy.loot;
        eppl = enemy.ppl;

        ui.mainTextArea.setText("You are in the siege camp. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + ename + " has " + esoldier + " tier " + etier + " soldiers.");

        ui.castleButtonPanel.setVisible(false);

        game.choice("Attack", "attack", "Retreat", "retreat" + "", "", "", "", "");
    }
    public void attack(){
        ui.mainTextArea.setText("You are in the siege camp. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + ename + " has " + esoldier + " tier " + etier + " soldiers.");

        ui.castleButtonPanel.setVisible(false);

        game.choice("Attack", "attack", "Retreat", "retreat", "", "", "", "");

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

        game.choice("Attack", "attack", "Retreat",  "retreat", "", "", "", "");
    }
    public void win(){
        player.gold = player.gold + eloot;
        player.loyalty = player.loyalty + eloot / 5;
        player.power = player.power + eloot / 2;
        player.ppl = player.ppl + eppl;

        ui.mainTextArea.setText("You defeated your enemy and subjugated " + eppl + " villagers,\nnow you have " + player.ppl + " subjects.\nThey paid you " + eloot + " gold. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + ename + " has " + esoldier + " tier " + etier + " soldiers.");

        ui.castleButtonPanel.setVisible(false);

        game.choice("Go back to office.", "office", "", "", "", "", "", "");

        updateStats();
    }
    public void lose(){
        ui.mainTextArea.setText("You lost against your enemy. \nYou have " + player.soldier + " tier " + player.tier + " soldiers. \nThe enemy " + ename + " has " + esoldier + " tier " + etier + " soldiers.");

        ui.castleButtonPanel.setVisible(false);

        game.choice("Go back to office.", "office", "", "", "", "", "", "");

        updateStats();
    }
    public void office(){
        ui.mainTextArea.setText("You are in your Office.\nFrom here you can make new laws.");
        ui.setImage(Img.title);

        castleDefault();

        game.choice("Wait sometime", "wait", "Adjust Tax", "tax", "Adjust Law", "law", "Game Files", "file");
    }
    public void warroom(){
        ui.mainTextArea.setText("You are in your Warroom.\nFrom here you can command your army.");
        ui.setImage(Img.warroom);

        castleDefault();

        game.choice("Nearby Village", "village", "Nearby Town", "town", "Nearby Castle", "castle", "", "");
    }
    public void barracks(){
        ui.mainTextArea.setText("You are in the Barracks.\nFrom here you can train your soldiers or adjust their budget.");
        ui.setImage(Img.title);

        castleDefault();

        game.choice("Recruit Soldiers", "recruit", "Train Soldiers", "train", "Loyalty Boost", "loyalty", "Adjust Budget", "bbudget");
    }
    public void file(){
        ui.mainTextArea.setText("Save or Load Game.");

        game.choice("Save Game", "save", "Load Game", "load", "", "", "Back", "office");
    }
    public void save(){
        ui.mainTextArea.setText("Save Game Files.");

        game.choice("Save to Game1", "save1", "Save to Game2", "save2", "Save to Game3", "save3", "Back", "file");
    }
    public void load(){
        ui.mainTextArea.setText("Load Game Files.");

        game.choice("Load Game1", "load1", "Load Game2", "load2", "Load Game3", "load3", "Back", "file");
    }
    public void recruit(){
        ui.mainTextArea.setText("You are in the Barracks.\nYou are discussing on the recruitment policy with your Chief Commander.\nThe Recruitment is influenced by the loyalty and the number of your subjects, it will cost gold too.\nRecruits per week: " + player.soldierProduction+ "\nRecruitment Status: " + player.recruitment);

        game.choice("Start Recruitment", "startrecruit", "Stop Recruitment", "stoprecruit", "", "", "Back", "barracks");
    }
    public void startRecruit(){

        if (!player.recruitment){
            player.recruitment = true;
            int recruits = player.ppl / 4;

            ui.mainTextArea.setText("You started recruiting soldiers.\nYou have " + player.ppl + " subjects.\nYou will recruit " + recruits + " recruits per week.");
        }else {
            ui.mainTextArea.setText("You are already recruiting soldiers.");
        }

        game.choice("Start Recruitment", "startrecruit", "Stop Recruitment", "stoprecruit", "", "", "Back", "barracks");
    }
    public void stopRecruit(){

        if (player.recruitment){
            player.recruitment = false;

            ui.mainTextArea.setText("You aren't recruiting anymore.");
        }else {
            ui.mainTextArea.setText("You aren't recruiting right now..");
        }

        game.choice("Start Recruitment", "startrecruit", "Stop Recruitment", "stoprecruit", "", "", "Back", "barracks");
    }
    public void loyalty(){
        ui.mainTextArea.setText("You are in the barracks of your soldiers.");

        game.choice("Give a Speech", "speech", "Give a Bonus", "bonus", "Entertainment", "entertainment", "Back", "barracks");
    }
    public void speech(){
        ui.mainTextArea.setText("You are in the barracks of your soldiers.\nSpend ");

        game.choice("", "", "", "", "", "", "Back", "loyalty");
    }
    public void basement(){
        ui.mainTextArea.setText("You are alone in the basement of your castle.");

        castleDefault();

        game.choice("Prison", "", "Hidden Room", "hiddenroom", "", "", "", "");
    }
    public void hiddenRoom(){
        ui.mainTextArea.setText("You are in your hidden room.\nHere you can do hidden rituals...");

        game.choice("Blood Magic", "bmagic", "Blood Rituals", "brituals", "Blood Summoning", "bsummoning", "Back", "basement");
    }
    public void defaultLawText(SuperLaw localLaw){
        ui.mainTextArea.setText("Adjusting Laws costs: 50 Gold and 25 Loyalty.\nLaw: " + localLaw.name + "\nState: " + player.law[localLaw.id] + "\nDescription: " + localLaw.description + "\nRequirements: " + localLaw.requirement);
    }public void law(){
        SuperLaw localLaw = laws[lawCount];

        defaultLawText(localLaw);

        game.choice("->", "nextavaiblelaw", "<-", "lastavaiblelaw", "Activate Law", "switchlaw", "Back", "office");
    }public void switchLaw(int count) {

        SuperLaw localLaw = laws[lawCount];

        if (!player.law[localLaw.id]) {
            if (player.gold > laws[count].gold && player.ppl > laws[count].ppl && player.soldier > laws[count].soldier && player.tier > laws[count].tier && player.loyalty > laws[count].loyalty && player.power > laws[count].power && player.gold > 50 && player.loyalty > 25) {
                player.gold = player.gold - 50;
                player.loyalty = player.loyalty - 25;
                player.law[localLaw.id] = true;
                defaultLawText(localLaw);
            }else {
                ui.mainTextArea.setText("Adjusting Laws costs: 50 Gold and 25 Loyalty.\nLaw: " + localLaw.name + "\nState: " + player.law[localLaw.id] + "\nDescription: " + localLaw.description + "\nRequirements: " + localLaw.requirement + "\n\nRequirements not met!");
            }
        }else {
            if(player.gold > 50 && player.loyalty > 25){
                player.gold = player.gold - 50;
                player.loyalty = player.loyalty - 25;
                player.law[localLaw.id] = false;
                defaultLawText(localLaw);
            }else {
                ui.mainTextArea.setText("Adjusting Laws costs: 50 Gold and 25 Loyalty.\nLaw: " + localLaw.name + "\nState: " + player.law[localLaw.id] + "\nDescription: " + localLaw.description + "\nRequirements: " + localLaw.requirement + "\n\nRequirements not met!");
            }
        }
        updateStats();
    }
    public void sleep(){
        ui.mainTextArea.setText("How long do you want to wait?");

        game.choice("Wait for a Day", "day", "Wait for a Week", "week", "", "", "Back", "office");
    }
    public void tax(){
        int goldDif = player.goldProduction - player.goldConsumption;
        ui.mainTextArea.setText("The Tax in your land is set on " + player.tax + ",\nwith " + player.ppl  +" subjects in your lands you make " + player.goldProduction + ".\nAfter " + player.goldConsumption + " gold consumption, your true gold balance is at: " + goldDif);

        game.choice("Raise Tax", "raise", "Decrease Tax", "decrease", "", "", "Back", "office");
    }
    public void taxRaise(){
        ui.mainTextArea.setText("Tax " + player.tax + "\nRaising Tax can gain you gold but with high tax you lose loyalty and power.");

        game.choice("Raise by 1", "raise1", "Raise by 5", "raise5", "Raise10", "raise10", "Back", "office");
    }
    public void taxDecrease(){
        ui.mainTextArea.setText("Tax " + player.tax + "\nDecreasing Tax helps gaining loyalty and power.");

        game.choice("Decrease by 1", "decrease1", "Decrease by 5", "decrease5", "Decrease by 10", "decrease10", "Back", "office");
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
