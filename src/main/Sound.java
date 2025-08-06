package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0]  = getClass().getResource("/sound/BACKGROUNDMUS.wav");
        soundURL[1]  = getClass().getResource("/sound/coin.wav");
        soundURL[2]  = getClass().getResource("/sound/Поздравляю!.wav");
        soundURL[3]  = getClass().getResource("/sound/powerup.wav");
        soundURL[5]  = getClass().getResource("/sound/unlock.wav");
        soundURL[6]  = getClass().getResource("/sound/record3.wav");
        soundURL[7]  = getClass().getResource("/sound/moan2.wav");
        soundURL[8]  = getClass().getResource("/sound/bg3.wav");
        soundURL[9]  = getClass().getResource("/sound/Кишлак2.wav");
        soundURL[10]  = getClass().getResource("/sound/yo1.wav");
        soundURL[11]  = getClass().getResource("/sound/yo2.wav");
        soundURL[12]  = getClass().getResource("/sound/yo3.wav");
        soundURL[13]  = getClass().getResource("/sound/yo4.wav");
        soundURL[14]  = getClass().getResource("/sound/yo5.wav");
    }

    public void setFile(int i){

        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch(Exception e){

        }
    }

    public void play(){
        clip.start();

    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stop(){
        clip.stop();

    }
}
