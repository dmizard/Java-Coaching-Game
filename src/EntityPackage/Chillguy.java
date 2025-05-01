package EntityPackage;

/*Класс игрока (у нас он ChillGuy - Спокойный парень)*/

import MainPackage.GamePanel;
import MainPackage.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Chillguy extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Chillguy(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues(){
        //Стандартные параметры игрока
        x = 100;
        y = 100;
        speed = 3;
        direction = "down";

    }
    //Метод для установки картинки игрока
    public void getPlayerImage(){

        try {

            upDef = ImageIO.read(new File("res/chillboy/mnatsakanyan_default_2.png"));
            up1 = ImageIO.read(new File("res/chillboy/mnatsakanyan_walkup_1.png"));
            up2 = ImageIO.read(new File("res/chillboy/mnatsakanyan_walkup_2.png"));
            downDef = ImageIO.read(new File("res/chillboy/mnatsakanyan_default_1.png"));
            down1 = ImageIO.read(new File("res/chillboy/mnatsakanyan_walkdown_1.png"));
            down2 = ImageIO.read(new File("res/chillboy/mnatsakanyan_walkdown_2.png"));
            leftDef = ImageIO.read(new File("res/chillboy/mnatsakanyan_left_default.png"));
            left1 = ImageIO.read(new File("res/chillboy/mnatsakanyan_walkleft_1.png"));
            left2 = ImageIO.read(new File("res/chillboy/mnatsakanyan_walkleft_2.png"));
            rightDef = ImageIO.read(new File("res/chillboy/mnatsakanyan_right_default.png"));
            right1 = ImageIO.read(new File("res/chillboy/mnatsakanyan_walkright_1.png"));
            right2 = ImageIO.read(new File("res/chillboy/mnatsakanyan_walkright_2.png"));

        }catch(IOException e){

            e.printStackTrace();

        }

    }

    public void update(){
        //Формула ходьбы для игрока
        if(keyH.upPressed){
            direction = "up";
            y -= speed;
        }else if(keyH.downPressed){
            direction = "down";
            y += speed;
        }else if(keyH.leftPressed){
            direction = "left";
            x -= speed;
        }else if(keyH.rightPressed){
            direction = "right";
            x += speed;
        }
        /*Цикл итераций спрайтов при ходьбе персонажа.*/
        spriteCounter++;
        if(spriteCounter > 10){
            if(spriteNum == 1){
                spriteNum = 2;
            }else if(spriteNum == 2){
                spriteNum = 3;
            }else if(spriteNum == 3){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        /*Добавим условие, если клавишы отжаты, то персонаж останавливается.*/
        if((!keyH.upPressed) && (direction.equals("up"))) spriteNum = 1;
        if((!keyH.downPressed) && (direction.equals("down"))) spriteNum = 1;
        if((!keyH.leftPressed) && (direction.equals("left"))) spriteNum = 1;
        if((!keyH.rightPressed) && (direction.equals("right"))) spriteNum = 1;

    }

    public void draw(Graphics2D g2){
        //Формула прорисовки фигуры

        BufferedImage image = null;
        //Смена спрайтов согласно переменной в методе update
        switch (direction){
            case "up":
                if(spriteNum == 1) image = upDef;
                if(spriteNum == 2) image = up1;
                if(spriteNum == 3) image = up2;
                break;
            case "down":
                if(spriteNum == 1) image = downDef;
                if(spriteNum == 2) image = down1;
                if(spriteNum == 3) image = down2;
                break;
            case "left":
                if(spriteNum == 1) image = leftDef;
                if(spriteNum == 2) image = left1;
                if(spriteNum == 3) image = left2;
                break;
            case "right":
                if(spriteNum == 1) image = rightDef;
                if(spriteNum == 2) image = right1;
                if(spriteNum == 3) image = right2;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}
