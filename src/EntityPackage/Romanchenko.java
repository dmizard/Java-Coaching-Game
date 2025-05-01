package EntityPackage;

//Класс NPC - Алексея Евгеньевича Романченко

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Romanchenko extends Entity{

    public Romanchenko(GamePanel gp){

        super(gp);

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues(){
        //Стандартные параметры игрока (начальные для координат - место спавна)
        x = 0;
        y = 0;

    }

    public void getPlayerImage(){

        try {

            def = ImageIO.read(new File("res/npc/romanchenko_base.png"));

        }catch(IOException e){

            e.printStackTrace();

        }

    }
    //У него отсутствует свой метод draw, т.к. он не передвигается как игрок - Chillguy
}
