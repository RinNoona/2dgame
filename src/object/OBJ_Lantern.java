package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Lantern extends SuperObject{
    GamePanel gp;

    public OBJ_Lantern(GamePanel gp){
        this.gp = gp;
        name = "Lantern";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/lantern.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}