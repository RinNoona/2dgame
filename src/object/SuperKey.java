package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class SuperKey extends SuperObject{

    GamePanel gp;

    public SuperKey(GamePanel gp){
        this.gp = gp;
        name = "superKey";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/superKey.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
