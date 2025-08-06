package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting {

    GamePanel gp;
    BufferedImage darknessFilter;

    int dayCounter;
    float filterAlpha = 0f;

    //Day State
    final int day = 0;
    final int dusk = 1;
    final int night = 2;
    final int dawn = 3;
    int dayState = day;

    public Lighting(GamePanel gp) {

        this.gp = gp;
        update();
    }

        public void setLight() {

            darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

            // центр х и у светового круга
            int centerX = gp.player.screenX + (gp.tileSize) / 2;
            int centerY = gp.player.screenY + (gp.tileSize) / 2;

            //размываем границы круга
            Color color[] = new Color[5];
            float fraction[] = new float[5];

            color[0] = new Color(0, 0, 0, 0f);
            color[1] = new Color(0, 0, 0, 0.25f);
            color[2] = new Color(0, 0, 0, 0.5f);
            color[3] = new Color(0, 0, 0, 0.75f);
            color[4] = new Color(0, 0, 0, 0.98f);

            fraction[0] = 0f;
            fraction[1] = 0.25f;
            fraction[2] = 0.5f;
            fraction[3] = 0.75f;
            fraction[4] = 1f;

            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, 180, fraction, color);

            g2.setPaint(gPaint);

            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.dispose();
        }


    public void update() {

        if(gp.player.hasLantern == 1) {
            setLight();
        }

        //время суток
        if(dayState == day) {
            dayCounter++;

            if(dayState > 300) { // 25 секунд (10 сек = 600) 1500 надо
                dayState = dusk;
                dayCounter = 0;
            }
            if(dayState == dusk){
                filterAlpha += 0.001f;

                if(filterAlpha > 1f) {
                    filterAlpha = 1f;
                    dayState = night;
                }
            }

            if(dayState == night) {

                dayCounter++;

                if(dayCounter > 300) {
                    dayState = dawn;
                    dayCounter = 0;
                }
            }

            if(dayState == dawn) {
                filterAlpha -= 0.001f;

                if(filterAlpha < 0f) {
                    filterAlpha = 0;
                    dayState = day;
                }
            }

        }
    }

    public void draw(Graphics2D g2) {
//        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        g2.drawImage(darknessFilter, 0, 0, null);
//        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
