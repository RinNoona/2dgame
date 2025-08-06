package entity;

import main.GamePanel;

import java.util.Random;

public class NPCFinal2 extends Entity {
    public NPCFinal2(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {

        up1 = setup("/npcFinal/3");
        up2 = setup("/npcFinal/4");
        down1 = setup("/npcFinal/3");
        down2 = setup("/npcFinal/4");
        right1 = setup("/npcFinal/3");
        right2 = setup("/npcFinal/4");
        left1 = setup("/npcFinal/3");
        left2 = setup("/npcFinal/4");
    }

    public void setDialogue() {
        dialogues[0] = "Программист ставит себе на ночь 2 стакана: " +
                "\nодин с водой, второй - пустой." +
                "\nЕсли ночью захочет пить - " +
                "\nвозьмет стакан с водой." +
                "\nЕсли не захочет - то пустой...";

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