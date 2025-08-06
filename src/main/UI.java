package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font purisaB;
    BufferedImage keyImage;
    public boolean messageON = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    double playTime;
//    boolean runTime = true;
//    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){

        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/PowerStationSolidRus-Regular.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(30F);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageON = true;
    }

    public void draw(Graphics2D g2) {

            this.g2 = g2;

            //FINAL STATE
        if (gp.gameState == gp.finalState) {
            drawFinalScreen();
        }

            //TITLE STATE
            if(gp.gameState == gp.titleState){

            drawTitleScreen();

            } else {

                g2.setColor(Color.black);
                g2.setFont(purisaB);
                g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
                g2.drawString("х " + gp.player.hasKey, 74, 65);

                //TIME
                g2.setFont(purisaB);
                playTime += (double) 1 / 60;
//                g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize * 12, 64);
                g2.drawString("Time:" + gp.timer.getFormattedTime(), gp.tileSize * 12, 64);

                //MESSAGE
                if (messageON == true) {

                    drawSubWindow(gp.tileSize*2, gp.tileSize*10, (gp.screenWidth - (gp.tileSize*4)), gp.tileSize);
                    g2.setFont(purisaB);
                    int x = getXforCenteredText(message);
                    g2.drawString(message, x, gp.tileSize * 11 - 12);

                    messageCounter++;

                    if (messageCounter > 100) { //120 = 2 сек на сообщение
                        messageCounter = 0;
                        messageON = false;
                    }
                }


                //PAUSE STATE
                if (gp.gameState == gp.pauseState) {
                    drawPauseScreen();
                }

                //DIALOGUE STATE
                if (gp.gameState == gp.dialogueState) {
                    if(currentDialogue != null) {
                        drawDialogueScreen();
                    }
                }


                if (gameFinished == true) {
                    gp.timer.stop();

                }
            }

        }

        public void drawTitleScreen() {

            //BG COLOR
            g2.setColor(Color.black);
            g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 74F));
            String text = ".♥.SKYRIN GAME.♥.";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            //SHADOW
            g2.setColor(new Color(96, 101, 108, 255));
            g2.drawString(text, x+4, y+4);

            //MAIN COLOR
            Color c = new Color(40, 236, 236, 255);
            g2.setColor(c);
            g2.drawString(text, x, y);

            //ПЕРС ТАЙТЛ СКРИН
            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.titleScreenImage, x, y, gp.tileSize*2, gp.tileSize*2, null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize*4;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "EXIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x-gp.tileSize, y);
            }

        }

        public void drawFinalScreen() {

        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);


        g2.setFont(g2.getFont().deriveFont(30F));
        g2.setColor(Color.red);
//        String text1 = "Total time: " + dFormat.format(playTime) + "!";
        String text1 = "Total time: " + gp.timer.getFormattedTime() + "!";
        int x1 = getXforCenteredText(text1);
        int y1 = gp.screenHeight/2 + (gp.tileSize*4);
        g2.drawString(text1, x1, y1);

        String text2 = "ПОЗДРАВЛЯЮ!!!";
        g2.setFont(g2.getFont().deriveFont(60F));
        g2.setColor(Color.red);
        int x2 = getXforCenteredText(text2);
        int y2 = gp.screenHeight/2 + (gp.tileSize*2);
        g2.drawString(text2, x2, y2);
        g2.setColor(Color.black);
        g2.drawString(text2, x2-2, y2-2);


            g2.setColor(Color.red);
            String text3 = "RESTART";
            int x3 = getXforCenteredText(text3);
            int y3 = gp.tileSize*4;
            g2.drawString(text3, x3, y3);
            if(commandNum == 0) {
                g2.drawString(">", x3-gp.tileSize, y3);
            }

            String text4 = "EXIT";
            int x4 = getXforCenteredText(text4);
            int y4 = gp.tileSize*5;
            g2.drawString(text4, x4, y4);
            if(commandNum == 1) {
                g2.drawString(">", x4-gp.tileSize, y4);
            }


//        g2.setFont(g2.getFont().deriveFont(30F));
//        g2.setColor(Color.red);
//        String text3 = "Skyrin's best time: 102,18 ♥";
//        int x3 = getXforCenteredText(text2);
//        int y3 = gp.screenHeight/2 + (gp.tileSize*5);
//        g2.drawString(text3, x3, y3);


        }

        public void drawPauseScreen() {

        g2.setFont(new Font("Arial", Font.BOLD, 40));
        String text = "ЖДУ...٩(„• ᴗ •„)۶";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2 - gp.tileSize; // середина экрана по высоте

        g2.drawString(text, x, y);
        }

        public void drawDialogueScreen() {

            //WINDOW
            int x = gp.tileSize * 2; //можно просто номер плитки указать
            int y = gp.tileSize/2;
            int width = gp.screenWidth - (gp.tileSize*4);
            int height = gp.tileSize*5;

            drawSubWindow(x, y, width, height);

            g2.setFont(new Font("Arial", Font.BOLD, 20));
            x += gp.tileSize;
            y += gp.tileSize;

            if(currentDialogue != null) {
                for (String line : currentDialogue.split("\n")) {
                    g2.drawString(line, x, y);
                    y += 40;
                }
            }
        }

        public void drawSubWindow(int x, int y, int width, int height) {
            Color c = new Color(0,25,35, 220); //4е число - опасити прозрачность
            g2.setColor(c);
            g2.fillRoundRect(x,y, width, height, 35, 35);

            c = new Color(255, 255, 255);
            g2.setColor(c);
            g2.setStroke(new BasicStroke(4)); // ширина обводки
            g2.drawRoundRect(x+5, y+5, width-5, height-5, 25, 25);
        }

        public int getXforCenteredText(String text) {
            int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = gp.screenWidth/2 - length/2;
            return x;
        }


}

