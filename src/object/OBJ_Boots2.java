package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots2 extends SuperObject{
    GamePanel gp;

    public OBJ_Boots2 (GamePanel gp){
        this.gp = gp;
        name = "Boots2";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots2.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}