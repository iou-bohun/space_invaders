package org.newdawn.spaceinvaders;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainUI extends JPanel {
    Font NeoDung;
    Graphics2D g2;
    GameLobbyPanel glp;
    BufferedImage background, gameLogo;
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
        /*try{
            background = ImageIO.read(getClass().getResourceAsStream("sprites/alien3.gif"));
            gameLogo = ImageIO.read(getClass().getResourceAsStream("sprites/alien.gif"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        g2.drawImage(background,0,0,null);
        g2.drawImage(gameLogo,225,157,null);*/
        String text = "";
        int x = getXforCenteredText(text);
        int y = 300;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48f));
        text = "Game Start";
        //x = getXforCenteredText(text);
        y += 50;
        g2.drawString(text,x,y);
    }

    public int getXforCenteredText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = 400 - length/2;
        return x;
    }
}
