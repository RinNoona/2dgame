package entity;

import main.GamePanel;

import java.util.Random;

public class NPCFinal3 extends Entity {
    public NPCFinal3(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {

        up1 = setup("/npcFinal/5");
        up2 = setup("/npcFinal/6");
        down1 = setup("/npcFinal/5");
        down2 = setup("/npcFinal/6");
        right1 = setup("/npcFinal/5");
        right2 = setup("/npcFinal/6");
        left1 = setup("/npcFinal/5");
        left2 = setup("/npcFinal/6");
    }

    public void setDialogue() {
        dialogues[0] = "Логика может привести тебя из пункта А " +
                "\nв пункт Б, а воображение — куда угодно. " +
                "\n                           =^_^=";

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