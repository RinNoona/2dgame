package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener { // считывает то что вводим с клавиатуры

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //TITLE STATE
        if (gp.gameState == gp.titleState) {

            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.timer.start();
                }
                if (gp.ui.commandNum == 1) {
                    gp.playSE(7);
                    System.exit(0);
                }
            }
        }

        //PLAY STATE

        if (gp.gameState == gp.playState) {

            if (code == KeyEvent.VK_W) {
                upPressed = true;

            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;

            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;

            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;

            }
        }

            //DIALOGUE STATE
            if (gp.gameState == gp.dialogueState) {
                if (code == KeyEvent.VK_ENTER) {
                    gp.gameState = gp.playState;
                }
            }

            //FINAL STATE
            if (gp.gameState == gp.finalState) {

                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 1;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 1) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {

                        gp.gameState = gp.playState;
                        gp.restart();
//                        gp.timer.start();

                    }
                    if (gp.ui.commandNum == 1) {
                        gp.playSE(7);
                        gp.gameThread = null;
                        System.exit(0);
                    }
                }
            }



    }

    @Override
    public void keyReleased(KeyEvent e) { //отпущенная клавиша
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;

        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;

        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;

        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;

        }

    }

}

