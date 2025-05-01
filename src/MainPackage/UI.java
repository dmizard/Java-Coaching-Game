package MainPackage;

/*Класс UI для пользования сторонними окнами интерфейса,
* что идут уже за (то есть после) обработкой текстур
* персонажей и объектов.*/

import java.awt.*;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_20, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameIsFinished = false;
    public String currentDialogue = "...";

    public UI(GamePanel gp){

        this.gp = gp;
        //Возможные шрифты для использования
        arial_20 = new Font("Arial", Font.PLAIN, 20); //обычный
        arial_80B = new Font("Arial", Font.BOLD, 80); //полужирный

    }

    public void showMessage(String text){

        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(arial_20);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){

        }else if(gp.gameState == gp.dialogueState){
            drawDialogue();
        }

    }

    public void drawDialogue(){
        //Создаем окошко для диалога и задаем координаты его расположения
        int x = 2 * gp.tileSize;
        int y = 7 * gp.tileSize + gp.tileSize / 2;
        int width= gp.screenWidth - 4 * gp.tileSize;
        int height = 4 * gp.tileSize;
        //Вызов метода окна диалога
        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize;
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height){
        //Рисуем диалоговое окно (заднее), оно черное
        g2.setColor(Color.black);
        g2.fillRect(x, y, width, height);
        //Теперь пришла очередь рисовать рамки для окна
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(x+5, y+5, width-10, height-10);

    }
    //Централизация текста по оси абсцисс
    public int CenterXforText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;

    }
    //Централизация текста по оси ординат
    public int CenterYforText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
        int y = gp.screenHeigth/2 - length/2;
        return y;

    }

}
