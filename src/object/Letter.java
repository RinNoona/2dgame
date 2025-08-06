package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Letter extends SuperObject{
    GamePanel gp;

    public Letter(GamePanel gp){
        this.gp = gp;
        name = "Letter";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/letter.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
