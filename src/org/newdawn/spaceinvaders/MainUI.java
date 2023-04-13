package org.newdawn.spaceinvaders;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainUI extends JPanel {
    Font NeoDung;
    Graphics2D g2;
    GameLobbyPanel glp;
    BufferedImage background, gameLogo, satellite, gameLogo_shadow, choiceButton;
    public int commandNum = 4;
    public MainUI(GameLobbyPanel glp){
        this.glp = glp;
        try {
            InputStream is = getClass().getResourceAsStream("fonts/NeoDunggeunmoPro-Regular.ttf");
            NeoDung = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        setFont(NeoDung);
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

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(NeoDung);
        g2.setColor(Color.white);

        if(glp.gameState == glp.titleState){
            drawTitleScreen();
        }

        else if(glp.gameState == glp.shopState){
            //drawShopScreen();
        }

        else if(glp.gameState == glp.selectShipState){
            //drawSelectSipScreen();
        }

        else if(glp.gameState == glp.scoreRecordState){
            //drawRecordScreen();
        }
    }

    public void drawTitleScreen(){
        try{
            InputStream is = new BufferedInputStream(Files.newInputStream(Paths.get("ui/computer_back.png")));
            background = ImageIO.read(is);
            InputStream is2 = new BufferedInputStream(Files.newInputStream(Paths.get("ui/gamelogo.png")));
            gameLogo = ImageIO.read(is2);
            InputStream is3 = new BufferedInputStream(Files.newInputStream(Paths.get("ui/satellite.png")));
            satellite = ImageIO.read(is3);
            InputStream is4 = new BufferedInputStream(Files.newInputStream(Paths.get("ui/gamelogo_shadow.png")));
            gameLogo_shadow = ImageIO.read(is4);
            InputStream is5 = new BufferedInputStream(Files.newInputStream(Paths.get("ui/choice_tiny.png")));
            choiceButton = ImageIO.read(is5);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        g2.drawImage(background,0,0,null);
        g2.drawImage(gameLogo_shadow,231,145,null);
        g2.drawImage(gameLogo,225,139,null);
        g2.drawImage(satellite,111,98,null);



        String text = "";
        int x,y;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,35f));

        text = "start";
        x = 171;
        y = 450;
        g2.drawString(text,x, y);
        if(commandNum == 0){
            g2.drawImage(choiceButton,x - 30, y - 17,null);
        }
        text = "shop";
        x = 310;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawImage(choiceButton,x - 30, y - 17,null);
        }

        text = "user";
        x = 440;
        g2.drawString(text, x,y);
        if(commandNum == 2){
            g2.drawImage(choiceButton,x - 30, y - 17,null);
        }

        text = "quit";
        x = 570;
        g2.drawString(text, x,y);
        if(commandNum == 3){
            g2.drawImage(choiceButton,x - 30, y - 17,null);
        }

        text = "sign out";
        x = 575;
        y = 88;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20f));
        g2.drawString(text, x,y);
        if(commandNum == 4){
            g2.drawImage(choiceButton,x - 30, y - 17,null);
        }

        text = UserDB.nickname;
        x = getXforCenteredText(text);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,35f));
        g2.drawString(text, x, 350);

        text = "High Score:";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20f));
        g2.drawString(text, 315, 390);


        g2.setColor(Color.orange);
        g2.drawString(getDisplayScore(),419,390);
    }

    public int getXforCenteredText(String text){
        int stringWidth = g2.getFontMetrics().stringWidth(text);
        int xCoordinate = (glp.screenWidth - stringWidth) / 2;
        return xCoordinate;
    }

    public String getDisplayScore(){
        int scoreLength = (int)(Math.log10(UserDB.best_score) + 1);
        String displayScore = "";

        if(scoreLength == 1) { displayScore = "_ _ _ _ _ " + UserDB.best_score; }
        else if(scoreLength == 2) { displayScore = "_ _ _ _ " + UserDB.best_score; }
        else if(scoreLength == 3) { displayScore = "_ _ _ " + UserDB.best_score; }
        else if(scoreLength == 4) { displayScore = "_ _ " + UserDB.best_score; }
        else if(scoreLength == 5) { displayScore = "_ " + UserDB.best_score; }
        else if(scoreLength == 6) { displayScore = Integer.toString(UserDB.best_score); }
        else { displayScore = "_ _ _ _ _ " + UserDB.best_score;}

        return displayScore;
    }
}
