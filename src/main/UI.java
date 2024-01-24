package main;

import javax.swing.*;
import java.awt.*;

public class UI {

    JFrame window;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, mainImgPanel, castleButtonPanel, choiceButtonPanel, playerPanel;
    JLabel titleNameLabel, mainImgLabel;
    JLabel dayLabel, goldLabel, taxLabel, foodLabel, woodLabel, pplLabel, soldierLabel, powerLabel;
    JButton startButton;
    public JButton choice1;
    public JButton choice2;
    public JButton choice3;
    public JButton choice4;
    public JButton castle1;
    public JButton castle2;
    public JButton castle3;
    public JButton castle4;
    public JTextArea mainTextArea;

    Font titleFont = new Font("Book Antiqua", Font.PLAIN, 70);
    Font textFont = new Font("Book Antiqua", Font.PLAIN, 24);

    public void createUI(Game.ChoiceHandler cHandler, Game.CastleHandler caHandler){


        // WINDOW
        window = new JFrame();
        window.setSize(1280,720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
        window.setVisible(true);

        // TITLE SCREEN
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100,100,1080,150);
        titleNamePanel.setBackground(Color.BLACK);
        titleNameLabel = new JLabel("Text-Based");
        titleNameLabel.setBounds(0,0,700,150);
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);

        // START BUTTON
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(500,400,200,100);
        startButtonPanel.setBackground(Color.BLACK);
        startButton = new JButton("START");
        startButton.setBounds(20,20,140,30);
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(textFont);
        startButton.setFocusPainted(false);
        startButton.addActionListener(cHandler);
        startButton.setActionCommand("start");
        startButtonPanel.add(startButton);

        window.add(titleNamePanel);
        window.add(startButtonPanel);

        // window.setVisible(true);

        //GAME SCREEN
        mainImgPanel = new JPanel();
        mainImgPanel.setBounds(480,50,400,600);
        mainImgPanel.setBackground(Color.BLACK);
        mainImgPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        mainImgLabel = new JLabel();
        mainImgLabel.setBackground(Color.BLACK);
        mainImgLabel.setBounds(2,2,790,390);
        mainImgPanel.add(mainImgLabel);
        window.add(mainImgPanel);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(50,50,410,400);
        mainTextPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        mainTextPanel.setBackground(Color.BLACK);
        window.add(mainTextPanel);

        mainTextArea = new JTextArea("Main-text-area");
        mainTextArea.setBounds(2,2,400,390);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);
        mainTextArea.setFocusable(false);
        mainTextPanel.add(mainTextArea);

        castleButtonPanel = new JPanel();
        castleButtonPanel.setBounds(50,500,410,150);
        castleButtonPanel.setBackground(Color.BLACK);
        castleButtonPanel.setLayout(new GridLayout(4,1));
        castleButtonPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        window.add(castleButtonPanel);

        castle1 = new JButton("Castle 1");
        castle1.setBounds(0,0,410,30);
        castle1.setBackground(Color.BLACK);
        castle1.setForeground(Color.WHITE);
        castle1.setFont(textFont);
        castle1.setFocusPainted(false);
        castle1.addActionListener(caHandler);
        castle1.setActionCommand("ca1");
        castleButtonPanel.add(castle1);
        castle2 = new JButton("Castle 2");
        castle2.setBounds(0,0,410,30);
        castle2.setBackground(Color.BLACK);
        castle2.setForeground(Color.WHITE);
        castle2.setFont(textFont);
        castle2.setFocusPainted(false);
        castle2.addActionListener(caHandler);
        castle2.setActionCommand("ca2");
        castleButtonPanel.add(castle2);
        castle3 = new JButton("Castle 3");
        castle3.setBounds(0,0,410,30);
        castle3.setBackground(Color.BLACK);
        castle3.setForeground(Color.WHITE);
        castle3.setFont(textFont);
        castle3.setFocusPainted(false);
        castle3.addActionListener(caHandler);
        castle3.setActionCommand("ca3");
        castleButtonPanel.add(castle3);
        castle4 = new JButton("Castle 4");
        castle4.setBounds(0,0,410,30);
        castle4.setBackground(Color.BLACK);
        castle4.setForeground(Color.WHITE);
        castle4.setFont(textFont);
        castle4.setFocusPainted(false);
        castle4.addActionListener(caHandler);
        castle4.setActionCommand("ca4");
        castleButtonPanel.add(castle4);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(900,500,300,150);
        choiceButtonPanel.setBackground(Color.BLACK);
        choiceButtonPanel.setLayout(new GridLayout(4,1));
        choiceButtonPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        window.add(choiceButtonPanel);

        choice1 = new JButton("Choice 1");
        choice1.setBounds(0,0,300,30);
        choice1.setBackground(Color.BLACK);
        choice1.setForeground(Color.WHITE);
        choice1.setFont(textFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(cHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);
        choice2 = new JButton("Choice 2");
        choice2.setBounds(0,0,300,30);
        choice2.setBackground(Color.BLACK);
        choice2.setForeground(Color.WHITE);
        choice2.setFont(textFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(cHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice3 = new JButton("Choice 3");
        choice3.setBounds(0,0,300,30);
        choice3.setBackground(Color.BLACK);
        choice3.setForeground(Color.WHITE);
        choice3.setFont(textFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(cHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);
        choice4 = new JButton("Choice 4");
        choice4.setBounds(0,0,300,30);
        choice4.setBackground(Color.BLACK);
        choice4.setForeground(Color.WHITE);
        choice4.setFont(textFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(cHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

        playerPanel = new JPanel();
        playerPanel.setBounds(900,50,300,400);
        playerPanel.setBackground(Color.BLACK);
        playerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        playerPanel.setLayout(new GridLayout(6,1));
        window.add(playerPanel);

        dayLabel = new JLabel();
        dayLabel.setFont(textFont);
        dayLabel.setForeground(Color.WHITE);
        dayLabel.setBounds(0,0,300,20);
        playerPanel.add(dayLabel);
        goldLabel = new JLabel();
        goldLabel.setFont(textFont);
        goldLabel.setForeground(Color.WHITE);
        goldLabel.setBounds(0,0,300,20);
        playerPanel.add(goldLabel);
        taxLabel = new JLabel();
        taxLabel.setFont(textFont);
        taxLabel.setForeground(Color.WHITE);
        taxLabel.setBounds(0,0,300,20);
        playerPanel.add(taxLabel);
        foodLabel = new JLabel();
        foodLabel.setFont(textFont);
        foodLabel.setForeground(Color.WHITE);
        foodLabel.setBounds(0,0,300,20);
        playerPanel.add(foodLabel);
        woodLabel = new JLabel();
        woodLabel.setFont(textFont);
        woodLabel.setForeground(Color.WHITE);
        woodLabel.setBounds(0,0,300,20);
        playerPanel.add(woodLabel);
        pplLabel = new JLabel();
        pplLabel.setFont(textFont);
        pplLabel.setForeground(Color.WHITE);
        pplLabel.setBounds(0,0,300,20);
        playerPanel.add(pplLabel);
        soldierLabel = new JLabel();
        soldierLabel.setFont(textFont);
        soldierLabel.setForeground(Color.WHITE);
        soldierLabel.setBounds(0,0,300,20);
        playerPanel.add(soldierLabel);
    }
}
