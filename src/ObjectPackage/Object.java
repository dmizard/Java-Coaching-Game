package ObjectPackage;

/*Пакет объектов подразумевает предметы локации.
 * Класс объектов для работы с вещами.*/

import MainPackage.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {
    //Описание этих переменных уже исходят из прошлых классов более важного значения.
    public BufferedImage image;
    public String name;
    public int x, y;

    public void draw(Graphics2D g2, GamePanel gp){

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}
