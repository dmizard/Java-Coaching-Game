package MainPackage;

import EntityPackage.Romanchenko;
import ObjectPackage.Laptop_Ponayotov;

//Класс настройки объектов и прочего (NPC и т.п.)
public class AssetSet {

    GamePanel gp;

    public AssetSet(GamePanel gp){
        //Вывод на игровую (главную) панель
        this.gp = gp;

    }

    public void setObject(){
        //Задаем координаты объекту, где он находится
        gp.obj[0] = new Laptop_Ponayotov();
        gp.obj[0].x = 11 * gp.tileSize;
        gp.obj[0].y = 5 * gp.tileSize;

    }

    public void setNPC(){
        //Задаем координаты NPC, где он находится
        gp.npc[0] = new Romanchenko(gp);
        gp.npc[0].x = 3 * gp.tileSize;
        gp.npc[0].y = 1 * gp.tileSize;

    }

}
