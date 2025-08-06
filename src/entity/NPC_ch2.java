package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_ch2 extends Entity{
    public NPC_ch2(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getNPCImage();
        setDialogue();
    }

    public void getNPCImage(){

        up1 = setup("/npcFinal/9");
        up2 = setup("/npcFinal/10");
        down1 = setup("/npcFinal/9");
        down2 = setup("/npcFinal/10");
        right1 = setup("/npcFinal/9");
        right2 = setup("/npcFinal/10");
        left1 = setup("/npcFinal/9");
        left2 = setup("/npcFinal/10");
    }

    public void setDialogue() {
        dialogues[0] = "Попроси программиста проверить " +
                "\n10 строк кода, он найдёт 10 проблем." +
                "\nПопроси его проверить 500 строк,  " +
                "\nон скажет, что выглядит норм." +
                "\n (´• ω •`)";

    }


    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;

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

