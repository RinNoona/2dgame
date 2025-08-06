package main;

import entity.Entity;
import entity.Player;
//import environment.EnvironmentManager;
import environment.EnvironmentManager;
import environment.Lighting;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; // 16x16 pixels
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //
    public final int screenHeight = tileSize * maxScreenRow; //

    // WORLD SETTINGS
    public int maxWorldCol;
    public int maxWorldRow;

    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public  CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EnvironmentManager eManager = new EnvironmentManager(this);
    Thread gameThread;
    Thread timerThread;
    SimpleGameTimer timer = new SimpleGameTimer(this);

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[30];
    public Entity npc[] = new Entity[20];

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int finalState = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        playMusic(8);
        gameState = titleState;
        eManager.setup();
    }

    public void restart() {
        player.setDefaultPosition();
        aSetter.setNPC();
        aSetter.setObject();
        playMusic(8);
        player.hasKey = 0;
        timer.start();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 1 секунда = 1 миллиард наносекунд (0.01666 секунд)
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            // 1 UPDATE апдейтим информацию например позицию персонажа
            update();
            // 2 DRAW рисуем фрейм в соответствии с обновленной информацией
            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // количество времени до следующего репэинта
                remainingTime = remainingTime/1000000; // переводим в миллисекунды
                if(remainingTime <0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){

        if(gameState == playState){

            //PLAYER
            player.update();

            //NPC
            for(int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }

            eManager.update();
        }

        if(gameState == pauseState) {
            //ниче не делаем
        }

    }



    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        }

        //OTHERS
        else {

            //TILE
            tileM.draw(g2);

            //OBJECT
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            //NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }

            //PLAYER
            player.draw(g2);

            //ENVIRONMENT
            eManager.draw(g2);

            //UI
            ui.draw(g2);
            g2.dispose(); //экономия памяти
        }


    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){ //sound effect
        se.setFile(i);
        se.play();
    }

}
