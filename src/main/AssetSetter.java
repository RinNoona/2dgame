package main;

import entity.*;
import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter (GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 41 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 10 * gp.tileSize;
        gp.obj[1].worldY = 15 * gp.tileSize;

        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 12 * gp.tileSize;
        gp.obj[2].worldY = 37 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 32 * gp.tileSize;
        gp.obj[3].worldY = 15 * gp.tileSize;

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 34 * gp.tileSize;
        gp.obj[4].worldY = 31 * gp.tileSize;

        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 41 * gp.tileSize;
        gp.obj[5].worldY = 33 * gp.tileSize;

        gp.obj[6] = new SuperKey(gp);
        gp.obj[6].worldX = 41 * gp.tileSize;
        gp.obj[6].worldY = 39 * gp.tileSize;

        gp.obj[7] = new OBJ_Boots(gp);
        gp.obj[7].worldX = 39 * gp.tileSize;
        gp.obj[7].worldY = 14 * gp.tileSize;

        gp.obj[8] = new OBJ_Key(gp);
        gp.obj[8].worldX = 18 * gp.tileSize;
        gp.obj[8].worldY = 40 * gp.tileSize;

        gp.obj[9] = new OBJ_Door(gp);
        gp.obj[9].worldX = 22 * gp.tileSize;
        gp.obj[9].worldY = 34 * gp.tileSize;

        gp.obj[13] = new OBJ_Door2(gp);
        gp.obj[13].worldX = 35 * gp.tileSize;
        gp.obj[13].worldY = 43 * gp.tileSize;

        gp.obj[10] = new OBJ_Key(gp);
        gp.obj[10].worldX = 60 * gp.tileSize;
        gp.obj[10].worldY = 88 * gp.tileSize;

        gp.obj[11] = new OBJ_Key(gp);
        gp.obj[11].worldX = 24 * gp.tileSize;
        gp.obj[11].worldY = 73 * gp.tileSize;

        gp.obj[12] = new OBJ_Key(gp);
        gp.obj[12].worldX = 80 * gp.tileSize;
        gp.obj[12].worldY = 89 * gp.tileSize;

        gp.obj[14] = new OBJ_Door(gp);
        gp.obj[14].worldX = 11 * gp.tileSize;
        gp.obj[14].worldY = 80 * gp.tileSize;

        gp.obj[15] = new OBJ_Door(gp);
        gp.obj[15].worldX = 67 * gp.tileSize;
        gp.obj[15].worldY = 73 * gp.tileSize;

        gp.obj[16] = new OBJ_Door(gp);
        gp.obj[16].worldX = 79 * gp.tileSize;
        gp.obj[16].worldY = 55 * gp.tileSize;

//        gp.obj[17] = new OBJ_Chest(gp);
//        gp.obj[17].worldX = 88 * gp.tileSize;
//        gp.obj[17].worldY = 43 * gp.tileSize;

        gp.obj[17] = new OBJ_Chest(gp);
        gp.obj[17].worldX = 28 * gp.tileSize;
        gp.obj[17].worldY = 18 * gp.tileSize;

        gp.obj[18] = new OBJ_Lantern(gp);
        gp.obj[18].worldX = 8 * gp.tileSize;
        gp.obj[18].worldY = 37 * gp.tileSize;

        gp.obj[19] = new OBJ_Door(gp);
        gp.obj[19].worldX = 8 * gp.tileSize;
        gp.obj[19].worldY = 32 * gp.tileSize;

        gp.obj[20] = new OBJ_Key(gp);
        gp.obj[20].worldX = 17 * gp.tileSize;
        gp.obj[20].worldY = 15 * gp.tileSize;

        gp.obj[21] = new OBJ_Boots2(gp);
        gp.obj[21].worldX = 17 * gp.tileSize;
        gp.obj[21].worldY = 48 * gp.tileSize;

        gp.obj[22] = new OBJ_Key(gp);
        gp.obj[22].worldX = 46 * gp.tileSize;
        gp.obj[22].worldY = 52 * gp.tileSize;

        gp.obj[23] = new OBJ_Door(gp);
        gp.obj[23].worldX = 57 * gp.tileSize;
        gp.obj[23].worldY = 33 * gp.tileSize;

        gp.obj[24] = new Letter(gp);
        gp.obj[24].worldX = 23 * gp.tileSize;
        gp.obj[24].worldY = 14 * gp.tileSize;

    }

    public void setNPC() {
        gp.npc[0] = new NPC_ch(gp);
        gp.npc[0].worldX = 19 * gp.tileSize;
        gp.npc[0].worldY = 29 * gp.tileSize;

        gp.npc[1] = new NPC_ch2(gp);
        gp.npc[1].worldX = 36 * gp.tileSize;
        gp.npc[1].worldY = 40 * gp.tileSize;

        gp.npc[2] = new NPCFinal1(gp);
        gp.npc[2].worldX = 39 * gp.tileSize;
        gp.npc[2].worldY = 39 * gp.tileSize;

        gp.npc[3] = new NPCFinal2(gp);
        gp.npc[3].worldX = 27 * gp.tileSize;
        gp.npc[3].worldY = 79 * gp.tileSize;

        gp.npc[4] = new NPCFinal3(gp);
        gp.npc[4].worldX = 51 * gp.tileSize;
        gp.npc[4].worldY = 82 * gp.tileSize;

        gp.npc[5] = new NPCFinal4(gp);
        gp.npc[5].worldX = 51 * gp.tileSize;
        gp.npc[5].worldY = 50 * gp.tileSize;


    }
}
