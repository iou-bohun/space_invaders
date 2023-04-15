package org.newdawn.spaceinvaders;

import java.io.File;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class BGM {
    public BGM(){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("space_invaders/src/org/newdawn/spaceinvaders/sound/game_back_music_sound.mp3"));
            Clip clip = AudioSystem.getClip();
            clip.open();

            //소리 설정
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            //볼륨 조절
            gainControl.setValue(-30.0f);
            clip.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
