package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public int hasSuperKey = 0;
    public int hasLantern = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 20, 20);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 9;
        worldY = gp.tileSize * 8;
        speed = 6;
        direction = "down";
    }

    public void getPlayerImage(){

        up1 = setup("/player/ocCatBack_1");
        up2 = setup("/player/ocCatBack_2");
        down1 = setup("/player/ocCat_1");
        down2 = setup("/player/ocCat_2");
        right1 = setup("/player/ocCatRight_1");
        right2 = setup("/player/ocCatRight_2");
        left1 = setup("/player/ocCatLeft_1");
        left2 = setup("/player/ocCatLeft_2");
        titleScreenImage = setup("/player/titleScreenImage");
    }


    public void update(){ //вызывается 60 раз в секунду

        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.rightPressed == true || keyH.leftPressed == true){
            if (keyH.upPressed == true){
                direction = "up";

            } else if (keyH.downPressed == true){
                direction = "down";

            } else if (keyH.rightPressed == true){
                direction = "right";

            } else if (keyH.leftPressed == true){
                direction = "left";

            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //если коллижн фолс - игрок двигается
            if(collisionOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 8){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }
    public void pickUpObject(int i){

        if(i != 999){
            String objectName = gp.obj[i].name;

            switch (objectName){
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null; //этот объект исчезает после поднятия
                    gp.ui.showMessage("ю гат дэ ки");
                    break;
                case "superKey":
                    gp.playSE(1);
                    hasSuperKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("ю гат дэ супа ки");
                    break;
                case "Door":
                    gp.playSE(5);
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("ну проходи блин!!");
                    } else {
                        gp.ui.showMessage("У ТЕБЯ НЕТ КЛЮЧА...");
                    }
                    break;
                case "Boots":
                    gp.playSE(3);
                    speed += 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("спид ап");
                    break;

                case "Boots2":
                    gp.playSE(3);
                    speed += 5;
                    gp.obj[i] = null;
                    gp.stopMusic();
                    gp.playMusic(9);
                    gp.ui.showMessage("СПИД АП НА КИШЛАКЕ");
                    break;

                case "Letter":
                    gp.obj[i] = null;
                    gp.ui.showMessage("не бери лампочку...");
                    break;

                case "Door2":
                    gp.playSE(5);
                    if(hasSuperKey > 0){
                        gp.obj[i] = null;
                        hasSuperKey--;
                        gp.ui.showMessage("ПЛАТО АЛЬТУС <3");
                    } else {
                        gp.ui.showMessage("У ТЕБЯ НЕТ СУПЕР КЛЮЧА...");
                    }
                    break;

                case "Lantern":
                    gp.playSE(1);
                    hasLantern++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("не бери в след раз");

                    break;

                case "Chest":
                    gp.stopMusic();
                    gp.playSE(2);
                    gp.player.speed = 6;
                    gp.gameState = gp.finalState;
                    gp.ui.messageON = false;
                    gp.ui.gameFinished = true;
                    break;
            }
        }
    }

    public void setDefaultPosition() {
        worldX = gp.tileSize * 9;
        worldY = gp.tileSize * 8;
        speed = 6;
        direction = "down";
        spriteCounter = 0;
    }

    public void interactNPC(int i){
        if(i != 999){
            if(gp.ui.currentDialogue != null) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                } if (spriteNum == 2){
                    image = up2;
            }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                } if (spriteNum == 2){
                    image = down2;
            }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                } if (spriteNum == 2){
                    image = right2;
            }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                } if (spriteNum == 2){
                    image = left2;
            }
                break;

        }

        g2.drawImage(image, screenX, screenY, null);

    }
}
