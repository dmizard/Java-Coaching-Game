package tile;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNumber;

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[10];
        //Считывание матрицы карты
        mapTileNumber = new int[gp.maxScreenColumn][gp.maxScreenRow];

        getTileImage();
        loadMap("res/maps/kabinetjava.txt");

    }

    public void getTileImage(){

        try{
            //Загрузка плиток из папки res
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("res/deftiles/deftiles_default.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("res/deftiles/deftiles_up.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("res/deftiles/deftiles_down.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("res/deftiles/deftiles_left.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("res/deftiles/deftiles_right.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("res/deftiles/deftiles_upleft.png"));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new File("res/deftiles/deftiles_upright.png"));
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new File("res/deftiles/deftiles_downleft.png"));
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(new File("res/deftiles/deftiles_downright.png"));
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(new File("res/deftiles/deftiles_door_open.png"));

        }catch (IOException e){

            e.printStackTrace();

        }

    }

    public void loadMap(String filePath){

        try{

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            //Для ограничения считывания содержимого за рамками
            while(col < gp.maxScreenColumn && row < gp.maxScreenRow){

                String line = br.readLine();

                while(col < gp.maxScreenColumn){

                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;

                }
                if(col == gp.maxScreenColumn){

                    col = 0;
                    row++;

                }

            }
            br.close();

        }catch (Exception e){}
    }

    //Отрисовка плиток
    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenColumn && row < gp.maxScreenRow) {

            int tileNum = mapTileNumber[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenColumn){

                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;

            }

        }

    }

}
