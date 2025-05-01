package EntityPackage;

/*Пакет сущностей подразумевает, что в нем находятся
 * все NPC - Non-playable characters, а также сами
 *  игроки (в нашем случае он один).*/

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//Основной класс существ
public class Entity {
    //Стандартные параметры игрока
    public int x, y;
    public int speed;
    //Инициализация картинок из директории res
    public BufferedImage upDef, up1, up2, downDef, down1, down2, leftDef, left1, left2, rightDef, right1, right2;
    public String direction;
    //Чередование спрайтов изображений персонажа/существа
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;

}
