package EntityPackage;

/*Пакет сущностей подразумевает, что в нем находятся
 * все NPC - Non-playable characters, а также сами
 *  игроки (в нашем случае он один).*/

import MainPackage.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

//Основной класс существ
public class Entity {

    GamePanel gp;
    //Стандартные параметры игрока
    public int x, y;
    public int speed;
    //Инициализация картинок из директории res
    public BufferedImage def, upDef, up1, up2, downDef, down1, down2, leftDef, left1, left2, rightDef, right1, right2;
    public String direction;
    //Чередование спрайтов изображений персонажа/существа
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    String[] dialogue = new String[21];

    public Entity(GamePanel gp){

        this.gp = gp;

    }

    public void speak(){} //Абстрактный метод для записи диалоговых текстов

    public void draw(Graphics2D g2){
        /*Для всех существ обозначим начальное изображение со статусом
        * def - т.е. default, стандартное. Т.к. не все они будут двигаться.*/
        BufferedImage image = def;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}
