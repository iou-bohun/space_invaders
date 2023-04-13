package org.newdawn.spaceinvaders;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class MainUI extends JPanel {
    Font NeoDung;
    Graphics2D g2;
    GameLobbyPanel glp;
    BufferedImage background, gameLogo, satellite, gameLogo_shadow, choiceButton;
    BufferedImage coinImg, healPotion, speedPotion, hardShip, luckShip;
    public int commandNum = -2;
    public boolean coinLackState = false;
    public boolean possState = false;
    public boolean purchaseState = false;

    //상점 가격
    public static int hpcoin = 50;
    public static int spcoin = 50;
    public static int hscoin = 1000;
    public static int lscoin = 1500;

    public MainUI(GameLobbyPanel glp){
        this.glp = glp;
        loadTitleImg();
        try {
            InputStream is = getClass().getResourceAsStream("fonts/NeoDunggeunmoPro-Regular.ttf");
            NeoDung = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        //setFont(NeoDung);
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        if(glp.gameState == glp.titleState){
            drawTitleScreen();
        }

        else if(glp.gameState == glp.shopState){
            drawShopScreen();
        }

        else if(glp.gameState == glp.selectShipState){
            //drawSelectSipScreen();
        }

        else if(glp.gameState == glp.scoreRecordState){
            //drawRecordScreen();
        }
    }

    public void drawTitleScreen(){

        g2.drawImage(background,0,0,null);
        g2.drawImage(gameLogo_shadow,231,145,null);
        g2.drawImage(gameLogo,225,139,null);
        g2.drawImage(satellite,111,98,null);

        g2.setFont(NeoDung);
        g2.setColor(Color.white);

        String text = "";
        String additionalText = "";
        int x,y;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,35f));
        text = "start";
        x = 171;
        y = 450;
        g2.drawString(text,x, y);
        if(commandNum == 0){
            selectOption(x,y,text);
            additionalText = "Start the game. There are a total of five stages and each stage has a boss. Good luck!";
            showExplanation(additionalText);
        }

        text = "shop";
        x = 310;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            selectOption(x,y,text);
            additionalText = "You can use the coins you got during the game. Be Stronger!";
            showExplanation(additionalText);
        }

        text = "user";
        x = 440;
        g2.drawString(text, x,y);
        if(commandNum == 2){
            selectOption(x,y,text);
            additionalText = "You can view the history of your play so far. Check out your efforts.";
            showExplanation(additionalText);
        }

        text = "quit";
        x = 570;
        g2.drawString(text, x,y);
        if(commandNum == 3){
            selectOption(x,y,text);
            additionalText = "Save and exit the game.";
            showExplanation(additionalText);
        }

        text = "sign out";
        x = 575;
        y = 88;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20f));
        g2.drawString(text, x,y);
        if(commandNum == 4){
            selectOption(x,y,text,true);
            additionalText = "Save the game and sign out.";
            showExplanation(additionalText);
        }

        text = UserDB.nickname;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,35f));
        x = getXforCenteredText(text);
        y = 350;
        g2.drawString(text, x, y);
        if(commandNum == 5){
            selectOption(x,y,text);
            additionalText = "Change your nickname.";
            showExplanation(additionalText);
        }

        text = "High Score:";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20f));
        g2.drawString(text, 315, 390);
        g2.setColor(Color.orange);
        g2.drawString(getDisplayScore(),419,390);
    }

    public void drawShopScreen(){

        g2.drawImage(background,0,0,null);
        g2.drawImage(healPotion,113,273,null);
        g2.drawImage(speedPotion,269,273,null);
        g2.drawImage(hardShip,440,255,null);
        g2.drawImage(luckShip,591,255,null);
        g2.drawImage(coinImg,552,114,null);
        g2.drawImage(coinImg,126,398,null);
        g2.drawImage(coinImg,284,398,null);
        g2.drawImage(coinImg,459,398,null);
        g2.drawImage(coinImg,613,398,null);

        g2.setFont(NeoDung);
        g2.setColor(Color.white);

        String text = "";
        String additionalText = "";
        int x,y;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50f));
        text = "shop";
        x = 120;
        y = 130;
        g2.drawString(text,x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30f));
        text = "potion";
        x = 106;
        y = 223;
        g2.drawString(text,x, y);

        text = "ship";
        x = 443;
        g2.drawString(text,x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20f));
        text = "heal potion";
        x = 99;
        y = 378;
        g2.drawString(text,x, y);
        if(commandNum == 0){
                selectOption(x,y,text,false);
        }

        text = "speed potion";
        x = 250;
        g2.drawString(text,x, y);
        if(commandNum == 1){
                selectOption(x,y,text,false);
        }

        text = "hard ship";
        x = 449;
        g2.drawString(text,x, y);
        if(commandNum == 2){
            selectOption(x,y,text,false);
        }

        text = "lucky ship";
        x = 598;
        g2.drawString(text,x, y);
        if(commandNum == 3){
            selectOption(x,y,text,false);
        }

        text = Integer.toString(hpcoin);
        x = 160;
        y = 418;
        g2.drawString(text,x, y);
        if(commandNum == 0){
            selectOptionWithImg(x,y,text);
            if(!coinLackState && !purchaseState) {
                additionalText = "Restore your 1 HP.";
                showExplanation(25f, 20f, Color.lightGray, 180, additionalText);
            }
            if(coinLackState) {
                String notice = "Not enough coins :(";
                showExplanation(25f,20f,Color.RED,180,notice);
            }
            if(purchaseState){
                String notice = "Purchased! :)";
                showExplanation(25f,20f,Color.CYAN,180,notice);
            }
        }

        text = Integer.toString(spcoin);
        x = x + 158;
        g2.drawString(text,x, y);
        if(commandNum == 1){
            selectOptionWithImg(x, y, text);
            if(!coinLackState && !purchaseState) {
                additionalText = "Can move FASTER!";
                showExplanation(25f, 20f, Color.lightGray, 180, additionalText);
            }
            if(coinLackState){
                String notice = "Not enough coins :(";
                showExplanation(25f,20f,Color.RED,180,notice);
            }
            if(purchaseState){
                String notice = "Purchased! :)";
                showExplanation(25f,20f,Color.CYAN,180,notice);
            }
        }

        text = Integer.toString(hscoin);
        x = x + 175;
        g2.drawString(text,x, y);
        if(commandNum == 2){
            selectOptionWithImg(x,y,text);
            if(!coinLackState && !possState && !purchaseState) {
                additionalText = "MAX HP increases by 2!";
                showExplanation(25f, 20f, Color.lightGray, 180, additionalText);
            }
            if(coinLackState) {
                String notice = "Not enough coins :(";
                showExplanation(25f,20f,Color.RED,180,notice);
            }
            if(possState){
                String notice = "You already have this ship.";
                showExplanation(25f,20f,Color.orange,180,notice);
            }
            if(purchaseState){
                String notice = "Purchased! Can equip in user.";
                showExplanation(25f,20f,Color.CYAN,180,notice);
            }
        }

        text = Integer.toString(lscoin);
        x = x + 154;
        g2.drawString(text,x, y);
        if(commandNum == 3){
            selectOptionWithImg(x,y,text);
            if(!coinLackState && !possState && !purchaseState) {
                additionalText = "Luck increases by 10%. For more coins!";
                showExplanation(25f, 20f, Color.lightGray, 180, additionalText);
            }
            if(coinLackState) {
                String notice = "Not enough coins :(";
                showExplanation(25f,20f,Color.RED,180,notice);
            }
            if(possState){
                String notice = "You already have this ship.";
                showExplanation(25f,20f,Color.orange,180,notice);
            }
            if(purchaseState){
                String notice = "Purchased! Can equip in user.";
                showExplanation(25f,20f,Color.CYAN,180,notice);
            }
        }

        text = Integer.toString(UserDB.coin);
        x = 585;
        y = 135;
        g2.drawString(text,x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30f));
        text = "back to title";
        x = getXforCenteredText(text);
        y = 490;
        g2.drawString(text,x, y);
        if(commandNum == 4){
            selectOption(x,y,text);
        }
    }

    public int getXforCenteredText(String text){
        int stringWidth = g2.getFontMetrics().stringWidth(text);
        int xCoordinate = (glp.screenWidth - stringWidth) / 2;
        return xCoordinate;
    }

    public String getDisplayScore(){
        int scoreLength = (int)(Math.log10(UserDB.best_score) + 1);
        String displayScore = "";
        DecimalFormat df = new DecimalFormat("###,###");
        String scoreComma = df.format(UserDB.best_score);

        if(scoreLength == 1) { displayScore = "_ _ _ _ _ " + scoreComma; }
        else if(scoreLength == 2) { displayScore = "_ _ _ _ " + scoreComma; }
        else if(scoreLength == 3) { displayScore = "_ _ _ " + scoreComma; }
        else if(scoreLength == 4) { displayScore = "_ _ " + scoreComma; }
        else if(scoreLength == 5) { displayScore = "_ " + scoreComma; }
        else if(scoreLength == 6) { displayScore = scoreComma; }
        else { displayScore = "_ _ _ _ _ " + scoreComma;}

        return displayScore;
    }

    public void selectOption(int x, int y, String text){
        g2.drawImage(choiceButton,x - 30, y - 20,null);
        g2.setColor(Color.gray);
        g2.drawString(text,x,y);
        g2.setColor(Color.white);
        g2.drawString(text,x-3,y-3);
    }

    public void selectOption(int x, int y, String text, boolean img){
        if (img) g2.drawImage(choiceButton,x - 30, y - 20,null);
        g2.setColor(Color.gray);
        g2.drawString(text,x,y);
        g2.setColor(Color.white);
        g2.drawString(text,x-2,y-2);
    }

    public void selectOptionWithImg(int x, int y, String text){
        g2.drawImage(choiceButton,x - 60, y - 15,null);
        g2.setColor(Color.gray);
        g2.drawString(text,x,y);
        g2.setColor(Color.white);
        g2.drawString(text,x-2,y-2);
    }

    public void selectOption(int x, int y){
        g2.drawImage(choiceButton,x - 30, y - 20,null);
    }

    public void showExplanation(String text){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15f));
        g2.setColor(Color.lightGray);
        int x = getXforCenteredText(text);
        int y = 500;
        g2.drawString(text, x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35f));
        g2.setColor(Color.white);
    }

    public void showExplanation(float fontsize, float fontsize2,Color c,int y, String text){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, fontsize));
        g2.setColor(c);
        int x = getXforCenteredText(text);
        g2.drawString(text, x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, fontsize2));
        g2.setColor(Color.white);
    }

    public void setFontNeo(JComponent jc,float size){
        try {
            InputStream is = getClass().getResourceAsStream("fonts/NeoDunggeunmoPro-Regular.ttf");
            NeoDung = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN,size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        jc.setFont(NeoDung);
    }

    public void loadTitleImg() {
        try {
            //타이틀 이미지 로딩
            InputStream is = new BufferedInputStream(Files.newInputStream(Paths.get("src/ui/computer_back.png")));
            background = ImageIO.read(is);
            InputStream is2 = new BufferedInputStream(Files.newInputStream(Paths.get("src/ui/gamelogo.png")));
            gameLogo = ImageIO.read(is2);
            InputStream is3 = new BufferedInputStream(Files.newInputStream(Paths.get("src/ui/satellite.png")));
            satellite = ImageIO.read(is3);
            InputStream is4 = new BufferedInputStream(Files.newInputStream(Paths.get("src/ui/gamelogo_shadow.png")));
            gameLogo_shadow = ImageIO.read(is4);
            InputStream is5 = new BufferedInputStream(Files.newInputStream(Paths.get("src/ui/choice_tiny.png")));
            choiceButton = ImageIO.read(is5);

            //상점 이미지 로딩
            InputStream is6 = new BufferedInputStream(Files.newInputStream(Paths.get("src/ui/coin.png")));
            coinImg = ImageIO.read(is6);
            InputStream is7 = new BufferedInputStream(Files.newInputStream(Paths.get("src/ui/heal_potion.png")));
            healPotion = ImageIO.read(is7);
            InputStream is8 = new BufferedInputStream(Files.newInputStream(Paths.get("src/ui/speed_potion.png")));
            speedPotion = ImageIO.read(is8);
            InputStream is9 = new BufferedInputStream(Files.newInputStream(Paths.get("src/ui/hard_ship.png")));
            hardShip = ImageIO.read(is9);
            InputStream is10 = new BufferedInputStream(Files.newInputStream(Paths.get("src/ui/lucky_ship.png")));
            luckShip = ImageIO.read(is10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
