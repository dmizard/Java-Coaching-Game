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
        setDialogue();

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

    public void setDialogue(){
        //Хранение записей текста диалога персонажа
        dialogue[0] = "М-м-м, фамилия? \n\n(А. Е. Романченко)";
        dialogue[1] = "Мнацаканян.\n\n(Артём)";
        dialogue[2] = "Вопрос... номер...? \n\n(А. Е. Романченко)";
        dialogue[3] = "49. \n\n(А. Е. Романченко)";
        dialogue[4] = "Что такое несогласованность конструкции\n исключений? \n(Артём)";
        dialogue[5] = "... \n\n(Артём)";
        dialogue[6] = "Ну, это когда мы обрабатываем одну ошибку во throw,\n а затем в блоке try и catch следует уже другая. \n(Артём)";
        dialogue[7] = "М-м.. не совсем точно, но более-менее правильно.\n Ладно. Следующий вопрос... 40.\n(А. Е. Романченко)";
        dialogue[8] = "Какие интерфейсы являются суперинтерфейсами \nдля массивов? \n(Артём)";
        dialogue[9] = "Cloneable и Serializable. \n\n(Артём)";
        dialogue[10] = "Угу-м.. Вопрос.. но-о-мер... 83 \n\n(А. Е. Романченко)";
        dialogue[11] = "Что такое Wildcard? \n\n(Артём)";
        dialogue[12] = "Wildcard - это неквалифицированный индетификатор. \n\n(Артём)";
        dialogue[13] = "И-и-и...? \n\n(А. Е. Романченко)";
        dialogue[14] = "У него есть границы, первая - это верхняя,\nвторая - нижняя\n(Артём)";
        dialogue[15] = "И-и.. \n\n(А. Е. Романченко)";
        dialogue[16] = "... \n\n(Артём)";
        dialogue[17] = "Ну, хорошо. Раз не знаете - ладно! \n\n(А. Е. Романченко)";
        dialogue[18] = "Вопрос такой: 'Почему небо голубое?' \n\n(А. Е. Романченко)";
        dialogue[19] = "??? \n\n(Артём)";
        dialogue[20] = "Шутка! 1.5 балла из 2. \n\n(А. Е. Романченко)";
    }

    public void speak(){
        //Указываем ему, что он должен говорить и в каком порядке
        gp.ui.currentDialogue = dialogue[gp.dialogueIndex];

    }

}
