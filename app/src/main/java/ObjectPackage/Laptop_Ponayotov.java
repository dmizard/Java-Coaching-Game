package ObjectPackage;

/*Ноутбук Понаётова*/

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Laptop_Ponayotov extends Object{

    public Laptop_Ponayotov(){
        //Вывод изображения (текстуры) на экран
        name = "Laptop1";

        try{
            //Загрузка изображения
            image = ImageIO.read(new File("src\\main\\resources\\objects\\laptop_ponayotov.png"));

        }catch (IOException e){

            e.printStackTrace();

        }

    }

}
