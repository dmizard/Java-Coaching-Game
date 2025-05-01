package MainPackage;

/*Класс ввода - вывода (считывания) клавиш
* Весь принцип состоит чтобы передать ключ
* для другого класса, а именно игровой панели,
* и уже там взаимодействовать с объектами, путем
* ввода с клавиатуры.*/

/*Напоминание, что Java, как и любой адекватный редактор, работающий с текстом
* любого формата читает координаты слева-сверху на справа-снизу. Пример:
*
* _____________________________
* |   Начало                   |
* |                            |
* |                            |
* |                            |
* |                      Конец |
* |____________________________|
*
* Поэтому предстоит работать с координатами.*/

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {}

    //Метод нажатой клавишы
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode(); //Ловит нажатую клавишу
        if(code == KeyEvent.VK_UP){
            upPressed = true;
        }
        else if(code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        else if(code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        else if(code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }

    }

    //Метод отпущенной клавишы
    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode(); //Ловит нажатую клавишу
        if(code == KeyEvent.VK_UP){
            upPressed = false;
        }
        else if(code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        else if(code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        else if(code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }
}
