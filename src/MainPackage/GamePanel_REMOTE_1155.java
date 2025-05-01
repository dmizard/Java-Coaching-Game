package MainPackage;

/*Класс игровой панели (или же панели игрока) - является
* неотъемлемой частью программы, как и собственно Main.
* по сути здесь и идет настройка окна интерфейса графики.*/

import EntityPackage.Chillguy;
import EntityPackage.Entity;
import ObjectPackage.Object;
import tile.TileManager;

import javax.swing.JPanel;

import java.awt.*;

//ПРИМЕЧАНИЕ! В самой Java уже есть класс JPanel, который уже прописан для создания Панелей (GUI).

public class GamePanel extends JPanel implements Runnable{

    //Настройки панели (экрана (в Main'е "окно") пользователя)

    final int ogTileSize = 16; //Стандартныйр размер клетки - 16 на 16 пикселей.
    /*То есть мы создаем игру в формате ретро. Но тогда игры создавались в таком формате
    * потому что разрешение экрана у многих устройств не позволяли перескочить за 1000 пикселей.
    * Например, в 80-х игры на игровых автоматах поддерживались в формате ~200 на 500 МАКСИМУМ!
    * Что же делать? Создадим переменную чтобы адаптировать экран под пользователя - настройку
    * разрешения экрана.*/

    final int scale = 3;
    //Следующий параметр будет public для переноса его свойств в следующий пакет - EntityPackage
    public final int tileSize = ogTileSize * scale; //Настоящий размер клетки, который видит пользователь.

    //Настройка самого экрана (его размер по пикселям)

    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;
    //Отсюда видно, что разрешение будет 4:3 (16:12)

    //Теперь нужно записать само разрешение экрана
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeigth = tileSize * maxScreenRow;

    /*Сама игра работает по принципу старой анимации мультиков.
    * Мы не двигаем объекты напрямую, они покадрово считаны как
    * статичные объекты. Поэтому чтобы создать понятие "время"
    * в проекте нужно использовать такое понятие как "поток" в Java.*/

    //Кадры в секунду (FPS)
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    public AssetSet assetSet = new AssetSet(this); //Запуск загрузки объектов
    Chillguy chillguy = new Chillguy(this, keyH); //Запуск объекта сущности игрока
    public Object obj[] = new Object[1]; //Где [1] - это количество объектов
    public Entity npc[] = new Entity[1]; //Где [1] - это количество NPC

    //Конструктор для GamePanel
    public GamePanel(){

        //Установка пустой панели (ее инициализация через конструктор)
        this.setPreferredSize(new Dimension(screenWidth, screenHeigth));
        //Зададим цвет - черный для заднего фона.
        this.setBackground(Color.black);
        //Чтобы создания не было видно пользователю
        this.setDoubleBuffered(true);
        //Считывание нажатия/отжатия клавишы через переменную keyH
        this.addKeyListener(keyH);
        //Указываем, чтобы панель "ожидала" получение ключа клавишы keyH
        this.setFocusable(true);

    }
    //Метод setupGame ля настройки NPC и объектов (предметов)
    public void setupGame(){

        assetSet.setObject(); //Настройка объектов
        assetSet.setNPC(); //Настройка NPC

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        /*Запуск именно конструктора
        * GameThread для потока gameThread*/
        gameThread.start(); //Переход к методу run

    }

    //Метод run для запуска потока
    @Override
    public void run() {

        /*Здесь будет прописан "цикл" игры, который будет покадрово
        * считывать изображение, преобразуя статичные записи объектов
        * в "динамичные" (своего рода), хотя нельзя именно назвать это
        * точно так. Пример цикла:
        *
        * ОБНОВЛЕНИЕ ---> ПРОРИСОВКА
        *      ^              |
        *      |              v
        *      ----------------
        *
        * */
        /*Используем дельта-цикл алгоритм, так как он
        * быстрее "рендер-ит"(с англ. обрабатывает) картинку.*/
        double drawInterval = 1000000000 / FPS; //То есть 1 кадр в 0,01(6) секунде
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null){
            /*Чтобы ограничить игру по кадрам и не было проблем,
            * связанных с передвижением объекта, зададим параметр,
            * известный всем как "время" - здесь же это кадры в секунду. Как пример:*/
            currentTime = System.nanoTime(); //В наносекундах, чтобы придерживаться точности
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                /*Обновление и прорисовка соответственно представлены ниже
                 * Это очень важно. Здесь игра прописывает понятие кадра для проекта.*/
                //Обновление
                update();
                //Прорисовка
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000){
                System.out.println("Кадров в секунду: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){

        //Здесь происходит изменение координат, по формуле скорости игрока
        chillguy.update();

    }

    public void paintComponent(Graphics g){

        //Нужно перезаписать под Graphics2D, так как там больше методов для работы
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //Перезапись объекта g под формат Graphic2D
        //Запись плиток как ячейка матрицы (карты)
        tileM.draw(g2);
        //Запись объектов локации как объект (вывод обязательно выполняется по очереди!)
        for(int i = 0; i < obj.length; i++){
            //Вывод на экран, если объект (т.е. его изображение) существует
            if(obj[i] != null) obj[i].draw(g2, this);

        }
        //Запись NPC как существо
        for(int i = 0; i < npc.length; i++){
            //Вывод на экран, если NPC (т.е. его изображение) существует
            if(npc[i] != null) npc[i].draw(g2);

        }
        //Запись игрока как существо
        chillguy.draw(g2);
        g2.dispose(); //Показать объекты и существа

    }
}
