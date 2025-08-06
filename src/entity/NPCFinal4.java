package entity;

import main.GamePanel;

import java.util.Random;

public class NPCFinal4 extends Entity {
    public NPCFinal4(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {

        up1 = setup("/npcFinal/7");
        up2 = setup("/npcFinal/8");
        down1 = setup("/npcFinal/7");
        down2 = setup("/npcFinal/8");
        right1 = setup("/npcFinal/7");
        right2 = setup("/npcFinal/8");
        left1 = setup("/npcFinal/7");
        left2 = setup("/npcFinal/8");
    }

    public void setDialogue() {
        dialogues[0] =  "Поезд конечно же может вернуться," +
                "\nно на остановке уже будут" +
                "\nсовсем другие люди ٩(„• ᴗ •„)۶";


    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;//выбираем число от 1 до 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "right";
            }
            if (i > 75 && i <= 100) {
                direction = "left";
            }
            actionLockCounter = 0;
        }
    }

    public void speak() {

        gp.playSE(13);
        super.speak();
    }
}