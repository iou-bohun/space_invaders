package org.newdawn.spaceinvaders;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainUI {
    Font NeoDung;
    public MainUI(){
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
}
